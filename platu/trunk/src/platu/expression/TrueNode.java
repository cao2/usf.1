package platu.expression;

import java.util.HashMap;
import java.util.HashSet;

public class TrueNode implements ExpressionNode{
	public TrueNode(){
	}
	
	public int evaluate(int[] stateVector, ExpressionError errorCode){
		return 1;
	}
	
	public void addVariables(HashSet<VarNode> variables){
	}
	
	@Override
	public String toString(){
		return "true";
	}
	
	public ExpressionNode copy(HashMap<String, VarNode> variables){
		return new TrueNode();
	}
}
