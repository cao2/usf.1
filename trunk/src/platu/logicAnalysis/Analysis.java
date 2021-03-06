package platu.logicAnalysis;

import java.util.*;
import java.io.*;
import platu.project.*;
import platu.MDD.*;
import platu.BinaryTree.*;
import platu.NativeLib.*;
import platu.common.*;
import platu.gui.Console;
import platu.logicAnalysis.Analysis;
import platu.lpn.*;
import platu.main.Options;
import platu.main.Options.SearchTypeDef;
import platu.stategraph.*;
import platu.util.Pair;
import platu.util.RuntimeMonitor;
import platu.por1.*;

public class Analysis {
	/* 
	 * Runtime options
	 */
	protected int StatusUpdatePeriodLong = 1000000;
	protected int StatusUpdatePeriodShort = 100000;

	/*
	 * State space information stored during the state space search.
	 */
	private SetIntTuple StateTable = null;
	private HashMap<IntArrayObj, IntArrayObj> uniqueDecompStateTbl = null;
	protected Mdd mddMgr = null;
	protected BinaryTree gVecTable = new BinaryTree(true);


	/*
	 * Information about the model to be analyzed
	 */
	protected StateGraph[] SgList = null;
	protected State[] initStateArray = null;
	protected DualHashMap<String,Integer> gVarIndexMap = null;
	protected HashMap<String,Integer> InitGlobalVecMap = null;
	protected int ArraySize = 0;
	protected boolean noGlobals = true;

	/*
	 * Independence relation between LPN transitions computed after the 
	 * CRA compositional analysis is finished.
	 */
	protected LPNTranRelation lpnTranRelation = null;

	/* Runtime status and stats */
	long tranFiringCnt = 0;  
	long totalStateCnt = 0; 
	long max_stack_depth = 0;
	RuntimeMonitor monitor = null;
	long localStartTime = 0; 
	long peakTotalMem = 0;
	long peakUsedMem = 0;
	float elapsedTimeSec = 0;
	long iterations = 0;
	Boolean memout = false;
	Boolean timeout = false;
	
	/* Verification conclusion and/or counter-example */
	enum ErrorType {NONE, DEADLOCK, DISABLING}; 
	ErrorType ErrorFlag = ErrorType.NONE;
	LinkedList<LPNTran> traceFiredTran;
	LinkedList<State[]> traceLocalStateVec;
	LinkedList<int[]> traceGlobalVec;
	String errorMsg = null;	

	/* For temporary experiments */
	HashMap<Pair<Integer, Integer>, Integer>[] idxPackageTbl = null; 

	
	public Analysis() {
		
	}

	public Analysis(Project prj, StateGraph[] sgList, State[] initStateArray, List<Pair<String, Integer>> globalVarList) {
		
		localStartTime = System.currentTimeMillis(); 

		this.ArraySize = sgList.length;
		
		if(Options.getStateFormat() == Options.StateFormatDef.EXPLICIT)
			this.StateTable = new HashTable();
			//this.StateTable = new NativeSetWrapper();
			//this.StateTable = new NativeHybridMDDWrapper(this.ArraySize+1, true);
			//this.StateTable = new NativeBinaryTree();
		else if(Options.getStateFormat() == Options.StateFormatDef.MDD || Options.getStateFormat() == Options.StateFormatDef.MDDBUF)
			this.StateTable = new MddTable(this.ArraySize+1);
		else if(Options.getStateFormat() == Options.StateFormatDef.BINARY_TREE)
			this.StateTable = new BtreeTable();
		else {
			System.err.println("Unsupported state representation");
		}

		this.traceFiredTran = new LinkedList<LPNTran>();	
		this.traceLocalStateVec = new LinkedList<State[]>();
		this.traceGlobalVec = new LinkedList<int[]>();

		mddMgr = new Mdd(this.ArraySize+1);

		this.SgList = sgList;

		this.initStateArray = initStateArray;

		/* Initialize the gVarIndexMap and gVec */		
		int gVarIdx = 0;
		this.gVarIndexMap = new DualHashMap<String,Integer>();
		this.InitGlobalVecMap = new HashMap<String,Integer>();

		for(Pair<String, Integer> gvar : globalVarList) {
			this.gVarIndexMap.insert(gvar.getLeft(), gVarIdx);
			gVarIdx++;
			this.InitGlobalVecMap.put(gvar.getLeft(), gvar.getRight());
		}
		
		this.idxPackageTbl = new HashMap[this.ArraySize]; 

		for(int i = 0; i< this.ArraySize; i++) {
			StateGraph curSG = sgList[i];
			curSG.initialize();

			VarSet curGlobals = curSG.getLpn().getGlobals();
			DualHashMap<String,Integer> curVarIndexMap = curSG.getLpn().getVarIndexMap();
			HashMap<String,Integer> curInitVec = curSG.getLpn().getInitVector();
			for(String gvar : curGlobals) {
				if(this.gVarIndexMap.containsKey(gvar)==false) {
					this.gVarIndexMap.insert(gvar, gVarIdx++);
					this.InitGlobalVecMap.put(gvar, curInitVec.get(gvar));
				}
			}

			this.idxPackageTbl[i] = new HashMap<Pair<Integer, Integer>, Integer>();
		}


		if(this.gVarIndexMap.size() > 0)
			this.noGlobals = false;


		/* Print the model structural information */
		{
			String info1 = "--> Number of processes: \t" + sgList.length + "\n";
			String info2 = "--> Length of global vecvtor: \t" + this.gVarIndexMap.size() + "\n";
			String info3 = "--> Global variables:\n\t" + globalVarList + "\n";

			String info4 = "--> Global variable indicies:\n\t" + this.gVarIndexMap + "\n\n";
			/*
			for(int i = 0; i < this.gVarIndexMap.size(); i++) {
				System.out.print("(" + this.gVarIndexMap.getKey(i) + " " + i + "), " );
			}
			System.out.println();
*/
			info4 += "--> Process infomation\n";
			for(int i = 0; i < sgList.length; i++)
				info4 += sgList[i].getLpn().header();
			Console.print(info1 + info2 + info3 + info4, Console.MessageType.dynamicInfo, 20);

		}

		this.monitor = new RuntimeMonitor();
		Thread t = new Thread(this.monitor);
		t.start();
		Options.SearchTypeDef searchType = Options.getSearchType();
		if (searchType==Options.SearchTypeDef.DFS) {
			// this.search_dfs_underapprox();
			this.search_dfs();
			// this.search_dfsNative(lpnList, initStateArray);
			// this.search_dfs_mdd_1(lpnList, initStateArray);
			// this.search_dfs_mdd_2(lpnList, initStateArray);
		}
		else if(Options.getSearchType()==Options.SearchTypeDef.DFS_UNDERAPPROX) {
			//this.search_dfs_underapprox();
			ProcSg[] psgArray = this.cra_sls();
			//search(psgArray);
		}
		else if (searchType==Options.SearchTypeDef.POR_STATIC)	{
			this.search_dfs_por(sgList, initStateArray, lpnTranRelation, "lpn");
		}
		else if (searchType==Options.SearchTypeDef.POR_BEHAVORIAL)	{
			lpnTranRelation = new LPNTranRelation(sgList);
			CompositionalAnalysis.setGlobalVarList(globalVarList);
			//CompositionalAnalysis.craConstr(sgList);
			CompositionalAnalysis.craLocalSearch(sgList);
			this.search_dfs_por(sgList, initStateArray, lpnTranRelation, "state");
		}
		else if (searchType==Options.SearchTypeDef.BFS)
			this.search_bfs(sgList, initStateArray);
		else {//if (method == "dfs_noDisabling")
			//this.search_dfs_noDisabling(lpnList, initStateArray);
			this.search_dfs_noDisabling_fireOrder(sgList, initStateArray);
		}
		
		
		if(timeout) {
			String timeoutMsg = "*** Time out\n" 
				+ "\tElapsed time: " + this.elapsedTimeSec + " exceeds the limit " + Options.getTimeUpperBound() +".\n"
				+ "\tSearch terminated!";
			Console.print(timeoutMsg, Console.MessageType.dynamicInfo, -1);
		}

		if(memout) {
			String memoutMsg = "*** Memory out\n" 
				+"\tUsed memory: " + this.peakUsedMem/1000000 + " exceeds the limit " + Options.getMemUpperBound() +".\n"
				+ "\tSearch terminated!";
			Console.print(memoutMsg, Console.MessageType.dynamicInfo, -1);
		}
		
		String stateSpaceInfo = "\tState transition count: "	+ this.tranFiringCnt + "\n" 
				+ "\tState count: " + this.totalStateCnt + "\n" 
				+ "\tGlobal state vector count: " + this.gVecTable.elementCount();
		
		
		String runtimeStats = "\tMaximal stack depth: " + this.max_stack_depth + "\n" 
			+ "\tPeak total memory: " + monitor.getPeakTotalMem() + " MB\n"
			+ "\tPeak used memory: " + monitor.getPeakUsedMem() + " MB\n"
			+ "\tTotal elapsed time = " + monitor.getElapsedime() + " sec";

		String finalReport = "--> Search stats:\n" + stateSpaceInfo + "\n" + runtimeStats + "\n";
		Console.print(finalReport, Console.MessageType.dynamicInfo, 0);

		String localSgInfo = "--> Local SG state count:\n";
		for(int i = 0; i < this.SgList.length; i++) {
			localSgInfo += "\t" + this.SgList[i].getLabel() + ":\t\t" + this.SgList[i].getModStateCount() + "\n";
		}
		localSgInfo += "\tGlobal vector count:\t\t" + this.gVecTable.elementCount() + "\n";
		Console.print(localSgInfo, Console.MessageType.dynamicInfo, 10);

			
	

		/* Output error trace into a file for debugging */ 
		if(this.ErrorFlag != ErrorType.NONE) {
			FileWriter outFile;
			try {				
				String traceOutput = Options.getCexOutputPath() + "/" + "error.trace";
				outFile = new FileWriter(traceOutput);
				
				System.err.println("*** Writing error trace to " + traceOutput);

				List<Pair<String, Integer>> prjGlobalVarList = globalVarList;
			
				
				/* Generate the output for the initial state */
				String errTrace = "Initial State: \n";
				
				State[] initStateVec = this.traceLocalStateVec.removeFirst();
				
				for (int i = 0; i < this.ArraySize; i++) {
					State tmp = initStateVec[i].hideGlobalVector();
					errTrace += tmp.toString() + "\n";
				}
				
				LinkedList<String> initGlobalVarAssign = new LinkedList<String>();
				for(Pair<String,Integer> gVarAssign : prjGlobalVarList) {
					initGlobalVarAssign.addLast(gVarAssign.getLeft() + " = " + gVarAssign.getRight());
				}
				
				errTrace += initGlobalVarAssign.toString() + "\n";

				int pos = 0;
				while(this.traceLocalStateVec.size() > 0) {
					State[] curStateVec = this.traceLocalStateVec.removeFirst();
					int[] curGlobalVec = this.traceGlobalVec.removeFirst();
					LPNTran tran = this.traceFiredTran.removeFirst(); 
					
					errTrace += "\n" +  pos + " --> " + tran.getFullLabel() + "\n\n";
					pos++;
					
					HashMap<String, Integer> curGlobalVarAssign = new HashMap<String, Integer>();
					for(int i = 0; i < this.gVarIndexMap.size(); i++) {
						String gVar = this.gVarIndexMap.getKey(i);
						int assign = curGlobalVec[i];
						curGlobalVarAssign.put(gVar, assign);
					}					
			
					for (int i = 0; i < this.ArraySize; i++) {
						State tmp = curStateVec[i].hideGlobalVector();
						errTrace += tmp.toString() + "\n";
					}

					
					LinkedList<String> prjGlobalVarAssign = new LinkedList<String>();
					for(Pair<String,Integer> gVarAssign : prjGlobalVarList) {
						String gVar = gVarAssign.getLeft();
						prjGlobalVarAssign.addLast(gVar + " = " + curGlobalVarAssign.get(gVar));
					}
					
					errTrace +=  prjGlobalVarAssign.toString() + "\n";
				} 
				
				
				errTrace += "\n#Error: " + this.errorMsg + "\n";
				outFile.write(errTrace);
				outFile.close();
				System.err.println("*** Done");
			}
			catch(IOException e) {
				System.out.println("Cannot open file error.trace");
				return;
			}
		}		
		
		// Terminate the thread for monitor.
		t.interrupt();
	}

