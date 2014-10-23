package platu.TimingAnalysis;

import java.util.*;

import platu.common.DualHashMap;
import platu.lpn.LPN;
import platu.lpn.LPNTran;
import platu.lpn.LpnTranList;
import platu.project.PrjState;
import platu.stategraph.State;
import platu.stategraph.StateGraph;
import platu.logicAnalysis.*;

public class TimingAnalysis {

	/*
	 * Data member section
	 */
	HashMap[] timingStateCache;

	/*
	 * Mapping of each LPN transition to an quniue intger index in the whole project. 
	 */
	DualHashMap<LPNTran, Integer> TranIndexMap;
	
	
	/*
	 * ------------------------------------------------------------
	 * ------------------------------------------------------------
	 */
	
	
	/*
	 * Member function section
	 */
	public TimingAnalysis(final StateGraph[] SgArray) {

		int ArraySize = SgArray.length;

		timingStateCache = new HashMap[ArraySize];

		long start = System.currentTimeMillis();

		/*
		 * Assign an unique integer index to each LPN transition in the whole project. 
		 */
		this.TranIndexMap = new DualHashMap<LPNTran, Integer>();
		int lpnTranCnt = 0;
		for(int i = 0; i < ArraySize; i++) {
			LpnTranList allTrans = SgArray[i].getLpn().getTransitions();
			for(LPNTran tran : allTrans) {
				this.TranIndexMap.insert(tran, lpnTranCnt+1);
				lpnTranCnt++;
			}
		}	
		
		// Initialize the project state
		HashMap<String, Integer> varValMap = new HashMap<String, Integer>();
		State[] initStateArray = new State[ArraySize];
		ArrayList<LinkedList<LPNTran>> enabledList = new ArrayList<LinkedList<LPNTran>>(1);
		for (int index = 0; index < ArraySize; index++) {
			LPN curLpn = SgArray[index].getLpn();
			initStateArray[index] = curLpn.getInitState();
			int[] curStateVector = initStateArray[index].getVector();
			//HashSet<String> outVars = curLpn.getOutputs();
			DualHashMap<String, Integer> VarIndexMap = curLpn.getVarIndexMap();
//			for (String var : outVars) {
//				varValMap.put(var, curStateVector[VarIndexMap.getValue(var)]);
//			}
		}

		// Adjust the value of the input variables in LPN in the initial state.
		for (int index = 0; index < ArraySize; index++) {
			StateGraph curLpn = SgArray[index];
			initStateArray[index].update(varValMap, curLpn.getLpn().getVarIndexMap());
			initStateArray[index] = curLpn.addState(initStateArray[index]);
			enabledList.add(index, curLpn.getEnabled(initStateArray[index], true));
		}
//
//		if (Analysis.deadLock(SgArray, initStateArray) == true) {
//			System.err.println("Verification failed: deadlock in the initial state.");
//			return;
//		}

		// Add the initial project state into the prjStateSet, and invoke
		// findsg_recursive().
		HashSet<PrjState> stateTrace = new HashSet<PrjState>(1);
		PrjState curPrjState = new PrjState(initStateArray);
		stateTrace.add(curPrjState);

		LinkedList<LPNTran> traceCex = new LinkedList<LPNTran>();

		/*
		 * Timing analysis by DFS
		 */
		this.search_dfs(SgArray, initStateArray);
		//this.search_dfs_abstraction(SgArray, initStateArray);
		// this.search_dfs_split_zone(SgArray, initStateArray);

		while (traceCex != null && traceCex.size() > 0) {
			LPNTran lpnTran = traceCex.removeFirst();
			System.out.println(lpnTran.getLpn().getLabel() + " : " + lpnTran.getLabel());
		}

		System.out.println("Modules' local states: ");
		for (int i = 0; i < ArraySize; i++) {
			System.out.println("module " + SgArray[i].getLpn().getLabel() + ": " + SgArray[i].reachSize());
		}

		long elapsedTimeMillis = System.currentTimeMillis() - start;
		float elapsedTimeSec = elapsedTimeMillis / 1000F;

		System.out.println("---> total runtime: " + elapsedTimeSec + " sec\n");// </editor-fold>
	}

