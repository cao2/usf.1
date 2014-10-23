package platu.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArrayNode extends VarNode{
	
	/**
	 * Number of dimensions of the array
	 */
	private int numDimensions = 0;
	
	/**
	 * List of the array's dimension sizes
	 */
	private List<Integer> dimensionList = null;
	
	/**
	 * Sequential list of the array's element nodes.
	 */
	private List<VarNode> elementList = null;
	
	/**
	 * Constructor
	 * @param name - Array name
	 * @param dimensions - Number of dimensions
	 * @param dimensionList - Dimension sizes
	 * @param elementList - Sequential list of element VarNodes
	 */
	public ArrayNode(String name, int numDimensions, List<Integer> dimensionList, List<VarNode> elementList){
		super(name);
		this.numDimensions = numDimensions;
		this.dimensionList = dimensionList;
		this.elementList = elementList;
	}
	
	/**
	 * Returns the sequential list of the array's element nodes.
	 * @return List of array elements
	 */
	public List<VarNode> getElementList(){
		return this.elementList;
	}

	/**
	 * Returns the number of dimensions contained in this array.
	 * @return Number of dimensions
	 */
	public int numDimensions(){
		return this.numDimensions;
	}
	
	/**
	 * Returns the number of array elements contained in this array.
	 * @return Number of elements
	 */
	public int size(){
		return this.elementList.size();
	}
	
	/**
	 * Returns the array element at the indices.
	 * Sets Expression's static error flag if an array index is out of bounds, 
	 * and returns the first element.
	 * @param indexList - List of indices for each dimension
	 * @param errorCode - Indicates if an expression error occurred
	 * @return Array element node
	 */
	public VarNode getElement(List<Integer> indexList, ExpressionError errorCode){
		if(this.numDimensions != indexList.size()){
			System.err.println("error: incorrect dimensions for array " + this.name);
			System.exit(1);
		}
		
		// make sure all indices are within the bounds
		for(int i = 0; i < indexList.size(); i++){
			int index = indexList.get(i);
			if(index < 0 || index >= this.dimensionList.get(i)){
				errorCode = ExpressionError.ARRAYOUTOFBOUNDS;
				return this.elementList.get(0);
			}
		}
		
		int offset = 0;
		if(this.dimensionList.size() > 1){
			offset = indexList.get(0) * this.dimensionList.get(1);
			for(int i = 2; i < this.dimensionList.size(); i++){
				offset = offset + indexList.get(i-1);
				offset = offset * this.dimensionList.get(i);
			}
		}
		
		offset = offset + indexList.get(this.numDimensions - 1);
		
		return this.elementList.get(offset);
	}
	
	@Override
	public void addVariables(HashSet<VarNode> variables){
		variables.add(this);
	}
	
	/**
	 * Returns a list containing the size of each dimension.
	 * @return List of dimensions
	 */
	public List<Integer> getDimensionList(){
		return this.dimensionList;
	}
	
	@Override
	public VarNode clone(){
		return new ArrayNode(this.name, this.numDimensions, this.dimensionList, this.elementList);
	}
	
	@Override
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		VarNode varNode = variables.get(this.name);
		if(varNode != null){
			 return varNode;
		}
		else{
			int iter = dimensionList.size() - 1;
			int dIndex = 0;
			int arraySize = dimensionList.get(dIndex++);
			int lastSize = 0;
			List<Object> topLevelArray = new ArrayList<Object>(arraySize);
			
			Queue<List<Object>> arrayQueue = new LinkedList<List<Object>>();
			arrayQueue.offer(topLevelArray);
					
			while(iter > 0){
				lastSize = arraySize;
				arraySize = dimensionList.get(dIndex++);
				int qSize = arrayQueue.size();
				for(int i = 0; i < qSize; i++){
					List<Object> array = arrayQueue.poll();
					for(int j = 0 ; j < lastSize; j++){
						List<Object> newArray = new ArrayList<Object>(arraySize);
						array.add(j, newArray);
						arrayQueue.offer(newArray);
					}
				}
				
				iter--;
			}
			
			dIndex--;
			arraySize = dimensionList.get(dIndex);
			
			int nodeIndex = 0;
			List<VarNode> varList = new ArrayList<VarNode>();
			while(!arrayQueue.isEmpty()){
				List<Object> array = arrayQueue.poll();
				for(int i = 0; i < arraySize; i++){
					VarNode node = this.elementList.get(nodeIndex++);
					
					VarNode newNode = variables.get(node.getName());
					if(newNode == null){
						newNode = node.clone();
					}
					
					array.add(i, newNode);
					varList.add(newNode);
				}
			}
	
			ArrayNode newArray = new ArrayNode(this.name, this.dimensionList.size(), this.dimensionList, varList);
			newArray.setType(this.type);
			if(this.shared)
				newArray.setShared();
			
			return newArray;
		}
	}
}
