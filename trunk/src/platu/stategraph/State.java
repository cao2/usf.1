package platu.stategraph;

import java.util.*;
import platu.common.DualHashMap;
import platu.common.PlatuObj;
import platu.lpn.*;
import platu.util.*;

public class State extends PlatuObj {
	protected int[] marking;
	protected int[] vector;
	private int hashVal = 0;
	private LPN lpnModel = null;
	private int index;
	private boolean localEnabledOnly;
	
	/*
	 * Dynamically generated information.
	 */

	private StateError errorCode = StateError.NONE;	
	
	/*
	 * Functions and operations.
	 */
	@Override
	public String toString() {
		DualHashMap<String, Integer> VarIndexMap = this.lpnModel.getVarIndexMap();

		LinkedList<Integer> stateMarking = new LinkedList<Integer>();
		for (int i = 0; i < this.marking.length; i++) 
			stateMarking.addLast(this.marking[i]);
		
		LinkedList<String> stateVec = new LinkedList<String>();
		for (int i = 0; i < vector.length; i++) {
			stateVec.addLast(VarIndexMap.getKey(i)+" = " + this.vector[i]);
		}
		if(stateVec.size() > 0)
			return this.getLpn().getLabel() + ": " + stateMarking.toString() + " + " + stateVec.toString();
		return this.getLpn().getLabel() + ": " + stateMarking.toString();
	}

	public State(final LPN thisLpn, int[] new_marking, int[] new_vector) {
		this.lpnModel = thisLpn;
		this.marking = new_marking;
		this.vector = new_vector;

		if (marking == null || vector == null) {
			new NullPointerException().printStackTrace();
		}

		Arrays.sort(this.marking);
		this.index = -1;
		//this.enabledTranTbl = new HashMap<Integer, LpnTranList>();
		localEnabledOnly = false;
	}

	public State(State other) {
		if (other == null) {
			new NullPointerException().printStackTrace();
		}

		this.lpnModel = other.lpnModel;
		this.marking = new int[other.marking.length];
		System.arraycopy(other.marking, 0, this.marking, 0,
				other.marking.length);

		this.vector = new int[other.vector.length];
		System.arraycopy(other.vector, 0, this.vector, 0, other.vector.length);

		this.index = other.index;
		this.localEnabledOnly = other.localEnabledOnly;
	}

	public State() {
		this.marking = new int[0];
		this.vector = new int[0];
		this.hashVal = 0;
		this.index = -1;
		//this.enabledTranTbl = null; //new HashMap<Integer, LpnTranList>();
		localEnabledOnly = false;
	}

	public void setErrorCode(StateError errorType) {
		this.errorCode = errorType;
	}

	public final StateError getErrorCode() {
		return this.errorCode;
	}

	public void setLpn(final LPN thisLpn) {
		this.lpnModel = thisLpn;
	}

	public LPN getLpn() {
		return this.lpnModel;
	}

	public void setLabel(String lbl) {

	}

	public String getLabel() {
		return null;
	}

	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	public int getIndex() {
		return this.index;
	}


	public Pair<State, HashMap<String, Integer>> getNextState(int gVecIdx, LPNTran firedTran) {
//		HashMap<LPNTran, Pair<State, HashMap<String, Integer>>> tran = this.nextStateTbl.get(gVecIdx);
//		if (tran != null)
//			return tran.get(firedTran);
		return null;
	}


	public boolean hasNonLocalEnabled() {
		return this.localEnabledOnly;
	}

	public void hasNonLocalEnabled(boolean nonLocalEnabled) {
		this.localEnabledOnly = nonLocalEnabled;
	}

	public static long tSum = 0;

	@Override
	public State clone() {
		State s = new State(this);
		return s;
	}

