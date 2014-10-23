/*
 * To change this template,choose Tools | Templates
 * and open the template in the editor.
 */
package platu.lpn;

import java.io.*;
import java.util.*;
import org.apache.commons.collections.primitives.ArrayIntList;

/**
 *
 * @author ldtwo
 */
public final class Markings extends ArrayIntList implements Serializable
{

	public static final Collection<Integer> toList(int[] arr) {
        TreeSet<Integer> l = new TreeSet<Integer>();
        for (int i : arr) {
            l.add(i);
        }
        return l;
    }
	
	public static final int[] toArray(Collection<Integer> set) {
          int[]arr=new int[set.size()];int idx=0;
          for(int i:set){
              arr[idx++]=i;
          }
               return arr;
    }
	
    public static int INIT_SIZE = 1;
    public static int[] counts = new int[10];

    public Markings() {
//        super(toArray(new ArrayList(INIT_SIZE)));
       // counts[0]++;
    }

    private Markings(Collection c) 
    {
        super(toArray(c));
       // counts[0]++;
    }

    public Markings(List<String> asList) {
        //super();
//        super(toArray(new ArrayList(INIT_SIZE)));
        String tmp;
        for (String s : asList) {
            tmp = s.trim();
            if (tmp.length() > 0) {
                add((int)Integer.parseInt(tmp));
            }
        }
       // counts[0]++;
    }

    public Markings(LinkedList<Integer> markingSet) {
        super(toArray(markingSet));
       // counts[0]++;
    }

    private Markings(Markings aThis) {
        super(aThis);
    }

//    public boolean contains(Object o) {
//       // counts[1]++;
//        return super.contains(o);
//    }

    public boolean add(Integer e) {
//        Iterator<Integer> it = iterator();
//        while (it.hasNext()) {
//            if ((int) e == it.next()) {
//                return false;
//            }
//        }
       // counts[2]++;
        return super.add(e);
    }

    public boolean addAll(Collection<? extends Integer> clctn) {
        Iterator<? extends Integer> it = clctn.iterator();
        boolean ret = true;
        while (it.hasNext()) {
            ret &= add(it.next());
        }
        return ret;
    }
    public boolean containsAll(Collection<? extends Integer> clctn) {
        Iterator<? extends Integer> it = clctn.iterator();
        boolean ret = true;
        while (it.hasNext()) {
            ret &= contains(it.next());
        }
        return ret;
    }
    public boolean containsAll(int[] clctn) {
        boolean ret = true;
        for (int i:clctn) {
            ret &= contains(i);
        }
        return ret;
    }

    public Integer get(Integer i) {
       // counts[3]++;
        if (contains(i)) {
            return i;
        }
        return null;
    }

    @Override
    public int[] toArray() {

        return super.toArray();
    }

//    public boolean remove(Object o) {
//       // counts[4]++;
//        return super.remove(o);
//    }

//    @SuppressWarnings("element-type-mismatch")
//    public boolean removeAll(Collection<?> clctn) {
//        Iterator<?> it = clctn.iterator();
//        boolean ret = true;
//        while (it.hasNext()) {
//            ret &= remove(it.next());
//        }
//        return ret;
//    }
    static int tmpi;
    public boolean removeAll(int[] vals) {
        for(tmpi=0;tmpi<vals.length;tmpi++){
            removeElement((vals[tmpi]));
        }
           return true;
    }
    public boolean addAll(int[] vals) {
        for(tmpi=0;tmpi<vals.length;tmpi++){
            add(vals[tmpi]);
        }
           return true;
    }
//
static int hash = -1;
    @Override
    public int hashCode() {  
//        if(hash!=-1)return hash;
        int[] arr=toArray();
//        Arrays.sort((toArray()));
        hash = //Arrays.hashCode(arr);
        Integer.MIN_VALUE;
//
        for (int e : arr) {
            hash ^= (Integer.rotateRight(e,e % 31));
            //hash ^= e;
        }
//////        System.out.println(toList(toArray()));
////       // counts[5]++;
        return hash;
//         return Arrays.hashCode(toArray(toList(toArray())));
    }

    @Override
    public boolean equals(Object o) {
       // counts[6]++;
        Markings other = (Markings) o;
        if (this == other) {
            return true;
        }
        if (size() != other.size()) {
            return false;
        }
//        return super.containsAll(other);
        for (int e : toArray()) {
            if (!other.contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Markings clone() {
        Markings m = new Markings(this);
       // counts[7]++;
        return m;
    }

    public String digest() {
       // counts[8]++;
        String s = "+M." + hashCode();
        int[] vals = new int[0];
        vals = toArray(vals);
        Arrays.sort(vals);
        for (int i : vals) {
            s += i;
        }
        return s.replace(".","").replace("+",".");
    }

    static public void printUsageStats() {
        System.out.printf("%-20s %11s\n","Markings",counts[0]);
        System.out.printf("\t%-20s %11s\n","contains",counts[1]);
        System.out.printf("\t%-20s %11s\n","add",counts[2]);
//        System.out.printf("\t%-20s %11s\n","get",counts[3]);
        System.out.printf("\t%-20s %11s\n","remove",counts[4]);
        System.out.printf("\t%-20s %11s\n","hashCode",counts[5]);
        System.out.printf("\t%-20s %11s\n","equals",counts[6]);
        System.out.printf("\t%-20s %11s\n","clone",counts[7]);
//        System.out.printf("\t%-20s %11s\n","digest",counts[8]);
//        System.out.printf("\t%-20s %11s\n","",counts[9]);
    }

    public void print() {
        System.out.println("Current marking " + this.size() + " : " + super.toString());
    }

    public File serialize(String filename)
            throws FileNotFoundException, IOException {
        File f = new File(filename);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
        os.writeObject(this);
        os.close();
        return f;
    }

    public static Markings deserialize(String filename)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(filename);
        return deserialize(f);
    }

    public static Markings deserialize(File f)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));
        Markings zone = (Markings) os.readObject();
        os.close();
        return zone;
    }
}