	private void search_dfs(StateGraph[] lpnList, State[] initStateArray) {
		System.out.println("---> Calling TimingAnalysis.search_dfs");

		int zoneType = 0;

		int arraySize = lpnList.length;
		int max_stack_depth = 0;
		long peakTotalMem = 0;
		long peakUsedMem = 0;
		boolean failure = false;

		/*
		 * Create objects to hold state space information
		 */
		HashMap<PrjState, HashSet<DBM>> prjStateZoneSet = new HashMap<PrjState, HashSet<DBM>>();
		// /*
		// * Compute the set of input transitions for each module
		// */
		// HashSet[] inputTranSetArray = new HashSet[arraySize];
		// for(int i = 0; i<arraySize; i++) {
		// LPN curLpn = lpnList[i];
		// HashSet<LPNTran> curInputTranSet = new HashSet<LPNTran>();
		// HashSet<String> inputVarSet = curLpn.getInputs();
		// for(int other_i = 0; other_i <arraySize; other_i++) {
		// if(i == other_i) continue;
		// LPNTranSet otherTranSet = lpnList[other_i].getTransitions();
		// for(LPNTran other_tran : otherTranSet) {
		// HashSet<String> assignedVarSet = other_tran.getAssignedVar();
		// for(String assignedVar : assignedVarSet)
		// if(inputVarSet.contains(assignedVar)==true) {
		// curInputTranSet.add(other_tran);
		// break;
		// }
		// }
		// }
		// inputTranSetArray[i] = curInputTranSet;
		// }
		//
		// for(int i = 0; i<arraySize; i++) {
		// for(LPNTran tran : (HashSet<LPNTran>)inputTranSetArray[i]) {
		// System.out.print(tran.getFullLabel() + "  ");
		// }
		// System.out.println("---------------------");
		// }

		/*
		 * Compute the untimed enabled transition arrays in the initial state
		 */
		LpnTranList[] initEnabledArray = new LpnTranList[arraySize];
		ArrayList<LinkedList<LPNTran>> enabledArrayList = new ArrayList<LinkedList<LPNTran>>();
		for (int i = 0; i < arraySize; i++) {
			LpnTranList tmp = lpnList[i].getEnabled(initStateArray[i], true);
			initEnabledArray[i] = tmp;
			enabledArrayList.add(i, tmp);
		}

		/*
		 * Compute the initial zone, project the zone to each module, and
		 * enlarge the zone corresponding to the zone projection.
		 */
		Zone1 initZone = new Zone1();
		initZone.initialize(initEnabledArray);

		/*
		 * Compute the timed enabled transitions in the initial state
		 */
		LpnTranList initTimedEnabled = new LpnTranList();
		for (int i = 0; i < arraySize; i++) {
			for (LPNTran tran : initEnabledArray[i])
				if (initZone.checkTiming(tran) == true)
					initTimedEnabled.addLast(tran);
		}

		if (initTimedEnabled.size() == 0) {
			System.err
					.println("---> ERROR: Verification failed: deadlock in the initial state.");
			failure = true;
			return;
		}

		/*
		 * Initializing the stacks needed for search.
		 */
		Stack<State[]> stateStack = new Stack<State[]>();
		Stack<Zone1> zone1Stack = new Stack<Zone1>();
		Stack<Poset> posetStack = new Stack<Poset>();
		Stack<LpnTranList> lpnTranStack = new Stack<LpnTranList>();

		stateStack.push(initStateArray);
		zone1Stack.push(initZone);
		posetStack.push(null);
		lpnTranStack.push(initTimedEnabled);

		PrjState initPrjState = new PrjState(initStateArray);
		HashSet initStateZoneSet = prjStateZoneSet.get(initPrjState);
		if (initStateZoneSet == null) {
			initStateZoneSet = new HashSet<DBM>();
			initStateZoneSet.add(initZone.getDbm());
			prjStateZoneSet.put(initPrjState, initStateZoneSet);
		} else
			initStateZoneSet.add(initZone.getDbm());

		int tranFiringCnt = 0;
		int iterations = 0;

		/*
		 * Main search loop.
		 */
		main_while_loop: while (failure == false && stateStack.empty() == false) {
			iterations++;

			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory()
					- Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			// if(iterations == 200) System.exit(0);
			if (iterations % 2000 == 0) {
				System.out.println("---> #iteration " + iterations
						+ "> # LPN transition firings: " + tranFiringCnt
						+ ", # of zones: " + Zone1.uniqueCache.size()
						+ ", max_stack_depth: " + max_stack_depth
						+ " used memory: " + (float) curUsedMem / 1000000
						+ " free memory: "
						+ (float) Runtime.getRuntime().freeMemory() / 1000000);
			}

			if (stateStack.size() > max_stack_depth) {
				max_stack_depth = stateStack.size();
			}

			State[] curStateArray = stateStack.peek();
			Zone1 curZone = zone1Stack.peek();
			Poset curPoset = posetStack.peek();
			LpnTranList curTimedEnabled = lpnTranStack.peek();

			if (curTimedEnabled.size() == 0) {
				stateStack.pop();
				zone1Stack.pop();
				posetStack.pop();
				lpnTranStack.pop();
				continue main_while_loop;
			}

			LPNTran firedTran = curTimedEnabled.removeLast();

			// System.out.println("firedTran " + firedTran.getFullLabel());

			State[] nextStateArray = firedTran.fire(lpnList, curStateArray);
			tranFiringCnt++;

			LpnTranList[] curEnabledArray = new LpnTranList[arraySize];
			LpnTranList[] nextEnabledArray = new LpnTranList[arraySize];
			for (int i = 0; i < arraySize; i++) {
				StateGraph lpn_tmp = lpnList[i];
				LpnTranList enabledList = null;
				enabledList = lpn_tmp.getEnabled(curStateArray[i], true);
				curEnabledArray[i] = enabledList;
				enabledList = lpn_tmp.getEnabled(nextStateArray[i], true);
				nextEnabledArray[i] = enabledList.clone();
			}

			// System.out.println(curZone);
			// System.out.println("\nfiredTran = " + firedTran.getFullLabel() +
			// "\n");
			Poset nextPoset = null;
			if (curPoset == null) {
				nextPoset = new Poset();
				nextPoset.initialize(firedTran, nextEnabledArray);
			} else {
				nextPoset = curPoset.update(firedTran, curEnabledArray,
						nextEnabledArray);
			}

			// System.out.println(nextPoset);

			Zone1 nextZone = curZone.update(firedTran, nextEnabledArray);
			// nextZone.enlarge(nextEnabledArray, inputTranSetArray);
			// Zone1 pZone = new Zone1(nextPoset, nextEnabledArray);

			// System.out.println(nextZone +
			// "\n---------------------------------\n");// + pZone +
			// "\n========================================\n");

			// /*
			// * Check if nextZone already exists in the cache. If so, get the
			// existing copy.
			// */
			// Zone1 newNextZone1 = Zone1.uniqueCache.get(nextZone);
			// if(newNextZone1 == null) {
			// Zone1.uniqueCache.put(nextZone, nextZone);
			// System.out.println(nextZone + "\n " + Zone1.uniqueCache.size() +
			// "\n---------------------------------\n");
			// }
			// else {
			// nextZone = newNextZone1;
			// }

			/*
			 * Compute the timed enabled transitions w.r.t nextZone.
			 */
			LpnTranList nextTimedEnabled = new LpnTranList();
			for (int i = 0; i < arraySize; i++) {
				LpnTranList tmp = nextEnabledArray[i];
				for (LPNTran tran : tmp) {
					if (nextZone.checkTiming(tran) == true)
						nextTimedEnabled.addLast(tran);
				}
			}

			/*
			 * Check disabling error
			 */
			LPNTran disabledTran = firedTran.disablingError1(curTimedEnabled, nextTimedEnabled);
			if (disabledTran != null) {
				System.out.println("---> Disabling Error: "
						+ disabledTran.getFullLabel() + " is disabled by "
						+ firedTran.getFullLabel());

				System.out.println("Current state:");
				for (int ii = 0; ii < arraySize; ii++) {
					System.out.println("module " + lpnList[ii].getLpn().getLabel());
					System.out.println(curStateArray[ii]);
					System.out.println("Enabled set: " + curEnabledArray[ii]);
				}
				System.out.println("Timed enabled transitions: "
						+ curTimedEnabled);

				System.out.println("======================\nNext state:");
				for (int ii = 0; ii < arraySize; ii++) {
					System.out.println("module " + lpnList[ii].getLpn().getLabel());
					System.out.println(nextStateArray[ii]);
					System.out.println("Enabled set: " + nextEnabledArray[ii]);
				}
				System.out.println("Timed enabled transitions: "
						+ nextTimedEnabled);

				System.out.println();

				failure = true;
				break main_while_loop;
			}

			/*
			 * Check deadlock.
			 */
			if (nextTimedEnabled.size() == 0) {
				System.out.println("---> ERROR: Verification failed: deadlock.");
				for (int ii = 0; ii < arraySize; ii++) {
					System.out.println("module " + lpnList[ii].getLpn().getLabel());
					System.out.println(nextStateArray[ii]);
					System.out.println("Enabled set: " + nextEnabledArray[ii]);
				}
				System.out.println("Zone: " + nextZone);
				failure = true;
				continue main_while_loop;
			}

			/*
			 * Add the new timed state, and check if this is a new timed state.
			 * Zone subset relation is checked to reduce redundant zones.
			 */
			boolean isNew = false;
			PrjState nxtPrjState = new PrjState(nextStateArray);
			HashSet<DBM> nxtStateZoneSet = prjStateZoneSet.get(nxtPrjState);
			if (nxtStateZoneSet == null) {
				nxtStateZoneSet = new HashSet<DBM>();
				nxtStateZoneSet.add(nextZone.getDbm());
				prjStateZoneSet.put(nxtPrjState, nxtStateZoneSet);
				isNew = true;
			} 
			else {
				LinkedList<DBM> subsetDBMs = new LinkedList<DBM>();
				boolean isSubset = false;
				for (Object old : nxtStateZoneSet) {
					DBM oldDbm = (DBM) old;
					if (isSubset == false && nextZone.getDbm().subset(oldDbm) == true)
						isSubset = true;
					else if (oldDbm.subset(nextZone.getDbm()) == true)
						subsetDBMs.addLast(oldDbm);
				}

				if (isSubset == false) {
					nxtStateZoneSet.add(nextZone.getDbm());
					isNew = true;
				}

				for (DBM d : subsetDBMs)
					nxtStateZoneSet.remove(d);
			}

			if (isNew == true) {
				stateStack.push(nextStateArray);
				zone1Stack.push(nextZone);
				posetStack.push(nextPoset);
				lpnTranStack.push(nextTimedEnabled);
			}
		}

		int dbmCnt = 0;
		Set<PrjState> stateSet = prjStateZoneSet.keySet();
		HashSet<DBM> allDbmSet = new HashSet<DBM>();
		for (PrjState curState : stateSet) {
			HashSet<DBM> curDbmSet = prjStateZoneSet.get(curState);
			for (DBM dbm_i : curDbmSet)
				allDbmSet.add(dbm_i);
		}
		dbmCnt = allDbmSet.size();
		int untimedStateCnt = prjStateZoneSet.size();

		System.out.println("---> # of untimed states = " + untimedStateCnt
				+ "\n" + "---> # of unique zones = " + Zone1.uniqueCache.size()
				+ "\n" + "---> # of unique enabled arrays = "
				+ Zone1.enabledArrayCache.size() + "\n"
				+ "---> # of unique DBMs = " + dbmCnt + "\n");

		System.out.println("SUMMARY: # LPN transition firings: "
				+ tranFiringCnt + ", max_stack_depth: " + max_stack_depth);
	}

