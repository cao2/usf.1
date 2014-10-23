package platu.MDD;

import java.util.*;

import platu.stategraph.*;

public class Mdd {
	static mddNode terminal = new mddNode();

	private HashMap<mddNode, mddNode> uniqueNodeTbl;
	private HashMap<State, HashSet<State>>[] localFirings;
	private int peakNodes;
	int height;
	
	/*
	 * Initialize MDD with the number of modules in the design model.
	 */
	public Mdd(int numMods) {
		Mdd.terminal.level = numMods;
		height = numMods;
		uniqueNodeTbl = new HashMap<mddNode, mddNode>();
		localFirings = (HashMap<State, HashSet<State>>[])new HashMap[height+1];
		for(int i = 0; i < this.height; i++) { 
			localFirings[i] = new HashMap<State, HashSet<State>>();
		}
			
		peakNodes = 0;
	}
	
	public mddNode newNode() {
		return new mddNode(0);
	}
	
	/*
	 * Return a MDD node that is the root of MDD representing the union of states encoded in MDD 'target' and MDD 'source'
	 */
	public mddNode union(mddNode target, mddNode source) {
		HashMap<mddNode, HashMap<mddNode, mddNode>> unionCache = new HashMap<mddNode, HashMap<mddNode, mddNode>>();
		mddNode unionResult = target.union(source, uniqueNodeTbl, unionCache);
		return unionResult;
	}
	

	
   	/*
	 * create a MDD for stateArray. The nodes in the created MDD are added into the nodeTbl.
	 */	
//	public boolean add(mddNode target, int[] idxArray) {					
//		target = target.add(idxArray, nodeTbl, 20);
//
//		int curNodes = 0;
//		for(int i = 0; i < this.height; i++) 
//			curNodes += nodeTbl[i].size();
//		
//		if(curNodes > peakNodes)
//			peakNodes = curNodes;
//
//		stateCount++;
//		return true;
//	}	
	
	public boolean add(mddNode target, int[] idxArray, boolean sharing) {
		if(sharing==true)
			target.add(idxArray, uniqueNodeTbl, 20);
		else
			//target.add(idxArray);
			this.add_noSharing(target, idxArray);
		return true;
	}
	
	private boolean add_noSharing(mddNode target, int[] byteArray) {
		boolean isNew = false;
		
		target.increaseRefCnt();
		
		mddNode curNode = target;		
		for(int i = 0; i < byteArray.length; i++) {
			int curIdx = byteArray[i];
			mddNode nxtNode = curNode.getSucc(curIdx);
			if(nxtNode == null) {
				nxtNode = Mdd.terminal;
				if(i < byteArray.length-1) {
					nxtNode = new mddNode();
					nxtNode.setLevel(i+1);
				}
				
				isNew = true;
			}
			curNode.addSucc(curIdx, nxtNode);
			curNode = nxtNode;
		} 
		return isNew;
	}
	
	public void compress(mddNode target) {
		target.compress(this.uniqueNodeTbl);
	}
	
	public void remove(mddNode target) {
		target.remove(this.uniqueNodeTbl);
		if(target.refCount<=0)
			this.uniqueNodeTbl.remove(target);
	}
	
	/*
	 * Check if there is a path in MDD that corresponds to stateArray. Return true if so.
	 */
//	public boolean contains(mddNode target, int[] idxArray) {
//		if(target == null)
//			return false;
//		return target.contains(idxArray)==Mdd.terminal;
//	}
//	
	public boolean contains(mddNode target, int[] idxArray) {
		if(target == null)
			return false;
		mddNode curNode = target;
		for(int i = 0; i < idxArray.length; i++) {
			mddNode succ = curNode.getSucc(idxArray[i]);
			if(succ == null)
				return false;
			curNode = succ;
		}
		return true;
	}
	
	public double numberOfStates(mddNode target) {
		HashSet<mddNode> uniqueNodes = new HashSet<mddNode>();
		double paths = target.pathCount(uniqueNodes);
		return paths;
	}
	
	public int nodeCnt() {
		int curNodes = 0;
		curNodes += this.uniqueNodeTbl.size();
		return curNodes;
	}
	
	public HashMap<State, HashSet<State>>[] getLocalFiringTbl() {
		return localFirings;
	}
	
	/*
	 * Returns the largest number of MDD nodes created when this MDD is live.
	 */
	public int peakNodeCnt() {
		return peakNodes;
	}
	
	public void check()
	{
		for(int i = 0; i < this.height; i++) {
			Set entries = this.uniqueNodeTbl.entrySet();
			Iterator it = entries.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				mddNode tmp = (mddNode)entry.getKey();
				System.out.println("nodeTbl@" + i + " >>> " + tmp + ": level = " + tmp.level + ", refCount = " + tmp.refCount);
	    		  //+ ", " + tmp.nodeMapSize);
			}
		}
	}
}
