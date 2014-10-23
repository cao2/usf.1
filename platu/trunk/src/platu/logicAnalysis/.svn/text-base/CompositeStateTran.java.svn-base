package platu.logicAnalysis;

import platu.lpn.LPNTran;

public class CompositeStateTran {
	/**
	 * Next state
	 */
	private CompositeState nextState;
	
	/**
	 * Current State
	 */
	private CompositeState currentState;
	
	/**
	 * Fired LPN transition
	 */
	private LPNTran lpnTransition = null;
	
	/**
	 * Visibility flag identifies whether this state transition is visible within a state graph.
	 */
	private boolean visible = false;
	
	
	/**
	 * Constructor creates a new state transition.
	 * @param currentState - Current state
	 * @param lpnTran - Fired LPN transition
	 * @param nextState - Next state
	 */
	public CompositeStateTran(CompositeState currentState, LPNTran lpnTran, CompositeState nextState) {
		this.currentState = currentState;
		this.nextState = nextState;
		this.lpnTransition = lpnTran;
	}

	/**
	 * Sets this state transition as visible.
	 */
	public void setVisibility(){
		this.visible = true;
	}
	
	/**
	 * Returns the visibility of this state transition.
	 */
	public boolean visible(){
		return this.visible;
	}
	
	/**
	 * Resets the current state to the specified state.
	 * @param currState - Current state
	 */
	public void setCurrentState(CompositeState currState){
		this.currentState = currState;
	}
	
	/**
	 * Resets the next state to the specified state.
	 * @param nxtState - Next state
	 */
	public void setNextState(CompositeState nxtState){
		this.nextState = nxtState;
	}
	
	/**
	 * Resets the fired LPN transition to the specified LPN transition
	 * @param lpnTran - LPN transition
	 */
	public void setLpnTran(LPNTran lpnTran){
		this.lpnTransition = lpnTran;
	}
	
	/**
	 * Returns the current state.
	 */
	public CompositeState getCurrentState(){
		return this.currentState;
	}
	
	/**
	 * Returns the next state.
	 */
	public CompositeState getNextState(){
		return this.nextState;
	}
	
	/**
	 * Returns the fired LPN transition.
	 */
	public LPNTran getLPNTran(){
		return this.lpnTransition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + this.nextState.hashCode();
		result = prime * result + this.lpnTransition.hashCode();
		result = prime * result + this.currentState.hashCode();
		
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
		
		CompositeStateTran other = (CompositeStateTran) obj;
		if (this.nextState != other.nextState)
			return false;
		
		if (this.lpnTransition != other.lpnTransition)
			return false;
		
		if (this.currentState != other.currentState)
			return false;
		
		return true;
	}
	
	@Override
	public String toString(){
		return (this.currentState + " --" + this.lpnTransition.getFullLabel() + "--> " + this.nextState);
	}
}
