package platu.common;

import java.util.*;
import platu.common.PlatuObj;

public class IndexObjMap<T extends PlatuObj> {
	
	protected HashMap<T, T> uniqueObjTbl;
	protected Vector<T> idx2ObjTbl;
	
	public IndexObjMap() {
		uniqueObjTbl = new HashMap<T, T>();
		idx2ObjTbl = new Vector<T>();
	}

	public T add(T obj) {
		T objCopy = uniqueObjTbl.get(obj);
		if(objCopy != null){
			return objCopy;
		}
		
		this.uniqueObjTbl.put(obj, obj);
		
		
		int idx = this.idx2ObjTbl.size();
		obj.setIndex(idx);
		if(idx > this.idx2ObjTbl.size())
			this.idx2ObjTbl.ensureCapacity(this.idx2ObjTbl.size() + 20);
		this.idx2ObjTbl.add(idx, obj);
		return obj;
	}
	
	public boolean contains(T obj) {
		return this.uniqueObjTbl.containsKey(obj);
	}
	
	
	public T get(Integer index) {
		return this.idx2ObjTbl.get(index);
	}
	
	public T get(T obj){
		return this.uniqueObjTbl.get(obj);
	}
	
	public int size() {
		return this.uniqueObjTbl.size();
	}
}
