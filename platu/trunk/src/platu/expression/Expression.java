package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class Expression{
	private ExpressionError ErrorCode = ExpressionError.NONE;
	private String ExprString = null;
	private ExpressionNode ExprNode = null;
	private HashSet<VarNode> Variables = null;
	
	  
	/**
	 * Constructor
	 * @param expression - Top level expression node associated with this expression
	 */
	public Expression(ExpressionNode expression){
		this.ExprNode = expression;
	}
	
	
	/**
	 * Returns this expression's error code.
	 * @return Error code
	 */
	public final ExpressionError getErrorCode(){
		return ErrorCode;
	}
	
	/**
	 * Resets this expression's error code back to ExpressionError.NONE.
	 */
	public void resetErrorCode(){
		ErrorCode = ExpressionError.NONE;
	}
	
	/**
	 * Evaluates this expression for a given state, and returns the expression's value.
	 * @param stateVector - State's state vector
	 * @return Expression's value
	 */
	public int evaluate(int[] stateVector){
		return ExprNode.evaluate(stateVector, ErrorCode);
	}
	
	/**
	 * Returns the set of variables used in this expression.
	 * @return Set of variable VarNodes
	 */
	public HashSet<VarNode> getVariables(){
		if(this.Variables == null){
			this.Variables = new HashSet<VarNode>();
			this.ExprNode.addVariables(Variables);
		}
		
		return Variables;
	}

	@Override
	public String toString(){
		if(ExprString == null){
			ExprString = ExprNode.toString();
		}
		
		return ExprString;
	}
	
	/**
     * Returns a copy of the expression and all subsequent nodes.
     * Variable nodes are replaced with the VarNode indexed at its name
     * in the HashMap variables, otherwise a new object is created with
     * the same attributes.  Constant nodes are not copied.
     * @param variables - HashMap of variable nodes keyed with their name
     * @return ExpressionNode - New ExpressionNode object
     */
	public Expression copy(HashMap<String, VarNode> variables){
		return new Expression(this.ExprNode.copy(variables));
	}
}