	/**
	 * An iterative implement of findsg_recursive().
	 * 
	 * @param lpnList
	 * @param curLocalStateArray
	 * @param enabledArray
	 */
	public Stack<State[]> search_dfs() {				
		String info = "--> start depth-first search ...";

		Console.print(info, Console.MessageType.dynamicInfo, 1);

		/* The vector of the value of all global variables and outputs from all modules in the current state  */
		int[] initGlobalVec = null;
		int curGVecId = 0;
		int nextGVecId = 0;

		Stack<State> stateStack = new Stack<State>();
		Stack<Integer> gVecStack = new Stack<Integer>();
		Stack<LinkedList<Pair<LPNTran, ProcState>>> lpnTranStack = new Stack<LinkedList<Pair<LPNTran, ProcState>>>();

		int[] initStateIdxArray = new int[this.ArraySize+1];
		for(int i = 0; i < this.ArraySize; i++)
			initStateIdxArray[i+1] = this.initStateArray[i].getIndex();

		initGlobalVec = this.makeVec(this.InitGlobalVecMap);
		curGVecId = this.gVecTable.add(initGlobalVec);
		initStateIdxArray[0] = curGVecId;

		int activeIdx = 0;
		State[] curStateArray = initStateArray;
		LinkedList<Pair<LPNTran, ProcState>> curEnabled = (LinkedList<Pair<LPNTran, ProcState>>)(this.SgList[0].getEnabled(curGVecId, initStateArray[0], this.gVecTable, this.gVarIndexMap)).clone();		
		
		boolean Done = false;

		this.StateTable.add(initStateIdxArray);
		this.totalStateCnt++;
		
		State[] errorLocalStateVec = null;
		int[] errorGlobalVec = null;

		/*  Main search loop */
		main_while_loop: while (this.ErrorFlag == ErrorType.NONE && Done == false) {
			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			if (stateStack.size() > max_stack_depth)
				max_stack_depth = stateStack.size();


			iterations++;


			if(this.monitor.getElapsedime() >= Options.TimeUpperBound) {
				this.timeout = true;
				break main_while_loop;	
			}

			if(this.monitor.getPeakUsedMem() >= Options.MemUpperBound) {
				this.memout = true;
				break main_while_loop;
			}

			if (iterations % this.StatusUpdatePeriodLong == 0) {
				String statusUpdate = "---> "
						+ "# LPN transition firings: " + tranFiringCnt + ", "
						+ "# of prjStates found: " + totalStateCnt+", "
						+ "stack_depth: " + stateStack.size()+", "
						//+ ", MDD node count: " + mddMgr.nodeCnt()
						+ "used memory: " + this.monitor.getPeakUsedMem() +" MB, "
						+ "free memory: " + Runtime.getRuntime().freeMemory() / 1000000 + " MB, "
						+ "elapsed time: " + (float)this.monitor.getElapsedime() + " seconds.";
				

				String statsLevel5 = "---> " + " # of global vectors: " + this.gVecTable.elementCount() + "\n"
				+ "---> " +  "state table stats: " + this.StateTable.stats();
				
					Console.print(statusUpdate, Console.MessageType.dynamicInfo, 0);	
					Console.print(statsLevel5, Console.MessageType.dynamicInfo, 5);

			}

			// If all enabled transitions of the current LPN are considered,
			// then consider the next LPN
			// by increasing the curIndex.
			// Otherwise, if all enabled transitions of all LPNs are considered,
			// then pop the stacks.

			if (curEnabled.size() == 0) {				
				activeIdx++;
				while (activeIdx < this.ArraySize) {
					curEnabled = (LinkedList<Pair<LPNTran, ProcState>>)(this.SgList[activeIdx].getEnabled(curGVecId, curStateArray[activeIdx], this.gVecTable, this.gVarIndexMap)).clone();
					if (curEnabled.size() > 0) {
						break;
					} 					
					activeIdx++;
				}
			}

			if (activeIdx == this.ArraySize) {
				if(stateStack.size() == 0) {
					Done=true;
					break main_while_loop;
				}
				State curState = stateStack.pop();
				activeIdx = curState.getLpn().getIndex();
				curStateArray[activeIdx] = curState;
				curEnabled = lpnTranStack.pop();
				if(this.noGlobals==false) {
					curGVecId = gVecStack.pop();
					//curGlobalVec = this.gVecTable.toIntArray(curGVecId);
				}

				if(traceFiredTran.size()>0)
					traceFiredTran.removeLast();		
				continue;
			}

			State curState = curStateArray[activeIdx];
			Pair<LPNTran, ProcState> tran2nextState = curEnabled.removeLast();
			//nextGlobalVec = curGlobalVec;
			//Pair<Integer, State> nextModState = firedTran.fire(curGVecId, curState, this.SgList[activeIdx], this.gVecTable,  this.gVarIndexMap);
			//ModuleState nextModState = this.SgList[activeIdx].getNextState(curGVecId, curState, firedTran);
			LPNTran firedTran = tran2nextState.getLeft();
			ProcState nextModState = tran2nextState.getRight();
			nextGVecId = nextModState.getGlobalVecIdx();
			State nextState = nextModState.getLocalState();
			traceFiredTran.addLast(firedTran);
			tranFiringCnt++;
						
			// Check if the firedTran causes disabling error or deadlock.
			LinkedList<Pair<LPNTran, ProcState>>[] curEnabledArray = new LinkedList[this.ArraySize];
			LinkedList<Pair<LPNTran, ProcState>>[] nextEnabledArray = new LinkedList[this.ArraySize];
			for (int i = 0; i < this.ArraySize; i++) {
				StateGraph thisSg = this.SgList[i];
				if(i == activeIdx)
					curStateArray[activeIdx] = nextState;

				LinkedList<Pair<LPNTran, ProcState>> enabledList = thisSg.getEnabled(nextGVecId, curStateArray[i], this.gVecTable, this.gVarIndexMap);
				if (enabledList == null) {
					return null;
				}
				nextEnabledArray[i] = (LinkedList<Pair<LPNTran, ProcState>>)enabledList.clone();

				if(i == activeIdx)
					curStateArray[activeIdx] = curState;

				if(Options.checkDisablingError()==true) {
					enabledList = thisSg.getEnabled(curGVecId, curStateArray[i], this.gVecTable, this.gVarIndexMap);
					curEnabledArray[i] = enabledList;

					LPNTran disabledTran = firedTran.disablingError(curEnabledArray[i], nextEnabledArray[i]);
					if (disabledTran != null) {
						System.err.println("Disabling Error: " + disabledTran.getFullLabel() + " is disabled by " + firedTran.getFullLabel());
						for (int curIdx = 0; curIdx < this.ArraySize; curIdx++) {
							System.out.println(curStateArray[curIdx].toString());
						}
						System.out.println(">>>>>");
						for (int nxtIdx = 0; nxtIdx < this.ArraySize; nxtIdx++) {
							System.out.println(curStateArray[nxtIdx].toString());
						}
						System.out.println("\n\n");
						
						errorLocalStateVec = Arrays.copyOf(curStateArray, curStateArray.length);
						errorLocalStateVec[activeIdx] = nextState;
						//errorGlobalVec = nextGlobalVec;
						this.errorMsg = "Disabling Error: " + disabledTran.getFullLabel() + " is disabled by " + firedTran.getFullLabel();
						
						this.ErrorFlag = ErrorType.DISABLING;
						break main_while_loop;
					}
				}
			}

			/* Build index array for the nextStateArray and nextGVec */
			int[] nextStateIdxArray = new int[this.ArraySize+1];
			curStateArray[activeIdx] = nextState;
			for(int i = 0; i < this.ArraySize; i++)
				nextStateIdxArray[i+1] = curStateArray[i].getIndex();
			curStateArray[activeIdx] = curState;

			if(this.noGlobals==true) 
				nextStateIdxArray[0] = 0;
			else {
				nextStateIdxArray[0] = nextGVecId;
			}

			boolean existingState = true;
			existingState = this.StateTable.contains(nextStateIdxArray);

			int nextActiveIdx = 0;
			if(existingState==false) {
				nextActiveIdx = Analysis.deadLock(this.SgList, nextEnabledArray) ;

				if(nextActiveIdx == this.ArraySize) {
					System.err.println("*** Verification failed: deadlock.\n");
					errorLocalStateVec = Arrays.copyOf(curStateArray, curStateArray.length);
					errorLocalStateVec[activeIdx] = nextState;
					errorGlobalVec = this.gVecTable.toIntArray(nextGVecId);
					this.errorMsg = "Deadlock";
					
					this.ErrorFlag =  ErrorType.DEADLOCK;
					break main_while_loop;
				}
			}
			
			if (existingState == false) {
				this.StateTable.add(nextStateIdxArray);
				stateStack.push(curState);
				gVecStack.push(curGVecId);
				lpnTranStack.push(curEnabled);

				curStateArray[activeIdx] = nextState;
				curGVecId = nextGVecId;
				//curGlobalVec = nextGlobalVec;
				activeIdx = nextActiveIdx;
				curEnabled = nextEnabledArray[activeIdx];
				totalStateCnt++;
				//System.out.println("--> new ");
			}
			else {
				this.traceFiredTran.removeLast();				
			}
		}
		

		/* Return error trace information if there is one. */
		if(this.ErrorFlag !=  ErrorType.NONE) {
			this.traceLocalStateVec.addFirst(errorLocalStateVec);
			this.traceLocalStateVec.addFirst(curStateArray);
			while(stateStack.empty()==false) {
				State[] prevStateArray = Arrays.copyOf(curStateArray, curStateArray.length);
				State prevState = stateStack.pop();
				activeIdx = prevState.getLpn().getIndex();
				prevStateArray[activeIdx] = prevState;
				this.traceLocalStateVec.addFirst(prevStateArray);
				curStateArray = prevStateArray;
			}
			
			this.traceGlobalVec.addFirst(errorGlobalVec);
			this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
			while(gVecStack.empty()==false) {
				curGVecId = gVecStack.pop();
				this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
			}
		}
		
		return null;
	}	

	/*
	private Stack<State[]> search_dfs(ProcSg psg, ProcState init) {				
		String info = "--> start depth-first search on process SG " + psg.getLabel() + " ...";
		Console.print(info, Console.MessageType.dynamicInfo, 1);

		//* The vector of the value of all global variables and outputs from all modules in the current state  
		int[] initGlobalVec = null;
		int curGVecId = 0;
		int nextGVecId = 0;

		Stack<ProcState> stateStack = new Stack<ProcState>();
		Stack<Integer> gVecStack = new Stack<Integer>();
		Stack<LinkedList<Pair<LPNTran, ModuleState>>> lpnTranStack = new Stack<LinkedList<Pair<LPNTran, ModuleState>>>();

		int[] initStateIdxArray = new int[this.ArraySize+1];
		for(int i = 0; i < this.ArraySize; i++)
			initStateIdxArray[i+1] = this.initStateArray[i].getIndex();

		initGlobalVec = this.makeVec(this.InitGlobalVecMap);
		curGVecId = this.gVecTable.add(initGlobalVec);
		initStateIdxArray[0] = curGVecId;

		int activeIdx = 0;
		State[] curStateArray = initStateArray;
		LinkedList<Pair<LPNTran, ModuleState>> curEnabled = (LinkedList<Pair<LPNTran, ModuleState>>)(this.SgList[0].getEnabled(curGVecId, initStateArray[0], this.gVecTable, this.gVarIndexMap)).clone();		
		
		boolean Done = false;

		this.StateTable.add(initStateIdxArray);
		this.totalStateCnt++;
		
		State[] errorLocalStateVec = null;
		int[] errorGlobalVec = null;

		//*  Main search loop 
		main_while_loop: while (this.ErrorFlag == ErrorType.NONE && Done == false) {
			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			if (stateStack.size() > max_stack_depth)
				max_stack_depth = stateStack.size();


			iterations++;


			if(this.monitor.getElapsedime() >= Options.TimeUpperBound) {
				this.timeout = true;
				break main_while_loop;	
			}

			if(this.monitor.getPeakUsedMem() >= Options.MemUpperBound) {
				this.memout = true;
				break main_while_loop;
			}

			if (iterations % this.StatusUpdatePeriodLong == 0) {
				String statusUpdate = "---> "
						+ "# LPN transition firings: " + tranFiringCnt + ", "
						+ "# of prjStates found: " + totalStateCnt+", "
						+ "stack_depth: " + stateStack.size()+", "
						//+ ", MDD node count: " + mddMgr.nodeCnt()
						+ "used memory: " + this.monitor.getPeakUsedMem() +" MB, "
						+ "free memory: " + Runtime.getRuntime().freeMemory() / 1000000 + " MB, "
						+ "elapsed time: " + (float)this.monitor.getElapsedime() + " seconds.";
				

				String statsLevel5 = "---> " + " # of global vectors: " + this.gVecTable.elementCount() + "\n"
				+ "---> " +  "state table stats: " + this.StateTable.stats();
				
					Console.print(statusUpdate, Console.MessageType.dynamicInfo, 0);	
					Console.print(statsLevel5, Console.MessageType.dynamicInfo, 5);

			}

			// If all enabled transitions of the current LPN are considered,
			// then consider the next LPN
			// by increasing the curIndex.
			// Otherwise, if all enabled transitions of all LPNs are considered,
			// then pop the stacks.

			if (curEnabled.size() == 0) {				
				activeIdx++;
				while (activeIdx < this.ArraySize) {
					curEnabled = (LinkedList<Pair<LPNTran, ModuleState>>)(this.SgList[activeIdx].getEnabled(curGVecId, curStateArray[activeIdx], this.gVecTable, this.gVarIndexMap)).clone();
					if (curEnabled.size() > 0) {
						break;
					} 					
					activeIdx++;
				}
			}

			if (activeIdx == this.ArraySize) {
				if(stateStack.size() == 0) {
					Done=true;
					break main_while_loop;
				}
				State curState = stateStack.pop();
				activeIdx = curState.getLpn().getIndex();
				curStateArray[activeIdx] = curState;
				curEnabled = lpnTranStack.pop();
				if(this.noGlobals==false) {
					curGVecId = gVecStack.pop();
					//curGlobalVec = this.gVecTable.toIntArray(curGVecId);
				}

				if(traceFiredTran.size()>0)
					traceFiredTran.removeLast();		
				continue;
			}

			State curState = curStateArray[activeIdx];
			Pair<LPNTran, ModuleState> tran2nextState = curEnabled.removeLast();
			//nextGlobalVec = curGlobalVec;
			//Pair<Integer, State> nextModState = firedTran.fire(curGVecId, curState, this.SgList[activeIdx], this.gVecTable,  this.gVarIndexMap);
			//ModuleState nextModState = this.SgList[activeIdx].getNextState(curGVecId, curState, firedTran);
			LPNTran firedTran = tran2nextState.getLeft();
			ModuleState nextModState = tran2nextState.getRight();
			nextGVecId = nextModState.getGlobalVecIdx();
			State nextState = nextModState.getLocalState();
			traceFiredTran.addLast(firedTran);
			tranFiringCnt++;

			// Check if the firedTran causes disabling error or deadlock.
			LinkedList<Pair<LPNTran, ModuleState>>[] curEnabledArray = new LinkedList[this.ArraySize];
			LinkedList<Pair<LPNTran, ModuleState>>[] nextEnabledArray = new LinkedList[this.ArraySize];
			for (int i = 0; i < this.ArraySize; i++) {
				StateGraph thisSg = this.SgList[i];
				if(i == activeIdx)
					curStateArray[activeIdx] = nextState;

				LinkedList<Pair<LPNTran, ModuleState>> enabledList = thisSg.getEnabled(nextGVecId, curStateArray[i], this.gVecTable, this.gVarIndexMap);
				nextEnabledArray[i] = (LinkedList<Pair<LPNTran, ModuleState>>)enabledList.clone();

				if(i == activeIdx)
					curStateArray[activeIdx] = curState;

				if(Options.checkDisablingError()==true) {
					enabledList = thisSg.getEnabled(curGVecId, curStateArray[i], this.gVecTable, this.gVarIndexMap);
					curEnabledArray[i] = enabledList;

					LPNTran disabledTran = firedTran.disablingError(curEnabledArray[i], nextEnabledArray[i]);
					if (disabledTran != null) {
						System.err.println("Disabling Error: " + disabledTran.getFullLabel() + " is disabled by " + firedTran.getFullLabel());
						for (int curIdx = 0; curIdx < this.ArraySize; curIdx++) {
							System.out.println(curStateArray[curIdx].toString());
						}
						System.out.println(">>>>>");
						for (int nxtIdx = 0; nxtIdx < this.ArraySize; nxtIdx++) {
							System.out.println(curStateArray[nxtIdx].toString());
						}
						System.out.println("\n\n");
						
						errorLocalStateVec = Arrays.copyOf(curStateArray, curStateArray.length);
						errorLocalStateVec[activeIdx] = nextState;
						//errorGlobalVec = nextGlobalVec;
						this.errorMsg = "Disabling Error: " + disabledTran.getFullLabel() + " is disabled by " + firedTran.getFullLabel();
						
						this.ErrorFlag = ErrorType.DISABLING;
						break main_while_loop;
					}
				}
			}

			//* Build index array for the nextStateArray and nextGVec 
			int[] nextStateIdxArray = new int[this.ArraySize+1];
			curStateArray[activeIdx] = nextState;
			for(int i = 0; i < this.ArraySize; i++)
				nextStateIdxArray[i+1] = curStateArray[i].getIndex();
			curStateArray[activeIdx] = curState;

			if(this.noGlobals==true) 
				nextStateIdxArray[0] = 0;
			else {
				nextStateIdxArray[0] = nextGVecId;
			}

			boolean existingState = true;
			existingState = this.StateTable.contains(nextStateIdxArray);

			int nextActiveIdx = 0;
			if(existingState==false) {
				nextActiveIdx = Analysis.deadLock(this.SgList, nextEnabledArray) ;

				if(nextActiveIdx == this.ArraySize) {
					String statusUpdate = "*** Verification failed: deadlock.\n" + "*** Deadlock state layout:\n";
//					for (int i = 0; i < this.ArraySize; i++) {
//						statusUpdate += this.SgList[i].getLabel() + "\n" + curStateArray[i].toString();
//					}
//
//					statusUpdate += this.printGlobalVec(nextGlobalVec);

					
					Console.print(statusUpdate, Console.MessageType.errorInfo, 0);
					
					
					errorLocalStateVec = Arrays.copyOf(curStateArray, curStateArray.length);
					errorLocalStateVec[activeIdx] = nextState;
					//errorGlobalVec = nextGlobalVec;
					this.errorMsg = "Deadlock";
					
					this.ErrorFlag =  ErrorType.DEADLOCK;
					break main_while_loop;
				}
			}
			
			if (existingState == false) {
				this.StateTable.add(nextStateIdxArray);

				stateStack.push(curState);
				gVecStack.push(curGVecId);
				lpnTranStack.push(curEnabled);

				curStateArray[activeIdx] = nextState;
				curGVecId = nextGVecId;
				//curGlobalVec = nextGlobalVec;
				activeIdx = nextActiveIdx;
				curEnabled = nextEnabledArray[activeIdx];
				totalStateCnt++;
				//System.out.println("--> new ");
			}
			else {
				this.traceFiredTran.removeLast();				
			}
		}
		
		for(int i = 0; i < this.ArraySize; i++)
			System.out.println(this.SgList[i].getModStateCount());
		System.out.println(this.gVecTable.elementCount()	);

		//* Return error trace information if there is one. 
		if(this.ErrorFlag !=  ErrorType.NONE) {
			this.traceLocalStateVec.addFirst(errorLocalStateVec);
			this.traceLocalStateVec.addFirst(curStateArray);
			while(stateStack.empty()==false) {
				State[] prevStateArray = Arrays.copyOf(curStateArray, curStateArray.length);
				State prevState = stateStack.pop();
				activeIdx = prevState.getLpn().getIndex();
				prevStateArray[activeIdx] = prevState;
				this.traceLocalStateVec.addFirst(prevStateArray);
				curStateArray = prevStateArray;
			}
			
			this.traceGlobalVec.addFirst(errorGlobalVec);
			this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
			while(gVecStack.empty()==false) {
				curGVecId = gVecStack.pop();
				this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
			}
		}
		
		return null;
	}	
	*/
	
