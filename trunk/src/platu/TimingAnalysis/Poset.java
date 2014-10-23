package platu.TimingAnalysis;

import java.util.*;

import platu.common.DualHashMap;
import platu.lpn.*;

/*
 * This class implements the POSET algorithm in C Myers's book, section 7.5.
 */
public class Poset {
	public static final int INFINITY = platu.common.Common.INFINITY;

	protected HashMap<LPNTran, HashSet<LPNTran>> causalityFwd;
	protected HashMap<LPNTran, LPNTran> causalityBwd;
	protected DualHashMap<LPNTran, Integer> tranIdxMap;
	protected DBM dbm;
	
	public Poset() {
		causalityFwd = null;
		causalityBwd = null;
		tranIdxMap = null;
		dbm = null;
	}
	
	public void initialize(LPNTran firedTran, LpnTranList[] nextEnabledArray) {
		causalityFwd = new HashMap<LPNTran, HashSet<LPNTran>>();
		causalityBwd = new HashMap<LPNTran, LPNTran>();
		HashSet<LPNTran> enabledSet = new HashSet<LPNTran>();
		for(int i = 0; i < nextEnabledArray.length; i++)
			if(nextEnabledArray[i] != null)
				for(LPNTran tran : nextEnabledArray[i]) {
					enabledSet.add(tran);
					this.causalityBwd.put(tran, firedTran);
				}
		this.causalityFwd.put(firedTran, enabledSet);
		
		this.tranIdxMap = new DualHashMap<LPNTran, Integer>();
		this.tranIdxMap.insert(firedTran, this.tranIdxMap.size());
				
		this.dbm = new DBM(1);
	}
	
	public Poset update(LPNTran firedTran, LpnTranList[] curEnabledArray, LpnTranList[] nextEnabledArray) {
		Poset newPoset = new Poset();
				
		newPoset.tranIdxMap = new DualHashMap<LPNTran, Integer>();
		for(int i = 0; i < this.tranIdxMap.size(); i++) {
			LPNTran tran = this.tranIdxMap.getKey(i);
			newPoset.tranIdxMap.insert(tran, i);
		}
		
		newPoset.causalityBwd = (HashMap<LPNTran, LPNTran>) this.causalityBwd.clone();
		newPoset.causalityFwd = new HashMap<LPNTran, HashSet<LPNTran>>();
		DBM dbmCopy = new DBM(this.dbm.dimension() + 1);

		for(int i = 0; i < this.tranIdxMap.size(); i++) {
			LPNTran curTran = newPoset.tranIdxMap.getKey(i);
			HashSet<LPNTran> curEnabledSet = this.causalityFwd.get(curTran);
			if(curEnabledSet==null) {
				System.out.println("*** LPNTran does not exist in causalityFwd");
				System.exit(0);
			}
			newPoset.causalityFwd.put(curTran, (HashSet)curEnabledSet.clone());
			
			for(int j = 0; j < this.tranIdxMap.size(); j++) {
				dbmCopy.assign(i, j, this.dbm.value(i, j));
			}
		}
		
		/*
		 * Adjust newPoset with firedTran
		 */
		
		// Find the new enabled transition due to firedTran.
		HashSet<LPNTran> curEnabledSet = new HashSet<LPNTran>();
		HashSet<LPNTran> newEnabledSet = new HashSet<LPNTran>();
		
		for(int i = 0; i < curEnabledArray.length; i++) {
			if(curEnabledArray[i] != null) 
				for(LPNTran tran : curEnabledArray[i])
					curEnabledSet.add(tran);
		}
		
		for(int i = 0; i < nextEnabledArray.length; i++) {
			if(nextEnabledArray[i] != null) 
				for(LPNTran tran : nextEnabledArray[i])
					if(curEnabledSet.contains(tran) == false) {
						newEnabledSet.add(tran);
						newPoset.causalityBwd.put(tran, firedTran);
					}
		}
		
		newPoset.causalityFwd.put(firedTran, newEnabledSet);
		newPoset.tranIdxMap.insert(firedTran, newPoset.tranIdxMap.size());
		
		// Add the firedTran into newPoset, and set its time separations with other transitions in newPoset.
		int firedTranIdx = newPoset.tranIdxMap.getValue(firedTran);
		for(int i = 0; i < newPoset.tranIdxMap.size(); i++) {
			LPNTran prevTran = newPoset.tranIdxMap.getKey(i);
			if(prevTran == firedTran) continue;
			
			HashSet prevEnabledSet = newPoset.causalityFwd.get(prevTran);
			if(prevEnabledSet.contains(firedTran) == true) {
				dbmCopy.assign(firedTranIdx, i, firedTran.getDelayUB());
				dbmCopy.assign(i, firedTranIdx, -firedTran.getDelayLB());
			}
			else {
				dbmCopy.assign(i, firedTranIdx, INFINITY);
				dbmCopy.assign(firedTranIdx, i, INFINITY);
			}
		}
		
		dbmCopy.canonicalize();
		
		/*
		 * Projecting the useless transitions from newPoset.
		 */
		LPNTran firedEnabling = newPoset.causalityBwd.get(firedTran);
		HashSet<LPNTran> firedSiblings = newPoset.causalityFwd.get(firedEnabling);
		firedSiblings.remove(firedTran);
		
//		System.out.println("tranIdxMap");
//		for(int i = 0; i < newPoset.tranIdxMap.size(); i++) {
//			LPNTran thisTran = newPoset.tranIdxMap.getKey(i);
//			System.out.print(thisTran.getFullLabel()+", ");
//		}
//		System.out.println("");
		HashSet<LPNTran> uselessTranSet = new HashSet<LPNTran>();
		for(int i = 0; i < newPoset.tranIdxMap.size(); i++) {
			LPNTran thisTran = newPoset.tranIdxMap.getKey(i);
//			HashSet<LPNTran> thisEnabledSet = newPoset.causalityFwd.get(thisTran);
//			boolean useless = true;
//			System.out.print(thisTran.getFullLabel() + " : ");
//			for(LPNTran enabled : thisEnabledSet) {
				//System.out.print(thisTran.getFullLabel() + ", ");
				//if(newPoset.tranIdxMap.getValue(enabled) == null) {
				if(newPoset.causalityFwd.get(thisTran).size() == 0) {
//					useless = false;
//					break;
//				}
			//}
			//System.out.println("\n");
			//if(useless==true)
				uselessTranSet.add(thisTran);
				}
		}
		
		for(LPNTran uselessTran : uselessTranSet) {
			HashSet<LPNTran> uselessTranEnabled = newPoset.causalityFwd.get(uselessTran);
			for(LPNTran tran : uselessTranEnabled) {
				newPoset.causalityBwd.remove(tran);
			}
			newPoset.causalityFwd.remove(uselessTran);
		}
		
		DualHashMap<LPNTran, Integer> newTranIdxMap = new DualHashMap<LPNTran, Integer>();
		for(int i = 0; i < newPoset.tranIdxMap.size(); i++) {
			LPNTran tran_i = newPoset.tranIdxMap.getKey(i);
			if(uselessTranSet.contains(tran_i) == false) {
				newTranIdxMap.insert(tran_i, newTranIdxMap.size());
			}
		}
			
		DualHashMap<LPNTran, Integer> oldTranIdxMap = newPoset.tranIdxMap;
		newPoset.tranIdxMap = newTranIdxMap;
		
		/*
		 * Adjust dbmCopy by removing the entries w.r.t newTranIdxMap
		 */
		newPoset.dbm = new DBM(newPoset.tranIdxMap.size());
		for(int new_i = 0; new_i < newPoset.tranIdxMap.size(); new_i++) {
			LPNTran new_tran_i = newPoset.tranIdxMap.getKey(new_i);
			int old_i = oldTranIdxMap.getValue(new_tran_i);
			for(int new_j = 0; new_j < newPoset.tranIdxMap.size(); new_j++) {
				LPNTran new_tran_j = newPoset.tranIdxMap.getKey(new_j);
				int old_j = oldTranIdxMap.getValue(new_tran_j);
				newPoset.dbm.assign(new_i, new_j, dbmCopy.value(old_i, old_j));
				newPoset.dbm.assign(new_j, new_i, dbmCopy.value(old_j, old_i));				
			}
		}
		
		return newPoset;
	}
	
