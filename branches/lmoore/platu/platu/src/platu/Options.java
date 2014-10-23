package platu;

public class Options {
	private static int verbosity = 0;
	private static String dotPath = "";
	private static String timingAnalysis = "off";
	private static String POR = "off";
	private static String searchType = "mono";
	private static String stateFormat = "explicit";
	private static boolean parallelFlag = false;
	private static boolean newParser = false;
	private static boolean stickySemantics = false;
	
	public static void setStickySemantics(){
		stickySemantics = true;
	}
	
	public static boolean getStickySemantics(){
		return stickySemantics;
	}
	
	public static void setVerbosity(int v){
		verbosity = v;
	}
	
	public static int getVerbosity(){
		return verbosity;
	}
	
	public static void setDotPath(String path){
		dotPath = path;
	}
	
	public static String getDotPath(){
		return dotPath;
	}
	
	public static void setTimingAnalsysis(String timing){
		timingAnalysis = timing;
	}
	
	public static String getTimingAnalysis(){
		return timingAnalysis;
	}
	
	public static void setPOR(String por){
		POR = por;
	}
	
	public static String getPOR(){
		return POR;
	}
	
	public static void setSearchType(String type){
		searchType = type;
	}
	
	public static String getSearchType(){
		return searchType;
	}
	
	public static void setStateFormat(String format){
		stateFormat = format;
	}
	
	public static String getStateFormat(){
		return stateFormat;
	}
	
	public static void setParallelFlag(boolean flag){
		parallelFlag = flag;
	}
	
	public static boolean getParallelFlag(){
		return parallelFlag;
	}
	
	public static void setNewParser(){
		newParser = true;
	}
	
	public static boolean getNewParser(){
		return newParser;
	}
}