	private HashMap<Pair<Integer, Integer>, IntArrayObj> decompose(int[] IntArray, LinkedList<Pair<Integer, Integer>> connectedPairs) {
		if(this.uniqueDecompStateTbl==null)
			this.uniqueDecompStateTbl = new HashMap<IntArrayObj, IntArrayObj>();
		
		HashMap<Pair<Integer, Integer>, IntArrayObj> result = new HashMap<Pair<Integer, Integer>, IntArrayObj>();

		for(Pair<Integer, Integer> sgPair : connectedPairs) {
			int i = sgPair.getLeft() + 1;
			int ii = sgPair.getRight() + 1;
			int[] tmp = new int[3];
				tmp[0] = IntArray[0];
				tmp[1] = IntArray[i];
				tmp[2] = IntArray[ii];
				
				IntArrayObj newObj = new IntArrayObj(tmp);
				IntArrayObj cachedObj = this.uniqueDecompStateTbl.get(tmp);
				if(cachedObj==null) {
					this.uniqueDecompStateTbl.put(newObj, newObj);
					cachedObj = newObj;
				}
				
				result.put(sgPair, cachedObj);
			}
		return result;
	}	

	/*
	 * A version of search_dfs() without storing the global states of the whole design under consideration.
	 * However, a global stack is manipulated during the search.
	 * It only produces a subset of states compared against the search_dfs()
	 */
	public Stack<State[]> search_dfs_underapprox() {				
		String info = "--> start depth-first search with under-approximate state space ...";
		Console.print(info, Console.MessageType.dynamicInfo, 1);

		/* Compute the connection relation among all modules */
		LinkedList<Pair<Integer, Integer>> connectedPairs = new LinkedList<Pair<Integer, Integer>>();
		for(int i = 0; i < this.ArraySize-1; i++) {
			for(int ii = i+1; ii < this.ArraySize; ii++) {
				boolean connected = false;
				LpnTranList firstList = this.SgList[i].getLpn().getTransitions();
				for(LPNTran tran : firstList)
					if(this.SgList[ii].getLpn().isInput(tran)==true) {
						connected = true;
						break;
					}
				
				if(connected == false) { 
					LpnTranList secondList = this.SgList[ii].getLpn().getTransitions();
					for(LPNTran tran : secondList)
						if(this.SgList[i].getLpn().isInput(tran)==true) {
							connected = true;
							break;
						}
				}
				if(connected==true) {
					if (i < ii)
						connectedPairs.addLast(new Pair<Integer, Integer>(i, ii));
					else
						connectedPairs.addLast(new Pair<Integer, Integer>(ii, i));
				}
			}
		}
		
		//*	Table to store all global states for comparing the results with the monolithic approach.
		boolean monolithicon = false;
		SetIntTuple gstTbl = new HashTable();
		
		//* The vector of the value of all global variables and outputs from all modules in the current state  
		int curGidx = -1;
		if(this.noGlobals==false)  {
			int[] curGlobalVec = this.makeVec(this.InitGlobalVecMap);
			curGidx = this.gVecTable.add(curGlobalVec);
		}

		ProcSg[] psgArray = new ProcSg[this.ArraySize];
		int[] initStateIdxArray = new int[this.ArraySize+1];

		for(int i = 0; i < this.ArraySize; i++) {
			psgArray[i] = new ProcSg(this.SgList[i].getLpn());
			ProcState initPst = new ProcState(curGidx, this.initStateArray[i]);
			initPst = psgArray[i].addState(initPst);	
			initStateIdxArray[i+1] = this.initStateArray[i].getIndex();
		}
		initStateIdxArray[0] = curGidx;

		if (monolithicon)	gstTbl.add(initStateIdxArray);

		State[] curStateArray = this.initStateArray;
			
		int uniqueGlobalSt = 0;
				
		
		HashMap<Pair<Integer, Integer>, HashSet<IntArrayObj>> syncStateTbl = new HashMap<Pair<Integer, Integer>, HashSet<IntArrayObj>>();
		for(Pair<Integer, Integer> share : connectedPairs) {
			HashSet<IntArrayObj> syncStateSet = syncStateTbl.get(share);
			if (syncStateSet == null)
				syncStateSet = new HashSet<IntArrayObj>();	
			syncStateTbl.put(share, syncStateSet);
		}
		
		HashMap<Pair<Integer, Integer>, IntArrayObj> tmp = this.decompose(initStateIdxArray, connectedPairs);
		for(Pair<Integer, Integer> share : connectedPairs) {
			IntArrayObj syncState = tmp.get(share);
			syncStateTbl.get(share).add(syncState);
		}
		/* end of decomposed state space method */
		
		//*	Initialize stacks used by DFS.
		Stack<State[]> stateStack = new Stack<State[]>();
		Stack<Integer> gidxStack = new Stack<Integer>();
		
		stateStack.push(curStateArray);
		gidxStack.push(curGidx);
		
		//*  Main search loop 
		int iter = 0;
		main_while_loop: while (stateStack.size() > 0) {	
			if (iter++ % 100000 == 0) {
			if(this.monitor.getElapsedime() >= Options.TimeUpperBound) {
				this.timeout = true;
				break main_while_loop;	
			}

			if(this.monitor.getPeakUsedMem() >= Options.MemUpperBound) {
				this.memout = true;
				break main_while_loop;
			}

			if (iterations % this.StatusUpdatePeriodLong == 0) {
				String statusUpdate = "---> "
						+ "# LPN transition firings: " + tranFiringCnt + ", "
						+ "stack_depth: " + stateStack.size()+", "
						//+ ", MDD node count: " + mddMgr.nodeCnt()
						+ "used memory: " + this.monitor.getPeakUsedMem() +" MB, "
						+ "free memory: " + Runtime.getRuntime().freeMemory() / 1000000 + " MB, "
						+ "elapsed time: " + (float)this.monitor.getElapsedime() + " seconds.";
				

				String statsLevel5 = "---> " + " # of global vectors: " + this.gVecTable.elementCount() + "\n"
				+ "---> " +  "state table stats: " + this.StateTable.stats();
				
					Console.print(statusUpdate, Console.MessageType.dynamicInfo, 0);
					Console.print(statsLevel5, Console.MessageType.dynamicInfo, 5);

			}
			}
							
			curStateArray = stateStack.pop();
			curGidx = gidxStack.pop();
			
			for (int i = 0; i < this.ArraySize; i++) {
				ProcSg thispsg = psgArray[i];			
				ProcState thispst = thispsg.getState(new ProcState(curGidx, curStateArray[i]));
				LinkedList<Pair<LPNTran, ProcState>> thisEnb = thispsg.getEnabled(thispst, this.gVecTable, this.gVarIndexMap);
						
				for (Pair<LPNTran, ProcState> enbtran : thisEnb) {
					LPNTran firedTran = enbtran.getLeft();
					ProcState thisnxtpst = enbtran.getRight();
					int nxtGidx = thisnxtpst.getGlobalVecIdx();
					State nxtlst = thisnxtpst.getLocalState();
					
					ProcStateTran pstTran = new ProcStateTran(thispst, firedTran, thisnxtpst);
					
					for (int ii = 0; ii < this.ArraySize; ii++) {
						ProcSg otherpsg = psgArray[ii];

						if (ii != i && otherpsg.getLpn().isInput(firedTran) == true) {
							ProcState otherpst = otherpsg.getState(new ProcState(curGidx, curStateArray[ii]));
							otherpsg.addExtTran(otherpst, pstTran);
						}
					}
					
					State[] nextStateArray = Arrays.copyOf(curStateArray, this.ArraySize);
					nextStateArray[i] = nxtlst;
					
					int[] nextStateIdxArray = new int[this.ArraySize+1];
					for (int ii = 0; ii < this.ArraySize; ii++) {
						nextStateIdxArray[ii+1] = nextStateArray[ii].getIndex();
						psgArray[ii].addState(new ProcState(nxtGidx, nextStateArray[ii]));
					}
					nextStateIdxArray[0] = nxtGidx;
					

					boolean newMono = false;
					if(monolithicon == true) {
						if (gstTbl.contains(nextStateIdxArray) == false)	 {
							newMono = true;
							gstTbl.add(nextStateIdxArray);
						}
					}
					
					boolean newNext1 = false;
					HashMap<Pair<Integer, Integer>, IntArrayObj> lclStateRelVec = this.decompose(nextStateIdxArray, connectedPairs);
					for(Pair<Integer, Integer> sgPair : connectedPairs) {
						IntArrayObj psgpair = lclStateRelVec.get(sgPair);
						HashSet<IntArrayObj> syncSet = syncStateTbl.get(sgPair);
						if (syncSet.add(psgpair) == true)
							newNext1 = true;
					}
					
					boolean newNext2 = false;
					if (newNext1 == false) {
						for (int i1 = 0; i1 < this.ArraySize; i1++) {
							for (int i2 = 0; i2 < this.ArraySize; i2++) {
								ProcSg psg1 = psgArray[i1];
								ProcSg psg2 = psgArray[i2];
								ProcState nxtpst1 = psg1.addState(new ProcState(nxtGidx, nextStateArray[i1]));
								ProcState nxtpst2 = psg2.addState(new ProcState(nxtGidx, nextStateArray[i2]));
								
								LinkedList<Pair<LPNTran, ProcState>> Enabled1 = psg1.getEnabled(nxtpst1, this.gVecTable, this.gVarIndexMap);
								LinkedList<Pair<LPNTran, ProcState>> Enabled2 = psg2.getEnabled(nxtpst2, this.gVecTable, this.gVarIndexMap);
								
								if (Enabled1 == null && Enabled2 == null) {
									newNext2 = true;
									break;
								}
								
								HashSet<ProcStateTran> ExternalTrans1 = psg1.getExtTrans(nxtpst1);
								HashSet<ProcStateTran> ExternalTrans2 = psg2.getExtTrans(nxtpst2);
								
								for (Pair<LPNTran, ProcState> tran : Enabled1) {
									if (psg2.getLpn().isInput(tran.getLeft()) == false) 
										continue;
									ProcStateTran psttran = new ProcStateTran(nxtpst1, tran.getLeft(), tran.getRight());
									if (ExternalTrans2 != null && ExternalTrans2.contains(psttran) == false) {
										newNext2 = true;
									}
								}
								for (Pair<LPNTran, ProcState> tran : Enabled2) {
									if (psg1.getLpn().isInput(tran.getLeft()) == false) 
										continue;
									ProcStateTran psttran = new ProcStateTran(nxtpst2, tran.getLeft(), tran.getRight());
									if (ExternalTrans1 != null && ExternalTrans1.contains(psttran) == false) {
										newNext2 = true;
									}
								}
							}
						}
					}
					
					if (newNext1 || newNext2 == true) {
						for(Pair<Integer, Integer> sgPair : connectedPairs) {
							IntArrayObj psgpair = lclStateRelVec.get(sgPair);
							HashSet<IntArrayObj> syncSet = syncStateTbl.get(sgPair);
							syncSet.add(psgpair);
						}
					}
					
					if (newNext1 == true || newNext2 == true) {						
						stateStack.push(nextStateArray);
						gidxStack.push(nxtGidx);
						uniqueGlobalSt++;
					}
				}
			}
		}
		
		//System.out.println("mono state count: " + gstTbl.size());

		
		for(int i = 0; i < this.ArraySize; i++)
			System.out.println(psgArray[i].stateCount() + " " + psgArray[i].tranCount());
		System.out.println(this.gVecTable.elementCount()	);
		System.out.println("maximal stack depth = " + max_stack_depth);
		System.out.println("unique global states = " + uniqueGlobalSt);

		/* Return error trace information if there is one. */
//		if(this.ErrorFlag !=  ErrorType.NONE) {
//			this.traceLocalStateVec.addFirst(errorLocalStateVec);
//			this.traceLocalStateVec.addFirst(curStateArray);
//			while(stateStack.empty()==false) {
//				State[] prevStateArray = Arrays.copyOf(curStateArray, curStateArray.length);
//				State prevState = stateStack.pop();
//				activeIdx = prevState.getLpn().getIndex();
//				prevStateArray[activeIdx] = prevState;
//				this.traceLocalStateVec.addFirst(prevStateArray);
//				curStateArray = prevStateArray;
//			}
//			
//			this.traceGlobalVec.addFirst(errorGlobalVec);
//			this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
//			while(gVecStack.empty()==false) {
//				curGVecId = gVecStack.pop();
//				this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
//			}
//		}
		
		return null;
	}	
	

	
	public ProcSg[] cra_sls() {	
		String info = "--> start compositional SG construction with synchronization-based local search ...";
		Console.print(info, Console.MessageType.dynamicInfo, 1);

		/* Compute the connection relation among all modules */
		LinkedList<Pair<Integer, Integer>> connectedPairs = new LinkedList<Pair<Integer, Integer>>();
		for(int i = 0; i < this.ArraySize-1; i++) {
			for(int ii = i+1; ii < this.ArraySize; ii++) {
				boolean connected = false;
				LpnTranList firstList = this.SgList[i].getLpn().getTransitions();
				for(LPNTran tran : firstList)
					if(this.SgList[ii].getLpn().isInput(tran)==true) {
						connected = true;
						break;
					}
				
				if(connected == false) { 
					LpnTranList secondList = this.SgList[ii].getLpn().getTransitions();
					for(LPNTran tran : secondList)
						if(this.SgList[i].getLpn().isInput(tran)==true) {
							connected = true;
							break;
						}
				}
				if(connected==true) {
					connectedPairs.addLast(new Pair<Integer, Integer>(i, ii));
				}
			}
		}
		
		//*	Initialize process SGs
		int[] initGlobalVec = this.makeVec(this.InitGlobalVecMap);
		int curGidx = this.gVecTable.add(initGlobalVec);
		
		ProcSg[] psgArray = new ProcSg[this.ArraySize];
		for(int i = 0; i < this.ArraySize; i++) {
			psgArray[i] = new ProcSg(this.SgList[i].getLpn());
			ProcState initPst = new ProcState(curGidx, this.initStateArray[i]);
			initPst = psgArray[i].setInitialState(initPst);	
		}
		
		
		//*	For each connected pair of PSGs, find more states and state transitions to make them synchronized.
		//*	Iterate until no more state transitions can be added to any PSG.
		boolean fixpoint = false;
		int[] sync_status = new int[psgArray.length];
		for (int i = 0; i < psgArray.length; i++)
			sync_status[i] = 1;
		
		while (fixpoint == false) {
			fixpoint = true;
			
			for (Pair<Integer, Integer> psgPair : connectedPairs) {
				if (sync_status[psgPair.getLeft()] == 0 && sync_status[psgPair.getRight()] == 0)	continue;
				
				ProcSg psg1 = psgArray[psgPair.getLeft()];
				ProcSg psg2 = psgArray[psgPair.getRight()];
				Pair<Integer, Integer> newPstTrans = this.synchronize(psg1, psg2);
				System.out.println("\t" + psg1.stateCount() + " + " + psg1.tranCount() + ",   " + psg2.stateCount() + " + " + psg2.tranCount());
				sync_status[psgPair.getLeft()] = newPstTrans.getLeft();
				sync_status[psgPair.getRight()] = newPstTrans.getRight();
				if (newPstTrans.getLeft() > 0 || newPstTrans.getRight()> 0)
					fixpoint = false;
				
//				 Scanner sc = new Scanner(System.in);
//				    int i = sc.nextInt();
			}
			
			for (int i = 0; i < this.ArraySize; i++)
				System.out.print(psgArray[i].stateCount() + " + " + psgArray[i].tranCount() + ",   ");
			System.out.println("\n");
		}
		
		return psgArray;
	}	
	
	
	public Pair<Integer, Integer> synchronize(ProcSg psg1, ProcSg psg2) {
		System.out.println("sync'ing " + psg1.getLabel() + " " + psg1.getStats() + " and " + psg2.getLabel() + " " + psg2.getStats());
		int oldPstTrans1 = psg1.tranCount();
		int oldPstTrans2 = psg2.tranCount();
		
		//*	Need to fill the code to sync psg1 and psg2.
		HashSet<Pair<ProcState, ProcState>> visitedTbl = new HashSet<Pair<ProcState, ProcState>>();
		Stack<Pair<ProcState, ProcState>> waitList = new Stack<Pair<ProcState, ProcState>>();
		ProcState init1 = psg1.getInitialPst();
		ProcState init2 = psg2.getInitialPst();
		Pair<ProcState, ProcState> initPair = new Pair<ProcState, ProcState>(init1, init2);
		visitedTbl.add(initPair);
		waitList.push(initPair);
		
		int iter = 0;
		while (waitList.size() > 0) {
			if (iter > 1000) {
				System.out.println(visitedTbl.size() + "  " + waitList.size());
				iter = 0;
			}
			Pair<ProcState, ProcState> pstPair = waitList.pop();
			ProcState pst1 = pstPair.getLeft();
			ProcState pst2 = pstPair.getRight();
			
			if (pst1.getGlobalVecIdx() != pst2.getGlobalVecIdx())
				throw new IllegalStateException("pst1 and pst2 have different global vector index!");
			
			//* Process the enabled transition in pst1 and pst2.
			LinkedList<Pair<LPNTran, ProcState>> enabled1 = psg1.getEnabled(pst1, this.gVecTable, this.gVarIndexMap);
			LinkedList<Pair<LPNTran, ProcState>> enabled2 = psg2.getEnabled(pst2, this.gVecTable, this.gVarIndexMap);

			for (Pair<LPNTran, ProcState> tran1 : enabled1) {
				LPNTran lpnTran1 = tran1.getLeft(); 
				ProcState succ1 = tran1.getRight();
				
				if (succ1 == ProcSg.badPst) continue;
				
				ProcStateTran pstTran1 = psg1.getPsTran(pst1, lpnTran1, succ1);
				ProcState succ2 = pst2;
				
				//if (psg2.getLpn().isInput(lpnTran1) == true) {
				if (pst1.getGlobalVecIdx() != succ1.getGlobalVecIdx()) { //(psg2.getLpn().isInput(lpnTran1) == true) {
					psg2.addExtTran(pst2, pstTran1);				
					succ2 = psg2.getSucc(pst2, pstTran1);
				}
				if (succ2 == null)
					throw new IllegalStateException("succ2 due to an external transition is null!");
				
				if (succ1.getGlobalVecIdx() != succ2.getGlobalVecIdx())
					throw new IllegalStateException("succ1 and succ2 have different global vector index!  0");

				Pair<ProcState, ProcState> succPair = new Pair<ProcState, ProcState>(succ1, succ2);	
				if (visitedTbl.contains(succPair) == false) {
					visitedTbl.add(succPair);
					waitList.push(succPair);
				}
			}
			
			for (Pair<LPNTran, ProcState> tran2 : enabled2) {
				LPNTran lpnTran2 = tran2.getLeft(); 
				ProcState succ2 = tran2.getRight();
				
				if (succ2 == ProcSg.badPst) continue;

				ProcStateTran pstTran2 = psg2.getPsTran(pst2, lpnTran2, succ2); 							
				ProcState succ1 = pst1;
				
				//if (psg1.getLpn().isInput(lpnTran2) == true) {
				if (pst2.getGlobalVecIdx() != succ2.getGlobalVecIdx()) { //(psg2.getLpn().isInput(lpnTran1) == true) {
					psg1.addExtTran(pst1, pstTran2);
					succ1 = psg1.getSucc(pst1, pstTran2);
				}
				
				if (succ1 == null)
					throw new IllegalStateException("succ1 due to an external transition is null!");
				
				if (succ1.getGlobalVecIdx() != succ2.getGlobalVecIdx()) 
					throw new IllegalStateException("succ1 and succ2 have different global vector index!  1");
				
				Pair<ProcState, ProcState> succPair = new Pair<ProcState, ProcState>(succ1, succ2);	
				if (visitedTbl.contains(succPair) == false) {
					visitedTbl.add(succPair);
					waitList.push(succPair);
				}
			}
			
			
			//*	Process the external transitions of pst1 and pst2.
			HashSet<ProcStateTran> extTrans1 = psg1.getExtTrans(pst1);
			HashSet<ProcStateTran> extTrans2 = psg2.getExtTrans(pst2);
			
			if (extTrans1 == null || extTrans2 == null)	continue;
			
			for (ProcStateTran tran1 : extTrans1) {
				if (extTrans2.contains(tran1) == false)	continue;
					
				ProcState succ1 = psg1.getSucc(pst1, tran1);
				ProcState succ2 = psg2.getSucc(pst2, tran1);
				
				if (succ1 == ProcSg.badPst || succ2 == ProcSg.badPst) continue;

				if (succ2 == null)
					throw new IllegalStateException("succ2 due to an external transition is null!");

				if (succ1.getGlobalVecIdx() != succ2.getGlobalVecIdx())
					throw new IllegalStateException("succ1 and succ2 have different global vector index!  2");

				Pair<ProcState, ProcState> succPair = new Pair<ProcState, ProcState>(succ1, succ2);	
				if (visitedTbl.contains(succPair) == false) {
					visitedTbl.add(succPair);
					waitList.push(succPair);
				}
			}
		}
		
		int newPstTrans1 = psg1.tranCount();
		int newPstTrans2 = psg2.tranCount();
		return new Pair<Integer, Integer>(newPstTrans1 - oldPstTrans1, newPstTrans2 - oldPstTrans2);
	}
	
	
	public Stack<State[]> search(ProcSg[] psgArray) {				
		String info = "--> Search the global state space of the constructed process SGs ...";
		Console.print(info, Console.MessageType.dynamicInfo, 1);

		//* Display the stats of the ProcSg to be composed.
		for (int i = 0; i < psgArray.length; i++) {
			String info1 = psgArray[i].getLabel() + ": " + psgArray[i].stateCount() + " + " + psgArray[i].tranCount();
			Console.print(info1, Console.MessageType.dynamicInfo, 10);
		}
			
		
		//*	Table to store all global states for comparing the results with the monolithic approach.
		SetIntTuple gstTbl = new HashTable();
		
		ProcState[] curPstArray = new ProcState[psgArray.length];
 		int[] initStateIdxArray = new int[this.ArraySize+1];
		int gidx = -1;
		for(int i = 0; i < psgArray.length; i++) {
			ProcState initPst = psgArray[i].getInitialPst();	
			curPstArray[i] = initPst;
			initStateIdxArray[i+1] = initPst.getLocalState().getIndex();
			if (gidx == -1)
				gidx = initPst.getGlobalVecIdx();
			else if (gidx >= 0)
				if (gidx != initPst.getGlobalVecIdx())
					throw new IllegalStateException("Unmatched global vector's indices.");
		}
		
		if (gidx < 0)
			throw new IllegalStateException("uninitialized global vector's index");
		
		initStateIdxArray[0] = gidx;

		gstTbl.add(initStateIdxArray);
				
		
		//*	Initialize stacks used by DFS.
		Stack<ProcState[]> stateStack = new Stack<ProcState[]>();		
		stateStack.push(curPstArray);
		
		/*  Main search loop */
		int iter = 0;
		main_while_loop: while (stateStack.size() > 0) {	
			if (iter++ % 1000 == 0) {
			if(this.monitor.getElapsedime() >= Options.TimeUpperBound) {
				this.timeout = true;
				break main_while_loop;	
			}

			if(this.monitor.getPeakUsedMem() >= Options.MemUpperBound) {
				this.memout = true;
				break main_while_loop;
			}

			if (iterations % this.StatusUpdatePeriodLong == 0) {
				String statusUpdate = "---> "
						+ "# LPN transition firings: " + tranFiringCnt + ", "
						+ "stack_depth: " + stateStack.size()+", "
						//+ ", MDD node count: " + mddMgr.nodeCnt()
						+ "used memory: " + this.monitor.getPeakUsedMem() +" MB, "
						+ "free memory: " + Runtime.getRuntime().freeMemory() / 1000000 + " MB, "
						+ "elapsed time: " + (float)this.monitor.getElapsedime() + " seconds.";
				

				String statsLevel5 = "---> " + " # of global vectors: " + this.gVecTable.elementCount() + "\n"
				+ "---> " +  "state table stats: " + this.StateTable.stats();
				
					Console.print(statusUpdate, Console.MessageType.dynamicInfo, 0);
					Console.print(statsLevel5, Console.MessageType.dynamicInfo, 5);

			}
			}
							
			curPstArray = stateStack.pop();
			
			for (int i = 0; i < psgArray.length; i++) {
				ProcSg psg = psgArray[i];			
				ProcState pst = curPstArray[i];
				LinkedList<Pair<LPNTran, ProcState>> Enb = psg.getEnabled(pst, this.gVecTable, this.gVarIndexMap);
						
				for (Pair<LPNTran, ProcState> enbtran : Enb) {
					LPNTran firedTran = enbtran.getLeft();
					ProcState nxtpst = enbtran.getRight();
					int nxtgidx = nxtpst.getGlobalVecIdx();
					
					ProcStateTran pstTran = new ProcStateTran(pst, firedTran, nxtpst);
					
					int[] nextPstIdxArray = new int[psgArray.length+1];
					ProcState[] nextPstArray = Arrays.copyOf(curPstArray, curPstArray.length);
					for (int ii = 0; ii < curPstArray.length; ii++) {
						if (i == ii) {
							nextPstArray[ii] = nxtpst;
						}						
						else if (psgArray[ii].getLpn().isInput(firedTran) == true) {
							nextPstArray[ii] = psgArray[ii].getSucc(curPstArray[ii], pstTran);
							if (nextPstArray[ii] == null)
								throw new IllegalStateException("Process " + psgArray[ii].getLabel() + " is not receptive.");
							if (nxtgidx != nextPstArray[ii].getGlobalVecIdx())
								throw new IllegalStateException("Unmatched global vector indices");
						}
					
						nextPstIdxArray[ii+1] = nextPstArray[ii].getLocalState().getIndex();
					}
					
					nextPstIdxArray[0] = nxtgidx;
					
					if (gstTbl.contains(nextPstIdxArray) == false)	 {
						gstTbl.add(nextPstIdxArray);	
						stateStack.push(nextPstArray);
					}
				}
			}
		}
		
		System.out.println("global state count: " + gstTbl.size());

		
		for(int i = 0; i < this.ArraySize; i++)
			System.out.println(psgArray[i].stateCount() + " " + psgArray[i].tranCount());
		System.out.println(this.gVecTable.elementCount()	);
		System.out.println("maximal stack depth = " + max_stack_depth);

		/* Return error trace information if there is one. */
//		if(this.ErrorFlag !=  ErrorType.NONE) {
//			this.traceLocalStateVec.addFirst(errorLocalStateVec);
//			this.traceLocalStateVec.addFirst(curStateArray);
//			while(stateStack.empty()==false) {
//				State[] prevStateArray = Arrays.copyOf(curStateArray, curStateArray.length);
//				State prevState = stateStack.pop();
//				activeIdx = prevState.getLpn().getIndex();
//				prevStateArray[activeIdx] = prevState;
//				this.traceLocalStateVec.addFirst(prevStateArray);
//				curStateArray = prevStateArray;
//			}
//			
//			this.traceGlobalVec.addFirst(errorGlobalVec);
//			this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
//			while(gVecStack.empty()==false) {
//				curGVecId = gVecStack.pop();
//				this.traceGlobalVec.addFirst(this.gVecTable.toIntArray(curGVecId));
//			}
//		}
		
		return null;
	}	
	
