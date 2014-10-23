package platu.stategraph;

import java.util.*;
import platu.util.*;
import platu.common.PlatuObj;
import platu.lpn.*;


public class ProcState extends PlatuObj  {

	//* Index of a global vector.
	private int gidx;
	
	//* Reference to a local state
	private State lst;
	
	//* Index of an object of this class.
	private int index;
	
	//* == 0: 	regular state
	//* > 0:	bad state	
	private int type;
	
	//*	Enabled LPN transitions and the corresponding successor states
	private LinkedList<Pair<LPNTran, ProcState>> nextStateSet;
	
	//*	External transitions that change global vectors.
	//private HashMap<LPNTran, HashSet<Integer>> extTranTable;
	private HashSet<ProcStateTran> extTranTable;

	private int hashVal = 0;
	
	
	
	//*
	//*	Member methods section
	//*
	
	public ProcState(int gVecIdx, State lclState) {
		this.gidx = gVecIdx;
		this.lst = lclState;
		this.nextStateSet = null;
		this.extTranTable = null;
		this.type	= 0;
	}
	
	public int getGlobalVecIdx() {
		return this.gidx;
	}
	
	public State getLocalState() {
		return this.lst;
	}

	public LinkedList<Pair<LPNTran, ProcState>> getNextStateSet() {
		return this.nextStateSet;
	}

	public void setNextStateSet(LinkedList<Pair<LPNTran, ProcState>> nextStateSet) {
		this.nextStateSet = nextStateSet;
	}
	
	public boolean addExtTran(ProcStateTran extTran) {//LPNTran extLpnTran, int nxtGidx) {
		if(this.extTranTable == null) {
			this.extTranTable = new HashSet<ProcStateTran>();
		}
		
		ProcState curExtPst = extTran.getCurrentProcState();
		if(this.gidx != curExtPst.getGlobalVecIdx())
			throw new IllegalStateException("Unmatched global vector index");
		
		return this.extTranTable.add(extTran);		
	}
	
	public LinkedList<Pair<LPNTran, ProcState>> getEnabledTrans() {
		return this.nextStateSet;
	}
	
	public HashSet<ProcStateTran> getExtTrans() {
		return this.extTranTable;
	}
	
	public boolean containsExtTran(ProcStateTran tran) {
		if (this.extTranTable != null)
			return this.extTranTable.contains(tran);
		return false;
	}
	
	public ProcState getSucc(ProcStateTran extTran) {
		if (this.extTranTable.contains(extTran) == false)
			return null;
		
		ProcState curExtPst = extTran.getCurrentProcState();
			
		if(this.gidx != curExtPst.getGlobalVecIdx())
			throw new IllegalStateException("Unmatched global vector index");
				
		int nxtGidx = extTran.getNextProcState().getGlobalVecIdx();
		return new ProcState(nxtGidx, this.lst);
	}

	public void setType(int ty) {
		this.type	= ty;
	}
	
	public int getType() {
		return this.type;
	}
	
	@Override
	public int hashCode() {
		if(this.hashVal != 0)
			return this.hashVal;
		
		final int prime = 31;
		this.hashVal = 1;
		this.hashVal = prime * this.hashVal + gidx;
		this.hashVal = prime * this.hashVal + ((lst == null) ? 0 : lst.hashCode());
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
		ProcState other = (ProcState) obj;
		if (gidx != other.gidx)
			return false;
		if (lst == null) {
			if (other.lst != null)
				return false;
		} 
		
		if (lst == other.lst)
			return true;
		
		return this.lst.equals(other.lst);
	}
	
	/*
	 * Remove any outgoing state transitions of this ModuleState due to local LPN transitions.
	 * Replace those with non-local state transitions.
	 */
	public LinkedList<Pair<LPNTran, ProcState>> removeLocalStateTran() {
		return null;
	}

	public void setLabel(String lbl) {}

	public String getLabel() {
		return null;
	}

	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	public int getIndex() {
		return this.index;
	}
	
	public int getProcIndex() {
		return this.lst.getLpn().getIndex();
	}
	
	public int tranCount() {
		int count = 0;
		
		if(this.nextStateSet != null)	count = this.nextStateSet.size();
		
		if(this.extTranTable != null) {
			//Set<LPNTran> lpnTranSet = this.extTranTable.keySet();
			//for(LPNTran lpnTran : lpnTranSet) 
				//count += this.extTranTable.get(lpnTran).size();
			count += this.extTranTable.size();
		}
		return count;
	}
}
