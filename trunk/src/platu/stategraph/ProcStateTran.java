package platu.stategraph;

import platu.lpn.LPNTran;

/**
 * Object representing an LPN state transition.
 */
public class ProcStateTran {
	
	private ProcState current;

    private ProcState next;
    
    private LPNTran lpnTran;
    
    private int hashVal = 0;

    
    public ProcStateTran() {
    	this.current = null;
    	this.next = null;
    	this.lpnTran = null;
    }
    
    /**
     * Constructor
     * @param currentState - Current state
     * @param lpnTran - Fired LPN transition
     * @param nextState - Next state
     */
    public ProcStateTran(ProcState cur, LPNTran lpnTran, ProcState nxt) {
        this.current = cur;
        this.next = nxt;
        this.lpnTran = lpnTran;
    }
    
    public final ProcState getCurrentProcState(){
    	return this.current;
    }
    
    public final ProcState getNextProcState(){
    	return this.next;
    }
    
    public final LPNTran getLpnTran(){
    	return this.lpnTran;
    }
    
    public void setCurrentState(ProcState cur){
    	this.current = cur;
    }
    
    public void setNextState(ProcState nxt){
    	this.next = nxt;
    }

    public void setLpnTran(LPNTran tran){
    	this.lpnTran = tran;
    }
    
    @Override
	public int hashCode() {
    	if(hashVal == 0){
			final int prime = 31;
			hashVal = 17;
			hashVal = prime * hashVal + this.next.hashCode();
			hashVal = prime * hashVal + lpnTran.hashCode();
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
		
		if (lpnTran == null) {
			if (other.lpnTran != null)
				return false;
		} 
		
		if (this.current == null) {
			if (other.current != null)
				return false;
		} 
		
		if (this.current == other.current && this.lpnTran == other.lpnTran && this.next == other.next)
			return true;
		
		return this.next.equals(other.next) && this.lpnTran.equals(other.lpnTran) && this.current.equals(other.current);
	}

	@Override
	public String toString() {
		return this.current.getIndex() + " --" + this.lpnTran.getFullLabel() + "--> " + this.next.getIndex();
	}
	
	
}

