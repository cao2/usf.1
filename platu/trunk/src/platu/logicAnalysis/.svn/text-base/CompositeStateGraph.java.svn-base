package platu.logicAnalysis;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import platu.lpn.LPN;
import platu.lpn.LPNTran;
import platu.main.Main;
import platu.main.Options;
import platu.main.Options.SearchTypeDef;
import platu.stategraph.State;
import platu.stategraph.StateError;
import platu.stategraph.StateGraph;
import platu.stategraph.StateTran;

public class CompositeStateGraph {	
	/**
	 * Set of states
	 */
	private Map<CompositeState, CompositeState> stateMap = new HashMap<CompositeState, CompositeState>();
	
	/**  
	 * Array containing the module state graphs composing this composite state graph
	 */
	private StateGraph[] stateGraphArray = null;
	
	/**
	 * Initial state
	 */
	private CompositeState initState = null;
	
	/**
	 * Label built from the module state graph labels
	 */
	private String label = "";
	
	private int stateIndex = 0;
	
	/**
	 * Constructor
	 * @param initialState - Composite initial state
	 * @param sgArray - Array of module state graphs composing this composite state graph.
	 */
	public CompositeStateGraph(CompositeState initialState, StateGraph[] sgArray){
		this.initState = initialState;
		this.stateGraphArray = sgArray;
		
		for(int i = 0; i < sgArray.length; i++){
			label += sgArray[i].getLpn().getLabel();
			
			if(i < sgArray.length - 1){
				label += "||";
			}
		}
		
		this.addState(this.initState);
	}
	
	/**
	 * Constructor
	 * @param sg - module state graph to convert into a composite state graph
	 */
	public CompositeStateGraph(StateGraph sg){
		int[] initStateArray = new int[1];
		initStateArray[0] = sg.getInitialState().getIndex();
		CompositeState init = new CompositeState(initStateArray);
		if(sg.getInitialState().getErrorCode() != StateError.NONE){
			init.setErrorCode(sg.getInitialState().getErrorCode());
		}
		
		StateGraph[] sgArray = new StateGraph[1];
		sgArray[0] = sg;
		
		CompositeState[] compositeStateArray = new CompositeState[sg.reachSize()];
		
		// initialize attributes
		this.initState = init;
		this.stateGraphArray = sgArray;
		
		// construct csg's label
		int size = 0;
		for(int i = 0; i < sgArray.length; i++){
			label += sgArray[i].getLpn().getLabel();
			
			if(i < sgArray.length - 1){
				label += "||";
			}
			
			size *= sgArray[i].reachSize();
		}
		
		/*
		 * Add the initial state
		 */
		this.addState(this.initState);
		compositeStateArray[0] = this.initState;
		
		/*
		 * Add the remaining states
		 */
		for(int i = 1; i < sg.reachSize(); i++){
			State currentState = sg.getState(i);
			int[] currentStateArray = new int[1];
			currentStateArray[0] = currentState.getIndex();

			CompositeState currentCompositeState = new CompositeState(currentStateArray);
			this.addState(currentCompositeState);
			compositeStateArray[i] = currentCompositeState;
			
			StateError errorCode = currentState.getErrorCode();
			if(errorCode != StateError.NONE){
				currentCompositeState.setErrorCode(errorCode);
			}
		}
		
		/*
		 * Add the state transitions
		 */
		for(StateTran stTran : sg.getStateTranMap().values()){
			CompositeState currentState = compositeStateArray[stTran.getCurrentState().getIndex()];			
			CompositeState nextState = compositeStateArray[stTran.getNextState().getIndex()];
			LPNTran lpnTran = stTran.getLpnTran();
			
			CompositeStateTran newStateTran = new CompositeStateTran(currentState, lpnTran, nextState);
			this.addStateTran(newStateTran);
			if(!lpnTran.local()){
				newStateTran.setVisibility();
			}
		}
		
		/*
		 * Check for dead lock states
		 */
		for(int i = 0; i < this.numStates(); i++){
			CompositeState currentState = compositeStateArray[i];
			if(currentState.getErrorCode() == StateError.NONE && currentState.numOutgoingTrans() == 0){
				currentState.setErrorCode(StateError.DEADLOCK);
			}
		}
	}
	
