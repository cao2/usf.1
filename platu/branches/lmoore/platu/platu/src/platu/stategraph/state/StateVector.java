package platu.stategraph.state;

import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.cursors.ObjectIntCursor;
import java.io.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.Deflater;
import org.apache.hadoop.io.compress.zlib.BuiltInZlibDeflater;
import platu.lpn.DualHashMap;
import platu.lpn.VarVal;
import platu.stategraph.zone.Zone;

/**
 *
 * @author ldtwo
 */
public class StateVector {

	transient   private ObjectIntOpenHashMap<String> map2;
    private static ObjectIntOpenHashMap.KeySet staticKeySet;//ver3

    public static int [] counts=new int[15];

 	/*
 	 * Initialize this state vector with 'other'.
 	 */
    public StateVector(HashMap other) //TODO: StateVector other)
    {
        counts[0]++;
        {
            map2 = new ObjectIntOpenHashMap<String>();
            Map.Entry en;
            for (Object o : other.entrySet()) {
                en = (Entry) o;
                if(en.getKey()==null)new NullPointerException().printStackTrace();
                
                put((String) en.getKey(), (Integer) en.getValue());
            }
        }
    }

    public StateVector(int[] other, DualHashMap<String, Integer> VarIndexMap) 
    {
    	map2 = new ObjectIntOpenHashMap<String>();	
    	for (int i = 0; i < other.length; i++) {
    		String var = VarIndexMap.getKey(i);
    		put(var, other[i]);
    	}
    }

    public StateVector(String list) {
        counts[1]++;
      {
            map2 = new ObjectIntOpenHashMap<String>();
        }

        list = list.replace(" ", "");
        String[] tmp = list.split(",\\(");
        String[] vv;
        for (String s : tmp) {
            if (s.trim().length() < 1) {
                continue;
            }
            // System.out.println("VV="+s.replace("(", "").replace(")", ""));
            try {
                vv = s.replace("(", "").replace(")", "").split(",");
                put(vv[0], new Integer(vv[1]));
            } catch (NumberFormatException ex) {
                System.err.println("ERROR: " + s);
                ex.printStackTrace();
            }
        }
        staticKeySet = map2.keySet();//ver3
    }

    public StateVector()
    {
        counts[2]++;
        {
            map2 = new ObjectIntOpenHashMap<String>();
        }

    }

    public void remove(String s) {
        map2.remove(s);
        counts[3]++;
    }

    public Iterator<ObjectIntCursor<String>> iterator() {
        return map2.iterator();
    }

    public int get(String s) {

        counts[4]++;
        {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> vv;
            for (; it.hasNext();) {
                vv = it.next();
                if (((String) vv.key).compareTo(s) == 0) {
                    return (Integer) vv.value;
                }
            }
        }
        return -1;
    }
    
    public boolean contains(String var) {
    	return map2.containsKey(var);
    }
    
    public int getValue(String key) {
       return map2.get(key);
    }

