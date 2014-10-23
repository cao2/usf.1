package platu.stategraph;

import platu.lpn.LPNTran;

/**
 * Object representing an LPN state transition.
 */
public class StateTran {
    private State current;
	private State next;
    private LPNTran action;
    
    private int hashVal = 0;

    /**
     * Constructor
     * @param currentState - Current state
     * @param act - Fired LPN transition
     * @param nextState - Next state
     */
    public StateTran(State currentState, LPNTran act, State nextState) {
        this.current = currentState;
        this.next = nextState;
        this.action = act;
    }
    
    public final State getCurrentState(){
    	return this.current;
    }
    
    public final State getNextState(){
    	return this.next;
    }
    
    public final LPNTran getAction(){
    	return this.action;
    }
    
    public void setNextState(State nextState){
    	this.next = nextState;
    }

    @Override
	public int hashCode() {
    	if(hashVal == 0){
			final int prime = 31;
			hashVal = 17;
			hashVal = prime * hashVal + next.hashCode();
			hashVal = prime * hashVal + action.hashCode();
			hashVal = prime * hashVal + current.hashCode();
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
		if (next == null) {
			if (other.next != null)
				return false;
		} 
		else if (!next.equals(other.next)){
			return false;
		}
		
		if (action == null) {
			if (other.action != null)
				return false;
		} 
		else if (!action.equals(other.action)){
			return false;
		}
		
		if (current == null) {
			if (other.current != null)
				return false;
		} 
		else if (!current.equals(other.current)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return this.current.getIndex() + " --" + this.action.getFullLabel() + "--> " + this.next.getIndex();
	}
	
	
}