	/**
	 * An iterative implement of findsg_recursive(). 
	 * @param lpnList
	 * @param curLocalStateArray
	 * @param enabledArray
	 */
	public Stack<State[]> search_dfs_noDisabling_fireOrder(final StateGraph[] lpnList, final State[] initStateArray) {

		boolean firingOrder = false;

		long peakUsedMem = 0;
		long peakTotalMem = 0;
		boolean failure = false;
		int tranFiringCnt = 0;
		int arraySize = lpnList.length;

		HashSet<PrjLpnState> globalStateTbl = new HashSet<PrjLpnState>();
		IndexObjMap<LpnState>[]  lpnStateCache = new IndexObjMap[arraySize];
		//HashMap<LpnState, LpnState>[] lpnStateCache1 = new HashMap[arraySize];

		Stack<LpnState[]> stateStack = new Stack<LpnState[]>();
		Stack<LpnTranList[]> lpnTranStack = new Stack<LpnTranList[]>();

		//get initial enable transition set
		LpnTranList initEnabled = new LpnTranList();
		LpnTranList initFireFirst = new LpnTranList();
		LpnState[] initLpnStateArray = new LpnState[arraySize];
		for (int i = 0; i < arraySize; i++) 
		{
			lpnStateCache[i] = new IndexObjMap<LpnState>();
			LinkedList<LPNTran> enabledTrans = lpnList[i].getEnabled(initStateArray[i], true);
			HashSet<LPNTran> enabledSet = new HashSet<LPNTran>();
			if(!enabledTrans.isEmpty())
			{
				for(LPNTran tran : enabledTrans) {
					enabledSet.add(tran);
					initEnabled.add(tran);
				}
			}
			LpnState curLpnState = new LpnState(lpnList[i].getLpn(), initStateArray[i], enabledSet);
			lpnStateCache[i].add(curLpnState);
			initLpnStateArray[i] = curLpnState;
		}
		LpnTranList[] initEnabledSet = new LpnTranList[2];
		initEnabledSet[0] = initFireFirst;
		initEnabledSet[1] = initEnabled;
		lpnTranStack.push(initEnabledSet);


		stateStack.push(initLpnStateArray);
		globalStateTbl.add(new PrjLpnState(initLpnStateArray));

		main_while_loop: while (failure == false && stateStack.empty() == false) {
			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory()
			- Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			if (stateStack.size() > max_stack_depth)
				max_stack_depth = stateStack.size();

			iterations++;
			//if(iterations>2)break;
			if (iterations % 100000 == 0)
				System.out.println("---> #iteration " + iterations
						+ "> # LPN transition firings: " + tranFiringCnt
						+ ", # of prjStates found: " + globalStateTbl.size()
						+ ", current_stack_depth: " + stateStack.size()
						+ ", total MDD nodes: " + mddMgr.nodeCnt()
						+ " used memory: " + (float) curUsedMem / 1000000
						+ " free memory: "
						+ (float) Runtime.getRuntime().freeMemory() / 1000000);


			LpnTranList[] curEnabled = lpnTranStack.peek();
			LpnState[] curLpnStateArray = stateStack.peek();

			// If all enabled transitions of the current LPN are considered,
			// then consider the next LPN
			// by increasing the curIndex.
			// Otherwise, if all enabled transitions of all LPNs are considered,
			// then pop the stacks.

			if(curEnabled[0].size()==0 && curEnabled[1].size()==0){
				lpnTranStack.pop();
				stateStack.pop();
				continue;
			}

			LPNTran firedTran = null;
			if(curEnabled[0].size() != 0)
				firedTran = curEnabled[0].removeFirst();
			else
				firedTran = curEnabled[1].removeFirst();

			traceFiredTran.addLast(firedTran);

			State[] curStateArray = new State[arraySize];
			for( int i = 0; i < arraySize; i++)
				curStateArray[i] = curLpnStateArray[i].getState();

			State[] nextStateArray = firedTran.fire(lpnList, curStateArray);			

			// Check if the firedTran causes disabling error or deadlock.
			HashSet<LPNTran>[] extendedNextEnabledArray = new HashSet[arraySize];
			for (int i = 0; i < arraySize; i++) {				
				HashSet<LPNTran> curEnabledSet = curLpnStateArray[i].getEnabled();
				LpnTranList nextEnabledList = lpnList[i].getEnabled(nextStateArray[i], true);
				HashSet<LPNTran> nextEnabledSet = new HashSet<LPNTran>();
				for(LPNTran tran : nextEnabledList) {
					nextEnabledSet.add(tran);
				}

				extendedNextEnabledArray[i] = nextEnabledSet;

				//non_disabling
				for(LPNTran curTran : curEnabledSet) {
					if(curTran == firedTran)
						continue;

					if(nextEnabledSet.contains(curTran) == false) {
						int[] nextMarking = nextStateArray[i].getMarking();
						int[] preset = curTran.getPreSet();

						boolean included = true;
						if (preset != null && preset.length > 0) {
							for (int pp : preset) {
								boolean temp = false;
								for (int mi = 0; mi < nextMarking.length; mi++) {
									if (nextMarking[mi] == pp) {
										temp = true;
										break;
									}
								}
								if (temp == false)
								{
									included = false;
									break;
								}
							}
						}
						if(preset==null || preset.length==0 || included==true) {
							extendedNextEnabledArray[i].add(curTran);
						}
					}					
				}
			}

			boolean deadlock=true;
			for(int i = 0; i < arraySize; i++) {
				if(extendedNextEnabledArray[i].size() != 0){
					deadlock = false;
					break;
				}
			}

			if(deadlock==true) {
				failure = true;
				break main_while_loop;
			}


			// Add nextPrjState into prjStateSet
			// If nextPrjState has been traversed before, skip to the next
			// enabled transition.
			LpnState[] nextLpnStateArray = new LpnState[arraySize];
			for(int i = 0; i < arraySize; i++) {
				HashSet<LPNTran> lpnEnabledSet = new HashSet<LPNTran>();
				for(LPNTran tran : extendedNextEnabledArray[i]) {
					lpnEnabledSet.add(tran);
				}
				LpnState tmp = new LpnState(lpnList[i].getLpn(), nextStateArray[i], lpnEnabledSet);
				LpnState tmpCached = (LpnState)(lpnStateCache[i].add(tmp));
				nextLpnStateArray[i] = tmpCached; 
			}

			boolean newState = globalStateTbl.add(new PrjLpnState(nextLpnStateArray));

			if(newState == false) {
				traceFiredTran.removeLast();
				continue;
			}

			stateStack.push(nextLpnStateArray);

			LpnTranList[] nextEnabledSet = new LpnTranList[2];
			LpnTranList fireFirstTrans = new LpnTranList();
			LpnTranList otherTrans = new LpnTranList();

			for(int i = 0; i < arraySize; i++)
			{
				for(LPNTran tran : nextLpnStateArray[i].getEnabled())
				{
					if(firingOrder == true)
						if(curLpnStateArray[i].getEnabled().contains(tran))
							otherTrans.add(tran);
						else
							fireFirstTrans.add(tran);
					else
						fireFirstTrans.add(tran);
				}
			}

			nextEnabledSet[0] = fireFirstTrans;
			nextEnabledSet[1] = otherTrans;
			lpnTranStack.push(nextEnabledSet);

		}// END while (stateStack.empty() == false)

		// graph.write(String.format("graph_%s_%s-tran_%s-state.gv",mode,tranFiringCnt,
		// prjStateSet.size()));
		System.out.println("SUMMARY: # LPN transition firings: "
				+ tranFiringCnt + ", # of prjStates found: "
				+ globalStateTbl.size() + ", max_stack_depth: " + max_stack_depth);

		/*
		 * by looking at stateStack, generate the trace showing the counter-example.
		 */
		if (failure == true) {
			System.out.println("-------------------------------------------");
			System.out.println("the deadlock trace:");
			//update traceCex from stateStack
			LpnState[] cur = null;
			LpnState[] next = null;
			for(LPNTran tran : traceFiredTran)
				System.out.println(tran.getFullLabel());
		}

		System.out.println("Modules' local states: ");
		for (int i = 0; i < arraySize; i++) {
			System.out.println("module " + lpnList[i].getLpn().getLabel() + ": "
					+ lpnList[i].reachSize());

		}

		return null;
	}