	/**
	 * Returns the array of module StateGraphs composing this CompositeStateGraph.
	 */
	public StateGraph[] getStateGraphArray(){
		return this.stateGraphArray;
	}
	
	/**
	 * Returns the set of LPN transitions enabled at the specified CompositeState.
	 */
	public List<LPNTran> getEnabled(CompositeState currentState){
		Set<LPNTran> lpnTranSet = new HashSet<LPNTran>(currentState.numOutgoingTrans());
		List<LPNTran> enabled = new ArrayList<LPNTran>(currentState.numOutgoingTrans());
		
		List<CompositeStateTran> outgoingTrans = currentState.getOutgoingStateTranList();
		for(int i=0; i<outgoingTrans.size(); i++){
			CompositeStateTran stTran = outgoingTrans.get(i);
			
			LPNTran lpnTran = stTran.getLPNTran();
			if(lpnTranSet.add(lpnTran))
				enabled.add(lpnTran);
		}
		
		return enabled;
	}
	
	/**
	 * Adds a state transition to the composite state graph.
	 * @param currentState - Current state
	 * @param nextState - Next state
	 * @param lpnTran - Fired LPN transition
	 * @return Existing state transition if not added, otherwise the new state transition
	 */
	public CompositeStateTran addStateTran(CompositeState currentState, CompositeState nextState, LPNTran lpnTran){
		CompositeStateTran stateTran = new CompositeStateTran(currentState, lpnTran, nextState);
		
		int index = currentState.getOutgoingStateTranList().indexOf(stateTran);
		if(index >= 0){
			return currentState.getOutgoingStateTranList().get(index);
		}
		
		currentState.addOutgoingStateTran(stateTran);
		nextState.addIncomingStateTran(stateTran);

		if(currentState == nextState){
			currentState.setContainsLoopFlag();
		}
		
		return stateTran;
	}
	
	/**
	 * Adds a state transition to the composite state graph.
	 * @param currentState - Current state
	 * @param nextState - Next state
	 * @param lpnTran - Fired LPN transition
	 * @return Existing state transition if not added, otherwise the new state transition
	 */
	public CompositeStateTran addStateTran(CompositeStateTran stateTran){
		CompositeState currentState = stateTran.getCurrentState();
		CompositeState nextState = stateTran.getNextState();
		
		int index = currentState.getOutgoingStateTranList().indexOf(stateTran);
		if(index >= 0){
			return currentState.getOutgoingStateTranList().get(index);
		}
		
		currentState.addOutgoingStateTran(stateTran);
		nextState.addIncomingStateTran(stateTran);

		if(currentState == nextState){
			currentState.setContainsLoopFlag();
		}
		
		return stateTran;
	}
	
	/**
	 * Removes a state transition from the composite state graph.
	 * @param stateTran - State transition to remove
	 */
	public void removeStateTran(CompositeStateTran stateTran){
		CompositeState currentState = stateTran.getCurrentState();
		CompositeState nextState = stateTran.getNextState();

		currentState.removeOutgoingStateTran(stateTran);
		nextState.removeIncomingStateTran(stateTran);
	}
	
	/**
	 * Removes a composite state from the composite state graph.
	 * @param st - Composite state to remove
	 * @return TRUE if removed, otherwise FALSE
	 */
	public boolean removeState(CompositeState st){
		CompositeState retState = this.stateMap.remove(st);
		if(retState == null){
			return false;
		}
		
		return true;
	}
	
	public List<CompositeStateTran> getOutgoingStateTrans(CompositeState s){
		return null;
	}
	
	public List<CompositeStateTran> getIncomingStateTrans(CompositeState s){
		return null;
	}
	
	/**
	 * Returns the set of states in the composite state graph.
	 */
	public Set<CompositeState> getStateSet(){
		return this.stateMap.keySet();
	}
	
