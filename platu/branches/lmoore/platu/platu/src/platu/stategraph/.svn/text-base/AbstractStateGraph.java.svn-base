package platu.stategraph;

import java.io.Console;
import java.io.PrintStream;
import platu.Main;
import java.util.HashMap;
import java.util.LinkedHashMap;
import platu.expression.VarNode;
import platu.lpn.LPN;
import platu.lpn.LPNTran;
import platu.lpn.LPNTranSet;
import platu.lpn.Markings;
import platu.lpn.VarSet;
import platu.project.Project;
import platu.stategraph.state.State;
import platu.stategraph.zone.Zone;

/**
 * 
 * @author ldmtwo
 */
public abstract class AbstractStateGraph extends LPN {

	public static PrintStream out = Main.out;
	public static boolean SIMULATE = false;
	private static int graphIdx = 0;
	public static String LPN_PATH = "";
	public static String DOT_PATH = "";
	public static boolean MAINTAIN_STATE_TRAN_LIST = false;
	public static boolean SHOW_STATE_INC_TREE = false;
	public static boolean STOP_ON_ERROR = false;
	public static boolean USING_POSET = false;
	public boolean failed = false, error = false;
	public static boolean STOP_ON_FAILURE = false;
	public static boolean OPTIMIZE_INF = false;
	public static boolean TIMED_ANALYSIS = false;
	public static boolean INTERACTIVE_MODE = false;
	public static boolean OPEN_STATE_EXPLORER = false;
	public static boolean DRAW_JAVA_GRAPH = false;
	public static boolean DRAW_STATE_GRAPH = false;
	public static boolean DRAW_MEMORY_GRAPH = false;
	public static boolean OUTPUT_DOT = false;
	public static int PRINT_LEVEL = 10;
	public static boolean ENABLE_PRINT = true;
	public String label;
	public int sgTranCount = 0;
	protected boolean infTimer = false;
	protected int lblK, lblI;

	Console con = System.console();
	final static int PATH_TAKEN = 1, STATE_EXISTS = 2, ERROR = 4, VERIFICATION_FAILED = 8;
	public LPNTranSet allTrans;
	public int stackHeight = 0;
	public static int[] counts = new int[10];
	LinkedHashMap<Integer, LPNTran> lpnMap = new LinkedHashMap<Integer, LPNTran>();

	public AbstractStateGraph(Project prj, String label, VarSet inputs,
			VarSet outputs, VarSet internals, LPNTranSet transitions,
			State initState) {
		super(prj, label, inputs, outputs, internals, transitions, initState);
		this.prj = prj;
		this.label = label;
		this.inputs = inputs;
		this.outputs = outputs;
		this.internals = internals;
		this.transitions = transitions;
		this.initState = initState;
		if (prj == null || label == null || inputs == null || outputs == null 
				|| internals == null || transitions == null || initState == null) {
			new NullPointerException().printStackTrace();
		}
		
		if (ENABLE_PRINT) {
			// System.out.println("new LPN()1: \t" + description());
		}

		counts[0]++;
	}
	
	public AbstractStateGraph(Project prj, String label, VarSet inputs, VarSet outputs,
            VarSet internals, HashMap<String, VarNode> varNodeMap, LPNTranSet transitions, 
            HashMap<String, Integer> initialVector, Markings initialMarkings, Zone initialZone) {
		
		super(prj, label, inputs, outputs, internals, varNodeMap, transitions, initialVector, initialMarkings, initialZone);
	}
}
