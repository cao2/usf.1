package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class AndNode implements ExpressionNode {
	ExpressionNode LeftOperand = null;
	ExpressionNode RightOperand = null;
	
	public AndNode(ExpressionNode leftOperand, ExpressionNode rightOperand){
		this.LeftOperand = leftOperand;
		this.RightOperand = rightOperand;
	}
	
	public int evaluate(int[] statevector, ExpressionError errorCode){
		if(LeftOperand.evaluate(statevector, errorCode) == 0 || RightOperand.evaluate(statevector, errorCode) == 0)
			return 0;
		
		return 1;
	}
	
	public void addVariables(HashSet<VarNode> variables){
		LeftOperand.addVariables(variables);
		RightOperand.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return LeftOperand.toString() + "&&" + RightOperand.toString();
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new AndNode(this.LeftOperand.copy(variables), this.RightOperand.copy(variables));
	}
}