    public int get(Map.Entry s) {
        counts[5]++;
        {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> vv;
            for (; it.hasNext();) {
                vv = it.next();
                if (((String) vv.key).compareTo((String) s.getKey()) == 0) {
                    return (Integer) vv.value;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String ret = "";
        {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> vv;
            for (; it.hasNext();) {
                vv = it.next();
                ret += vv.key + "=" + vv.value + "; ";
            }
        }
        return ret;
    }

    public void putAll(Collection<? extends VarVal> vvc) {
        Iterator<? extends VarVal> it = vvc.iterator();
        VarVal vv;
        while (it.hasNext()) {
            vv = it.next();
            put(vv.getVariable(), (int) vv.getIntValue());
        }
        counts[6]++;
    }

    public final int put(String key, int value) {
        counts[7]++;
        if (key == null) {
            throw new NullPointerException("key=" + key + "; value=" + value);
        }
        {
            return map2.put(key.trim(), value);
        }
    }

    public final void rename(String oldKey, String newKey) {
        if (oldKey == null) {
            throw new NullPointerException("key=" + oldKey + "; value=" + newKey);
        }
        if (newKey == null) {
            throw new NullPointerException("key=" + oldKey + "; value=" + newKey);
        }
        int val = get(oldKey);
        map2.remove(oldKey);
        map2.put(newKey, val);
    }

    public int size() {
        {
            return map2.size();
        }
    }

    @Override
    public boolean equals(Object o) {
        counts[8]++;
        StateVector other = (StateVector) o;
        if (this == other) {
            return true;
        }
        {
            if (map2.size() != other.size()) {
                return false;
            }
        }
         {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> thisEntry;
            for (; it.hasNext();) {
                thisEntry = it.next();
                if ((int) other.get(thisEntry.key) != (int) thisEntry.value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean contains(StateVector other) {
        counts[9]++;
        if (this == other) {
            return true;
        }
        {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> thisEntry;
            int total = 0;
            for (; it.hasNext();) {
                thisEntry = it.next();
                if ((int) other.get(thisEntry.key) == (int) thisEntry.value) {
                    total++;
                }
            }
            return total == other.size();
        }
       // return true;
    }
    private int hash = -1;

    @Override
    public int hashCode() {
        if (hash != -1) {
            return hash;
        }
        counts[10]++;
        hash = Integer.MIN_VALUE;
        {
            int hash_code;
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> vv;
            for (; it.hasNext();) {
                vv = it.next();
                if (vv == null || vv.key == null) {
                    if (vv != null) {
                        new NullPointerException("vv.key=" + vv.key + ";\tvv.value="
                                + vv.value + ";\tvv.index=" + vv.index).printStackTrace();
                    } else {
                        new NullPointerException("vv=" + vv).printStackTrace();
                    }
                    continue;
                }
                hash_code = vv.key.hashCode() ^ vv.value;
                hash ^= (Integer.rotateRight(hash_code, hash_code % 31));
            }
        }
        return hash;
    }

    @Override
    public StateVector clone() {
        counts[11]++;
        StateVector sv = new StateVector();
//        StateVector sv = new StateVector(this.map2);
//        if (VERSION == 1) {
//            for (Map.Entry<String, Integer> en : map.entrySet()) {
//                sv.put(en.getKey(), en.getValue());
//            }
//        } else if (VERSION == 2) {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> vv;
            for (; it.hasNext();) {
                vv = it.next();
                sv.put(vv.key, vv.value);
            }
//        }
        return sv;
    }

    public String digest() {
    	System.exit(0);
        String s = ".V" + hashCode();
        String[] vv = new String[size()];
        int x = 0;
         {
            Iterator<ObjectIntCursor<String>> it = map2.iterator();
            ObjectIntCursor<String> v;
            for (; it.hasNext();) {
                v = it.next();
                vv[x++] = v.key + "=" + v.value + ", ";
            }
        }
        Arrays.sort(vv);
        for (String str : vv) {
            s += str;
        }
        s= s.replace(" ", "")//.replace(",", "")//
                .replace("[", "").replace("]", "") //+ super.toString()
                ;
         try {
//         for(Provider pr:   Security.getProviders()){
//            System.out.println(pr);
//         }
            byte[] data;
            Deflater def = new BuiltInZlibDeflater(BuiltInZlibDeflater.BEST_COMPRESSION);
            def.setInput(s.getBytes());
            def.finish();
            ByteArrayOutputStream os = new ByteArrayOutputStream(s.getBytes().length);
            byte[] buf = new byte[8];
            while (!def.finished()) {
                int count = def.deflate(buf);
                os.write(buf, 0, count);
            }
            os.close();
            data = os.toByteArray();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            data = md.digest();
//            for(byte b:message){
//                s2+=Integer.toHexString(b&0xff);
//            }
//            byte[] data=s.getBytes();

            s = "";
            for (byte b : data) {
                s += Integer.toString(b & 0xff, Character.MAX_RADIX);
            }

        } catch (Exception ex) {
        }
        return s;
    }

    public void putAll(StateVector vector) {
        counts[12]++;
        {
            map2.putAll(vector.map2);
        }
    }

    public int[] toIntArray(DualHashMap<String, Integer> VarIndexMap) {
    	int var_cnt = VarIndexMap.size();
    	int[] result = new int[var_cnt];
    	for(int index = 0; index < var_cnt; index++) {
    		String var = VarIndexMap.getKey(index);
    		int val = map2.get(var);
    		result[index] = val;
    	}
    	
    	return result;
    }


    public String valueString() {
        String ret = "";
        Iterator<ObjectIntCursor<String>> it = map2.iterator();
        ObjectIntCursor<String> v;
        for (; it.hasNext();) {
            v = it.next();
            ret += v.key + "=" + v.value + (it.hasNext() ? ", " : "");
        }
        return ret;
    }

//    public void setVariablesForExpression(Expression expr) {
//        expr.setVariables(map2);
//    }
    
    static public void printUsageStats(){
        System.out.printf("%-20s %11s\n",   "StateVector",  counts[0]);
//        System.out.printf("\t%-20s %11s\n",   "StateVector",  counts[1]);
        System.out.printf("\t%-20s %11s\n",   "StateVector",  counts[2]);
//        System.out.printf("\t%-20s %11s\n",   "remove",  counts[3]);
        System.out.printf("\t%-20s %11s\n",   "get",  counts[4]);
//        System.out.printf("\t%-20s %11s\n",   "get",  counts[5]);
//        System.out.printf("\t%-20s %11s\n",   "putAll",  counts[6]);
        System.out.printf("\t%-20s %11s\n",   "put",  counts[7]);
        System.out.printf("\t%-20s %11s\n",   "equals",  counts[8]);
//        System.out.printf("\t%-20s %11s\n",   "contains",  counts[9]);
        System.out.printf("\t%-20s %11s\n",   "hashCode",  counts[10]);
        System.out.printf("\t%-20s %11s\n",   "clone",  counts[11]);
//        System.out.printf("\t%-20s %11s\n",   "putAll",  counts[12]);
    }


    public void print()
    {
    	System.out.print("Current vector: ");
//    	Iterator iter = map2.iterator();
//    	while(iter.hasNext())
//    		System.out.print(iter.next()+" ");

    	System.out.print(map2.toString() + "\n");
    }

    public File serialize(String filename)
            throws FileNotFoundException, IOException {
        File f = new File(filename);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
        os.writeObject(this);
        os.writeObject(map2);
        os.close();
        return f;
    }

    public static StateVector deserialize(String filename)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(filename);
        ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));
        StateVector zone = (StateVector) os.readObject();
        os.close();
        return zone;
    }

    public static Zone deserialize(File f)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));
        Zone zone = (Zone) os.readObject();
        os.close();
        return zone;
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        ObjectIntProcedure proc=new ObjectIntProcedure() {
//
//            @Override
//            public void apply(Object ktype, int i) {
//                try {
//                    out.writeInt(i);
//                    out.writeObject(ktype);
//                } catch (IOException iOException) {
//                }
//            }
//        };
           }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        
    }
    public void writeObject(DataOutputStream out) throws IOException {
       
    }

    public void readObject(DataInputStream in) throws IOException, ClassNotFoundException {

    }
}
