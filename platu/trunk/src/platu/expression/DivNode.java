package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class DivNode implements ExpressionNode {
	ExpressionNode LeftOperand = null;
	ExpressionNode RightOperand = null;
	
	public DivNode(ExpressionNode leftOperand, ExpressionNode rightOperand){
		this.LeftOperand = leftOperand;
		this.RightOperand = rightOperand;
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		int value = 0;
		
		try{
			value = LeftOperand.evaluate(stateVector, errorCode) / RightOperand.evaluate(stateVector, errorCode);
		}
		catch(ArithmeticException e){
			errorCode = ExpressionError.DIVISIONBYZERO;
		}
		
		return value;
	}
	
	public void addVariables(HashSet<VarNode> variables){
		LeftOperand.addVariables(variables);
		RightOperand.addVariables(variables);
	}
	
	@Override
	public String toString(){
		return LeftOperand.toString() + "/" + RightOperand.toString();
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new DivNode(this.LeftOperand.copy(variables), this.RightOperand.copy(variables));
	}
}
