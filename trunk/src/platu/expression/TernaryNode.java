package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class TernaryNode implements ExpressionNode {
	ExpressionNode Condition = null;
	ExpressionNode LeftOperand = null;
	ExpressionNode RightOperand = null;
	
	public TernaryNode(ExpressionNode condition, ExpressionNode leftOperand, ExpressionNode rightOperand){
		this.Condition = condition;
		this.LeftOperand = leftOperand;
		this.RightOperand = rightOperand;
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		if(this.Condition.evaluate(stateVector, errorCode) != 0)
			return LeftOperand.evaluate(stateVector, errorCode);
		
		return RightOperand.evaluate(stateVector, errorCode);
	}
	
	public void addVariables(HashSet<VarNode> variables){
		LeftOperand.addVariables(variables);
		RightOperand.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return Condition + "?" + LeftOperand.toString() + ":" + RightOperand.toString();
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new TernaryNode(this.Condition.copy(variables), this.LeftOperand.copy(variables), this.RightOperand.copy(variables));
	}
}