	/**
	 * findsg using iterative approach based on DFS search. The states found are
	 * stored in MDD.
	 * 
	 * When a state is considered during DFS, only one enabled transition is
	 * selected to fire in an iteration.
	 * 
	 * @param lpnList
	 * @param curLocalStateArray
	 * @param enabledArray
	 * @return a linked list of a sequence of LPN transitions leading to the
	 *         failure if it is not empty.
	 */
	public Stack<State[]> search_dfs_mdd_1(final StateGraph[] lpnList, final State[] initStateArray) {
		System.out.println("---> calling function search_dfs_mdd_1");

		long peakUsedMem = 0;
		long peakTotalMem = 0;
		long peakmddNodeCnt = 0;
		int memUpBound = 500; // Set an upper bound of memory in MB usage to
		// trigger MDD compression.
		boolean compress = false;
		int tranFiringCnt = 0;
		int totalStateCnt = 1;
		int arraySize = lpnList.length;

		Stack<State[]> stateStack = new Stack<State[]>();
		Stack<LinkedList<LPNTran>> lpnTranStack = new Stack<LinkedList<LPNTran>>();
		Stack<Integer> curIndexStack = new Stack<Integer>();

		mddNode reachAll = null;
		mddNode reach = mddMgr.newNode();

		int[] localIdxArray = Analysis.getStateIdxArray(initStateArray, true);
		mddMgr.add(reach, localIdxArray, compress);

		stateStack.push(initStateArray);
		LpnTranList initEnabled = lpnList[0].getEnabled(initStateArray[0], true);
		lpnTranStack.push(initEnabled.clone());
		curIndexStack.push(0);

		long nextMemUpBound = memUpBound;

		main_while_loop: while (this.ErrorFlag !=  ErrorType.NONE && stateStack.empty() == false) {

			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			if (stateStack.size() > max_stack_depth)
				max_stack_depth = stateStack.size();

			iterations++;

			/*
			 * In some number of iterations, check if the runtime or memory limit is exceeded. 
			 * Terminate the loop if yes.
			 */
			if(iterations % this.StatusUpdatePeriodShort == 0) {
				long elapsedTimeMillis = System.currentTimeMillis() - localStartTime; 
				float elapsedTimeSec = elapsedTimeMillis/1000F;
				if(elapsedTimeSec >= Options.TimeUpperBound || peakUsedMem/1000000 >= Options.MemUpperBound) {
					System.out.println("Timed out!");
					break main_while_loop;
				}

				if (curUsedMem >= nextMemUpBound * 1000000) {
					mddMgr.compress(reach);
					if (reachAll == null)
						reachAll = reach;
					else {
						mddNode newReachAll = mddMgr.union(reachAll, reach);
						if (newReachAll != reachAll) {
							mddMgr.remove(reachAll);
							reachAll = newReachAll;
						}
					}
					mddMgr.remove(reach);
					reach = mddMgr.newNode();

					/* Increase memory Upper Bound as more states are found. */
					nextMemUpBound = curUsedMem + memUpBound;
				}
			}

			if (iterations % this.StatusUpdatePeriodLong == 0) {
				long curmddNodeCnt = mddMgr.nodeCnt();
				peakmddNodeCnt = peakmddNodeCnt > curmddNodeCnt ? peakmddNodeCnt : curmddNodeCnt;

				System.out.println("---> #iteration " + iterations
						+ "> # LPN transition firings: " + tranFiringCnt
						+ ", # of prjStates found: " + totalStateCnt
						+ ", stack depth: " + stateStack.size()
						+ ", total MDD nodes: " + curmddNodeCnt
						+ " used memory: " + (float) curUsedMem / 1000000
						+ " free memory: "
						+ (float) Runtime.getRuntime().freeMemory() / 1000000);
			}


			State[] curStateArray = stateStack.peek();
			int curIndex = curIndexStack.peek();
			LinkedList<LPNTran> curEnabled = lpnTranStack.peek();

			// If all enabled transitions of the current LPN are considered,
			// then consider the next LPN
			// by increasing the curIndex.
			// Otherwise, if all enabled transitions of all LPNs are considered,
			// then pop the stacks.
			if (curEnabled.size() == 0) {
				lpnTranStack.pop();
				curIndexStack.pop();

				curIndex++;
				while (curIndex < arraySize) {
					LpnTranList enabledCached = (lpnList[curIndex].getEnabled(curStateArray[curIndex], true));
					if (enabledCached.size() > 0) {
						curEnabled = enabledCached.clone();
						lpnTranStack.push(curEnabled);
						curIndexStack.push(curIndex);
						break;
					} else
						curIndex++;
				}
			}

			if (curIndex == arraySize) {
				stateStack.pop();
				continue;
			}

			LPNTran firedTran = curEnabled.removeLast();
			State[] nextStateArray = firedTran.fire(lpnList, curStateArray);
			tranFiringCnt++;

			// Check if the firedTran causes disabling error or deadlock.
			LinkedList<LPNTran>[] curEnabledArray = new LinkedList[arraySize];
			LinkedList<LPNTran>[] nextEnabledArray = new LinkedList[arraySize];
			for (int i = 0; i < arraySize; i++) {
				StateGraph lpn_tmp = lpnList[i];
				LinkedList<LPNTran> enabledList = lpn_tmp.getEnabled(curStateArray[i], true);
				curEnabledArray[i] = enabledList;
				enabledList = lpn_tmp.getEnabled(nextStateArray[i], true);
				nextEnabledArray[i] = enabledList;

				if(Options.checkDisablingError()==true) {
					LPNTran disabledTran = firedTran.disablingError1(curEnabledArray[i], nextEnabledArray[i]);
					if (disabledTran != null) {
						System.err.println("Disabling Error: " + disabledTran.getFullLabel() + " is disabled by " + firedTran.getFullLabel());
						this.ErrorFlag =  ErrorType.DISABLING;
						break main_while_loop;
					}
				}
			}

			int nextActiveIdx = Analysis.deadLock1(lpnList, nextEnabledArray);
			if(nextActiveIdx == this.ArraySize) {
				System.out.println("*** Verification failed: deadlock.");
				this.ErrorFlag =  ErrorType.DEADLOCK;
				break main_while_loop;
			}

			/*
			 * Check if the local indices of nextStateArray already exist.
			 * if not, add it into reachable set, and push it onto stack.
			 */
			localIdxArray = Analysis.getStateIdxArray(nextStateArray, true);

			Boolean existingState = false;
			if (reachAll != null && mddMgr.contains(reachAll, localIdxArray) == true)
				existingState = true;
			else if (mddMgr.contains(reach, localIdxArray) == true)
				existingState = true;

			if (existingState == false) {
				mddMgr.add(reach, localIdxArray, compress);
				totalStateCnt++;
				stateStack.push(nextStateArray);
				lpnTranStack.push((LpnTranList) nextEnabledArray[0].clone());
				curIndexStack.push(0);
			}
		}



		System.out.println("---> run statistics: \n"
				+ "# LPN transition firings: "	+ tranFiringCnt + "\n" 
				+ "# of prjStates found: " + totalStateCnt + "\n" 
				+ "max_stack_depth: " + max_stack_depth + "\n" 
				+ "peak MDD nodes: " + peakmddNodeCnt + "\n"
				+ "peak used memory: " + peakUsedMem / 1000000 + " MB\n"
				+ "peak total memory: " + peakTotalMem / 1000000 + " MB\n");

		return null;
	}

