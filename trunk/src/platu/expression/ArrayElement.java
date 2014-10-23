package platu.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import platu.main.Options;

/**
 * Expression node used to represent a dynamic and static array elements.
 */
public class ArrayElement extends VarNode{
	// List of index expressions
	List<Expression> indexExpressions = null;
	
	// array to which this dynamic array element belongs
	ArrayNode array = null;
	
	/**
	 * Constructor
	 * @param array - Array node associated with this element
	 * @param indexExprs - The list of expressions used for this array element
	 */
	public ArrayElement(ArrayNode array, List<Expression> indexExprs){
		super(array.getName());
		this.array = array;
		this.indexExpressions = indexExprs;
	}
	
	@Override
	public String getName(){
		return array.getName();
	}
	
	/**
	 * Returns the array node to which this array element belongs.
	 * @return Array node
	 */
	public ArrayNode getArray(){
		return this.array;
	}
	
	@Override
	public int evaluate(int[] statevector, ExpressionError errorCode){
		List<Integer> indexValues = new ArrayList<Integer>(this.indexExpressions.size());
		for(Expression n : this.indexExpressions){
			indexValues.add(n.evaluate(statevector));
			if(n.getErrorCode() != ExpressionError.NONE){
				errorCode = n.getErrorCode();
				n.resetErrorCode();
			}
		}
		
		return this.array.getElement(indexValues, errorCode).evaluate(statevector, errorCode);
	}
	
	@Override
	public void addVariables(HashSet<VarNode> variables){
		variables.add(this.array);
	}
	
	@Override
	public String getSignature(int[] stateVector){
		List<Integer> indexValues = new ArrayList<Integer>(this.indexExpressions.size());
		
		for(Expression n : this.indexExpressions){
			indexValues.add(n.evaluate(stateVector));
			if(n.getErrorCode() != ExpressionError.NONE){
				if(Options.getVerbosity() > 25){
					System.err.println("error in ArrayElement.getIndex(): expression error " + n.getErrorCode() + " for " + this.getName() + " index " + n.toString());
				}
				
				n.resetErrorCode();
			}
		}
		
		ExpressionError errorCode = ExpressionError.NONE;
		VarNode element = this.array.getElement(indexValues, errorCode);
		if(errorCode != ExpressionError.NONE){
			if(Options.getVerbosity() > 25){
				System.err.println("error in ArrayElement.getSignature(): expression error " + errorCode + " for " + this.getName());
			}
		}
		
		return element.getName();
	}
	
	@Override
	public String toString(){
		String s = array.getName();
		for(Expression n : this.indexExpressions){
			s += "[" + n.toString() + "]";
		}
		
		return s;
	}
	
	@Override
	public int getIndex(int[] stateVector){
		List<Integer> indexValues = new ArrayList<Integer>(this.indexExpressions.size());
		for(Expression n : this.indexExpressions){
			indexValues.add(n.evaluate(stateVector));
			if(n.getErrorCode() != ExpressionError.NONE){
				if(Options.getVerbosity() > 25){
					System.err.println("error in ArrayElement.getIndex(): expression error " + n.getErrorCode() + " for " + this.getName() + " index " + n.toString());
				}
				n.resetErrorCode();
			}
		}
		
		ExpressionError errorCode = ExpressionError.NONE;
		VarNode element = this.array.getElement(indexValues, errorCode);
		if(errorCode != ExpressionError.NONE){
			if(Options.getVerbosity() > 25){
				System.err.println("error in ArrayElement.getIndex(): expression error " + errorCode + " for " + this.getName());
			}
		}
		
		return element.getIndex(stateVector);
	}
	
	@Override
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		List<Expression> copyList = new ArrayList<Expression>(this.indexExpressions.size());
		for(Expression n : this.indexExpressions){
			copyList.add(n.copy(variables));
		}
		
		return new ArrayElement((ArrayNode) this.array.copy(variables), copyList);
	}
}
