package platu.stategraph;

import platu.lpn.LPNTran;

/**
 * Object representing an LPN state transition.
 */
public class ProcStateTran {
	
	private ProcState current;

    private ProcState next;
    
    private LPNTran action;
    
    private int hashVal = 0;

    
    public ProcStateTran() {
    	this.current = null;
    	this.next = null;
    	this.action = null;
    }
    
    /**
     * Constructor
     * @param currentState - Current state
     * @param act - Fired LPN transition
     * @param nextState - Next state
     */
    public ProcStateTran(ProcState cur, LPNTran act, ProcState nxt) {
        this.current = cur;
        this.next = nxt;
        this.action = act;
    }
    
    public final ProcState getCurrentProcState(){
    	return this.current;
    }
    
    public final ProcState getNextProcState(){
    	return this.next;
    }
    
    public final LPNTran getAction(){
    	return this.action;
    }
    
    public void setCurrentState(ProcState cur){
    	this.current = cur;
    }
    
    public void setNextState(ProcState nxt){
    	this.next = nxt;
    }

    public void setLpnTran(LPNTran act){
    	this.action = act;
    }
    
    @Override
	public int hashCode() {
    	if(hashVal == 0){
			final int prime = 31;
			hashVal = 17;
			hashVal = prime * hashVal + this.next.hashCode();
			hashVal = prime * hashVal + action.hashCode();
			hashVal = prime * hashVal + this.current.hashCode();
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
		
		ProcStateTran other = (ProcStateTran) obj;
		if (this.next == null) {
			if (other.next != null)
				return false;
		} 
		
		if (action == null) {
			if (other.action != null)
				return false;
		} 
		
		if (this.current == null) {
			if (other.current != null)
				return false;
		} 
		
		if (this.current == other.current && this.action == other.action && this.next == other.next)
			return true;
		
		return this.next.equals(other.next) && this.action.equals(other.action) && this.current.equals(other.current);
	}

	@Override
	public String toString() {
		return this.current.getIndex() + " --" + this.action.getFullLabel() + "--> " + this.next.getIndex();
	}
	
	
}

