package platu.MDD;

import java.util.*;

public class mddNode {
	static int blockSize = 8;
	static int numBlocks = 16;
	static int blockIdxMask = 15; 
	static int arrayIdxoffset = 4;
	
	int level;
	int refCount;
	
	private mddNode[] nodeMap_0;
	private mddNode[] nodeMap_1;
	private mddNode[] nodeMap_2;
	private mddNode[] nodeMap_3;
	private mddNode[] nodeMap_4;
	private mddNode[] nodeMap_5;
	private mddNode[] nodeMap_6;
	private mddNode[] nodeMap_7;
	private mddNode[] nodeMap_8;
	private mddNode[] nodeMap_9;
	private mddNode[] nodeMap_10;
	private mddNode[] nodeMap_11;
	private mddNode[] nodeMap_12;
	private mddNode[] nodeMap_13;
	private mddNode[] nodeMap_14;
	private mddNode[] nodeMap_15;
	
	private int blkHashVal_0;
	private int blkHashVal_1;
	private int blkHashVal_2;
	private int blkHashVal_3;
	private int blkHashVal_4;
	private int blkHashVal_5;
	private int blkHashVal_6;
	private int blkHashVal_7;
	private int blkHashVal_8;
	private int blkHashVal_9;
	private int blkHashVal_10;
	private int blkHashVal_11;
	private int blkHashVal_12;
	private int blkHashVal_13;
	private int blkHashVal_14;
	private int blkHashVal_15;
	int hashVal;
	
	public mddNode() {
		this.reset();
	}
	
	public mddNode(int thisLevel) {
		this.reset();
		level = thisLevel;
	}
	
	private void reset() {
		level = -1;
		refCount = 0;
		
		this.nodeMap_0 = null;
		this.nodeMap_1 = null;
		this.nodeMap_2 = null;
		this.nodeMap_3 = null;
		this.nodeMap_4 = null;
		this.nodeMap_5 = null;
		this.nodeMap_6 = null;
		this.nodeMap_7 = null;
		this.nodeMap_8 = null;
		this.nodeMap_9 = null;
		this.nodeMap_10 = null;
		this.nodeMap_11 = null;
		this.nodeMap_12 = null;
		this.nodeMap_13 = null;
		this.nodeMap_14 = null;
		this.nodeMap_15 = null;
		
		this.blkHashVal_0 = 0;
		this.blkHashVal_1 = 0;
		this.blkHashVal_2 = 0;
		this.blkHashVal_3 = 0;
		this.blkHashVal_4 = 0;
		this.blkHashVal_5 = 0;
		this.blkHashVal_6 = 0;
		this.blkHashVal_7 = 0;
		this.blkHashVal_8 = 0;
		this.blkHashVal_9 = 0;
		this.blkHashVal_10 = 0;
		this.blkHashVal_11 = 0;
		this.blkHashVal_12 = 0;
		this.blkHashVal_13 = 0;
		this.blkHashVal_14 = 0;
		this.blkHashVal_15 = 0;
		hashVal = 0;
	}

	
	private mddNode split() {
		mddNode copy = new mddNode();
		copy.level = this.level;
		for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
			mddNode[] thisNodeMapBlk = this.getNodeMapBlock(blkIter);
			if(thisNodeMapBlk == null) 
				continue;
			for(int arrayIter = 0; arrayIter < thisNodeMapBlk.length; arrayIter++) {
				mddNode succ = thisNodeMapBlk[arrayIter];
				if(succ != null)
					copy.addSucc(blkIter, arrayIter, succ);
			}
		}
		
		copy.refCount = 0;
		
		copy.blkHashVal_0 = this.blkHashVal_0;
		copy.blkHashVal_1 = this.blkHashVal_1;
		copy.blkHashVal_2 = this.blkHashVal_2;
		copy.blkHashVal_3 = this.blkHashVal_3;
		copy.blkHashVal_4 = this.blkHashVal_4;
		copy.blkHashVal_5 = this.blkHashVal_5;
		copy.blkHashVal_6 = this.blkHashVal_6;
		copy.blkHashVal_7 = this.blkHashVal_7;
		copy.blkHashVal_8 = this.blkHashVal_8;
		copy.blkHashVal_9 = this.blkHashVal_9;
		copy.blkHashVal_10 = this.blkHashVal_10;
		copy.blkHashVal_11 = this.blkHashVal_11;
		copy.blkHashVal_12 = this.blkHashVal_12;
		copy.blkHashVal_13 = this.blkHashVal_13;
		copy.blkHashVal_14 = this.blkHashVal_14;
		copy.blkHashVal_15 = this.blkHashVal_15;
		copy.hashVal = this.hashVal;

