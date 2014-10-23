package platu.expression;

import java.util.HashMap;
import java.util.HashSet;
import platu.lpn.VarType;

public class VarNode implements ExpressionNode {
	protected String name;
	protected VarType type = VarType.INTERNAL;
	protected int index = -1;
	protected boolean shared = false;
	protected boolean atomic = false;
	
	public VarNode(String name){
		this.name = name;
	}
	
	public VarNode(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	public void setAtomic(){
		this.atomic = true;
	}
	
	public boolean isAtomic(){
		return this.atomic;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setType(VarType type){
		this.type = type;
	}
	
	public VarType getType(){
		return this.type;
	}
	
	/**
	 * Returns the static name of a variable.  
	 * Array elements will return only the name of the array
	 * @return Variable name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Returns the dynamic name of a variable.
	 * Array elements evaluate dynamic indices to return the element's name.
	 * @param stateVector - LPN state's vector
	 * @return
	 */
	public String getSignature(int[] stateVector){
		return this.name;
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		return stateVector[this.index];
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	/**
	 * Returns the variable's corresponding position in the state vector.  
	 * The state vector is used to evaluate dynamic array indices.
	 * @param stateVector - LPN state's vector
	 * @return state vector index
	 */
	public int getIndex(int[] stateVector){
		return this.index;
	}
	
	public void setShared(){
		this.shared = true;
	}
	
	public boolean isShared(){
		return this.shared;
	}
	
	public void addVariables(HashSet<VarNode> variables){
		variables.add(this);
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	@Override
	public VarNode clone(){
		VarNode cloneNode = new VarNode(this.name, this.index);
		cloneNode.setType(this.type);
		
		if(this.shared)
			cloneNode.setShared();
		
		if(this.atomic)
			cloneNode.setAtomic();
		
		return cloneNode;
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		VarNode node = variables.get(this.name);
		if(node == null){
			node = this.clone();
		}
		
		return node;
	}
}
