package platu.util;

import java.util.Arrays;
import java.util.HashMap;

public class MddNode {

	private MddNode[] nodeMap;
	int level;
	int refCount;

	public MddNode() {
		this.nodeMap = new MddNode[256];
		this.level = -1;
		this.refCount = 0;

		for (int i = 0; i < 256; i++)
			this.nodeMap[i] = null;
	}

	MddNode makeCopy() {
		MddNode newNode = new MddNode();
		newNode.level = this.level;
		newNode.refCount = 0;
		for (int i = 0; i < 256; i++)
			newNode.nodeMap[i] = this.nodeMap[i];

		return newNode;
	}

	public void setLevel(int l) {
		this.level = l;
	}

	public int getLevel() {
		return this.level;
	}

	public int getRefCount() {
		return this.refCount;
	}

	public void addSucc(int idx, MddNode node) {
		node.increaseRefCnt();
		this.nodeMap[idx] = node;
	}

	public MddNode getSucc(int idx) {
		return this.nodeMap[idx];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(nodeMap);
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
		MddNode other = (MddNode) obj;

		for (int i = 0; i < 256; i++) {
			if (this.nodeMap[i] != other.nodeMap[i])
				return false;
		}
		return true;
	}

	public void increaseRefCnt() {
		this.refCount++;
	}

	public void decreaseRefCnt() {
		if (refCount == 1) {
			System.out.println("Cannot decrease the ref count of 1");
			System.exit(0);
		}
		this.refCount--;
	}

	public MddNode add(int[] idxArray, HashMap<MddNode, MddNode>[] uniqueNodeTbl) {
		if (this.level == -1) {
			System.out.println("level is not right 1");
			System.exit(0);
		}

		int curIdx = idxArray[this.level];

		MddNode nextNode = this.getSucc(curIdx);

		if (nextNode == null) {
			if (this.level == idxArray.length - 1) {
				uniqueNodeTbl[this.level].remove(this);
				this.addSucc(curIdx, Mdd.terminal);

				MddNode newThisNode = uniqueNodeTbl[this.level].get(this);
				if (newThisNode != null) {
					return newThisNode;
				}

				uniqueNodeTbl[this.level].put(this, this);
				return this;
			} else {
				nextNode = new MddNode();
				nextNode.level = this.level + 1;

				MddNode newNextNode = nextNode.add(idxArray, uniqueNodeTbl);
				if (newNextNode != nextNode)
					nextNode = newNextNode;
				nextNode.refCount++;

				uniqueNodeTbl[this.level].remove(this);

				this.addSucc(curIdx, nextNode);

				MddNode newThis = uniqueNodeTbl[this.level].get(this);
				if (newThis != null) {
					for (int idx = 0; idx < 256; idx++) {
						if (this.nodeMap[idx] != null)
							this.nodeMap[idx].refCount--;
					}
					return newThis;
				}

				uniqueNodeTbl[this.level].put(this, this);
				return this;
			}
		} else if (nextNode == Mdd.terminal) {
			// System.out.println("mddNode: should not reach here. Abort!");
			// System.exit(0);
			return Mdd.terminal;
		} else {
			MddNode newNextNode = nextNode;
			if (nextNode.refCount > 1) {
				newNextNode = nextNode.makeCopy();
				nextNode.refCount--;

				MddNode newNextNode_1 = newNextNode.add(idxArray, uniqueNodeTbl);

				if (newNextNode_1 != newNextNode)
					newNextNode = newNextNode_1;
			} else {
				newNextNode = nextNode.add(idxArray, uniqueNodeTbl);

				// if no next node is splitted and the next node does not have
				// equivalent,
				// do nothing further.
				if (newNextNode == nextNode)
					return this;

				nextNode.refCount--;
				for (int idx = 0; idx < 256; idx++) {
					if (nextNode.nodeMap[idx] != null)
						nextNode.nodeMap[idx].refCount++;
				}
			}

			uniqueNodeTbl[this.level].remove(this);

			newNextNode.refCount++;
			this.addSucc(curIdx, newNextNode);

			MddNode newThis = uniqueNodeTbl[this.level].get(this);
			if (newThis != null) {
				for (int idx = 0; idx < 256; idx++) {
					if (this.nodeMap[idx] != null)
						this.nodeMap[idx].refCount--;
				}
				return newThis;
			}

			uniqueNodeTbl[this.level].put(this, this);
			return this;
		}
	}