	/**
	 * Returns the number of module state graphs composing this composite state graph.
	 */
	public int getSize(){
		return this.stateGraphArray.length;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	/**
     * Adds a composite state to the composite state graph
     * @param st - CompositeState to be added
     * @return Equivalent CompositeState object it exists, otherwise CompositeState st.
     */
	public CompositeState addState(CompositeState st){
		CompositeState retState = this.stateMap.get(st);
		if(retState == null){
			int index = 0;
			if(Options.getSearchType() == SearchTypeDef.CRA_GLOBAL){
				index = this.stateIndex++;
			}
			else{
				index = this.stateMap.size();
			}
			
			st.setIndex(index);
			
			this.stateMap.put(st, st);
			
			return st;
		}
		
		return retState;
	}
	
	/**
	 * Returns the initial state.
	 */
	public final CompositeState getInitState(){
		return this.initState;
	}
	
	public void setInitState(CompositeState init){
		this.initState = init;
	}
	
	/**
	 * Returns the number of states in the composite state graph.
	 */
	public int numStates(){
		return this.stateMap.size();
	}
	
	/**
	 * Returns the number of state transitions in the composite state graph.
	 */
	public int numStateTrans(){		
		int numTrans = 0;
		for(CompositeState state : this.stateMap.keySet()){
			numTrans += state.numOutgoingTrans();
		}
		
		return numTrans;
	}
	
	public boolean containsLpn(LPN lpn){
		for(int i=0; i < this.stateGraphArray.length; i++){
			if(this.stateGraphArray[i].getLpn() == lpn){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether the specified state or an equivalent state is contained in the composite state graph.
	 * @param state - Composite state to check
	 * @return TRUE if contained, otherwise FALSE
	 */
	public boolean containsState(CompositeState state){
		return this.stateMap.containsKey(state);
	}
	
	/**
	 * Checks whether the specified state transition or an equivalent state transition 
	 * is contained in the composite state graph.
	 * @param stateTran - State transition to check
	 * @return TRUE if contained, otherwise FALSE
	 */
//	public boolean containsStateTran(CompositeStateTran stateTran){
//		return this.stateTranMap.containsKey(stateTran);
//	}
	
	/**
	 * Generates a DOT file from the composite state graph and 
	 * outputs it to the location specified by the DOT_PATH option.
	 */
	public void draw(){
		String dotFile = Options.getDotPath();
		if(!dotFile.endsWith("/") && !dotFile.endsWith("\\")){
			String dirSlash = "/";
			if(Main.isWindows){
				dirSlash = "\\";
			}
			
			dotFile = dotFile += dirSlash;
		}
		
		/*
		 * Windows does not allow '|' character in file names.
		 * Change to single quote character.
		 */
		String sgLabel = "";
		if(Main.isWindows){
			StringTokenizer st = new StringTokenizer(this.label, "||" );
			while(st.hasMoreTokens()){
				sgLabel += st.nextToken();
				if(st.hasMoreTokens()){
					sgLabel += "''";
				}
			}
		}
		else{
			sgLabel = this.label;
		}
		
		dotFile += sgLabel + ".dot";
    	PrintStream graph = null;
    	
		try {
			graph = new PrintStream(new FileOutputStream(dotFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	
    	graph.println("digraph SG{");
    	
    	for(CompositeState currentState : this.stateMap.keySet()){
    		String attributes = "";
    		if(currentState == this.initState){
    			attributes = " peripheries=2";
    		}
    		
    		graph.println(currentState.getIndex() + " [shape=ellipse label=\"" + currentState.getIndex() 
    				+ "\\n" + Arrays.toString(currentState.getStateTuple()) + "\"" + attributes + "]");
    		
    		for(CompositeStateTran stateTran : currentState.getOutgoingStateTranList()){
    			CompositeState nextState = stateTran.getNextState();
    			LPNTran lpnTran = stateTran.getLPNTran();

    			graph.println("  \"" + currentState.getIndex() + "\" " + " -> " + "\"" + nextState.getIndex() + "\"" + " [label=\"" + lpnTran.getFullLabel() + "\"]");
    		}
    	}
    	
    	graph.println("}");
	    graph.close();
    }
}
