package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class BitNegNode implements ExpressionNode {
	ExpressionNode RightOperand = null;
	
	public BitNegNode(ExpressionNode rightOperand){
		this.RightOperand = rightOperand;
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		return ~RightOperand.evaluate(stateVector, errorCode);
	}
	
	public void addVariables(HashSet<VarNode> variables){
		RightOperand.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return "~" + RightOperand.toString();
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new BitNegNode(this.RightOperand.copy(variables));
	}
}
