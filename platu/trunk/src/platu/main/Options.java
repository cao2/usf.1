package platu.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class Options {
	/*
	 * Levels given by an integer number to indicate the amount information to display
	 * while the tool is running
	 */
	private static int verbosity = 0;
	
	/*
	 * Folder path where to output DOT files
	 */
	private static String dotPath = Main.workingDirectory;
	
	private static String cexOutputPath = Main.workingDirectory;
	
	/*
	 * Timing analysis options:
	 */
	public static enum timingAnalysisDef {
		OFF,			// no timing
		ZONE,			// Zone-based timing analysis;
		POSET,			// POSET-based timing analysis (ref. Myers async book);
		ABSTRACTION		// merge zones to form convex hull for the same untimed state. 
	}
	private static timingAnalysisDef timingAnalysisType = timingAnalysisDef.OFF;
	
	/*
	 * Partial order reduction options
	 */
	public static enum PorDef { 
		OFF,		// no POR 
		STATIC, 	// POR  based on dependency relation by static analysis
		BEHAVIORAL // POR based on dependency relation by behavioral analysis
		};
		
	private static String POR = "off";
	
	/*
	 * Search algorithm options:
	 */
	public static enum SearchTypeDef { 
		DFS, 			// DFS search on the entire state space
		DFS_UNDERAPPROX,
		BFS, 			// BFS on the entire state space.
		POR_STATIC,		// DSF with POR using static analysis to compute dependency relation  
		POR_BEHAVORIAL, // DFS with POR using behavioral analysis to compute dependency relation
		CRA, 	// using compositional search/reduction to build the reduce SG.
		CRA_ORIG, // original compositional search
		CRA_LOCAL, // compositional analysis using local search
		CRA_GLOBAL // compositional analysis using global search
		};
		
	private static SearchTypeDef searchType = SearchTypeDef.DFS;
	
	/*
	 * Options on how reachable states are stored.
	 */
	public static enum StateFormatDef {
		EXPLICIT,	// hash tables in java
		MDDBUF,		// Mutli-Value DD
		MDD,		// Mutli-Value DD
		BDD, 		// BDD
		AIG,			// AIG
		BINARY_TREE,		// Binary tree
		DECOMPOSED, 	// decompose a global state into a set of triples of global vectors and two local states sharing variables.
		NATIVE_HASH			// hash table in C/C++
	}
	private static StateFormatDef stateFormat = StateFormatDef.EXPLICIT;
	
	/*
	 * Use multithreading when set to true.
	 */
	private static boolean parallelFlag = false;
	
	/*
	 * Option for compositional minimization type.
	 */
	public static enum CompMinDef { 
		OFF, 				// No state space reduction
		ABSTRACTION, 		// Use transition-based abstraction
		REDUCTION			// Use state space reduction     
		};
		
	/*
	 * Memory upper bound for a verification run. The unit is MB.
	 */
	public static int MemUpperBound = 1800;
	
	/*
	 * Upper bound on the total runtime for a run.  The unit is in seconds.
	 */
	public static int TimeUpperBound = 1500;
		
	private static CompMinDef compositionalMinimization = CompMinDef.OFF;
	
	private static String compositionOrder = "";

	private static boolean disablingErrorFlag = true;
	private static boolean livelockFlag = true;
	private static boolean assertionFlag = true;
	private static boolean clearMemoryFlag = false;
	private static boolean simplificationFlag = true;
	private static boolean autoFailureFlag = true;
	private static boolean equivFailureFlag = true;
	private static boolean improvedEquivRemovalFlag = false;
	private static boolean drawFinalStateGraph = false;
	private static boolean drawModuleStateGraphs = false;
	
	/*
	 * When true, use non-disabling semantics for transition firing.
	 */
	private static boolean stickySemantics = false;

	
	private static void setDrawModuleStateGraphs(String flag){
		if(flag.equalsIgnoreCase("true")){
			drawModuleStateGraphs = true;
		}
		else if(!flag.equalsIgnoreCase("false")){
			System.err.println("Warning: invalid DRAW_MODULE_STATE_GRAPHS value - default is \"false\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean drawModuleStateGraphs(){
		return drawModuleStateGraphs;
	}
	
	private static void setDrawFinalStateGraph(String flag){
		if(flag.equalsIgnoreCase("true")){
			drawFinalStateGraph = true;
		}
		else if(!flag.equalsIgnoreCase("false")){
			System.err.println("Warning: invalid DRAW_FINAL_STATE_GRAPH value - default is \"false\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean drawFinalStateGraph(){
		return drawFinalStateGraph;
	}
	
	private static void setImprovedEquivRemovalFlag(String flag){
		if(flag.equalsIgnoreCase("true")){
			improvedEquivRemovalFlag = true;
		}
		else if(!flag.equalsIgnoreCase("false")){
			System.err.println("Warning: invalid IMPROVED_EQUIV_REMOVAL value - default is \"false\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean getImprovedEquivRemovalFlag(){
		return improvedEquivRemovalFlag;
	}
	
	private static void setEquivFailureFlag(String flag){
		if(flag.equalsIgnoreCase("false")){
			equivFailureFlag = false;
		}
		else if(!flag.equalsIgnoreCase("true")){
			System.err.println("Warning: invalid EQUIV_FAILURE_REMOVAL value - default is \"true\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean getEquivFailureFlag(){
		return equivFailureFlag;
	}
	
	private static void setAutoFailureFlag(String afr){
		if(afr.equalsIgnoreCase("false")){
			autoFailureFlag = false;
		}
		else if(!afr.equalsIgnoreCase("true")){
			System.err.println("Warning: invalid AUTO_FAILURE_REDUCTION value - default is \"true\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean getAutoFailureFlag(){
		return autoFailureFlag;
	}
	
	public static void setCompositionOrder(String partitionPath){
		File partFile = new File(partitionPath);        
        Scanner scanner;
		try {
			scanner = new Scanner(partFile);
		} catch (FileNotFoundException e) {
			System.err.println("Warning: partition file \"" + partFile.getAbsolutePath() + "\" not found - default will be used");
            return;
		}
		
		while (scanner.hasNextLine()){
			compositionOrder += scanner.nextLine();
		}
	}
	
	public static String getCompositionOrder(){
		return compositionOrder;
	}
	
	private static void setDisablingFlag(String checkDisabling){
		if(checkDisabling.equalsIgnoreCase("false")){
			disablingErrorFlag = false;
		}
		else if(!checkDisabling.equalsIgnoreCase("true")){
			System.err.println("Warning: invalid CHECK_DISABLING_ERROR value - default is \"true\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	private static void setAssertionFlag(String checkAssertion){
		if(checkAssertion.equalsIgnoreCase("false")){
			assertionFlag = false;
		}
		else if(!checkAssertion.equalsIgnoreCase("true")){
			System.err.println("Warning: invalid CHECK_ASSERTION option - default is \"true\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	private static void setLivelockFlag(String checkLivelock){
		if(checkLivelock.equalsIgnoreCase("false")){
			livelockFlag = false;
		}
		else if(!checkLivelock.equalsIgnoreCase("true")){
			System.err.println("Warning: invalid CHECK_LIVELOCK option - default is \"true\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean checkDisablingError(){
		return disablingErrorFlag;
	}
	
	public static boolean checkAssertionError(){
		return assertionFlag;
	}
	
	public static boolean checkLivelock(){
		return livelockFlag;
	}
	
	private static void setCompositionalMinimization(String minimizationType){
		if (minimizationType.equalsIgnoreCase("abstraction")){
    		compositionalMinimization = CompMinDef.ABSTRACTION;
    	}
    	else if (minimizationType.equalsIgnoreCase("reduction")){
    		compositionalMinimization = CompMinDef.REDUCTION;
    	}
    	else if (!minimizationType.equalsIgnoreCase("off")){
    		System.err.println("Warning: invalid COMPOSITIONAL_MINIMIZATION option - default is \"off\"");
    		System.err.println("---> Choices include: " + "off, abstraction, and reduction");
    	}
	}
	
	public static CompMinDef getCompositionalMinimization(){
		return compositionalMinimization;
	}
	
	
	private static void setStickySemantics(String checkSticky){
		if(checkSticky.equalsIgnoreCase("true")){
			stickySemantics = true;
		}
		else if(!checkSticky.equalsIgnoreCase("false")){
			System.err.println("Warning: invalid STICKY_SEMANTICS option - default is \"false\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean getStickySemantics(){
		return stickySemantics;
	}
	
	private static void setVerbosity(String v){    	
    	try{
    		verbosity = Integer.parseInt(v);
    		if(verbosity < 0){
    			verbosity = 0;
    			System.err.println("Warning: invalid VERBOSITY option - default is 0");
        		System.err.println("---> Choices include: " + "0 - 10");
    		}
    	}
    	catch(Exception e){
    		System.err.println("Warning: invalid VERBOSITY option - default is 0");
    		System.err.println("---> Choices include: " + "0 - 10");
    	}
	}
	
	public static int getVerbosity(){
		return verbosity;
	}
	
	private static void setMemUpperBound(String v){    	
    	try{
    		MemUpperBound = Integer.parseInt(v);
    		if(MemUpperBound < 0){
    			MemUpperBound = 3000;
    			System.err.println("Warning: invalid MEM_UPPER_BOUND option - default is 2000");
        		System.err.println("---> Choices include: " + "Positive integer values");
    		}
    	}
    	catch(Exception e){
    		System.err.println("Warning: invalid MEM_UPPER_BOUND option - default is 2000");
    		System.err.println("---> Choices include: " + "Positive integer values");
    	}
	}
	
	public static int getMemUpperBound(){
		return MemUpperBound;
	}
	
	private static void setTimeUpperBound(String v){    	
    	try{
    		TimeUpperBound = Integer.parseInt(v);
    		if(TimeUpperBound < 0){
    			TimeUpperBound = 1500;
    			System.err.println("Warning: invalid Time_UPPER_BOUND option - default is 1500");
        		System.err.println("---> Choices include: " + "Positive integer values");
    		}
    	}
    	catch(Exception e){
    		System.err.println("Warning: invalid TIME_UPPER_BOUND option - default is 1500");
    		System.err.println("---> Choices include: " + "Positive integer values");
    	}
	}
	
	public static int getTimeUpperBound(){
		return TimeUpperBound;
	}
	
	private static void setDotPath(String path){
		if (!new File(path).exists()){
		   System.err.println("Warning: invalid DOT_PATH option - default is the working directory");
		}
		else{
			dotPath = path;
		}
	}
	
	public static String getDotPath(){
		return dotPath;
	}
	
	private static void setCexOutputPath(String path){
		if (!new File(path).exists()){
		   System.err.println("Warning: invalid CEX_OUTPUT option - default is the current working directory");
		}
		else{
			cexOutputPath = path;
		}
	}
	
	public static String getCexOutputPath(){
		return cexOutputPath;
	}
	
	private static void setTimingAnalsysisType(String timing){
		if (timing.equalsIgnoreCase("zone")){
    		timingAnalysisType = timingAnalysisDef.ZONE;
    	}
    	else if (timing.equalsIgnoreCase("poset")){
    		timingAnalysisType = timingAnalysisDef.POSET;
    	}
    	else if (!timing.equalsIgnoreCase("off")){
    		System.err.println("Warning: invalid TIMING_ANALYSIS option - default is \"off\"");
    		System.err.println("---> Choices include: " + "off, zone, and poset");
    	}
	}
	
	public static timingAnalysisDef getTimingAnalysisType(){
		return timingAnalysisType;
	}
	
	private static void setPOR(String type) {
		//TODO: use enum and check for valid option
		POR = type;
	}
	
	public static String getPOR() {
		return POR;
	}
	
	private static void setSearchType(String type){
		if(type.equalsIgnoreCase("dfs")){
			searchType = SearchTypeDef.DFS;
		}
		else if(type.equalsIgnoreCase("dfs_underapprox")){
			searchType = SearchTypeDef.DFS_UNDERAPPROX;
		}
		else if(type.equalsIgnoreCase("bfs")){
			searchType = SearchTypeDef.BFS;
		}
		else if(type.equalsIgnoreCase("por_static")){
			searchType = SearchTypeDef.POR_STATIC;
		}
		else if(type.equalsIgnoreCase("por_behavioral")){
			searchType = SearchTypeDef.POR_BEHAVORIAL;
		}
		else if(type.equalsIgnoreCase("cra_ic")){
			searchType = SearchTypeDef.CRA;
		}
		else if(type.equalsIgnoreCase("cra_ic_orig")){
			searchType = SearchTypeDef.CRA_ORIG;
		}
		else if(type.equalsIgnoreCase("cra_ls")){
			searchType = SearchTypeDef.CRA_LOCAL;
		}
		else if(type.equalsIgnoreCase("cra_gs")){
			searchType = SearchTypeDef.CRA_GLOBAL;
		}
		else{
    		System.err.println("Warning: invalid SEARCH option - default is \"dfs\"");
    		System.err.println("---> Choices include: " + "dfs, bfs, por_static, por_behavioral, " +
    				"cra_ic, cra_ic_orig, cra_ls, and cra_gs");
		}
	}
	
	public static SearchTypeDef getSearchType(){
		return searchType;
	}
	
	private static void setStateFormat(String format){
		if (format.equalsIgnoreCase("bdd")){
    		stateFormat = StateFormatDef.BDD;
    	}
    	else if (format.equalsIgnoreCase("aig")){
    		stateFormat = StateFormatDef.AIG;
    	}
    	else if (format.equalsIgnoreCase("mddbuf")){
    		stateFormat = StateFormatDef.MDDBUF;
    	}
       	else if (format.equalsIgnoreCase("mdd")){
    		stateFormat = StateFormatDef.MDD;
    	}
      	else if (format.equalsIgnoreCase("btree")){
    		stateFormat = StateFormatDef.BINARY_TREE;
    	}     
     	else if (format.equalsIgnoreCase("decompposed")){
    		stateFormat = StateFormatDef.DECOMPOSED;
    	}     
      	else if (format.equalsIgnoreCase("native_hash")){
    		stateFormat = StateFormatDef.NATIVE_HASH;
    	}
    	else if (!format.equalsIgnoreCase("explicit")){
    		System.err.println("Warning: invalid STATE_FORMAT option " + format + " - default is \"explicit\"");
    		System.err.println("---> Choices include: " + "explicit, bdd, aig, and mdd");
    	}
	}
	
	public static StateFormatDef getStateFormat(){
		return stateFormat;
	}
	
	private static void setParallelFlag(String parallel){
		if(parallel.equalsIgnoreCase("true")){
			parallelFlag = true;
		}
		else if(!parallel.equalsIgnoreCase("false")){
			System.err.println("Warning: invalid PARALLEL option - default is \"false\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean getParallelFlag(){
		return parallelFlag;
	}
	
	public static boolean getClearMemoryFlag(){
		return clearMemoryFlag;
	}
	
	private static void setClearMemoryFlag(String flag){
		if(flag.equalsIgnoreCase("true")){
			clearMemoryFlag = true;
		}
		else if(!flag.equalsIgnoreCase("false")){
			System.err.println("Warning: invalid CLEAR_MEMORY option - default is \"false\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static boolean getSimplificationFlag(){
		return simplificationFlag;
	}
	
	private static void setSimplificationFlag(String flag){
		if(flag.equalsIgnoreCase("false")){
			simplificationFlag = false;
		}
		else if(!flag.equalsIgnoreCase("true")){
			System.err.println("Warning: invalid COMPOSITIONAL_SIMPLIFICATION option - default is \"true\"");
			System.err.println("---> Choices include: " + "true and false");
		}
	}
	
	public static void setOptions(Properties prop){
		String value = prop.getProperty("DOT_PATH");
        if(value != null) {
        	Options.setDotPath(value.trim());
        	prop.remove("DOT_PATH");
        }
        
        value = prop.getProperty("VERBOSITY");
        if(value != null) {
        	Options.setVerbosity(value.trim());
        	prop.remove("VERBOSITY");
        }
        
        value = prop.getProperty("TIMING_ANALYSIS");
        if(value != null){
        	Options.setTimingAnalsysisType(value.trim());
        	prop.remove("TIMING_ANALYSIS");
        }
        
        value = prop.getProperty("COMPOSITIONAL_MINIMIZATION");
        if(value != null){
        	Options.setCompositionalMinimization(value.trim());
        	prop.remove("COMPOSITIONAL_MINIMIZATION");
        }
        
        value = prop.getProperty("POR");
        if(value != null){
        	Options.setPOR(value.trim());
        	prop.remove("POR");
        }
        
        value = prop.getProperty("SEARCH");
        if(value != null){
        	Options.setSearchType(value.trim());
        	prop.remove("SEARCH");
        }
        
        value = prop.getProperty("STATE_FORMAT");
        if(value != null){
        	Options.setStateFormat(value.trim());
        	prop.remove("STATE_FORMAT");
        }
        
        value = prop.getProperty("STICKY_SEMANTICS");
        if(value != null){
        	Options.setStickySemantics(value.trim());
        	prop.remove("STICKY_SEMANTICS");
        }
        
        value = prop.getProperty("PARALLEL");
        if(value != null){
        	Options.setParallelFlag(value.trim());
        	prop.remove("PARALLEL");
        }
        
        value = prop.getProperty("CHECK_DISABLING_ERROR");
        if(value != null){
        	Options.setDisablingFlag(value.trim());
        	prop.remove("CHECK_DISABLING_ERROR");
        }
        
        value = prop.getProperty("CHECK_LIVELOCK");
        if(value != null){
        	Options.setLivelockFlag(value.trim());
        	prop.remove("CHECK_LIVELOCK");
        }
        
        value = prop.getProperty("CHECK_ASSERTION");
        if(value != null){
        	Options.setAssertionFlag(value.trim());
        	prop.remove("CHECK_ASSERTION");
        }
        
        value = prop.getProperty("CLEAR_MEMORY");
        if(value != null){
        	Options.setClearMemoryFlag(value.trim());
        	prop.remove("CLEAR_MEMORY");
        }
        
        value = prop.getProperty("COMPOSITIONAL_SIMPLIFICATION");
        if(value != null){
        	Options.setSimplificationFlag(value.trim());
        	prop.remove("COMPOSITIONAL_SIMPLIFICATION");
        }
        
        value = prop.getProperty("AUTO_FAILURE_REDUCTION");
        if(value != null){
        	Options.setAutoFailureFlag(value.trim());
        	prop.remove("AUTO_FAILURE_REDUCTION");
        }
        
        value = prop.getProperty("EQUIV_FAILURE_REMOVAL");
        if(value != null){
        	Options.setEquivFailureFlag(value.trim());
        	prop.remove("EQUIV_FAILURE_REMOVAL");
        }
        
        value = prop.getProperty("IMPROVED_EQUIV_REMOVAL");
        if(value != null){
        	Options.setImprovedEquivRemovalFlag(value.trim());
        	prop.remove("IMPROVED_EQUIV_REMOVAL");
        }
        
        value = prop.getProperty("MEM_UPPER_BOUND");
        if(value != null){
        	Options.setMemUpperBound(value.trim());
        	prop.remove("MEM_UPPER_BOUND");
        }
        
        value = prop.getProperty("TIME_UPPER_BOUND");
        if(value != null){
        	Options.setTimeUpperBound(value.trim());
        	prop.remove("TIME_UPPER_BOUND");
        }
        
        value = prop.getProperty("DRAW_FINAL_STATE_GRAPH");
        if(value != null){
        	Options.setDrawFinalStateGraph(value.trim());
        	prop.remove("DRAW_FINAL_STATE_GRAPH");
        }
        
        value = prop.getProperty("DRAW_MODULE_STATE_GRAPHS");
        if(value != null){
        	Options.setDrawModuleStateGraphs(value.trim());
        	prop.remove("DRAW_MODULE_STATE_GRAPHS");
        }
        
        for(Object o : prop.keySet()){
        	String s = (String) o;
        	if(s.startsWith("//")){
        		continue;
        	}
        	
        	System.err.println("warning: invalid option " + o);
        }
	}
}