	/**
	 * findsg using iterative approach based on DFS search. The states found are
	 * stored in MDD.
	 * 
	 * It is similar to findsg_dfs_mdd_1 except that when a state is considered
	 * during DFS, all enabled transition are fired, and all its successor
	 * states are found in an iteration.
	 * 
	 * @param lpnList
	 * @param curLocalStateArray
	 * @param enabledArray
	 * @return a linked list of a sequence of LPN transitions leading to the
	 *         failure if it is not empty.
	 */
	public Stack<State[]> search_dfs_mdd_2(final StateGraph[] lpnList, final State[] initStateArray) {
		System.out.println("---> calling function search_dfs_mdd_2");

		int tranFiringCnt = 0;
		int totalStateCnt = 0;
		int arraySize = lpnList.length;
		long peakUsedMem = 0;
		long peakTotalMem = 0;
		long peakmddNodeCnt = 0;
		int memUpBound = 100; // Set an upper bound of memory in MB usage to
		// trigger MDD compression.
		boolean failure = false;

		MDT state2Explore = new MDT(arraySize);
		state2Explore.add(initStateArray);
		totalStateCnt++;
		long peakState2Explore = 0;

		Stack<Integer> searchDepth = new Stack<Integer>();
		searchDepth.push(1);

		boolean compressed = false;
		mddNode reachAll = null;
		mddNode reach = mddMgr.newNode();

		main_while_loop: 
			while (failure == false	&& state2Explore.empty() == false) {
				long curTotalMem = Runtime.getRuntime().totalMemory();
				long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

				if (curTotalMem > peakTotalMem)
					peakTotalMem = curTotalMem;

				if (curUsedMem > peakUsedMem)
					peakUsedMem = curUsedMem;

				iterations++;

				if(iterations % this.StatusUpdatePeriodShort == 0) {
					long elapsedTimeMillis = System.currentTimeMillis() - localStartTime; 
					float elapsedTimeSec = elapsedTimeMillis/1000F;
					if(elapsedTimeSec >= Options.TimeUpperBound || peakUsedMem/1000000 >= Options.MemUpperBound) {
						System.out.println("Timed out!");
						break main_while_loop;
					}
				}

				if (iterations % this.StatusUpdatePeriodLong == 0) {
					int mddNodeCnt = mddMgr.nodeCnt();
					peakmddNodeCnt = peakmddNodeCnt > mddNodeCnt ? peakmddNodeCnt : mddNodeCnt;
					int state2ExploreSize = state2Explore.size();
					peakState2Explore = peakState2Explore > state2ExploreSize ? peakState2Explore : state2ExploreSize;

					System.out.println("---> #iteration " + iterations
							+ "> # LPN transition firings: " + tranFiringCnt
							+ ", # of prjStates found: " + totalStateCnt
							+ ", # states to explore: " + state2ExploreSize
							+ ", # MDT nodes: " + state2Explore.nodeCnt()
							+ ", total MDD nodes: " + mddNodeCnt
							+ " used memory: " + (float) curUsedMem / 1000000
							+ " free memory: "
							+ (float) Runtime.getRuntime().freeMemory() / 1000000);
				}

				if (iterations % this.StatusUpdatePeriodShort == 0) {
					if (curUsedMem >= memUpBound * 1000000) {
						mddMgr.compress(reach);
						if (reachAll == null)
							reachAll = reach;
						else {
							mddNode newReachAll = mddMgr.union(reachAll, reach);
							if (newReachAll != reachAll) {
								mddMgr.remove(reachAll);
								reachAll = newReachAll;
							}
						}
						mddMgr.remove(reach);
						reach = mddMgr.newNode();
					}
				}

				State[] curStateArray = state2Explore.remove();
				State[] nextStateArray = null;

				int states2ExploreCurLevel = searchDepth.pop();
				if(states2ExploreCurLevel > 1) 
					searchDepth.push(states2ExploreCurLevel-1);

				int[] localIdxArray = Analysis.getLocalStateIdxArray(lpnList, curStateArray, false);
				mddMgr.add(reach, localIdxArray, compressed);

				int nextStates2Explore = 0;
				for (int index = arraySize - 1; index >= 0; index--) {
					StateGraph curLpn = lpnList[index];
					State curState = curStateArray[index];
					LinkedList<LPNTran> curEnabledSet = curLpn.getEnabled(curState, true);

					LpnTranList curEnabled = (LpnTranList) curEnabledSet.clone();
					while (curEnabled.size() > 0) {
						LPNTran firedTran = curEnabled.removeLast();
						nextStateArray = firedTran.fire(lpnList, curStateArray);
						tranFiringCnt++;

						for (int i = 0; i < arraySize; i++) {
							StateGraph lpn_tmp = lpnList[i];
							if (curStateArray[i] == nextStateArray[i])
								continue;

							LinkedList<LPNTran> curEnabled_l = lpn_tmp.getEnabled(curStateArray[i], true);
							LinkedList<LPNTran> nextEnabled = lpn_tmp.getEnabled(nextStateArray[i], true);

							if(Options.checkDisablingError()==true) {
								LPNTran disabledTran = firedTran.disablingError1(curEnabled_l, nextEnabled);
								if (disabledTran != null) {
									System.err.println("Verification failed: disabling error: " + disabledTran.getFullLabel() + " disabled by " + firedTran.getFullLabel() + "!!!");
									failure = true;
									break main_while_loop;
								}
							}
						}

						//					if (Analysis.deadLock(lpnList, nextEnabledA) == true) {
						//						System.err.println("Verification failed: deadlock.");
						//						failure = true;
						//						break main_while_loop;
						//					}

						/*
						 * Check if the local indices of nextStateArray already exist.
						 */
						//localIdxArray = Analysis.getLocalStateIdxArray(lpnList, curStateArray, nextStateArray, index, false);
						localIdxArray = Analysis.getLocalStateIdxArray(lpnList, nextStateArray, false);

						Boolean existingState = false;
						if (reachAll != null && mddMgr.contains(reachAll, localIdxArray) == true)
							existingState = true;
						else if (mddMgr.contains(reach, localIdxArray) == true)
							existingState = true;
						else if(state2Explore.contains(nextStateArray)==true)
							existingState = true;

						if (existingState == false) {
							totalStateCnt++;
							//mddMgr.add(reach, localIdxArray, compressed);
							state2Explore.add(nextStateArray);
							nextStates2Explore++;
						}
					}
				}
				if(nextStates2Explore > 0)
					searchDepth.push(nextStates2Explore);
			}

		endoffunction: System.out.println("-------------------------------------\n"
				+ "---> run statistics: \n"
				+ " # Depth of search (Length of Cex): " + searchDepth.size() + "\n"
				+ " # LPN transition firings: " + (double)tranFiringCnt/1000000 + " M\n"
				+ " # of prjStates found: " + (double)totalStateCnt / 1000000 + " M\n"
				+ " peak states to explore : " + (double) peakState2Explore / 1000000 + " M\n"
				+ " peak MDD nodes: " + peakmddNodeCnt + "\n"
				+ " peak used memory: " + peakUsedMem / 1000000 + " MB\n"
				+ " peak total memory: " + peakTotalMem /1000000 + " MB\n"
				+ "_____________________________________");

			return null;
	}