	/*
	 * Return the time separation between two transitions as defined in this Poset.
	 */
	public int getTimeSep(LPNTran tran_i, LPNTran tran_j) {
		LPNTran enablingTran_i = this.causalityBwd.get(tran_i);
		LPNTran enablingTran_j = this.causalityBwd.get(tran_j);
		int i = this.tranIdxMap.getValue(enablingTran_i);
		int j = this.tranIdxMap.getValue(enablingTran_j);
		return this.dbm.value(i, j);
	}
	
	@Override
	public int hashCode() {
		return Integer.rotateLeft(this.tranIdxMap.hashCode(), 11) ^ Integer.rotateLeft(this.dbm.hashCode(), 7);     
	}

	@Override
	public boolean equals(Object other) {
		Poset otherPoset = (Poset)other;
		if(this.tranIdxMap.equals(otherPoset.tranIdxMap)==false)
		//if(this.enabledSet != otherZone.enabledSet)	
			return false;	
		
//		System.out.println("this zone : " + this.dbm);
//		System.out.println("\nother zone : " + otherZone.dbm + "\n =================================");
		
		if(this.dbm.equals(otherPoset.dbm)==false)
		//if(this.dbm != otherZone.dbm)
			return false;

//		if(this.timeSep == null && otherZone.timeSep == null)
//			return true;
//		
//		if((this.timeSep == null && otherZone.timeSep != null) || (this.timeSep != null && otherZone.timeSep == null))
//			return false;
//		
//		if(this.timeSep.equals(otherZone.timeSep) == false)
//			return false;
				
		return true;
	}
	
	@Override
	public String toString() {
		String ret = new String();
		for(int i = 0; i < this.tranIdxMap.size(); i++) {
			ret  += "\t" + this.tranIdxMap.getKey(i).getFullLabel();
		}
		String timeSepConstraints = new String();
		
		return ret + "\n\n" + this.dbm.toString() + timeSepConstraints +"\n";
	}
}
