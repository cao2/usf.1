package platu.logicAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import platu.common.DualHashMap;
import platu.expression.VarNode;
import platu.lpn.LPN;
import platu.lpn.LPNTran;
import platu.stategraph.State;

/**
 * Class used to represent the constraints between LPN modules.
 * This object is used to represent the constraint from an LPN onto one other LPN over a common interface.
 */
public class Constraint{
	private LPN lpn; // LPN the constraint belongs
	final private LPNTran lpnTransition; // LPNTran modifying output variable
	final private int[] vector; // state vector of the start state where the LPNTran is fired
	List<VarNode> variableList = new ArrayList<VarNode>(1); // list of output variables modified
	List<Integer> valueList = new ArrayList<Integer>(1); // list of new output variable values
	private int hashVal = -1; // object hash value
	
	// values, from the start state, of the common variables with the destination LPN
	final private int[] interfaceValues; 

	/**
	 * Creates and initializes the constraint object between the source LPN and one of the destination LPNs.
	 * @param start - Start state
	 * @param end - End state
	 * @param tran - Fired LPNTran
	 * @param dstLpn - LPN module that reads a modified variable
	 * @param commonIndexArray - Array of the index values for the common interface values
	 */
	public Constraint(State start, State end, LPNTran tran, LPN dstLpn, int[] commonIndexArray) {
	    this.lpnTransition = tran;
		this.lpn = tran.getLpn();
		this.vector = start.getVector();
		
		// state vector of the LPNTran's end state
		int[] endVector = end.getVector();
		
		// Constraint's LPN varIndexMap
		DualHashMap<String, Integer> varIndexMap = this.lpn.getVarIndexMap();

		// array of common variable values
		this.interfaceValues = new int[commonIndexArray.length];
		
		// go through the common interface
		for(int i = 0; i < commonIndexArray.length; i++){
			// index of the common variable
			int varIndex = commonIndexArray[i];
			
			// start state's variable value
			this.interfaceValues[i] = this.vector[varIndex];
			
			// add modified variable and new value to respective lists
			if(this.vector[varIndex] != endVector[varIndex]){
				String variable = varIndexMap.getKey(varIndex);
				this.valueList.add(endVector[varIndex]);
				this.variableList.add(dstLpn.getVarNode(variable));
			}
		}
	}
	
	/**
	 * Returns a list containing the variables that were modified.
     * @return List of modified variables
     */
	public List<VarNode> getVariableList(){
		return this.variableList;
	}
	
	/**
	 * Returns a list containing the new values of the variables that were modified.
     * @return List of new variable values
     */
	public List<Integer> getValueList(){
		return this.valueList;
	}
	
	/**
	 * Returns the state vector of the start state.
     * @return State vector.
     */
	public int[] getVector(){
		return this.vector;
	}
	
	/**
	 * Returns the LPN where the constraint was generated.
     * @return LPN
     */
	public LPN getLpn(){
		return this.lpn;
	}

	/**
	 * Returns the start state's values of the common interface
     * @return Values of the interface variables
     */
	public int[] getInterfaceValue(){
		return this.interfaceValues;
	}
	
	/**
	 * Returns the LPNTran that was fired.
     * @return LPNTran
     */
    public LPNTran getLpnTransition(){
    	return this.lpnTransition;
    }

	@Override
	public int hashCode() {
		if(this.hashVal == -1){
			final int prime = 31;
			this.hashVal = 1;
			this.hashVal = prime * this.hashVal + Arrays.hashCode(interfaceValues);
//			this.hashVal = prime * this.hashVal + ((this.lpn == null) ? 0 : this.lpn.getLabel().hashCode());
			this.hashVal = prime * this.hashVal + ((this.lpnTransition == null) ? 0 : this.lpnTransition.hashCode());
			this.hashVal = prime * this.hashVal + this.valueList.hashCode();
		}
		
		return this.hashVal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Constraint other = (Constraint) obj;
		if (!Arrays.equals(this.interfaceValues, other.interfaceValues))
			return false;
		
//		if (this.lpn == null) {
//			if (other.lpn != null)
//				return false;
//		} 
//		else if (!this.lpn.equals(other.lpn))
//			return false;
		
		if (this.lpnTransition == null) {
			if (other.lpnTransition != null)
				return false;
		} 
		else if (!this.lpnTransition.equals(other.lpnTransition))
			return false;
		
		if(!this.valueList.equals(other.valueList))
			return false;
		
		return true;
	}    
}