	public LinkedList<LPNTran> search_bfs(final StateGraph[] sgList, final State[] initStateArray) {
		System.out.println("---> starting search_bfs");

		long peakUsedMem = 0;
		long peakTotalMem = 0;
		long peakmddNodeCnt = 0;
		int memUpBound = 1000; 	// Set an upper bound of memory in MB usage to
		// trigger MDD compression.

		int arraySize = sgList.length;

		for (int i = 0; i < arraySize; i++)
			sgList[i].addState(initStateArray[i]);

		mddNode reachSet = null;
		mddNode reach = mddMgr.newNode();
		MDT frontier = new MDT(arraySize);
		MDT image = new MDT(arraySize);

		frontier.add(initStateArray);

		State[] curStateArray = null;
		int tranFiringCnt = 0;
		int totalStates = 0;
		int imageSize = 0;

		boolean verifyError = false;

		bfsWhileLoop: while (true) {
			if (verifyError == true)
				break;

			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			long curmddNodeCnt = mddMgr.nodeCnt();
			peakmddNodeCnt = peakmddNodeCnt > curmddNodeCnt ? peakmddNodeCnt : curmddNodeCnt;

			long elapsedTimeMillis = System.currentTimeMillis() - localStartTime; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			if(elapsedTimeSec >= Options.TimeUpperBound || peakUsedMem/1000000 >= Options.MemUpperBound)
				break bfsWhileLoop;

			iterations++;
			System.out.println("iteration " + iterations
					+ "> # LPN transition firings: " + tranFiringCnt 
					+ ", # of prjStates found: "	+ totalStates
					+ ", total MDD nodes: " + curmddNodeCnt
					+ " used memory: " + (float) curUsedMem / 1000000
					+ " free memory: "
					+ (float) Runtime.getRuntime().freeMemory() / 1000000);

			if (curUsedMem >= memUpBound * 1000000) {
				mddMgr.compress(reach);
				if (reachSet == null)
					reachSet = reach;
				else {
					mddNode newReachSet = mddMgr.union(reachSet, reach);
					if (newReachSet != reachSet) {
						mddMgr.remove(reachSet);
						reachSet = newReachSet;
					}
				}
				mddMgr.remove(reach);
				reach = mddMgr.newNode();
			}

			while(frontier.empty() == false) {
				boolean deadlock = true;

				Stack<State[]> curStateArrayList = frontier.removeList();
				while(curStateArrayList.empty() == false) {
					curStateArray = curStateArrayList.pop();
					//				{
						//				 curStateArray = frontier.remove();
					int[] localIdxArray = Analysis.getLocalStateIdxArray(sgList, curStateArray, false);
					mddMgr.add(reach, localIdxArray, false);
					totalStates++;

					for (int i = 0; i < arraySize; i++) {
						LinkedList<LPNTran> curEnabled = sgList[i].getEnabled(curStateArray[i], true);
						if (curEnabled.size() > 0)
							deadlock = false;

						for (LPNTran firedTran : curEnabled) {
							State[] nextStateArray = firedTran.fire(sgList, curStateArray);
							tranFiringCnt++;

							/*
							 * Check if any transitions can be disabled by fireTran.
							 */
							LinkedList<LPNTran> nextEnabled = sgList[i].getEnabled(nextStateArray[i], true);
							LPNTran disabledTran = firedTran.disablingError1(curEnabled, nextEnabled);

							if (Options.checkDisablingError()==true && disabledTran != null) {
								System.err.println("*** Verification failed: disabling error: " + disabledTran.getFullLabel() + " disabled by " + firedTran.getFullLabel() + "!!!");
								verifyError = true;
								break bfsWhileLoop;
							}

							localIdxArray = Analysis.getLocalStateIdxArray(sgList, nextStateArray, false);
							if (mddMgr.contains(reachSet, localIdxArray) == false && mddMgr.contains(reach, localIdxArray) == false) {
								//if(image.contains(nextStateArray)==false) {
								{
									boolean isNewState = image.add(nextStateArray);
									if(isNewState) imageSize++;
								}
							}

							//						if(Analysis.deadLock(sgList, nextEnabledArray) == true) {
							//							System.err.println("*** Verification failed: deadlock.");
							//							verifyError = true;
							//							break bfsWhileLoop;
							//						}

						}
					}
				}



				/*
				 * If curStateArray deadlocks (no enabled transitions), terminate.
				 */
				if (deadlock == true) {
					System.err.println("*** Verification failed: deadlock.");
					verifyError = true;
					break bfsWhileLoop;
				}
			}

			if(image.empty()==true) break;

			System.out.println("---> size of image: " + imageSize);

			frontier = image;		
			image = new MDT(arraySize);
			imageSize = 0;
		}

		System.out.println("---> final numbers: # LPN transition firings: " + tranFiringCnt / 1000000 + "M\n"
				+ "---> # of prjStates found: " + (double) totalStates / 1000000 + "M\n"
				+ "---> peak total memory: " + peakTotalMem / 1000000F + " MB\n" 
				+ "---> peak used memory: " + peakUsedMem / 1000000F + " MB\n" 
				+ "---> peak MDD nodes: " + peakmddNodeCnt);

		return null;
	}

	/**
	 * partial order reduction
	 * 
	 * @param lpnList
	 * @param curLocalStateArray
	 * @param enabledArray
	 */
	public Stack<State[]> search_dfs_por(final StateGraph[] lpnList, final State[] initStateArray, LPNTranRelation lpnTranRelation, String approach) {	
		String info = "---> Calling search_dfs with partial order reduction:"+ approach;
		Console.print(info, Console.MessageType.dynamicInfo, 1);
		
		long peakUsedMem = 0;
		long peakTotalMem = 0;
		int max_stack_depth = 0;
		int iterations = 0;
		boolean failure = false;
		this.tranFiringCnt = 0;
		this.totalStateCnt = 0;
		int arraySize = lpnList.length;;
		boolean useMdd = Options.getStateFormat() == Options.StateFormatDef.MDD;
		mddNode reach = mddMgr.newNode();

		//inittial POR function and get the independent transition set
		Console.print("---> Computing the independence relation", Console.MessageType.dynamicInfo, 5);
		AmpleSet ampleClass = new AmpleSet();
		HashMap<LPNTran,HashSet<LPNTran>> indepTranSet = new HashMap<LPNTran,HashSet<LPNTran>>();
		if(approach == "state")
			indepTranSet = ampleClass.getIndepSet_FromState(lpnList, lpnTranRelation);
		else if (approach == "lpn")
			indepTranSet = ampleClass.getIndepSet_FromLPN(lpnList);
		Console.print("---> Done.\n", Console.MessageType.dynamicInfo, 5);


		HashSet<PrjState> stateStack = new HashSet<PrjState>();
		//record if current state has an un-cycle transition.
		Stack<String> stateFlagStack = new Stack<String>();
		Stack<LpnTranList> lpnTranStack = new Stack<LpnTranList>();
		HashMap<PrjState,HashSet<LPNTran>> firedTranMap= new HashMap<PrjState,HashSet<LPNTran>>();  

		//get initial enable transition set
		LinkedList<LPNTran>[] initEnabledArray = new LinkedList[arraySize];
		for (int i = 0; i < arraySize; i++) {
			lpnList[i].getLpn().setIndex(i);
			initEnabledArray[i] = lpnList[i].getEnabled(initStateArray[i], true);
		}

		//set initEnableSubset
		LpnTranList initEnableSubset = ampleClass.getAmpleSet(initEnabledArray,lpnList,initStateArray,indepTranSet);

		/*
		 * Initialize the reach state set with the initial state.
		 */
		HashSet<PrjState> prjStateSet = new HashSet<PrjState>();
		PrjState initPrjState = new PrjState(initStateArray);

		//		if (useMdd) {
		//			int[] initIdxArray = Analysis.getIdxArray(initStateArray);
		//			mddMgr.add(reach, initIdxArray, true);
		//		}
		//		else 
		//			prjStateSet.add(initPrjState);

		stateStack.add(initPrjState);
		stateFlagStack.push("F");
		PrjState stateStackTop = initPrjState;
		lpnTranStack.push(initEnableSubset);
		firedTranMap.put(initPrjState,new HashSet<LPNTran>());	
		
		/*
		 * Start the main search loop.
		 */
		main_while_loop:
			while(failure == false && stateStack.size() != 0) {
				long curTotalMem = Runtime.getRuntime().totalMemory();
				long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

				if (curTotalMem > peakTotalMem)
					peakTotalMem = curTotalMem;

				if (curUsedMem > peakUsedMem)
					peakUsedMem = curUsedMem;

				if (stateStack.size() > max_stack_depth)
					max_stack_depth = stateStack.size();

				iterations++;
				if (iterations % 500000 == 0) {
					if (useMdd==true)
						this.totalStateCnt = (long)mddMgr.numberOfStates(reach);
					else
						this.totalStateCnt = prjStateSet.size();

					System.out.println("---> #iteration " + iterations
							+ "> # LPN transition firings: " + tranFiringCnt
							+ ", # of prjStates found: " + this.totalStateCnt
							+ ", max_stack_depth: " + max_stack_depth
							+ ", total MDD nodes: " + mddMgr.nodeCnt()
							+ " used memory: " + (float) curUsedMem / 1000000
							+ " free memory: "
							+ (float) Runtime.getRuntime().freeMemory() / 1000000);
				}

				State[] curStateArray = stateStackTop.toStateArray();
				LpnTranList curEnabled = lpnTranStack.peek();

				// If all enabled transitions of the current LPN are considered, then consider the next LPN
				// by increasing the curIndex.
				// Otherwise, if all enabled transitions of all LPNs are considered, then pop the stacks.
				if(curEnabled.size() == 0) {
					int[] nextIdxArray = null;
					if(useMdd==true)
						nextIdxArray = Analysis.getLocalStateIdxArray(lpnList, stateStackTop.toStateArray(), false);
					//nextIdxArray = Analysis.getIdxArray(stateStackTop.toStateArray());

					if (useMdd == true) 	
						mddMgr.add(reach, nextIdxArray, true);
					else
						prjStateSet.add(stateStackTop);

					lpnTranStack.pop();
					firedTranMap.remove(stateStackTop);
					stateStack.remove(stateStackTop);
					stateFlagStack.pop();
					stateStackTop = stateStackTop.getFather();
					if (stateStack.size() > 0)
						traceFiredTran.removeLast();
					continue;
				}

				LPNTran firedTran = curEnabled.removeFirst();

				firedTranMap.get(stateStackTop).add(firedTran);
				traceFiredTran.addLast(firedTran);

				State[] nextStateArray = firedTran.fire(lpnList, curStateArray);

				tranFiringCnt++;

				// Check if the firedTran causes disabling error or deadlock.
				LinkedList<LPNTran>[] curEnabledArray = new LinkedList[arraySize];
				LinkedList<LPNTran>[] nextEnabledArray = new LinkedList[arraySize];
				for (int i = 0; i < arraySize; i++) {
					lpnList[i].getLpn().setIndex(i);
					StateGraph lpn_tmp = lpnList[i];
					LinkedList<LPNTran> enabledList = lpn_tmp.getEnabled(curStateArray[i], true);
					curEnabledArray[i] = enabledList;
					enabledList = lpn_tmp.getEnabled(nextStateArray[i], true);
					nextEnabledArray[i] = enabledList;

					if(Options.checkDisablingError()==true) {
						LPNTran disabledTran = firedTran.disablingError1(curEnabledArray[i], nextEnabledArray[i]);
						if(disabledTran != null) {
							System.out.println("---> Disabling Error: " + disabledTran.getFullLabel() + " is disabled by " + firedTran.getFullLabel());

							System.out.println("Current state:");
							for(int ii = 0; ii < arraySize; ii++) {
								System.out.println("module " + lpnList[ii].getLpn().getLabel());
								System.out.println(curStateArray[ii]);
								System.out.println("Enabled set: " + curEnabledArray[ii]);
							}
							System.out.println("======================\nNext state:");
							for(int ii = 0; ii < arraySize; ii++) {
								System.out.println("module " + lpnList[ii].getLpn().getLabel());
								System.out.println(nextStateArray[ii]);
								System.out.println("Enabled set: " + nextEnabledArray[ii]);
							}
							System.out.println();

							failure = true;	
							break main_while_loop;
						}
					}
				}
				

				int nextActiveIdx = Analysis.deadLock1(lpnList, nextEnabledArray);
				if(nextActiveIdx == this.ArraySize) {
					System.out.println("---> Deadlock.");
					failure = true;
					break main_while_loop;
				}


				/*
			// Add nextPrjState into prjStateSet
			// If nextPrjState has been traversed before, skip to the next
			// enabled transition.
			//exist cycle
				 */

				PrjState nextPrjState = new PrjState(nextStateArray);
				boolean isExisting = false;
				int[] nextIdxArray = null;
				//System.out.println(nextStateArray[0]);
				if(useMdd==true)
					//nextIdxArray = Analysis.getIdxArray(nextStateArray);
					nextIdxArray = Analysis.getLocalStateIdxArray(lpnList, nextStateArray, false);

				if (useMdd == true) 
					isExisting = stateStack.contains(nextPrjState) || mddMgr.contains(reach, nextIdxArray);
				else
					isExisting = stateStack.contains(nextPrjState) || prjStateSet.contains(nextPrjState);

				//System.out.println("stateStack:"+stateStack.size()+", prjStateSet:"+prjStateSet.size());
				if (isExisting == false) {		
					//				if (useMdd == true) 	
					//					mddMgr.add(reach, nextIdxArray, true);
					//				else
					//					prjStateSet.add(nextPrjState);

					//get next enable transition set
					LpnTranList nextEnableSubset = ampleClass.getAmpleSet(nextEnabledArray, lpnList, nextStateArray, indepTranSet);

					stateFlagStack.pop();
					stateFlagStack.push("T");
					stateFlagStack.push("F");

					stateStack.add(nextPrjState);
					stateStackTop.setChild(nextPrjState);
					nextPrjState.setFather(stateStackTop);
					stateStackTop = nextPrjState;
					lpnTranStack.push(nextEnableSubset);
					firedTranMap.put(nextPrjState, new HashSet<LPNTran>());

					continue;
				}
				//condition 2(if exist next state do not form a cycle, then skip cycle detection)
				if(stateStack.contains(nextPrjState) == false)
				{
					stateFlagStack.pop();
					stateFlagStack.push("T");
					continue;
				}	

				/*
				 * Remove firedTran from traceCex if its successor state already exists.
				 */
				traceFiredTran.removeLast();

				/*
				 * When firedTran forms a cycle in the state graph, 
				 * 1. consider if exist one transition which is already fired and not forms a cycle----skip cycle condition
				 * 2. If all fired forms a cycle, then consider all enabled transitions:
				 *    (1). all reduced transitions are already fired in the next state, ignore reduced set;
				 *    (2). exist one reduced transitions are independent with all other transitions, fire it first;
				 *    (3). otherwise all reduced transitions are interleaving transition.
				 *         1. exist false dependent transition, fire it first.
				 *         2. exist ready dependent pairs, fired them first.
				 *         3. add all reduced back to ample set.
				 */


				//condition 1(if ample set is not empty, then skip cycle detection)
				if(curEnabled.size()!=0)
					continue;
				
				//this exist state is non-cycle state
				if(stateFlagStack.peek().equals("T"))
					continue;

				/**
				 * if all fired transitions forms a cycle , then consider condition 3 and 4
				 * condition 3: if reduced(s) is empty or reduced(s) belong to ample(next_s), then do not need to add back any transitions.
				 * condition 4: otherwise caculate the new ample set of reduced(s)-ample(next_s) and add ample set back
				 */	

				LinkedList<LPNTran>[] curReducedSet = new LinkedList[arraySize];
				int reducedSetCnt = 0;
				//record reduced(s_k)/ample(s_1)
				LinkedList<LPNTran>[] nextNotIncludeArray = new LinkedList[arraySize];
				int nextNotIncludeCnt = 0;

				HashSet<LPNTran> curFiredTranSet = firedTranMap.get(stateStackTop);
				HashSet<LPNTran> nextFiredTranSet = firedTranMap.get(nextPrjState);

				for (int i = 0; i < arraySize; i++) {
					curReducedSet[i] = new LinkedList<LPNTran>();
					nextNotIncludeArray[i] = new LinkedList<LPNTran>();

					for (LPNTran tran : curEnabledArray[i]) {
						if(curFiredTranSet.contains(tran) == false){
							curReducedSet[i].add(tran);
							reducedSetCnt++;
							if(!nextFiredTranSet.contains(tran)){
								nextNotIncludeArray[i].add(tran);
								nextNotIncludeCnt++;
							}
						}
					}
				}


				//conidition 3
				if(reducedSetCnt==0 || nextNotIncludeCnt == 0)
					continue;

				//conidition 4
				LpnTranList curNewAmpleSet = ampleClass.getAmpleSet(nextNotIncludeArray, lpnList, curStateArray, indepTranSet);

				//			//get one independent from reduced(s_k)/ample(s_1)
				//			LpnTranList curNewAmpleSet = ampleClass.getOneIndepEnabledTran(nextNotIncludeArray, interleavingTranSet);
				//			//get reduced(s_k)/B
				//			if(curNewAmpleSet.isEmpty() || curNewAmpleSet.size()==0)
				//				curNewAmpleSet = ampleClass.getAmpleSet(curReducedSet, lpnList, curStateArray, indepTranSet);

				LpnTranList sortedAmpleSet = curNewAmpleSet;

				/*
				 * Sort transitions in ampleSet for better performance for MDD.
				 * Not needed for hash table.
				 */
				if (useMdd == true) {
					LpnTranList[] newCurEnabledArray = new LpnTranList[arraySize];
					for (int i = 0; i < arraySize; i++)
						newCurEnabledArray[i] = new LpnTranList();

					for (LPNTran tran : curNewAmpleSet)
						newCurEnabledArray[tran.getLpn().getIndex()].addLast(tran);

					sortedAmpleSet = new LpnTranList();
					for (int i = 0; i < arraySize; i++) {
						LpnTranList localEnabledSet = newCurEnabledArray[i];
						for (LPNTran tran : localEnabledSet)
							sortedAmpleSet.addLast(tran);
					}
				}

				lpnTranStack.pop();
				lpnTranStack.push(sortedAmpleSet);
				//System.out.println("Backtrack........\n");
			}//END while (stateStack.empty() == false)


		if (useMdd==true)
			this.totalStateCnt = (long)mddMgr.numberOfStates(reach)+stateStack.size();
		else
			this.totalStateCnt = prjStateSet.size()+stateStack.size();

		long curTotalMem = Runtime.getRuntime().totalMemory();
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		/*System.out.println("SUMMARY: # LPN transition firings: " + tranFiringCnt
				+ ", # of prjStates found: " + stateCount
				+ ", max_stack_depth: " + max_stack_depth
				+ ", used memory: " + (float) curUsedMem / 1000000
				+ ", free memory: "
				+ (float) Runtime.getRuntime().freeMemory() / 1000000);*/

		return null;
	}

