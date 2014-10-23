package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class ParenNode implements ExpressionNode{
	ExpressionNode insideExpression = null;
	
	public ParenNode(ExpressionNode insideExpr){
		this.insideExpression = insideExpr;
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		return insideExpression.evaluate(stateVector, errorCode);
	}
	
	public void addVariables(HashSet<VarNode> variables){
		insideExpression.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return "(" + insideExpression.toString() + ")";
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new ParenNode(this.insideExpression.copy(variables));
	}
}
