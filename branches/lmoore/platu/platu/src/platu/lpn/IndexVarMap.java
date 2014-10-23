package platu.lpn;

import java.util.*;
import platu.expression.*;

public class IndexVarMap {
	
	protected HashMap<VarNode, VarNode> uniqueVarTbl;
	protected HashMap<String, VarNode> str2VarTbl;
	protected HashMap<Integer, VarNode> idx2VarTbl;
	
	public IndexVarMap() {
		uniqueVarTbl = new HashMap<VarNode, VarNode>();
		str2VarTbl = new HashMap<String, VarNode>();
		idx2VarTbl = new HashMap<Integer, VarNode>();
	}

	public VarNode add(VarNode var) {
		VarNode objCopy = uniqueVarTbl.get(var);
		if(objCopy != null)
			return objCopy;
		
		this.uniqueVarTbl.put(var, var);
		this.str2VarTbl.put(var.getName(), var);
		int idx = this.idx2VarTbl.size();
		var.setIndex(idx);
		this.idx2VarTbl.put(idx, var);
		return var;
	}
	
	public VarNode get(String label) {
		return this.str2VarTbl.get(label);
	}
	
	public VarNode get(Integer index) {
		return this.idx2VarTbl.get(index);
	}
	
	public int size() {
		return this.str2VarTbl.size();
	}
}