	/*
	 * Check if this project deadlocks in the current state 'stateArray'.
	 */
	public static int deadLock(StateGraph[] lpnArray, LinkedList<Pair<LPNTran, ProcState>>[] EnabledArray) {
		boolean deadlock = true;
		int activeIdx = 0;
		for (activeIdx = 0; activeIdx < EnabledArray.length; activeIdx++) {
			LinkedList<Pair<LPNTran, ProcState>> tmp = EnabledArray[activeIdx];
			if (tmp != null && tmp.size() > 0) {
				deadlock = false;
				break;
			}
		}

		return activeIdx;
	}

	public static int deadLock1(StateGraph[] lpnArray, LinkedList<LPNTran>[] EnabledArray) {
		boolean deadlock = true;
		int activeIdx = 0;
		for (activeIdx = 0; activeIdx < EnabledArray.length; activeIdx++) {
			LinkedList<LPNTran> tmp = EnabledArray[activeIdx];
			if (tmp.size() > 0) {
				deadlock = false;
				break;
			}
		}

		return activeIdx;
	}
	
	public static boolean deadLock(StateGraph[] lpnArray, int[] stateIdxArray) {
		boolean deadlock = true;
		for (int i = 0; i < stateIdxArray.length; i++) {
			LinkedList<LPNTran> tmp = lpnArray[i].getEnabled(stateIdxArray[i]);
			if (tmp.size() > 0) {
				deadlock = false;
				break;
			}
		}

		return deadlock;
	}

	/*
	 * Scan enabledArray, identify all sticky transitions other the firedTran, and return them.
	 * 
	 * Arguments remain constant.
	 */

	public static LpnTranList[] getStickyTrans(LpnTranList[] enabledArray, LPNTran firedTran) {
		int arraySize = enabledArray.length;
		LpnTranList[] stickyTranArray = new LpnTranList[arraySize];
		for (int i = 0; i < arraySize; i++) {
			stickyTranArray[i] = new LpnTranList();
			for (LPNTran tran : enabledArray[i]) {
				if (tran != firedTran) 
					stickyTranArray[i].add(tran);
			}

			if(stickyTranArray[i].size()==0)
				stickyTranArray[i] = null;
		}
		return stickyTranArray;
	}

	/**
	 * Identify if any sticky transitions in currentStickyTransArray can  existing in the nextState. If so, add them to 
	 * nextStickyTransArray.
	 * 
	 * Arguments:  curStickyTransArray and nextState are constant, nextStickyTransArray may be added with sticky transitions
	 * from curStickyTransArray.
	 * 
	 *  Return: sticky transitions from curStickyTransArray that are not marking disabled in nextState.
	 */
	public static LpnTranList[] checkStickyTrans(
			LpnTranList[] curStickyTransArray, LpnTranList[] nextEnabledArray, 
			LpnTranList[] nextStickyTransArray, State nextState) {
		int arraySize = curStickyTransArray.length;
		LpnTranList[] stickyTransArray = new LpnTranList[arraySize];
		boolean[] hasStickyTrans = new boolean[arraySize];

		for (int i = 0; i < arraySize; i++) {
			HashSet<LPNTran> tmp = new HashSet<LPNTran>();
			if(nextStickyTransArray[i] != null)
				for(LPNTran tran : nextStickyTransArray[i])
					tmp.add(tran);


			stickyTransArray[i] = new LpnTranList();
			hasStickyTrans[i] = false;
			for (LPNTran tran : curStickyTransArray[i]) {
				if (tran.sticky() == true && tmp.contains(tran)==false) {
					int[] nextMarking = nextState.getMarking();
					int[] preset = tran.getPreSet();
					boolean included = false;
					if (preset != null && preset.length > 0) {
						for (int pp : preset) {
							for (int mi = 0; i < nextMarking.length; i++) {
								if (nextMarking[mi] == pp) {
									included = true;
									break;
								}
							}
							if (included == false)
								break;
						}
					}
					if(preset==null || preset.length==0 || included==true) {
						stickyTransArray[i].add(tran);
						hasStickyTrans[i] = true;
					}
				}
			}

			if(stickyTransArray[i].size()==0)
				stickyTransArray[i] = null;
		}

		return stickyTransArray;
	}

	/*
	 * Return an array of indices for the given stateArray.
	 */
	private static int[] getIdxArray(State[] stateArray) {
		int[] idxArray = new int[stateArray.length];

		for(int i = 0; i < stateArray.length; i++) {
			idxArray[i] = stateArray[i].getIndex();
		}
		return idxArray;
	}

	static HashSet<IntArrayObj> tmp0 = new HashSet<IntArrayObj>();
	static HashSet<IntArrayObj> tmp = new HashSet<IntArrayObj>();
	static HashSet<Integer> tmp1 = new HashSet<Integer>();

	private static int[] getStateIdxArray(State[] stateArray, boolean reverse) {
		int arraySize = stateArray.length;
		int[] localIdxArray = new int[arraySize];

		for(int i = 0; i < arraySize; i++) {
			if(reverse == false) {
				localIdxArray[i] = stateArray[i].getIndex();
			}
			else {
				localIdxArray[i] = stateArray[arraySize - i - 1].getIndex();
			}			
		}

		return localIdxArray;
	}

	private static int[] getLocalStateIdxArray(StateGraph[] sgList, State[] stateArray, boolean reverse) {
		int arraySize = sgList.length;
		int[] localIdxArray = new int[arraySize];

		for(int i = 0; i < arraySize; i++) {
			if(reverse == false) {
				State tmp = sgList[i].getLocalState(stateArray[i]);
				System.out.println(tmp + "  " + i);
				localIdxArray[i] = sgList[i].getLocalState(stateArray[i]).getIndex();
			}
			else {
				localIdxArray[i] = sgList[arraySize - i - 1].getLocalState(stateArray[arraySize - i - 1]).getIndex();
			}			
		}

		return localIdxArray;
	}

	//	public HashMap<String, Integer> getTotalVec(StateGraph[] sgList, State[] stateArray, int[] gVec) {	
	//		System.out.println("--> Get TotalVec\n================");
	//		HashMap<String,Integer> totalVec = new HashMap<String,Integer>();
	//		
	//		for(int i = 0; i< this.ArraySize; i++) {
	//			State curState = stateArray[i];
	//			//VarSet outputVar = curState.getLpn().getOutputs();
	//			DualHashMap<String,Integer> VarIdxMap = curState.getLpn().getVarIndexMap();
	//			HashMap<String,Integer> outputVec = curState.getOutVector(outputVar, VarIdxMap);
	//			for(String var : outputVar) 
	//				totalVec.put(var, outputVec.get(var));
	//			
	//			System.out.println(curState + "\n" + totalVec);
	//		}
	//		
	//		if(this.noGlobals==false) {
	//			for(int i = 0; i < gVec.length; i++) {
	//				totalVec.put(this.gVarIndexMap.getKey(i), gVec[i]);
	//			}
	//		}
	//		System.out.println(totalVec);
	//		System.out.println("================");
	//
	//		return totalVec;
	//	}

	private int[] getGlobalVec(HashMap<String,Integer> totalVec) {
		int gVarCnt = this.gVarIndexMap.size();
		if(gVarCnt == 0)
			return null;

		int[] vec = new int[gVarCnt];
		for(int i = 0; i < gVarCnt; i++) {
			String var = this.gVarIndexMap.getKey(i);
			vec[i] = totalVec.get(var);
		}
		return vec;
	}

	private int[] makeVec(HashMap<String,Integer> gVecMap) {
//		if(gVecMap == null || gVecMap.size()==0)
//			return null;
//		
		int[] gVec = new int[this.gVarIndexMap.size()];
		for(int i = 0; i < this.gVarIndexMap.size(); i++) {
			String var = this.gVarIndexMap.getKey(i);
			Integer val = gVecMap.get(var);
			gVec[i] = val;
		}
		return gVec;
	}	
	private String printGlobalVec(int[] gVec) {
		if(gVec==null)
			return "[]";

		String result = "[";
		for(int i = 0; i < gVec.length; i++) {
			String var = this.gVarIndexMap.getKey(i);
			result += "(" + this.gVarIndexMap.getKey(i) + "," + gVec[i] + "), ";
		}
		result += "]\n";

		return result;
	}

	private int[] makeIndexVec(int[] localStateIdxVec, int[] globalVec) {
		int lVecSize = localStateIdxVec.length;
		int gVecSize = globalVec.length;

		int[] prjIdxVec = new int[lVecSize + gVecSize];
		for(int i = 0; i < lVecSize; i++) 
			prjIdxVec[i] = localStateIdxVec[i];

		for(int i = 0; i < gVecSize; i++) 
			prjIdxVec[lVecSize + i] = globalVec[i];

		return prjIdxVec;
	}

	private int[] reduce4mdd(int[] idxArray, int activeIdx) {
		activeIdx++;
		int[] result = new int[this.ArraySize+1];
		result[0] = 0;

		for(int i = 1; i < this.ArraySize+1; i++) {
			Pair<Integer, Integer> idxPair = null;
			if(i != activeIdx) {
				idxPair = new Pair(idxArray[i], idxArray[0]);				
				if(this.idxPackageTbl[i-1].containsKey(idxPair)==true)
					result[i] = this.idxPackageTbl[i-1].get(idxPair);
				else
					result[i] = idxArray[i];

			}
			else {
				idxPair = new Pair(idxArray[i], idxArray[0]);				
				if(this.idxPackageTbl[i-1].containsKey(idxPair)==true)
					result[i] = this.idxPackageTbl[i-1].get(idxPair);
				else {
					int idx = this.idxPackageTbl[i-1].size() + 1;
					this.idxPackageTbl[i-1].put(idxPair, idx);
					result[i] = idx;
				}
			}
		}

		System.out.println(Arrays.toString(idxArray) + "   " + activeIdx + "\n"
				+ Arrays.toString(result)+"\n-------------------");
		return result;
	}
}