	@Override
	public int hashCode() {
		if (this.hashVal == 0) {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((this.lpnModel == null) ? 0 : this.lpnModel.getLabel()
							.hashCode());
			result = prime * result + Arrays.hashCode(marking);
			result = prime * result + Arrays.hashCode(vector);
			result = prime * result + this.errorCode.hashCode();
			this.hashVal = result;
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

		State other = (State) obj;
		if (lpnModel == null) {
			if (other.lpnModel != null)
				return false;
		} else if (!lpnModel.equals(other.lpnModel)) {
			return false;
		}

		if (!Arrays.equals(marking, other.marking))
			return false;

		if (!Arrays.equals(vector, other.vector))
			return false;

		if (this.errorCode != other.errorCode)
			return false;

		return true;
	}

	public void print(DualHashMap<String, Integer> VarIndexMap) {
		System.out.print("Marking: [");
		for (int i : marking) {
			System.out.print(i + ",");
		}
		System.out.println("]");

		System.out.print("Vector: [");
		for (int i = 0; i < vector.length; i++) {
			System.out.print(VarIndexMap.getKey(i) + "=>" + vector[i] + ", ");
		}
		System.out.println("]");
	}

	/**
	 * @return State marking
	 */
	public int[] getMarking() {
		return marking;
	}

	public void setMarking(int[] newMarking) {
		marking = newMarking;
	}

	/**
	 * @return State vector
	 */
	public int[] getVector() {
		// new Exception("StateVector getVector(): "+s).printStackTrace();
		return vector;
	}

	public HashMap<String, Integer> getOutVector(VarSet outputs,
			DualHashMap<String, Integer> VarIndexMap) {
		HashMap<String, Integer> outVec = new HashMap<String, Integer>();
		for (int i = 0; i < vector.length; i++) {
			String var = VarIndexMap.getKey(i);
			if (outputs.contains(var) == true)
				outVec.put(var, vector[i]);
		}

		return outVec;
	}

	public int[] getLocalVector() {
		DualHashMap<String, Integer> VarIndexMap = this.lpnModel
				.getVarIndexMap();
		VarSet locals = this.lpnModel.getInternals();
		int[] vec = new int[locals.size()];

		for (int i = 0; i < locals.size(); i++) {
			String var = VarIndexMap.getKey(i);
			if (locals.contains(var) == true)
				vec[i] = this.vector[i];
		}

		return vec;
	}

	public int[] getGlobalVector(DualHashMap<String, Integer> gVarIndexMap) {
		int gVecSize = gVarIndexMap.size();
		DualHashMap<String, Integer> localVarIndexMap = this.lpnModel
				.getVarIndexMap();

		int[] gVec = new int[gVecSize];
		for (int i = 0; i < this.vector.length; i++) {
			String var = localVarIndexMap.getKey(i);
			if (gVarIndexMap.containsKey(var) == true) {
				gVec[gVarIndexMap.getValue(var)] = this.vector[i];
			}
		}
		return gVec;
	}

	// public State getLocalState() {
	// VarSet lpnOutputs = this.lpnModel.getOutputs();
	// VarSet lpnInternals = this.lpnModel.getInternals();
	// DualHashMap<String,Integer> varIndexMap = this.lpnModel.getVarIndexMap();
	//
	// int[] outVec = new int[this.vector.length];
	//
	// //System.out.println(this + " \n");
	// /*
	// * Create a copy of the vector of mState such that the values of inputs
	// are set to 0
	// * and the values for outputs/internal variables remain the same.
	// */
	// for(int i = 0; i < this.vector.length; i++) {
	// String curVar = varIndexMap.getKey(i);
	// if(lpnOutputs.contains(curVar) ==true ||
	// lpnInternals.contains(curVar)==true)
	// outVec[i] = this.vector[i];
	// else
	// outVec[i] = 0;
	// }
	//
	// return new State(this.lpnModel, this.marking, outVec);
	// }
	//

	/**
	 * Return a new state if the newVector leads to a new state from this state;
	 * otherwise return null.
	 * 
	 * @param newVector
	 * @param VarIndexMap
	 * @return
	 */
	public State update(HashMap<String, Integer> newVector,
			DualHashMap<String, Integer> VarIndexMap) {
		int[] newStateVector = new int[this.vector.length];

		boolean newState = false;
		for (int index = 0; index < vector.length; index++) {
			String var = VarIndexMap.getKey(index);
			int this_val = this.vector[index];

			Integer newVal = newVector.get(var);
			if (newVal != null) {
				if (this_val != newVal) {
					newState = true;
					newStateVector[index] = newVal;
				} else
					newStateVector[index] = this.vector[index];
			} else
				newStateVector[index] = this.vector[index];
		}

		if (newState == true)
			return new State(this.lpnModel, this.marking, newStateVector);

		return null;
	}
	
	/*
	 * This function updates the value of the global/shared variables of this module using a global 
	 * variable vector globalVec.  When it returns, the value of the global/shared variables o fthis 
	 * module is consistent with that defined in globalVec.
	 */
	public void projectGlobalVector(HashMap<String, Integer> globalVec) {
		HashSet<String> gVarSet = this.getLpn().getGlobals();
		DualHashMap<String, Integer> varIdxMap = this.getLpn().getVarIndexMap();
		for(String gVar : gVarSet) {
			int gIdx = varIdxMap.getValue(gVar);
			this.vector[gIdx] = globalVec.get(gVar);
		}
	}
	
	/*
	 * This function returns a copy of this state where the state vector is adjusted to contain
	 * only local variable assignments.
	 */
	public State hideGlobalVector() {
		VarSet localVarSet = this.getLpn().getInternals();
		int[] localVec = new int[localVarSet.size()];
		for(int i = 0; i < localVec.length; i++) 
			localVec[i] = this.vector[i];
			
		return (new State(this.lpnModel, this.marking, localVec));
	}
}
