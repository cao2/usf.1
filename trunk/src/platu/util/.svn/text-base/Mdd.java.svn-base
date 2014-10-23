package platu.util;

import java.util.*;


public class Mdd {
	static MddNode terminal = new MddNode();

	/* Table that stores unqiue copies of MddNodes. */
	private HashMap<MddNode, MddNode>[] uniqueNodeTbl;
	int height;

	/* Runtime stats */
	private int pathCount;
	private int nodeCount;
	private int peakNodeCount;

	/*
	 * Initialize MDD with the number of modules in the design model.
	 */
	public Mdd(int numMods) {
		Mdd.terminal.level = numMods;
		this.pathCount = 0;
		this.height = numMods;
		this.uniqueNodeTbl = (HashMap<MddNode, MddNode>[]) new HashMap[height];
		for (int i = 0; i < this.height; i++) {
			this.uniqueNodeTbl[i] = new HashMap<MddNode, MddNode>();
		}
		this.pathCount = 0;
	}

	public MddNode newNode() {
		MddNode newNode = new MddNode();
		newNode.setLevel(0);
		return newNode;
	}

	/*
	 * Return a MDD node that is the root of MDD representing the union of
	 * states encoded in MDD 'target' and MDD 'source'
	 */
	public MddNode union(MddNode target, MddNode source) {
		HashMap<MddNode, HashMap<MddNode, MddNode>> unionCache = new HashMap<MddNode, HashMap<MddNode, MddNode>>();
		MddNode unionResult = target.union(source, this.uniqueNodeTbl, unionCache);
		return unionResult;
	}

	/*
	 * create a Mdd for byteArray. The nodes in the created Mdd are NOT added
	 * into the uniqueNodeTbl.
	 */
	public boolean add(MddNode target, int[] byteArray, boolean sharing) {
		if(sharing==true)
			return this.add_sharing(target, byteArray);
		else
			return this.add_noSharing(target, byteArray);
	}

	private boolean add_sharing(MddNode target, int[] byteArray) {
		target.add(byteArray, this.uniqueNodeTbl);
		return true;
	}
	
	private boolean add_noSharing(MddNode target, int[] byteArray) {
		boolean isNew = false;
		
		target.increaseRefCnt();
		
		MddNode curNode = target;		
		for(int i = 0; i < byteArray.length; i++) {
			int curIdx = byteArray[i];
			MddNode nxtNode = curNode.getSucc(curIdx);
			if(nxtNode == null) {
				nxtNode = Mdd.terminal;
				if(i < byteArray.length-1) {
					nxtNode = new MddNode();
					nxtNode.setLevel(i+1);
				}
				
				isNew = true;
			}
			curNode.addSucc(curIdx, nxtNode);
			curNode = nxtNode;
		} 
		return isNew;
	}

	/* Check nodes in Mdd rooted at target, and merge all equivalent nodes. */
	public void compress(MddNode target) {
		target.compress(this.uniqueNodeTbl);
	}

	public void remove(MddNode target) {
		Stack<MddNode> deadNodes = new Stack<MddNode>();
		
		target.decreaseRefCnt();
		
		if (target.getRefCount() == 0)
			deadNodes.push(target);
		
		while(deadNodes.empty()==false) {
			MddNode curNode = deadNodes.pop();		
			
			for(int i = 0; i < 256; i++) {
				MddNode succNode = curNode.getSucc(i);
				if(succNode == null) 
					continue;
				succNode.decreaseRefCnt();
				if(succNode.getRefCount()==0)
					deadNodes.push(succNode);
			}
			this.uniqueNodeTbl[curNode.getLevel()].remove(curNode);
		}
	}

	/*
	 * Check if there is a path in MDD that corresponds to stateArray. Return
	 * true if so.
	 */
	public boolean contains(MddNode target, int[] byteArray) {		
		MddNode curNode = target;		
		for(int i = 0; i < byteArray.length; i++) {
			int curIdx = byteArray[i];
			MddNode nxtNode = curNode.getSucc(curIdx);
			if(nxtNode == null) {				
				return false;
			}
			curNode = nxtNode;
		} 
		return true;	
	}

	public double getPathCount(MddNode target) {
		// HashSet<MddNode> uniqueNodes = new HashSet<MddNode>();
		// double paths = target.pathCount(uniqueNodes);
		// return paths;
		return 0;
	}

	public int getNodeCount() {
		int curNodes = 0;
		for (int i = 0; i < this.height; i++)
			curNodes += this.uniqueNodeTbl[i].size();
		return curNodes;
	}

	/*
	 * Returns the largest number of MDD nodes created when this MDD is live.
	 */
	public int getPeakNodeCount() {
		return this.peakNodeCount;
	}
}
