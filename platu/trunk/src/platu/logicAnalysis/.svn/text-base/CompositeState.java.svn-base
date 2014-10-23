package platu.logicAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import platu.lpn.LPNTran;
import platu.stategraph.StateError;

public class CompositeState{
	// Module state graph states composing the composite state
	private int[] stateTuple = null;
	
	// list state transitions to this state
	private List<CompositeStateTran> incomingStateTranList = new ArrayList<CompositeStateTran>();
	
	// list of state transitions from this state
	private List<CompositeStateTran> outgoingStateTranList = new ArrayList<CompositeStateTran>();
	
	// state label built from the state tuple
	private String label = null;
	
	// sequential state index starting from 0
	private int index = 0;
	
	// error code indicating if an error exists and type
	private StateError errorCode = StateError.NONE;
	
	// indicates if this state contains a self loop
	private boolean containsLoop = false;
	
	
	public boolean flag = false;
	public int time = -1;
	public int time2 = -1;
	public int status = -1;
	public boolean trav = false;
	
	/**
	 * Returns the state's error code indicating if an error exists and type.
	 * @return Error code
	 */
	public StateError getErrorCode(){
		return this.errorCode;
	}
	
	/**
	 * Sets the state's error code
	 * @param errorCode - Error code
	 */
	public void setErrorCode(StateError errorCode){
		this.errorCode = errorCode;
	}
	
	/**
	 * Sets the containsLoop flag.
	 */
	public void setContainsLoopFlag(){
		this.containsLoop = true;
	}
	
	/**
	 * Returns the state's containsLoop flag.
	 * @return TRUE if the state contains a self loop, otherwise FALSE
	 */
	public boolean containsLoop(){
		return this.containsLoop;
	}
	
	/**
	 * Adds a state transition to the state's incoming transition list.
	 * @param incomingTran - State transition
	 */
	public void addIncomingStateTran(CompositeStateTran incomingTran){
		this.incomingStateTranList.add(incomingTran);
	}
	
	/**
	 * Remove a state transition from the state's incoming transition list.
	 * @param incomingTran - State transition
	 * @return TRUE if the list contained the specified state transition, otherwise FALSE
	 */
	public boolean removeIncomingStateTran(CompositeStateTran incomingTran){
		return this.incomingStateTranList.remove(incomingTran);
	}
	
	/**
	 * Removes a state transition from the state's outgoing transition set.
	 * @param outgoingTran - State transition
	 * @return TRUE if the list contained the specified state transition, otherwise FALSE
	 */
	public boolean removeOutgoingStateTran(CompositeStateTran outgoingTran){
		return this.outgoingStateTranList.remove(outgoingTran);
	}
	
	/**
	 * Returns the state's list of incoming state transitions.
	 * @return List of state transitions
	 */
	public List<CompositeStateTran> getIncomingStateTranList(){
		return this.incomingStateTranList;
	}
	
	/**
	 * Adds a state transition to the state's outgoing state transition list.
	 * @param outgoingTran - State transition
	 */
	public void addOutgoingStateTran(CompositeStateTran outgoingTran){
		this.outgoingStateTranList.add(outgoingTran);
	}
	
	/**
	 * Returns the state's list of outgoing state transitions.
	 * @return List of state transitions
	 */
	public List<CompositeStateTran> getOutgoingStateTranList(){
		return this.outgoingStateTranList;
	}
	
	/**
	 * Returns the number of incoming state transitions
	 * @return Integer value
	 */
	public int numIncomingTrans(){
		return this.incomingStateTranList.size();
	}
	
	/**
	 * Returns the number of outgoing state transitions
	 * @return Integer value
	 */
	public int numOutgoingTrans(){
		return this.outgoingStateTranList.size();
	}
	
	/**
	 * Constructor
	 * @param stateArray - Array containing the module state graph states composing this composite state
	 */
	public CompositeState(int[] stateArray){
		this.stateTuple = stateArray;
	}
	
	/**
	 * Constructor creates an empty state
	 */
	public CompositeState(){
		this.stateTuple = new int[0];
	}
	
	/**
	 * Returns a set of LPNTrans that are enabled at this composite state.
	 * @return Set of LPNTrans
	 */
	public Set<LPNTran> getEnabled(){
		HashSet<LPNTran> enabledSet = new HashSet<LPNTran>();
		
		for(int i=0; i<this.outgoingStateTranList.size(); i++){
			CompositeStateTran stateTran = this.outgoingStateTranList.get(i);
			enabledSet.add(stateTran.getLPNTran());
		}
		
		return enabledSet;
	}
	
	/**
	 * Sets the state's label.
	 * @param lbl - State label
	 */
	public void setLabel(String lbl){
		this.label = lbl;
	}
	
	/**
	 * Returns the state's label.
	 * @return State label
	 */
	public String getLabel(){
		if(this.label == null){
			this.label = "";
			
			for(int i = 0; i < this.stateTuple.length; i++){
				label += this.stateTuple[i];
				
				if(i < this.stateTuple.length - 1){
					label += ",";
				}
			}
		}
		
		return this.label;
	}
	
	/**
	 * Sets the state's sequential index.
	 * @param idx - Integer value
	 */
	public void setIndex(int idx){
		this.index = idx;
	}
	
	/**
	 * Returns the state's index value
	 * @return Integer value
	 */
	public int getIndex(){
		return this.index;
	}
	
	/**
	 * Clears the state's incoming and outgoing state transition lists.
	 */
	public void clear(){
		this.outgoingStateTranList.clear();
		this.incomingStateTranList.clear();
		this.containsLoop = false;
	}
	
	/**
	 * Clears the state's incoming state transition list.
	 */
	public void clearIncoming(){
		this.incomingStateTranList.clear();
		this.incomingStateTranList = null;
	}
	
	@Override
	public String toString(){
		return "" + getIndex();
	}
	
	/**
	 * Returns the number of module states composing this composite state.
	 * @return Integer value
	 */
	public int getSize(){
		return this.stateTuple.length;
	}
	
	/**
	 * Returns the array containing the module states composing this composite state.
	 * @return Array containing module states
	 */
	public int[] getStateTuple(){
		return this.stateTuple;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(stateTuple);
		//result = prime * result + this.errorCode.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		CompositeState other = (CompositeState) obj;
		if (!Arrays.equals(stateTuple, other.stateTuple))
			return false;
		
		return true;
	}
}
