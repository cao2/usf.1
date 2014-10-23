package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Interface that should be implemented by each type of expression node.
 */
public interface ExpressionNode {
	/**
	 * Evaluates this expression and returns the value.
	 * @param stateVector - LPN state's state vector
	 * @param errorCode - Indicates where an expression error occurred
	 * @return - Integer value
	 */
    public int evaluate(int[] stateVector, ExpressionError errorCode);
    
    /**
     * Adds the variables used by the expression.
     * @param variables - Set to add the variables
     */
	public void addVariables(HashSet<VarNode> variables);
	
	@Override
	public String toString();
	
	/**
     * Returns a copy of the top level node and all subsequent nodes.
     * Variable nodes are replaced with the VarNode indexed at its name
     * in the HashMap variables, otherwise a new object is created with
     * the same attributes.  Constant nodes are not copied.
     * @param variables - HashMap of variable nodes keyed with their name
     * @return ExpressionNode - New ExpressionNode object
     */
	public ExpressionNode copy(HashMap<String, VarNode> variables);
}

