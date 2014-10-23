package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class FalseNode implements ExpressionNode{
	public FalseNode(){
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		return 0;
	}
	
	public void addVariables(HashSet<VarNode> variables){
	}
	
	@Override
	public String toString(){
		return "false";
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new FalseNode();
	}
}
