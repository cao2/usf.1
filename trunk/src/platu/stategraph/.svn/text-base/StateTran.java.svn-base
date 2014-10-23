package platu.stategraph;

import platu.lpn.LPNTran;

/**
 * Object representing an LPN state transition.
 */
public class StateTran {
	/**
	 * Next state
	 */
	private State nextState;
	
	/**
	 * Current state
	 */
    private State currentState;
    
    /**
     * Fired LPN transition
     */
    private LPNTran lpnTran;
    
    private int hashVal = 0;

    /**
     * Constructor
     * @param currentState - Current state
     * @param lpnTran - Fired LPN transition
     * @param nextState - Next state
     */
    public StateTran(State currentState, LPNTran lpnTran, State nextState) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.lpnTran = lpnTran;
    }
    
    public final State getCurrentState(){
    	return this.currentState;
    }
    
    public final State getNextState(){
    	return this.nextState;
    }
    
    public final LPNTran getLpnTran(){
    	return this.lpnTran;
    }
    
    public void setNextState(State nextState){
    	this.nextState = nextState;
    }

    @Override
	public int hashCode() {
    	if(hashVal == 0){
			final int prime = 31;
			hashVal = 17;
			hashVal = prime * hashVal + nextState.hashCode();
			hashVal = prime * hashVal + lpnTran.hashCode();
			hashVal = prime * hashVal + currentState.hashCode();
    	}
    	
		return hashVal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		StateTran other = (StateTran) obj;
		if (nextState == null) {
			if (other.nextState != null)
				return false;
		} 
		else if (!nextState.equals(other.nextState)){
			return false;
		}
		
		if (lpnTran == null) {
			if (other.lpnTran != null)
				return false;
		} 
		else if (!lpnTran.equals(other.lpnTran)){
			return false;
		}
		
		if (currentState == null) {
			if (other.currentState != null)
				return false;
		} 
		else if (!currentState.equals(other.currentState)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return this.currentState.getIndex() + " --" + this.lpnTran.getFullLabel() + "--> " + this.nextState.getIndex();
	}
	
	
}