	private void search_dfs_abstraction(StateGraph[] lpnList, State[] initStateArray) {
		System.out.println("---> Calling timedAnalysis.findsg_dfs");

		int arraySize = lpnList.length;
		int max_stack_depth = 0;
		long peakTotalMem = 0;
		long peakUsedMem = 0;
		boolean failure = false;
		boolean useMDT = false;  // switch between prjStateSet and reachSet fto store states

		/*
		 * Create objects to hold state space information
		 */
		HashMap<PrjState, DBM> prjStateSet = null;
		mdtNode reachSet = null;
		if(useMDT==true)
			reachSet = new mdtNode();
		else
			prjStateSet = new HashMap<PrjState, DBM>();

		// /*
		// * Compute the set of input transitions for each module
		// */
		// HashSet[] inputTranSetArray = new HashSet[arraySize];
		// for(int i = 0; i<arraySize; i++) {
		// LPN curLpn = lpnList[i];
		// HashSet<LPNTran> curInputTranSet = new HashSet<LPNTran>();
		// HashSet<String> inputVarSet = curLpn.getInputs();
		// for(int other_i = 0; other_i <arraySize; other_i++) {
		// if(i == other_i) continue;
		// LPNTranSet otherTranSet = lpnList[other_i].getTransitions();
		// for(LPNTran other_tran : otherTranSet) {
		// HashSet<String> assignedVarSet = other_tran.getAssignedVar();
		// for(String assignedVar : assignedVarSet)
		// if(inputVarSet.contains(assignedVar)==true) {
		// curInputTranSet.add(other_tran);
		// break;
		// }
		// }
		// }
		// inputTranSetArray[i] = curInputTranSet;
		// }
		//
		// for(int i = 0; i<arraySize; i++) {
		// for(LPNTran tran : (HashSet<LPNTran>)inputTranSetArray[i]) {
		// System.out.print(tran.getFullLabel() + "  ");
		// }
		// System.out.println("---------------------");
		// }

		/*
		 * Compute the untimed enabled transition arrays in the initial state
		 */
		LpnTranList[] initEnabledArray = new LpnTranList[arraySize];
		ArrayList<LinkedList<LPNTran>> enabledArrayList = new ArrayList<LinkedList<LPNTran>>();
		for (int i = 0; i < arraySize; i++) {
			LpnTranList tmp = lpnList[i].getEnabled(initStateArray[i], true);
			initEnabledArray[i] = tmp;
			enabledArrayList.add(i, tmp);
		}

		/*
		 * Compute the initial zone, project the zone to each module, and
		 * enlarge the zone corresponding to the zone projection.
		 */
		Zone1 initZone = new Zone1();
		initZone.initialize(initEnabledArray);

		/*
		 * Compute the timed enabled transitions in the initial state
		 */
		LpnTranList initTimedEnabled = new LpnTranList();
		for (int i = 0; i < arraySize; i++) {
			for (LPNTran tran : initEnabledArray[i])
				if (initZone.checkTiming(tran) == true)
					initTimedEnabled.addLast(tran);
		}

		if (initTimedEnabled.size() == 0) {
			System.err
					.println("---> ERROR: Verification failed: deadlock in the initial state.");
			failure = true;
			return;
		}

		/*
		 * Initializing the stacks needed for search.
		 */
		Stack<State[]> stateStack = new Stack<State[]>();
		Stack<Zone1> zone1Stack = new Stack<Zone1>();
		Stack<Poset> posetStack = new Stack<Poset>();
		Stack<LpnTranList> lpnTranStack = new Stack<LpnTranList>();

		stateStack.push(initStateArray);
		zone1Stack.push(initZone);
		posetStack.push(null);
		lpnTranStack.push(initTimedEnabled);

		PrjState initPrjState = new PrjState(initStateArray);
		if (useMDT==true)
			reachSet.merge(initStateArray, initZone.getDbm(), 0);
		else
			prjStateSet.put(initPrjState, initZone.getDbm());

		int tranFiringCnt = 0;
		int iterations = 0;

		/*
		 * Main search loop.
		 */
		main_while_loop: while (failure == false && stateStack.empty() == false) {
			iterations++;

			long curTotalMem = Runtime.getRuntime().totalMemory();
			long curUsedMem = Runtime.getRuntime().totalMemory()
					- Runtime.getRuntime().freeMemory();

			if (curTotalMem > peakTotalMem)
				peakTotalMem = curTotalMem;

			if (curUsedMem > peakUsedMem)
				peakUsedMem = curUsedMem;

			// if(iterations == 200) System.exit(0);
			if (iterations % 2000 == 0) {
				System.out.println("---> #iteration " + iterations
						+ "> # LPN transition firings: " + tranFiringCnt
						+ ", # of prjStates: " + (useMDT ? reachSet.pathCount() : prjStateSet.size())
						+ ", # of zones: " + Zone1.uniqueCache.size()
						+ ", max_stack_depth: " + max_stack_depth
						+ " used memory: " + (float) curUsedMem / 1000000
						+ " free memory: "
						+ (float) Runtime.getRuntime().freeMemory() / 1000000);
			}

			if (stateStack.size() > max_stack_depth) {
				max_stack_depth = stateStack.size();
			}

			State[] curStateArray = stateStack.peek();
			Zone1 curZone = zone1Stack.peek();
			PrjState curPrjState = new PrjState(curStateArray);
			DBM curExistingDbm = null;
			if (useMDT==true)
				curExistingDbm = reachSet.getDbm(curStateArray, 0);
			else
				curExistingDbm = prjStateSet.get(curPrjState);
			boolean curZoneUseless = (curZone.getDbm().equals(curExistingDbm) == false && curZone.getDbm().subset(curExistingDbm) == true);
			Poset curPoset = posetStack.peek();
			LpnTranList curTimedEnabled = lpnTranStack.peek();

			if (curTimedEnabled.size() == 0 || curZoneUseless==true) {
				stateStack.pop();
				zone1Stack.pop();
				posetStack.pop();
				lpnTranStack.pop();
				continue main_while_loop;
			}

			LPNTran firedTran = curTimedEnabled.removeLast();

//			for(int i = 0; i < curStateArray.length; i++)
//				System.out.print(curStateArray[i] + ", ");
//			System.out.println();
//			System.out.println("firedTran " + firedTran.getFullLabel());

			int curIndex = firedTran.getLpn().getIndex();
			State[] nextStateArray = firedTran.fire(lpnList, curStateArray);
			tranFiringCnt++;

			LpnTranList[] curEnabledArray = new LpnTranList[arraySize];
			LpnTranList[] nextEnabledArray = new LpnTranList[arraySize];
			for (int i = 0; i < arraySize; i++) {
				StateGraph lpn_tmp = lpnList[i];
				LpnTranList enabledList = null;
				enabledList = lpn_tmp.getEnabled(curStateArray[i], true);
				curEnabledArray[i] = enabledList;
				enabledList = lpn_tmp.getEnabled(nextStateArray[i], true);
				nextEnabledArray[i] = enabledList.clone();
			}

			// System.out.println(curZone);
			// System.out.println("\nfiredTran = " + firedTran.getFullLabel() +
			// "\n");
			Poset nextPoset = null;
			if (curPoset == null) {
				nextPoset = new Poset();
				nextPoset.initialize(firedTran, nextEnabledArray);
			} else {
				nextPoset = curPoset.update(firedTran, curEnabledArray,
						nextEnabledArray);
			}

			// System.out.println(nextPoset);

			Zone1 nextZone = curZone.update(firedTran, nextEnabledArray);
			// nextZone.enlarge(nextEnabledArray, inputTranSetArray);
			// Zone1 pZone = new Zone1(nextPoset, nextEnabledArray);

			// System.out.println(nextZone +
			// "\n---------------------------------\n");// + pZone +
			// "\n========================================\n");

			// /*
			// * Check if nextZone already exists in the cache. If so, get the
			// existing copy.
			// */
			// Zone1 newNextZone1 = Zone1.uniqueCache.get(nextZone);
			// if(newNextZone1 == null) {
			// Zone1.uniqueCache.put(nextZone, nextZone);
			// System.out.println(nextZone + "\n " + Zone1.uniqueCache.size() +
			// "\n---------------------------------\n");
			// }
			// else {
			// nextZone = newNextZone1;
			// }

			/*
			 * Compute the timed enabled transitions w.r.t nextZone.
			 */
			LpnTranList nextTimedEnabled = new LpnTranList();
			for (int i = 0; i < arraySize; i++) {
				LpnTranList tmp = nextEnabledArray[i];
				for (LPNTran tran : tmp) {
					if (nextZone.checkTiming(tran) == true)
						nextTimedEnabled.addLast(tran);
				}
			}

			/*
			 * Check disabling error
			 */
			LPNTran disabledTran = firedTran.disablingError1(curTimedEnabled, nextTimedEnabled);
			if (disabledTran != null) {
				System.out.println("---> Disabling Error: "
						+ disabledTran.getFullLabel() + " is disabled by "
						+ firedTran.getFullLabel());

				System.out.println("Current state:");
				for (int ii = 0; ii < arraySize; ii++) {
					System.out.println("module " + lpnList[ii].getLpn().getLabel());
					System.out.println(curStateArray[ii]);
					System.out.println("Enabled set: " + curEnabledArray[ii]);
				}
				System.out.println("Timed enabled transitions: "
						+ curTimedEnabled);

				System.out.println("======================\nNext state:");
				for (int ii = 0; ii < arraySize; ii++) {
					System.out.println("module " + lpnList[ii].getLpn().getLabel());
					System.out.println(nextStateArray[ii]);
					System.out.println("Enabled set: " + nextEnabledArray[ii]);
				}
				System.out.println("Timed enabled transitions: "
						+ nextTimedEnabled);

				System.out.println();

				failure = true;
				break main_while_loop;
			}

			/*
			 * Check deadlock.
			 */
			if (nextTimedEnabled.size() == 0) {
				System.out
						.println("---> ERROR: Verification failed: deadlock.");
				for (int ii = 0; ii < arraySize; ii++) {
					System.out.println("module " + lpnList[ii].getLpn().getLabel());
					System.out.println(nextStateArray[ii]);
					System.out.println("Enabled set: " + nextEnabledArray[ii]);
				}
				System.out.println("Zone: " + nextZone);
				failure = true;
				continue main_while_loop;
			}

			/*
			 * Add the new timed state, and check if this is a new timed state.
			 * Zone subset relation is checked to reduce redundant zones.
			 */
			boolean isNew = false;

			PrjState nextPrjState = new PrjState(nextStateArray);
			DBM existingDBM = null;
			if (useMDT==true)
				existingDBM = reachSet.getDbm(nextStateArray, 0);
			else
				existingDBM = prjStateSet.get(nextPrjState);
			if (existingDBM == null) {
				if (useMDT==true)
					reachSet.merge(nextStateArray, nextZone.getDbm(), 0);
				else
					prjStateSet.put(nextPrjState, nextZone.getDbm());
				isNew = true;
			} 
			else if (nextZone.getDbm().subset(existingDBM)==false) {
				DBM newDbm = existingDBM.merge(nextZone.getDbm());
				Zone1 newNextZone = new Zone1(nextZone.getEnabledSet(), newDbm);
				nextZone = newNextZone;
				if (useMDT==true)
					reachSet.merge(nextStateArray, newDbm, 0);
				else
					prjStateSet.put(nextPrjState, newDbm);
				isNew = true;
			}

			if (isNew == true) {
				stateStack.push(nextStateArray);
				zone1Stack.push(nextZone);
				posetStack.push(nextPoset);
				lpnTranStack.push(nextTimedEnabled);
			}
		}

		System.out.println("SUMMARY:\n"
				+ "# LPN transition firings: "
				+ tranFiringCnt + ", # of prjStates found: " + (useMDT ? reachSet.pathCount() : prjStateSet.size()) + ", "
				+ ", max_stack_depth: "
				+ max_stack_depth + "\n" 
				+ "---> # of unique zones = "
				+ Zone1.uniqueCache.size() + "\n"
				+ "---> # of unique enabled arrays = "
				+ Zone1.enabledArrayCache.size() + "\n"
				+ "---> # of unique DBMs = " + (useMDT ? reachSet.pathCount() : prjStateSet.size()) + "\n"
				+ "---> # of unique timeSeps = " + Zone1.timeSepTbl.size()
				+ "\n");
	}
}
