package platu.lpn.io;

import java.util.List;


public class Instance {
	// name of the new instantiated module
	private String moduleName = "";
	
	// name of the class used for instantiation
	private String className = null;
	
	// list of variables used as arguments
	private List<String> variableList = null;
	
	// list of modules the variables belong to, null if global or constant
	private List<String> varModuleList = null;
	
	// Line number of instance declaration
	private int lineNumber = 0;
	
	public Instance(String lpnLabel, String name, List<String> varList, List<String> modList){
		this.moduleName = name;
		this.className = lpnLabel;
		this.variableList = varList;
		this.varModuleList = modList;
	}
	
	/**
	 * Sets the line number where the instance was declared
	 * @param line - Line number
	 */
	public void setLineNumber(int line){
		this.lineNumber = line;
	}
	
	/**
	 * Returns the line number where this instance was declared
	 * @return Line number
	 */
	public int getLineNumber(){
		return this.lineNumber;
	}
	
	/**
	 * Returns the name of the new instantiated module.
	 * @return Module name
	 */
	public String getModuleName(){
		return this.moduleName;
	}
	
	/**
	 * Returns the name of the class used for instantiation.
	 * @return Class name
	 */
	public String getClassName(){
		return this.className;
	}
	
	/**
	 * Returns the list of variables used as arguments for instantiation.
	 * @return List of variables
	 */
	public List<String> getVariableList(){
		return this.variableList;
	}
	
	/**
	 * Returns the list of module names to which the argument variables belong.  
	 * Entry is null if an argument is a constant or global variable
	 * @return List of module names
	 */
	public List<String> getVarModuleList(){
		return this.varModuleList;
	}
}
