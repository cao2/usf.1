package platu.common;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public  class DualHashMap<Key, Value> {
    private HashMap<Key, Value> KeyMap;
    private HashMap<Value, Key> ValueMap;

    public DualHashMap(int size) {
        this.KeyMap = new HashMap<Key, Value>(size);
        this.ValueMap = new HashMap<Value, Key>(size);
    }

    public DualHashMap() {
    	this.KeyMap = new HashMap<Key, Value>();
        this.ValueMap = new HashMap<Value, Key>();
    }

    public DualHashMap(HashMap<Key, Value> other) {
    	this.KeyMap = new HashMap<Key, Value>(other.size());
    	this.ValueMap = new HashMap<Value, Key>(other.size());
        for (Entry<Key, Value> e : other.entrySet()) {
            insert(e.getKey(), e.getValue());
        }
    }

    public DualHashMap(HashMap<Key, Value> other, int size) {
        this.KeyMap = new HashMap<Key, Value>(size);
        this.ValueMap = new HashMap<Value, Key>(size);
        for (Entry<Key, Value> e : other.entrySet()) {
            insert(e.getKey(), e.getValue());
        }
    }

    /**
     * Adds a <key, value> pair into the dual hash map.  This will override existing key and value pairs.
     * @param key
     * @param value
     */
    public void insert(Key key, Value value) {
        this.KeyMap.put(key, value);
        this.ValueMap.put(value, key);
    }

    /**
     * Removes the mapping associated with the Key.
     * @param key
     */
    public void delete(Key key) {
        Value v = this.KeyMap.remove(key);
        this.ValueMap.remove(v);
    }

    /**
     * @param value Value associated with key.
     * @return Key associated with the specified value, otherwise null.
     */
    public Key getKey(Value value) {
    	return this.ValueMap.get(value);
    }
    
    public int size(){
    	return this.KeyMap.size();
    }

    /**
     * @param key Key associated with value.
     * @return Returns the value associated with the specified key, otherwise null.
     */
    public Value getValue(Key key) {
    	return this.KeyMap.get(key);
    }
    
    /**
     * Returns true if the map contains the specified key.
     * @param key - The key to check
     * @return TRUE if contained, otherwise FALSE
     */
    public boolean containsKey(Key key){
    	return this.KeyMap.containsKey(key);
    }
    
    /**
     * Returns the set keys contained in this DualHashMap.
     */
    public Set<Key> keySet(){
    	return this.KeyMap.keySet();
    }
    
    public Set<Entry<Key, Value>> entrySet(){
    	return this.KeyMap.entrySet();
    }
    
    @Override
    public DualHashMap<Key, Value> clone(){
    	DualHashMap<Key, Value> copy = new DualHashMap<Key, Value>();
    	for(Entry<Key, Value> e : this.KeyMap.entrySet()){
    		copy.insert(e.getKey(), e.getValue());
    	}
    	
    	return copy;
    }

	@Override
	public String toString() {
		return this.KeyMap.toString();
	}
}
