/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platu.lpn;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import platu.expression.VarNode;

/**
 *
 * @author ldtwo
 */
final public class LPNTranSet extends LinkedList<LPNTran> {

    public static int[] counts = new int[10];
    private LPN lpn;

    public LPNTranSet() {
        super();
        counts[0]++;
    }

    public LPNTranSet(Collection<? extends LPNTran> lpnt) {
        super(lpnt);
        counts[0]++;
    }

    public LPNTranSet(final int i) {
       // super(i);
        counts[0]++;
    }

//    public int[] toArrayOfIDs() {
//        int[] IDs = new int[size()];
//        int x = 0;
//        for (LPNTran t : this) {
//            IDs[x++] = t.getLabel();
//        }
//        counts[1]++;
//        return IDs;
//    }

//    public String getIDArrayString() {
//        String ret = "";
//        int[] arr = toArrayOfIDs();
//        for (int i : arr) {
//            ret += i + ", ";
//        }
//        return ret;
//    }

    public LPNTran get(LPNTran lpnt) {
        counts[2]++;
        for (LPNTran t : this) {
            if (lpnt.getLabel() == t.getLabel()) {
                return t;
            }
        }
        return null;
    }

//    public LPNTran get(int label) {
//        counts[3]++;
//        for (LPNTran t : this) {
//            if (label == t.getLabel()) {
//                return t;
//            }
//        }
//        return null;
//    }

//    public LPNTranSet get(String var) {
//        counts[3]++;
//        LPNTranSet set = new LPNTranSet();
//        for (LPNTran t : this) {
//            for (VarExpr ve : t.getAssignments()) {
//                if (ve.getVar().equals(var)) {
//                    set.add(t);
//                    break;
//                }
//            }
//        }
//        return set;
//    }

    /**
     * @return the lpn
     */
    public LPN getLpn() {
        return lpn;
    }

    /**
     * @param lpn the lpn to set
     */
    public void setLPN(LPN lpn) {
        counts[4]++;
        this.lpn = lpn;
        for (LPNTran t : this) {
            t.setLpn(lpn);
        }
    }

    @Override
    public String toString() {
        counts[5]++;
        String ret = "";
        Iterator<LPNTran> it = this.iterator();
        while (it.hasNext()) {
            ret += it.next().getLabel();
            if (it.hasNext()) {
                ret += ", ";
            }
        }
        return "[" + ret + "]";
    }

    @Override
    public LPNTranSet clone() {
        counts[6]++;
        return new LPNTranSet(this);
    }
    
    public LPNTranSet copy(HashMap<String, VarNode> variables){
    	LPNTranSet copy = new LPNTranSet();
    	for(LPNTran lpnTran : this){
    		copy.add(lpnTran.copy(variables));
    	}
    	
    	return copy;
    }

    static public void printUsageStats() {
        System.out.printf("%-20s %11s\n", "LPNTranSet", counts[0]);
        System.out.printf("\t%-20s %11s\n", "toArrayOfIDs", counts[1]);
//        System.out.printf("\t%-20s %11s\n",   "get",  counts[2]);
//        System.out.printf("\t%-20s %11s\n",   "get",  counts[3]);
        System.out.printf("\t%-20s %11s\n", "setLPN", counts[4]);
//        System.out.printf("\t%-20s %11s\n",   "toString",  counts[5]);
//        System.out.printf("\t%-20s %11s\n",   "clone",  counts[6]);
//        System.out.printf("\t%-20s %11s\n",   "",  counts[7]);
//        System.out.printf("\t%-20s %11s\n",   "",  counts[8]);
//        System.out.printf("\t%-20s %11s\n",   "",  counts[9]);
    }

    public LPNTranSet subset(int[] enabledSet) {
        LPNTranSet lpnt = new LPNTranSet();
        for (int i : enabledSet) {
            LPNTran o = this.get(i);
            if (o != null) {
                lpnt.add(o);
            }
        }
        return lpnt;
    }
}
