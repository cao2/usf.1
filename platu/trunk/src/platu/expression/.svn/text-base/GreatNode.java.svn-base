package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class GreatNode implements ExpressionNode {
	ExpressionNode LeftOperand = null;
	ExpressionNode RightOperand = null;
	
	public GreatNode(ExpressionNode leftOperand, ExpressionNode rightOperand){
		this.LeftOperand = leftOperand;
		this.RightOperand = rightOperand;
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		if(LeftOperand.evaluate(stateVector, errorCode) > RightOperand.evaluate(stateVector, errorCode))
			return 1;
		
		return 0;
	}
	
	public void addVariables(HashSet<VarNode> variables){
		LeftOperand.addVariables(variables);
		RightOperand.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return LeftOperand.toString() + ">" + RightOperand.toString();
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new GreatNode(this.LeftOperand.copy(variables), this.RightOperand.copy(variables));
	}
}
