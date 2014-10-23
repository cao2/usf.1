package platu.common;

import java.util.Arrays;

import platu.main.*;
import platu.BinaryTree.*;

public class BtreeTable extends SetIntTuple {
	
	BinaryTree StateTable = null;
	int Size = 0;

	public BtreeTable() {
		this.StateTable = new BinaryTree(false);
		this.Size = 0;
	}
	
	public int add(int[] IntArray) {
		int newEle = StateTable.add(IntArray);
		//System.out.println(Arrays.toString(IntArray) + "  " + newEle);
		if(newEle != 0)
			this.Size++;
		return newEle==-1 ? 0 : 1;
	}
	
	public boolean contains(int[] IntArray) {
		boolean existing = this.StateTable.contains(IntArray);
		//System.out.println(existing);
		return existing;
	}
	
	public int size() {
		return this.Size;
	}
	
	public String stats() {
		return "Element count = "+ this.StateTable.elementCount() + ",  Tree node count = " + this.StateTable.nodeCount();
	}
}
