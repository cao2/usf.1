package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class BitXorNode implements ExpressionNode {
	ExpressionNode LeftOperand = null;
	ExpressionNode RightOperand = null;
	
	public BitXorNode(ExpressionNode leftOperand, ExpressionNode rightOperand){
		this.LeftOperand = leftOperand;
		this.RightOperand = rightOperand;
	}

	public int evaluate(int[] stateVector, ExpressionError errorCode){
		return LeftOperand.evaluate(stateVector, errorCode) ^ RightOperand.evaluate(stateVector, errorCode);
	}
	
	public void addVariables(HashSet<VarNode> variables){
		LeftOperand.addVariables(variables);
		RightOperand.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return LeftOperand.toString() + "^" + RightOperand.toString();
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new BitXorNode(this.LeftOperand.copy(variables), this.RightOperand.copy(variables));
	}
}
