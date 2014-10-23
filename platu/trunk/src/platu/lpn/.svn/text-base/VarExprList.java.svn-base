package platu.lpn;

import java.util.ArrayList;
import java.util.HashMap;
import platu.expression.Expression;
import platu.expression.VarNode;

public class VarExprList extends ArrayList<VarExpr> {

	private static final long serialVersionUID = -1369767272263074498L;

	
	public VarExprList() {
        super();
    }

    @Override
    public String toString() {
        String ret = "";
        int x = 0;
        try {
            for (; x < this.size() - 1; x++) {
                ret += this.get(x).getVar() + " = " + this.get(x).getExpr() + ";";
            }//x=this.size()-1 ;
            if (size() > 0) {
                ret += this.get(x).getVar() + " = " + this.get(x).getExpr();
            }
        } catch (Exception e) {
            System.err.print("ret=" + ret + "\tx=" + x + "\tsize=" + size() + "\tget(x)=");
            try {
                System.err.println(get(x));
            } catch (Exception e2) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            e.printStackTrace();
        }
        return ret;
    }

    public Expression get(VarNode var){
        for(VarExpr ve : this){
            if(ve.getVar() == var){
            	return ve.getExpr();
            }
        }
        
        return null;
    }
    
    public VarExprList copy(HashMap<String, VarNode> variables){
    	VarExprList copy = new VarExprList();
    	for(VarExpr v : this){
    		copy.add(v.copy(variables));
    	}
    	
    	return copy;
    }
}