		return copy;
	}
	
	public void setLevel(int l) {
		this.level = l;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * Add an integer tuple into MDD root at this node. 
	 * Isomorphic nodes are not shared.
	 * 
	 * @param idxArray
	 * @return null if input idxArray is added into the MDD, root node otherwise.
	 */
	public mddNode add(int[] idxArray) {	
		if(this.level == -1) {
			System.out.println("level is not right 1");
			System.exit(0);
		}
		
		int curIdx = idxArray[this.level];
		int stateIdx = curIdx;
		int blockIdx = stateIdx & this.blockIdxMask;
		int arrayIdx = stateIdx >> this.arrayIdxoffset;
		
		mddNode nextNode = this.getSucc(blockIdx, arrayIdx);

		if(nextNode == null) {
			if(this.level == idxArray.length-1) {
				this.addSucc(blockIdx, arrayIdx, Mdd.terminal);				
				this.updateBlockHash(blockIdx);
				return null;
			}
			else { 
				nextNode = new mddNode();
				nextNode.level = this.level + 1;
				nextNode.add(idxArray);
				this.addSucc(blockIdx, arrayIdx, nextNode);
				this.updateBlockHash(blockIdx);				
				return null;
			}			
		}
		else {
			if(nextNode == Mdd.terminal)
				return this;
			else if(nextNode.add(idxArray)==null)
				return null;
			return this;
		}
	}
	
	public mddNode add(int[] idxArray, HashMap<mddNode, mddNode> nodeTbl, int shrLevel) {	
		if(this.level == -1) {
			System.out.println("level is not right 1");
			System.exit(0);
		}
		
		int curIdx = idxArray[this.level];
		int stateIdx = curIdx;
		int blockIdx = stateIdx & this.blockIdxMask;
		int arrayIdx = stateIdx >> this.arrayIdxoffset;
		
		mddNode nextNode = this.getSucc(blockIdx, arrayIdx);

		if(nextNode == null) {
			if(this.level == idxArray.length-1) {
				nodeTbl.remove(this);
				this.addSucc(blockIdx, arrayIdx, Mdd.terminal);				
				this.updateBlockHash(blockIdx);
								
				mddNode newThisNode = nodeTbl.get(this);  
				if(newThisNode != null) {
					return newThisNode;
				}
				
				nodeTbl.put(this, this);
				return this;
			}
			else { 
				nextNode = new mddNode();
				nextNode.level = this.level + 1;
				
				mddNode newNextNode = nextNode.add(idxArray, nodeTbl, shrLevel);				
				if(newNextNode != nextNode) 
					nextNode = newNextNode;
				nextNode.refCount++;	
				
				nodeTbl.remove(this);
				
				this.addSucc(blockIdx, arrayIdx, nextNode);
				this.updateBlockHash(blockIdx);
					
				mddNode newThis = nodeTbl.get(this);
				if (newThis != null) {		
					for(int blkIter = 0; blkIter < this.numBlocks; blkIter++) {
						mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);
						if(thisNodeBlk == null)
							continue;
						for(int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++)
							if(thisNodeBlk[arrayIter] != null)
								thisNodeBlk[arrayIter].refCount--;
					} 
				    
					return newThis;
				}
				
				nodeTbl.put(this, this);
				return this;
			}			
		}
		else if(nextNode == Mdd.terminal) {
			return Mdd.terminal;
		}
		else {
			mddNode newNextNode = nextNode;
			if(nextNode.refCount > 1) {
				newNextNode = nextNode.split();//new mddNode(nextNode);
				nextNode.refCount--;
				
			    mddNode newNextNode_1 = newNextNode.add(idxArray, nodeTbl, shrLevel);
			    if (newNextNode_1 != newNextNode) 
			    	newNextNode = newNextNode_1;
			}
			else {
				newNextNode = nextNode.add(idxArray, nodeTbl, shrLevel);

				// if no next node is splitted and the next node does not have equivalent, 
				// do nothing further.
				if(newNextNode == nextNode)
					return this;
				
				nextNode.refCount--;
				for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
					mddNode[] nextNodeBlk = nextNode.getNodeMapBlock(blkIter);
					if(nextNodeBlk == null)
						continue;
					
					for(int arrayIter = 0; arrayIter < nextNodeBlk.length; arrayIter++)
						if(nextNodeBlk[arrayIter] != null)
							nextNodeBlk[arrayIter].refCount++;
				}
			}

			nodeTbl.remove(this);

			newNextNode.refCount++;
			this.addSucc(blockIdx, arrayIdx, newNextNode);
			this.updateBlockHash(blockIdx);

			mddNode newThis = nodeTbl.get(this);
			if (newThis != null) {
				for (int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
					mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);
					if (thisNodeBlk == null)
						continue;
					for (int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++)
						if (thisNodeBlk[arrayIter] != null)
							thisNodeBlk[arrayIter].refCount--;
				}

				return newThis;
			}

			nodeTbl.put(this, this);
			return this;
		}
	}	
	
	public static int numCalls = 0;
	public static int cacheNodes = 0;
	public static int splits_level1 = 0;
	public static int splits_level2 = 0;
	public static int splits_level3 = 0;

	public mddNode union(final mddNode other, HashMap<mddNode, mddNode> nodeTbl, HashMap<mddNode, HashMap<mddNode, mddNode>> unionCache) {
		if(this==other || other.subSet(this) == true)
			return this;
		
		numCalls++;
		
		if(other.subSet(this) == true)
			return this;
				
		mddNode thisCopy = this.split();
		
		for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
			mddNode[] otherNodeBlk = other.getNodeMapBlock(blkIter);
			if(otherNodeBlk == null)
				continue;
			
			for(int arrayIter = 0; arrayIter < otherNodeBlk.length; arrayIter++) {
				mddNode thisSucc = this.getSucc(blkIter, arrayIter);
				mddNode otherSucc = other.getSucc(blkIter, arrayIter);
				
				if(otherSucc == null)
					continue;
				
				if(thisSucc == null) {
					thisCopy.addSucc(blkIter, arrayIter, otherSucc);
					continue;
				}
				
				// When successors are terminals, return. 
				if(thisSucc == Mdd.terminal || otherSucc == Mdd.terminal)
					continue;
				
				if(thisSucc==otherSucc || otherSucc.subSet(thisSucc) == true)
					continue;
				
				mddNode succCached = null;
				HashMap<mddNode, mddNode> second = unionCache.get(thisSucc);
				if(second != null) {
					succCached = second.get(otherSucc);
				}
			
				if(succCached == null) {
					mddNode succUnion = thisSucc.union(otherSucc, nodeTbl, unionCache);
					
					// Add newSucc into the cache to avoid call union(thisSuccOriginal, otherSucc) again.		
					HashMap<mddNode, mddNode> secondCache = unionCache.get(thisSucc);
					cacheNodes++;
					if(secondCache == null) {
						secondCache = new HashMap<mddNode, mddNode>();
						secondCache.put(otherSucc, succUnion);
						unionCache.put(thisSucc, secondCache);
					}
					else
						secondCache.put(otherSucc, succUnion);
					
					succCached = succUnion;
				}
				thisSucc.remove(nodeTbl);
				thisCopy.addSucc(blkIter, arrayIter, succCached);
			}
		}
		
		mddNode result = nodeTbl.get(thisCopy);
		if(result == null) {
			nodeTbl.put(thisCopy, thisCopy);
			return thisCopy;
		}
		thisCopy.remove(nodeTbl);
		return result;
	}
	
	
	public mddNode compress(HashMap<mddNode, mddNode> nodeTbl) {
		//int mddHeight = nodeTbl.length;
				
		//if(this.level < mddHeight-1) {	
		if(this != Mdd.terminal) {
			for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
				mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);
				if(thisNodeBlk == null)
					continue;
				
				for(int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++) {
					mddNode nextNode = thisNodeBlk[arrayIter];
					if(nextNode == null || nextNode==Mdd.terminal)
						continue;

					mddNode newNextNode = nextNode.compress(nodeTbl);
					if(newNextNode != nextNode) {
						this.addSucc(blkIter, arrayIter, newNextNode);
					}
				}
			}
		}
		mddNode newThis = nodeTbl.get(this);
		if(newThis == null) {
			nodeTbl.put(this, this);
			return this;
		}
		else if(newThis != this) 
			return newThis;

		return this;
	}

	/*
	 * Recursively remove this nodes and its successor nodes from nodeTbl if their reference
	 * count is 0.
	 */
	public void remove(HashMap<mddNode, mddNode> nodeTbl) {
		//int mddHeight = nodeTbl.length;
		
		this.refCount--;
		if(this.refCount > 0)
			return;

		//if (this.level < mddHeight - 1) {
		if(this != Mdd.terminal) {
			for (int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
				mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);
				if (thisNodeBlk == null)
					continue;
				for (int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++) {
					mddNode thisSucc = thisNodeBlk[arrayIter];
					if (thisSucc == null)
						continue;
					thisSucc.remove(nodeTbl);
				}
			}
		}
		nodeTbl.remove(this);
	}
	

	
	/**
	 * Check if stateArray already exists in MDD.
	 * @param stateArray
	 * @param index
	 * @param terminal
	 * @return true if stateArray exists in MDD, false otherwise.
	 */
	public mddNode contains(int[] idxArray) {	
		if(this.level == -1) {
			System.out.println("level is not right 2");
			System.exit(0);
		}
		
		int curIdx = idxArray[this.level];
		int blockIdx = curIdx & mddNode.blockIdxMask;
		int arrayIdx = curIdx >> mddNode.arrayIdxoffset;

		mddNode nextNode = this.getSucc(blockIdx, arrayIdx);
		
		if (nextNode == null) {
			return null;
		}
		if (nextNode == Mdd.terminal) {
			return Mdd.terminal;
		}

		return nextNode.contains(idxArray);
	}	
	
	/*
	 * Return the successor node with index exists in nodeMap.
	 */
	public mddNode getSucc(int idx) {
		int blockIdx = idx & this.blockIdxMask;
		int arrayIdx = idx >> this.arrayIdxoffset;

		mddNode[] thisNodeBlk = this.getNodeMapBlock(blockIdx);
		if(thisNodeBlk ==null || arrayIdx >= thisNodeBlk.length)
			return null;
		
		return thisNodeBlk[arrayIdx];
	}
	
	private mddNode getSucc(int blockIdx, int arrayIdx) {
		mddNode[] thisNodeBlk = this.getNodeMapBlock(blockIdx);
		if(thisNodeBlk==null || arrayIdx >= thisNodeBlk.length)
			return null;
		
		return thisNodeBlk[arrayIdx];
	}
	
	/*
	 * Insert a succNode with index into the nodeMap, whose size is automatically adjusted.
	 */
	public boolean addSucc(int idx, mddNode succNode) {		
		int blockIdx = idx & this.blockIdxMask;
		int arrayIdx = idx >> this.arrayIdxoffset;
	
		mddNode[] thisNodeBlk = this.getNodeMapBlock(blockIdx);
		if(thisNodeBlk==null || arrayIdx >= thisNodeBlk.length) {
			this.resizeNodeMap(blockIdx, arrayIdx);
			thisNodeBlk = this.getNodeMapBlock(blockIdx);
		}
		
		thisNodeBlk[arrayIdx] = succNode;
		succNode.refCount++;
		this.updateBlockHash(blockIdx);
		return true;
	}
	
	private boolean addSucc(int blockIdx, int arrayIdx, mddNode succNode) {		
		mddNode[] thisNodeMapBlk = this.getNodeMapBlock(blockIdx);
		if(thisNodeMapBlk==null || arrayIdx >= thisNodeMapBlk.length) {
			this.resizeNodeMap(blockIdx, arrayIdx);
			thisNodeMapBlk = this.getNodeMapBlock(blockIdx);
		}
		
		thisNodeMapBlk[arrayIdx] = succNode;
		succNode.refCount++;
		this.updateBlockHash(blockIdx);
		return true;
	}
	
	/*
	 * Resize the nodeMap so that an element with 'index' can be inserted into the nodeMap.
	 */
	private void resizeNodeMap(int blockIdx, int arrayIdx) {
		mddNode[] thisNodeMapBlk = this.getNodeMapBlock(blockIdx);
		if(thisNodeMapBlk == null) {
			int newBlockSize = (arrayIdx / mddNode.blockSize + 1) * mddNode.blockSize;
			try {
				thisNodeMapBlk = new mddNode[newBlockSize];
				for (int i = 0; i < newBlockSize; i++) {
					thisNodeMapBlk[i] = null;
                }
				this.setNodeMapBlock(blockIdx, thisNodeMapBlk);
			} 
			catch (Exception e) {
				String errorMessage = String.format(
                            "blockIdx=%s, arrayIdx=%s, newBlockSize=%s\n",
                           blockIdx, arrayIdx,newBlockSize);
                    throw new RuntimeException(errorMessage, e);
			}
		}
		else if(arrayIdx >= thisNodeMapBlk.length) {
			int newBlockSize = (arrayIdx / mddNode.blockSize + 1) * mddNode.blockSize;
			mddNode[] newBlock = new mddNode[newBlockSize];
			for(int i = 0; i < newBlock.length; i++) {
				if(i < thisNodeMapBlk.length)
					newBlock[i] = thisNodeMapBlk[i];
				else
					newBlock[i] = null;
			}
			this.setNodeMapBlock(blockIdx, newBlock);
		}
	}
	
	private mddNode[] getNodeMapBlock(int blkIdx) {
		if (blkIdx == 0)
			return this.nodeMap_0;
		else if (blkIdx == 1)
			return this.nodeMap_1;
		else if (blkIdx == 2)
			return this.nodeMap_2;
		else if (blkIdx == 3)
			return this.nodeMap_3;
		else if (blkIdx == 4)
			return this.nodeMap_4;
		else if (blkIdx == 5)
			return this.nodeMap_5;
		else if (blkIdx == 6)
			return this.nodeMap_6;
		else if (blkIdx == 7)
			return this.nodeMap_7;
		else if (blkIdx == 8)
			return this.nodeMap_8;
		else if (blkIdx == 9)
			return this.nodeMap_9;
		else if (blkIdx == 10)
			return this.nodeMap_10;
		else if (blkIdx == 11)
			return this.nodeMap_11;
		else if (blkIdx == 12)
			return this.nodeMap_12;
		else if (blkIdx == 13)
			return this.nodeMap_13;
		else if (blkIdx == 14)
			return this.nodeMap_14;
		else if (blkIdx == 15)
			return this.nodeMap_15;
		else {
			System.out.println("Wrong block index " + blkIdx);
			System.exit(0);
		}
		return null;
	}

	private void setNodeMapBlock(int blkIdx, mddNode[] newNodeBlk) {
		if (blkIdx == 0)
			this.nodeMap_0 = newNodeBlk;
		else if (blkIdx == 1)
			this.nodeMap_1 = newNodeBlk;
		else if (blkIdx == 2)
			this.nodeMap_2 = newNodeBlk;
		else if (blkIdx == 3)
			this.nodeMap_3 = newNodeBlk;
		else if (blkIdx == 4)
			this.nodeMap_4 = newNodeBlk;
		else if (blkIdx == 5)
			this.nodeMap_5 = newNodeBlk;
		else if (blkIdx == 6)
			this.nodeMap_6 = newNodeBlk;
		else if (blkIdx == 7)
			this.nodeMap_7 = newNodeBlk;
		else if (blkIdx == 8)
			this.nodeMap_8 = newNodeBlk;
		else if (blkIdx == 9)
			this.nodeMap_9 = newNodeBlk;
		else if (blkIdx == 10)
			this.nodeMap_10 = newNodeBlk;
		else if (blkIdx == 11)
			this.nodeMap_11 = newNodeBlk;
		else if (blkIdx == 12)
			this.nodeMap_12 = newNodeBlk;
		else if (blkIdx == 13)
			this.nodeMap_13 = newNodeBlk;
		else if (blkIdx == 14)
			this.nodeMap_14 = newNodeBlk;
		else if (blkIdx == 15)
			this.nodeMap_15 = newNodeBlk;
		else {
			System.out.println("Wrong block index " + blkIdx);
			System.exit(0);
		}
	}
	
	@Override
	public boolean equals(Object other) {
		mddNode otherNode = (mddNode)other;
		if(level != otherNode.level)
			return false;
		
		for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
			mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);
			mddNode[] otherNodeBlk = otherNode.getNodeMapBlock(blkIter);

			if(thisNodeBlk == null && otherNodeBlk == null)
				continue;
			
			if(thisNodeBlk == null && otherNodeBlk != null)
				return false;
			
			if(thisNodeBlk != null && otherNodeBlk == null)
				return false;
		
			if(thisNodeBlk.length != otherNodeBlk.length)
				return false;
			
			for(int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++)
				if(thisNodeBlk[arrayIter] != otherNodeBlk[arrayIter])
					return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		if (this.hashVal != 0)
			return hashVal;

		final int prime = 31;
		int result = 1;
		result = prime * result + blkHashVal_0;
		result = prime * result + blkHashVal_1;
		result = prime * result + blkHashVal_10;
		result = prime * result + blkHashVal_11;
		result = prime * result + blkHashVal_12;
		result = prime * result + blkHashVal_13;
		result = prime * result + blkHashVal_14;
		result = prime * result + blkHashVal_15;
		result = prime * result + blkHashVal_2;
		result = prime * result + blkHashVal_3;
		result = prime * result + blkHashVal_4;
		result = prime * result + blkHashVal_5;
		result = prime * result + blkHashVal_6;
		result = prime * result + blkHashVal_7;
		result = prime * result + blkHashVal_8;
		result = prime * result + blkHashVal_9;
		this.hashVal = result;
		return result;
	}

	private void updateBlockHash(int blkIdx) {
		mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIdx);
		if (blkIdx == 0)
			this.blkHashVal_0 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 1)
			this.blkHashVal_1 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 2)
			this.blkHashVal_2 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 3)
			this.blkHashVal_3 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 4)
			this.blkHashVal_4 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 5)
			this.blkHashVal_5 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 6)
			this.blkHashVal_6 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 7)
			this.blkHashVal_7 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 8)
			this.blkHashVal_8 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 9)
			this.blkHashVal_9 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 10)
			this.blkHashVal_10 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 11)
			this.blkHashVal_11 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 12)
			this.blkHashVal_12 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 13)
			this.blkHashVal_13 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 14)
			this.blkHashVal_14 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else if (blkIdx == 15)
			this.blkHashVal_15 = Integer.rotateLeft(Arrays.hashCode(thisNodeBlk), this.level);
		else {
			System.out.println("Wrong block index " + blkIdx);
			System.exit(0);
		}
		this.hashVal = 0;
	}

	public boolean subSet(mddNode other) {
		for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
			mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);
			mddNode[] otherNodeBlk = other.getNodeMapBlock(blkIter);

			if(otherNodeBlk == null && thisNodeBlk != null)
				return false;
			if(thisNodeBlk == null)
				continue;
			if(thisNodeBlk.length > otherNodeBlk.length)
				return false;
			for(int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++) {
				mddNode thisSucc = this.getSucc(blkIter, arrayIter);
				mddNode otherSucc = other.getSucc(blkIter, arrayIter);
				if(thisSucc != null && otherSucc == null)
					return false;
				if(thisSucc != otherSucc)
					return false;
			}
		}
		return true;
	}
	


	
	public void increaseRefCnt() {
		refCount++;
	}
	
	public void decreaseRefCnt() {
		refCount--;
	}
	
	public int getRefCount() {
		return refCount;
	}
	
	public double pathCount(HashSet<mddNode> uniqueNodes) {
		uniqueNodes.add(this);
		double paths = 0.0;
		
		if(this == Mdd.terminal)
			return paths;
		
		for(int blkIter = 0; blkIter != mddNode.numBlocks; blkIter++) {
			mddNode[] thisNodeBlk = this.getNodeMapBlock(blkIter);

			if(thisNodeBlk == null)
				continue;
		
			for(int arrayIter = 0; arrayIter < thisNodeBlk.length; arrayIter++) {
				if(thisNodeBlk[arrayIter] == null)
					continue;
				
				if(thisNodeBlk[arrayIter] == Mdd.terminal)
					paths += 1;
				else
					paths += thisNodeBlk[arrayIter].pathCount(uniqueNodes);
			}
		}
		return paths;
	}
	
//	public void print() {
//		if(nodeMap.length == 0)
//			return;
//		
//		for(int blkIter = 0; blkIter < mddNode.numBlocks; blkIter++) {
//			if(this.nodeMap[blkIter] == null)
//				continue;
//
//			for(int arrayIter = 0; arrayIter < nodeMap[blkIter].length; arrayIter++) {
//				if(this.nodeMap[blkIter][arrayIter] == null)
//					continue;
//				System.out.println(this + " level = " + level + ",  " + (blkIter + (arrayIter << this.arrayIdxoffset)) + " -> " + nodeMap[blkIter][arrayIter] + "  refCount = " + nodeMap[blkIter][arrayIter].refCount);
//				nodeMap[blkIter][arrayIter].print();
//			}
//		}
//	}
}
