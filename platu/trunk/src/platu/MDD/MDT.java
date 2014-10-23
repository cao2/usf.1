package platu.MDD;

import java.util.*;

import platu.stategraph.*;

/*
 * This data structure cannot be used as stack as it allows node sharing.
 */

public class MDT {
	static mdtNode terminal = new mdtNode();

	private mdtNode root;
	private int stateCount;
	private int peakNodes;
	int height;
	int Size;
		
	/*
	 * Initialize MDD with the number of modules in the design model.
	 */
	public MDT(int levels) {
		root = new mdtNode();
		stateCount = 0;
		height = levels;
		peakNodes = 0;
	}
		
	public boolean add(State[] curIdxArray) {
//		boolean isNewState = root.push(curIdxArray, 0);
//		if(isNewState) this.Size++;
//		return isNewState;

		mdtNode curNode = root;
		while(true) {
			State curIdx = curIdxArray[curNode.level];
			mdtNode nextNode = curNode.nodeMap.get(curIdx);
			if(nextNode != null) {
				if(curNode.level == curIdxArray.length-1) {
					return false;
				}
				curNode = nextNode;
				continue;
			}
			else {
				if(curNode.level == curIdxArray.length-1) {
					curNode.nodeMap.put(curIdx, MDT.terminal);
					this.Size++;
					return true;
				}
				else {
					nextNode = new mdtNode();
					nextNode.level = curNode.level + 1;
					curNode.nodeMap.put(curIdx, nextNode);
					curNode = nextNode;
					continue;
				}
			}
		}
	}
		
	public State[] remove() {
		this.Size--;
		State[] results = new State[this.height];
		return root.pop(results);
	}
	
	public Stack<State[]> removeList() {
		this.Size--;
		State[] results = new State[this.height];
		return root.popList(results);
		
//		mdtNode curNode = root;
//		State[] stateArrayBase = new State[this.height];
//		Stack<State[]> stateArrayList = null;
//		Stack<mdtNode> nodeStack = new Stack<mdtNode>();
//		Stack<State> stateStack = new Stack<State>();
//		while(true) {
//			if(curNode.level != this.height-1) {
//				Set<State> keySet = curNode.nodeMap.keySet();
//				for(State st : keySet) {
//					mdtNode nextNode = curNode.nodeMap.get(st);
//					stateArrayBase[curNode.level] = st;
//					nodeStack.push(curNode);
//					stateStack.push(st);
//					curNode = nextNode;
//					break;
//				}
//			}
//			else {
//				Stack<State[]> results = new Stack<State[]>();
//				Set<State> keySet = curNode.nodeMap.keySet();
//				for(State st : keySet) {
//					State[] stateArray = stateArrayBase.clone();
//					stateArray[curNode.level] = st;
//					results.push(stateArray);
//				}
//				
//				while(nodeStack.empty() == false) {
//					System.out.println(nodeStack.size());
//					mdtNode nextNode = curNode;
//					curNode = nodeStack.pop();
//					State curState = stateStack.pop();
//					if(nextNode.empty()==true)
//						curNode.nodeMap.remove(curState);
//					else
//						break;
//				}
//				
//				System.out.println(nextNod);
//				return results;
//			}
//		}
	}
	
//	public State[] peek() {
//		return root.peek(0);
//	}
	
	public boolean contains(State[] stateArray) {
		return root.contains(stateArray, 0);
	}
	
	public boolean empty() {
		return root.empty();
	}
	
	public int size() {
		return this.Size;
	}
	
	public int nodeCnt() {
		return root.nodeCnt();
	}
}
