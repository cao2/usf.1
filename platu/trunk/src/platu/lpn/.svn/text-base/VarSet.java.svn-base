package platu.lpn;

import java.util.Arrays;
import java.util.HashSet;

public class VarSet extends HashSet<String> {

	private static final long serialVersionUID = -6495820132749048047L;

	public VarSet(HashSet<String> in) {
        super(in);
    }

	public  VarSet(String[] in) {
        super(Arrays.asList(in));
    }

    public VarSet() {
        super(0);
    }

    @Override
    public VarSet clone() {
        VarSet set=new VarSet();
        set.addAll(this);
        return set;
    }
}