	public MddNode compress(HashMap<MddNode, MddNode>[] uniqueNodeTbl) {
		int mddHeight = uniqueNodeTbl.length;

		if (this.level < mddHeight - 1) {
			for (int idx = 0; idx < 256; idx++) {
				MddNode succNode = this.nodeMap[idx];

				if (succNode == null)
					continue;

				MddNode newSuccNode = succNode.compress(uniqueNodeTbl);
				if (newSuccNode != succNode)
					this.addSucc(idx, newSuccNode);
			}
		}

		MddNode newThis = uniqueNodeTbl[this.level].get(this);

		if (newThis == null) {
			uniqueNodeTbl[this.level].put(this, this);
			return this;
		} else if (newThis != this)
			return newThis;

		return this;
	}

	public boolean subSet(MddNode other) {
		for (int idx = 0; idx < 256; idx++) {
			MddNode thisSucc = this.getSucc(idx);
			MddNode otherSucc = other.getSucc(idx);
			if (thisSucc != null && otherSucc == null)
				return false;
			if (thisSucc != otherSucc)
				return false;
		}
		return true;
	}

	public static int numCalls = 0;
	public static int cacheNodes = 0;
	public static int splits_level1 = 0;
	public static int splits_level2 = 0;
	public static int splits_level3 = 0;

	public MddNode union(final MddNode other, HashMap<MddNode, MddNode>[] uniqueNodeTbl, HashMap<MddNode, HashMap<MddNode, MddNode>> unionCache) {
		numCalls++;

		MddNode thisCopy = this.makeCopy();

		for (int idx = 0; idx < 256; idx++) {
			MddNode thisSucc = this.getSucc(idx);
			MddNode otherSucc = other.getSucc(idx);

			if (otherSucc == null)
				continue;

			if (thisSucc == null) {
				thisCopy.addSucc(idx, otherSucc);
				continue;
			}

			// When successors are terminals, continue.
			if (thisSucc == Mdd.terminal || otherSucc == Mdd.terminal)
				continue;

			if (thisSucc == otherSucc || otherSucc.subSet(thisSucc) == true)
				continue;

			MddNode succCached = null;
			HashMap<MddNode, MddNode> second = unionCache.get(thisSucc);
			if (second != null) {
				succCached = second.get(otherSucc);
			}

			if (succCached == null) {
				MddNode succUnion = thisSucc.union(otherSucc, uniqueNodeTbl, unionCache);

				// Add newSucc into the cache to avoid call
				// union(thisSuccOriginal, otherSucc) again.
				HashMap<MddNode, MddNode> secondCache = unionCache.get(thisSucc);
				cacheNodes++;
				if (secondCache == null) {
					secondCache = new HashMap<MddNode, MddNode>();
					secondCache.put(otherSucc, succUnion);
					unionCache.put(thisSucc, secondCache);
				} else
					secondCache.put(otherSucc, succUnion);

				succCached = succUnion;
			}
			thisSucc.remove(uniqueNodeTbl);
			thisCopy.addSucc(idx, succCached);
		}

		MddNode result = uniqueNodeTbl[thisCopy.level].get(thisCopy);
		if (result == null) {
			uniqueNodeTbl[thisCopy.level].put(thisCopy, thisCopy);
			return thisCopy;
		}

		thisCopy.remove(uniqueNodeTbl);
		return result;
	}

	public void remove(HashMap<MddNode, MddNode>[] uniqueNodeTbl) {
		int mddHeight = uniqueNodeTbl.length;

		this.refCount--;

		if (this.refCount > 0)
			return;

		if (this.level < mddHeight - 1) {
			for (int idx = 0; idx < 256; idx++) {
				MddNode thisSucc = this.getSucc(idx);
				if (thisSucc == null)
					continue;
				thisSucc.remove(uniqueNodeTbl);
			}
			uniqueNodeTbl[this.level].remove(this);
		}
	}

	// public double pathCount(HashSet<mddNode> uniqueNodes) {
	// uniqueNodes.add(this);
	// double paths = 0.0;
	//
	// if(this == Mdd.terminal)
	// return paths;
	//
	// for(int blkIter = 0; blkIter != mddNode.numBlocks; blkIter++) {
	// if(this.nodeMap[blkIter] == null)
	// continue;
	//
	// for(int arrayIter = 0; arrayIter < this.nodeMap[blkIter].length;
	// arrayIter++) {
	// if(this.nodeMap[blkIter][arrayIter] == null)
	// continue;
	//
	// if(this.nodeMap[blkIter][arrayIter] == Mdd.terminal)
	// paths += 1;
	// else
	// paths += nodeMap[blkIter][arrayIter].pathCount(uniqueNodes);
	// }
	// }
	// return paths;
	// }
}
