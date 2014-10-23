grammar PlatuInst;

options {
  language = Java;
}

@header{
    package platu.lpn.io;
    
    import java.util.StringTokenizer;
    import java.io.File;
    import platu.lpn.io.Instance;
    import java.util.Map.Entry;
    import java.util.HashMap;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.LinkedList;
    import platu.lpn.LPN;
    import platu.lpn.VarSet;
    import platu.lpn.LpnTranList;
    import platu.lpn.LPNTran;
    import platu.common.DualHashMap;
    import platu.lpn.VarExpr;
    import platu.lpn.VarExprList;
    import platu.project.Project;
    import platu.expression.*;   
}

@members{
	// static variables
    static private int INFINITY = Integer.MAX_VALUE;
    static private ExpressionNode ZERO = new ConstNode("FALSE", 0);  // constant false node
    static private ExpressionNode ONE = new ConstNode("TRUE", 1);  // constant true node
    static private Expression TrueExpr = new Expression(ONE); // constant true expression
    
    // static variables used to contain information for all the parsed module classes
    private boolean main = false;  // true if main module has been parsed
    public HashMap<String, LPN> LpnMap = new HashMap<String, LPN>();  // all modules parsed, keyed by module name
    public HashMap<String, Integer> GlobalVarHashMap = new HashMap<String, Integer>();  // global variables and associated values
    public HashMap<String, VarNode> GlobalVarNodeMap = new HashMap<String, VarNode>();
    public HashMap<String, Integer> GlobalConstHashMap = new HashMap<String, Integer>();  // global constants within a single lpn file
    public List<Instance> InstanceList = new ArrayList<Instance>();
    public HashSet<String> IncludeSet = new HashSet<String>();
    public HashMap<String, HashMap<String, List<String>>> ClassInputInitMap = new HashMap<String, HashMap<String, List<String>>>();
    public HashMap<String, Set<String>> ClassReadMap = new HashMap<String, Set<String>>();
    public HashMap<String, Set<String>> ClassWriteMap = new HashMap<String, Set<String>>();
    
    // non-static variables used during the parsing of each module class
    private HashMap<String, VarNode> VarNodeMap = null; // maps variable name to variable object
	private DualHashMap<String, Integer> VarIndexMap = null;  // maps variables to an array index
    private HashMap<String, Integer> ConstHashMap = null;  // constants within a single module
    private HashMap<String, Integer> StatevectorMap = null;  // module variables mapped to initial values
    private List<String> ArgumentList = null; // list of class arguments
    private VarSet Inputs = null;  // module inputs
    private VarSet Internals = null; // module internal variables
    private int VariableIndex = 0;  // count of index assigned to module variables
    private int TransitionIndex = 0; // count of index assigned to lpn transitions
    private HashMap<String, List<String>> InputInitializationMap = null; // maps input variables to the internal they initialize
    private Set<String> ClassReadSet = null; // set of class variables that are read
    private Set<String> ClassWriteSet = null; // set of class variables that are modified
    private Set<String> TransitionLabelSet = null; // set of LPN transition labels
    
    // ************************** Methods **********************************
	// outputs specified error message to the standard error stream and exits the program
	private void error(String errorMessage){
		System.err.println(errorMessage);
		System.exit(1);
	}
	
	// Creates the array node and element nodes for an input array variable
	private void createInputArray(String var, List<Integer> dimensionList){
		// calculate the number of elements in the array
		int numElements = 1;
		for(int i = 0; i < dimensionList.size(); i++){
			numElements = numElements * dimensionList.get(i);
		}
		
		// sequential list of element's associated VarNode object
		List<VarNode> varList = new ArrayList<VarNode>();
		
		// list containing the element's indices, initialized to the first element
		// used to name the element's VarNode object
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for(int i = 0; i < dimensionList.size(); i++){
			indexList.add(0);
		}
		
		// create element's associated VarNode
		for(int i = 0; i < numElements; i++){
			// build the element's VarNode name from the array name and indices
			String name = var;
			for(int j = 0; j < indexList.size(); j++){
				name = name + "[" + indexList.get(j) + "]";
			}
			
			// statevector index
			int vectorIndex = VariableIndex++;
			
			// create element's VarNode and set type
			VarNode element = new VarNode(name, vectorIndex);
			element.setType(platu.lpn.VarType.INPUT);
			
			// add element to sequential list of array's elemntal VarNodes
			varList.add(element);
			
			// add element's initial value to the statevector
			StatevectorMap.put(name, 0);
			
			// add element's statevector index to the VarIndexMap
   			VarIndexMap.insert(name, vectorIndex);
   			
   			// add element's VarNode to the VarNodeMap
   			VarNodeMap.put(name, element);
			
			// increment the element indices for the next element
			incrementElementIndex(indexList, dimensionList);
		}

		ArrayNode newArray = new ArrayNode(var, dimensionList.size(), dimensionList, varList);
		newArray.setType(platu.lpn.VarType.INPUT);
		VarNodeMap.put(var, newArray);
		Inputs.add(var);
		ArgumentList.add(var);
	}
	
	// increments a list of array index values according to an array's dimensions
	private void incrementElementIndex(List<Integer> indexList, List<Integer> dimensionList){
		if(indexList.size() != dimensionList.size()){
			// error
			System.exit(1);
		}
		
		for(int i = dimensionList.size()-1; i >= 0; i--){
			int indexValue = indexList.get(i);
			int dimValue = dimensionList.get(i);
			
			indexValue++;
			if(indexValue >= dimValue){
				indexValue = 0;
				indexList.set(i, indexValue);
			}
			else{
				indexList.set(i, indexValue);
				break;
			}
		}
	}
}

@rulecatch{
    catch (RecognitionException e){
    	System.err.println(e.input.getSourceName() + ":");
    	System.err.println("---> Mismatched token '" + e.token.getText() + "' on line " + e.line);
    	System.err.println();
    	System.exit(1);
    }
}

@lexer::header{
    package platu.lpn.io;
}

parseLpnFile[Project prj]
    :	include? globalConstants? main[prj]? (moduleClass[prj])* EOF
    ;
    
include
	:	'<' 'include' '>' (PATH ';'
			{
				File f = new File($PATH.text);
        		IncludeSet.add(f.getAbsolutePath());
			}
		)* '<' '/include' '>'
	;
	
main[Project prj]
	:	'<' 'main' '>'
			{
				if(main == true){
					error("error: multiple main modules defined");
				}
				
				main = true;
				
				// initialize hash map holding symolic constants
				ConstHashMap = new HashMap<String, Integer>();
				VarNodeMap = new HashMap<String, VarNode>();
			}
		constants? mainVariables?  instantiation '<' '/main' '>'
	;

moduleClass[Project prj] returns [LPN lpn]
    :	( '<' 'class' 'name' '=' '"' modName=ID
    		{
    			// module names must be unique
    			if(LpnMap.containsKey($modName.text)){
    				error("error on line " + $modName.getLine() + ": module " + $modName.text + " already exists");
    			}
    			else if($modName.text.equals("main")){
    				error("error on line " + $modName.getLine() + ": main is reserved");
    			}
    			
    			// initialize non static variables for new module
        	    VarIndexMap = new DualHashMap<String, Integer>();
			    ConstHashMap = new HashMap<String, Integer>();
			    VarNodeMap = new HashMap<String, VarNode>();
			    Inputs = new VarSet();
			    Internals = new VarSet();
			    ArgumentList = new ArrayList<String>();
			    StatevectorMap = new HashMap<String, Integer>();
			    InputInitializationMap = new HashMap<String, List<String>>();
			    ClassReadSet = new HashSet<String>();
			    ClassWriteSet = new HashSet<String>();
			    TransitionLabelSet = new HashSet<String>();
			    VariableIndex = 0;
			    TransitionIndex = 0;
    		}
    	'"' 'arg' '=' '"' inputs? '"' '>' constants? variables? logic '<' '/class' '>'
			{
				// create new LPN
	            int[] initialMarking = new int[$logic.initMarking.size()];
	            
	            int i = 0;
	            for(Integer mark : $logic.initMarking){
	            	initialMarking[i++] = mark;
	            }
	            
				$lpn = new LPN(prj, $modName.text, Inputs, Internals, VarNodeMap, $logic.lpnTranSet, 
	         			StatevectorMap, initialMarking);
				
	            $lpn.setVarIndexMap(VarIndexMap);         
	            $logic.lpnTranSet.setLPN($lpn);     

	            LpnMap.put($modName.text, $lpn);
	            $lpn.setArgumentList(ArgumentList);
	            
	            ClassInputInitMap.put($modName.text, InputInitializationMap);
	            ClassReadMap.put($modName.text, ClassReadSet);
	            ClassWriteMap.put($modName.text, ClassWriteSet);
			}
		)
    ; 
   
// parses the input arguments for a module class 
inputs
	:	((arrayArg2=ID 
    		{
    			// check aginst globals
	   			if(GlobalConstHashMap.containsKey($arrayArg2.text)){
	   				error("error on line " + $arrayArg2.getLine() + ": variable " + $arrayArg2.text + " is already defined as a global constant");
	   			}
	   			
	   			List<Integer> dimensionList = new ArrayList<Integer>();
    		}
    	('[' arrayVal=constIntValue 
    		{
    			dimensionList.add($arrayVal.value);
    		}
    	']')+
    		{
    			createInputArray($arrayArg2.text, dimensionList);
    		}
    	) | (arg2=ID
    		{
    			// check against globals
    			if(GlobalConstHashMap.containsKey($arg2.text)){
    				error("error on line " + $arg2.getLine() + ": variable " + $arg2.text + " is already defined as a global constant");
    			}
    			
    			// add variable and value to state vector
				StatevectorMap.put($arg2.getText(), 0);
				
				// generate variable index and create new var node  
				int index = VariableIndex++;
   				VarIndexMap.insert($arg2.getText(), index);
   				
   				VarNode inputVarNode =  new VarNode($arg2.getText(), index);
   				inputVarNode.setType(platu.lpn.VarType.INPUT);
   				VarNodeMap.put($arg2.getText(), inputVarNode);
    			
    			ArgumentList.add($arg2.getText());
				Inputs.add($arg2.getText());
    		}
    	)) ((',' arrayArg=ID
    		{
	    		// check aginst globals and other inputs
	   			if(GlobalConstHashMap.containsKey($arrayArg.text)){
	   				error("error on line " + $arrayArg.getLine() + ": variable " + $arrayArg.text + " is already defined as a global constant");
	   			}
	   			else if(VarNodeMap.containsKey($arrayArg.text)){
	   				error("error on line " + $arrayArg.getLine() + ": variable " + $arrayArg.text + " is already defined");
	   			}
	   			
	   			List<Integer> dimensionList = new ArrayList<Integer>();
	   		}
    	('[' arrayVal=constIntValue
    		{
    			dimensionList.add($arrayVal.value);
    		}
    	']')+
    		{
    			createInputArray($arrayArg.text, dimensionList);
    		} 
    	) | (',' arg=ID 
    		{
    			// check aginst globals and other inputs
    			if(GlobalConstHashMap.containsKey($arg.text)){
    				error("error on line " + $arg.getLine() + ": variable " + $arg.text + " is already defined as a global constant");
    			}
    			else if(VarNodeMap.containsKey($arg.text)){
    				error("error on line " + $arg.getLine() + ": variable " + $arg.text + " is already defined");
    			}
    			
    			// add variable and value to state vector
				StatevectorMap.put($arg.getText(), 0);
				
				// generate variable index and create new var node  
				int index = VariableIndex++;
   				VarIndexMap.insert($arg.getText(), index);
   				
   				VarNode inputVarNode = new VarNode($arg.getText(), index);
   				inputVarNode.setType(platu.lpn.VarType.INPUT);
   				VarNodeMap.put($arg.getText(), inputVarNode);
    			ArgumentList.add($arg.getText());
				Inputs.add($arg.getText());
    		}
    	))*
	;
	
// parses the constants in a module class
constants
	:	'<' 'const' '>' (const1=ID '=' intValue
			{
				// make sure constant is not defined as something else
				if(VarNodeMap.containsKey($const1.text)){
					error("error on line " + $const1.getLine() + ": " + $const1.text + " already exists as a variable"); 
				}
				else if(GlobalConstHashMap.containsKey($const1.text)){
				    error("error on line " + $const1.getLine() + ": " + $const1.text + " already exists as a global constant");
				}
				
				// put will override previous value
				Integer result = ConstHashMap.put($const1.text, $intValue.value);
				
				// make sure constant hasn't already been defined
				if(result != null){
					error("error on line " + $const1.getLine() + ": " + $const1.text + " has already been defined");
				}
			}
		 ';')* '<' '/const' '>'
	;
	
// parses the global constants
globalConstants
    :   '<' 'const' '>' (const1=ID '=' intValue 
            {
         
            	// put will override previous value
                Integer result = GlobalConstHashMap.put($const1.text, $intValue.value);
                
                // make sure constant hasn't already been defined
                if(result != null){
                    error("error on line " + $const1.getLine() + ": " + $const1.text + " has already been defined");
                }
            }
         ';')* '<' '/const' '>'
    ;
	
// parses a variable or an array in a module class
variables
	:	'<' 'var' '>' ( varDecl | arrayDecl)* '<' '/var' '>'
	;

// parses a variable or an array in the main module	
mainVariables
	:	'<' 'var' '>' ( mainVarDecl | mainArrayDecl)* '<' '/var' '>'
	;

// parses a varible in a module class
varDecl
	:	var=ID 
			{
				// check variable is unique in scope
				if(GlobalConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global constant"); 
				}
				else if(ConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a constant"); 
				}
				else if(VarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " has already been defined");
				}
				
				// add variable and value to state vector
				StatevectorMap.put($var.text, 0);
				
				// generate variable index and create new var node  
				int index = VariableIndex++;
   				VarIndexMap.insert($var.text, index);
   				
   				VarNode internalVar = new VarNode($var.text, index);
   				internalVar.setType(platu.lpn.VarType.INTERNAL);
   				VarNodeMap.put($var.text, internalVar);
				Internals.add($var.text);
			}
		('=' val=assignmentIntValue[$var.text]
			{
				// add variable and value to state vector
				StatevectorMap.put($var.text, $val.value);
				
				if($val.atomic)
					internalVar.setAtomic();
			}
		)? ';'
	;
	
// parses a variable in the main module
mainVarDecl
	:	var=ID 
			{
				// check variable is unique in scope
				if(GlobalConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global constant"); 
				}
				else if(ConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a constant");
				}
				else if(GlobalVarHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global variable"); 
				}
				else if(GlobalVarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global array"); 
				}
				
				// add variable and value to global hash map
				GlobalVarHashMap.put($var.text, 0);
			}
		('=' mainIntValue
			{
				// replace initial value
				GlobalVarHashMap.put($var.text, $mainIntValue.value);
			}
		)? ';'
	;
	
// parses an array in a module class
arrayDecl
	:	var=ID 
			{
				// check variable is unique in scope
				if(GlobalConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global constant"); 
				}
				else if(ConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a constant");
				}
				else if(VarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " has already been defined");
				}
				
				List<Integer> dimensionList = new ArrayList<Integer>();
				int startIndex = VariableIndex;
				int numElements = 1;
				int currentIndex = 0;
				int lastIndex = 0;
				boolean atomic = true;
			}
		('[' (arrayExpr=expression
			{
				dimensionList.add($arrayExpr.value);
			}
		) ']')+
			{
				// calculate the number of elements in the array
				numElements = 1;
				for(int i = 0; i < dimensionList.size(); i++){
					numElements = numElements * dimensionList.get(i);
				}
				
				// sequential list of element's associated VarNode object
				List<VarNode> varList = new ArrayList<VarNode>();
				
				// list containing the element's indices, initialized to the first element
				// used to name the element's VarNode object
				ArrayList<Integer> indexList = new ArrayList<Integer>();
				for(int i = 0; i < dimensionList.size(); i++){
					indexList.add(0);
				}
				
				// create element's associated VarNode
				for(int i = 0; i < numElements; i++){
					// build the element's VarNode name from the array name and indices
					String name = $var.text;
					for(int j = 0; j < indexList.size(); j++){
						name = name + "[" + indexList.get(j) + "]";
					}
					
					// statevector index
					int vectorIndex = VariableIndex++;
					
					// create element's VarNode and set type
					VarNode element = new VarNode(name, vectorIndex);
					element.setType(platu.lpn.VarType.INTERNAL);
					
					// add element to sequential list of array's elemntal VarNodes
					varList.add(element);
					
					// add element's initial value to the statevector
					StatevectorMap.put(name, 0);
					
					// add element's statevector index to the VarIndexMap
		   			VarIndexMap.insert(name, vectorIndex);
		   			
		   			// add element's VarNode to the VarNodeMap
		   			VarNodeMap.put(name, element);
					
					// increment the element indices for the next element
					incrementElementIndex(indexList, dimensionList);
				}
				
				lastIndex = VariableIndex - 1;
			}
		('=' ('{' ( (arrayInit1[startIndex, lastIndex]) | (nest1=arrayInit[2, dimensionList, startIndex, lastIndex]
			{
				int count = 1;
				currentIndex = $nest1.indexValue;
			} 
		(',' nest2=arrayInit[2, dimensionList, currentIndex, lastIndex]
			{
				count++;
				currentIndex = $nest2.indexValue;
			}
		)*
			{
				// check dimensions
				if(count != dimensionList.get(0)){
					error("error on line ??: invalid array initialization");
				}
				else if(currentIndex != lastIndex+1){
					error("error on line ??: invalid array initialization");
				}
			} 
		)) '}') | (var2=ID
			{
				if(!VarNodeMap.containsKey($var2.text)){
					error("error on line " + $var2.getLine() + ": " + $var2.text + " is not an array");
				}
				
				ArrayNode node2 = (ArrayNode) VarNodeMap.get($var2.text);
				
				// make sure the number of dimesnsions match
				List<Integer> dimList2 = node2.getDimensionList();
				if(dimList2.size() != dimensionList.size()){
					error("error on line " + $var2.getLine() + ": incompatible array assignment");
				}
				
				// make sure that each dimension is the same size
				for(int i = 0; i < dimList2.size(); i++){
					if(dimList2.get(i) != dimensionList.get(i)){
						error("error on line " + $var2.getLine() + ": incompatible array assignment");
					}
				}
				
				boolean isInput = false;
				if(Inputs.contains($var2.text)){
					isInput = true;
				}
				
				List<VarNode> elementList2 = node2.getElementList();
				for(int i = 0; i < numElements; i++){
					String element2 = elementList2.get(i).getName();
					String element1 = varList.get(i).getName();
					
					int val = StatevectorMap.get(element2);
					StatevectorMap.put(element1, val);
					
					if(isInput){
						List<String> assignmentList = InputInitializationMap.get(element2);
						if(assignmentList == null){
							assignmentList = new LinkedList<String>();
							InputInitializationMap.put(element2, assignmentList);
						}
						
						assignmentList.add(element1);
					}
				}
				
				atomic = false;
			} 
		) )? 
			{
				// create new array node
				ArrayNode newArray = new ArrayNode($var.text, dimensionList.size(), dimensionList, varList);
				newArray.setType(platu.lpn.VarType.INTERNAL);
				VarNodeMap.put($var.text, newArray);
				Internals.add($var.text);
				
				if(atomic){
					newArray.setAtomic();
					
					for(VarNode element : varList){
						element.setAtomic();
					}
				}
			}
		';'
	;
	
// parses an array in the main module
mainArrayDecl
	:	var=ID 
			{
				// check variable is unique in scope
				if(GlobalConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global constant"); 
				}
				else if(ConstHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a constant"); 
				}
				else if(GlobalVarHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global variable"); 
				}
				else if(GlobalVarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": " + $var.text + " is a global array"); 
				}
				
				List<Integer> dimensionList = new ArrayList<Integer>();
				int startIndex = 0;
				int numElements = 1;
				int currentIndex = 0;
				int lastIndex = 0;
			}
		('[' ( dimVal=mainIntValue
			{
				dimensionList.add($dimVal.value);
			}
		) ']')+
			{
				// calculate the number of elements in the array
				numElements = 1;
				for(int i = 0; i < dimensionList.size(); i++){
					numElements = numElements * dimensionList.get(i);
				}
				
				// sequential list of element's associated VarNode object
				List<VarNode> varList = new ArrayList<VarNode>();
				
				// list containing the element's indices, initialized to the first element
				// used to name the element's VarNode object
				ArrayList<Integer> indexList = new ArrayList<Integer>();
				for(int i = 0; i < dimensionList.size(); i++){
					indexList.add(0);
				}
				
				// create element's associated VarNode
				for(int i = 0; i < numElements; i++){
					// build the element's VarNode name from the array name and indices
					String name = $var.text;
					for(int j = 0; j < indexList.size(); j++){
						name = name + "[" + indexList.get(j) + "]";
					}
					
					// statevector index
					int vectorIndex = VariableIndex++;
					
					// create element's VarNode and set type
					VarNode element = new VarNode(name, vectorIndex);
					element.setType(platu.lpn.VarType.GLOBAL);
					
					// add element to sequential list of array's elemntal VarNodes
					varList.add(element);
					
					// add element's initial value to the GlobalVarHashMap
					GlobalVarHashMap.put(name, 0);
					
					// increment the element indices for the next element
					incrementElementIndex(indexList, dimensionList);
				}
				
				lastIndex = startIndex + numElements - 1;
			}
		('=' '{' (mainArrayInit1[startIndex, lastIndex, varList] | (nest1=mainArrayInit[2, dimensionList, startIndex, lastIndex, varList]
			{
				int count = 1;
				currentIndex = $nest1.indexValue;
			} 
		(',' nest2=mainArrayInit[2, dimensionList, currentIndex, lastIndex, varList]
			{
				count++;
				currentIndex = $nest2.indexValue;
			}
		)*
			{
				// check dimension size
				// make sure the number of dimensions match
				if(count != dimensionList.get(0)){
					error("error on line ??: invalid array initialization");
				}
				else if(currentIndex != lastIndex+1){
					error("error on line ??: invalid array initialization");
				}
			} 
		)) '}' )? 
			{
				// create new array node
				ArrayNode newArray = new ArrayNode($var.text, dimensionList.size(), dimensionList, varList);
				newArray.setType(platu.lpn.VarType.GLOBAL);
				GlobalVarNodeMap.put($var.text, newArray);
				GlobalVarHashMap.put($var.text, null);
			}
		';'
	;
	
/*
 * recursive c-style array initialization for multi-dimensional arrays
 */
arrayInit[int dimension, List<Integer> dimensionList, int index, int lastIndex] returns [int indexValue]
	:	('{' 
			{				
				int count = 1;
				String varName = VarIndexMap.getKey(index);
			} 
		val1=assignmentIntValue[varName]
			{
				// override default value
				StatevectorMap.put(varName, $val1.value);
				
				index++;
				$indexValue = index;
			}
		(',' 
			{
				if(index > lastIndex){
					error("error on line ??: value out of bounds");
				}
				
				String varName2 = VarIndexMap.getKey(index);				
			} 
		val2=assignmentIntValue[varName2]
			{
				// override default value
				StatevectorMap.put(varName2, $val2.value);
				
				index++;
				$indexValue = index;
				count++;
			}
		)* '}'
			{
				if(count != $dimensionList.get($dimension-1)){
					error("error on line ??: invalid array initialization");
				}
				else if($dimension > $dimensionList.size()){
					error("error on line ??: invalid array initialization");
				}
			}
		)
	| 
		( '{' nest1=arrayInit[$dimension+1, $dimensionList, $index, $lastIndex]
			{
				$indexValue = $nest1.indexValue;
				int numLists = 1;
				int count = 1;
			}
		(',' nest2=arrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex]
			{
				$indexValue = $nest2.indexValue;
				numLists++;
				count++;
			}
		)*'}'
			{
				// make sure the number of lists maches the # of index value at current dimension
				if(count != $dimensionList.get($dimension-1)){
					error("error on line ??: invalid array initialization");
				}
				else if($dimension > $dimensionList.size()){
					error("error on line ??: invalid array initialization");
				}
			}
		)
	;
	
/*
 * recursive c-style array initialization for a single dimensional array
 */
arrayInit1[int startIndex, int lastIndex]
	:		{
				int index = startIndex;
				boolean singleValue = true;
				String varName = VarIndexMap.getKey(index);
			}
		val1=assignmentIntValue[varName]
			{
				// override default value
				StatevectorMap.put(varName, $val1.value);
				
				index++;
			} 
		(',' 
			{
				singleValue = false;
				if(index > lastIndex){
					error("error on line ??: value out of bounds");
				}
				
				String varName2 = VarIndexMap.getKey(index);
			}
		val2=assignmentIntValue[varName2]
			{
				// override default value
				StatevectorMap.put(varName2, $val2.value);
				
				index++;
			} 
		)*
			{
				if(singleValue){
					// assign value to all elements from nextIndex to nextIndex+numElements-1
					int elementValue = $val1.value;
					for(int i = index; i <= lastIndex; i++){
						String name = VarIndexMap.getKey(i);
						StatevectorMap.put(name, elementValue);
					}
				}
				else{
					// make sure the number of values matches the dimension size
					if(index != lastIndex+1){
						error("error on line ??: invalid array initialization");
					}
				}
			}
	;
	
/*
 * recursive c-style array initialization for a multi-dimensinal array in the main module
 */
mainArrayInit[int dimension, List<Integer> dimensionList, int index, int lastIndex, List<VarNode> varList] returns [int indexValue]
	:	('{' val1=mainIntValue
			{				
				int count = 1;
				int val = $val1.value;
				
				// override default value
				String varName = varList.get(index).getName();
				GlobalVarHashMap.put(varName, val);
				
				index++;
				$indexValue = index;
			} 
		(',' val2=mainIntValue
			{
				if(index > lastIndex){
					error("error on line ??: value out of bounds");
				}
				
				val = $val2.value;
				
				// override default value
				String varName2 = varList.get(index).getName();				
				GlobalVarHashMap.put(varName2, val);
				
				index++;
				$indexValue = index;
				count++;
			} 
		)* '}'
			{
				// make sure the number of values matches the size of the dimension
				// make sure the number of dimensions match
				if(count != $dimensionList.get($dimension-1)){
					error("error on line ??: invalid array initialization");
				}
				else if($dimension > $dimensionList.size()){
					error("error on line ??: invalid array initialization");
				}
			}
		)
	| 
		( '{' nest1=mainArrayInit[$dimension+1, $dimensionList, $index, $lastIndex, $varList]
			{
				$indexValue = $nest1.indexValue;
				int numLists = 1;
				int count = 1;
			}
		(',' nest2=mainArrayInit[$dimension+1, $dimensionList, $indexValue, $lastIndex, $varList]
			{
				$indexValue = $nest2.indexValue;
				numLists++;
				count++;
			}
		)*'}'
			{
				// make sure the number of lists maches the # of index value at current dimension
				// make sure the number of dimensions match
				if(count != $dimensionList.get($dimension-1)){
					error("error on line ??: invalid array initialization");
				}
				else if($dimension > $dimensionList.size()){
					error("error on line ??: invalid array initialization");
				}
			}
		)
	;
	
/*
 * recursive c-style array initialization for a single dimensional array in the main module
 */
mainArrayInit1[int startIndex, int lastIndex, List<VarNode> varList]
	:	val1=mainIntValue
			{
				int index = startIndex;
				boolean singleValue = true;
				int val = $val1.value;
				
				// override default value
				String varName = varList.get(index).getName();
				GlobalVarHashMap.put(varName, val);
				
				index++;
			} 
		(',' val2=mainIntValue
			{
				singleValue = false;
				if(index > lastIndex){
					error("error on line ??: value out of bounds");
				}
				
				val = $val2.value;
				
				// override default value
				String varName2 = varList.get(index).getName();
				GlobalVarHashMap.put(varName2, val);
				
				index++;
			} 
		)*
			{
				if(singleValue){
					// assign value to all elements from nextIndex to nextIndex+numElements-1
					int elementValue = $val1.value;
					for(int i = index; i <= lastIndex; i++){
						String name = varList.get(i).getName();
						GlobalVarHashMap.put(name, elementValue);
					}
				}
				else{
					// make sure the number of values matches the dimension size
					if(index != lastIndex+1){
						error("error on line ??: invalid array initialization");
					}
				}
			}
	;
	
// parses and returns the value of an integer, symbolic constant, variable, or array element
intValue returns [int value]
	:		{boolean negative = false;}
		('-' {negative = !negative;})* intVal=INT
			{
				$value = Integer.parseInt($intVal.text);
				if(negative)
					$value = -$value;
			} 
		| var=ID
			{
				if(GlobalConstHashMap.containsKey($var.text)){
					$value = GlobalConstHashMap.get($var.text);
				}
				else if(ConstHashMap.containsKey($var.text)){
					$value = ConstHashMap.get($var.text);
				}
				else if(StatevectorMap.containsKey($var.text)){
					$value = StatevectorMap.get($var.text);
					ClassReadSet.add($var.text);
				}
				else if(VarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": incorrect usage of array variable " + $var.text);
				}
				else{
					error("error on line " + $var.getLine() + ": " + $var.text + " is not defined"); 
				}
			}
		| (arrayVar=ID 
			{
				List<Integer> indexList = new ArrayList<Integer>();
			}
		('[' expression
			{
				indexList.add($expression.value);
			} 
		']')+
			{
				// check for valid ID (make sure its an array variable)
				if(GlobalConstHashMap.containsKey($arrayVar.text)){
					System.err.println("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array"); 
    				System.exit(1);
				}
				else if(ConstHashMap.containsKey($arrayVar.text)){
					System.err.println("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array"); 
    				System.exit(1);
				}
				else if(VarNodeMap.containsKey($arrayVar.text)){
					VarNode node = VarNodeMap.get($arrayVar.text);
					if(!ArrayNode.class.isAssignableFrom(node.getClass())){
						error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array"); 
					}
					
					ArrayNode array = (ArrayNode) node;
					if(indexList.size() != array.numDimensions()){
						error("error on line " + $arrayVar.getLine() + ": invalid number of dimensions");
					}
				}
				else if(StatevectorMap.containsKey($arrayVar.text)){
					error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array");
				}
				else{
					error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not defined"); 
				}
				
				// build name
				String arrayName = $arrayVar.text;
				for(int i = 0; i < indexList.size(); i++){
					arrayName = arrayName + "[" + indexList.get(i) + "]";
				}
				
				// get value from StatevectorMap
				Integer val = StatevectorMap.get(arrayName);
				
				if(val == null){
					System.err.println("error on line " + $arrayVar.getLine() + ": invalid bounds"); 
    				System.exit(1);
				}
				
				$value = val;
				ClassReadSet.add($arrayVar.text);
			}
		)
	;
	
// parses returns the value of an integer or symbolic constant
constIntValue returns [int value]
	:		{boolean negative = false;}
		('-' {negative = !negative;})* intVal=INT
			{
				$value = Integer.parseInt($intVal.text);
				if(negative)
					$value = -$value;
			} 
		| var=ID
			{
				if(GlobalConstHashMap.containsKey($var.text)){
					$value = GlobalConstHashMap.get($var.text);
				}
				else if(ConstHashMap.containsKey($var.text)){
					$value = ConstHashMap.get($var.text);
				}
				else{
					error("error on line " + $var.getLine() + ": constant " + $var.text + " is not defined"); 
				}
			}
	;
	
// parses returns the value of an integer, symbolic constant, or variable in the main module
mainIntValue returns [int value]
	:		{boolean negative = false;}
		('-' {negative = !negative;})* intVal=INT
			{
				$value = Integer.parseInt($intVal.text);
				if(negative)
					$value = -$value;
			} 
		| var=ID
			{
				if(GlobalConstHashMap.containsKey($var.text)){
					$value = GlobalConstHashMap.get($var.text);
				}
				else if(ConstHashMap.containsKey($var.text)){
					$value = ConstHashMap.get($var.text);
				}
				else if(GlobalVarHashMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": variables can only be used in instantiation"); 
				}
				else if(GlobalVarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": variables can only be used in instantiation"); 
				}
				else{
					error("error on line " + $var.getLine() + ": " + $var.text + " is not defined"); 
				}
			}
	;

/*
 * parses and returns the value of an integer, symbolic constant, variable, or array element
 * in a module class initialization
 */
assignmentIntValue[String lhs] returns [int value, boolean atomic]
	:		{boolean negative = false;}
		( ('-' {negative = !negative;})* intVal=INT )
			{
				$value = Integer.parseInt($intVal.text);
				if(negative)
					$value = -$value;
					
				$atomic = true;
			} 
		| var=ID
			{
				if(GlobalConstHashMap.containsKey($var.text)){
					$value = GlobalConstHashMap.get($var.text);
					$atomic = true;
				}
				else if(ConstHashMap.containsKey($var.text)){
					$value = ConstHashMap.get($var.text);
					$atomic = true;
				}
				else if(StatevectorMap.containsKey($var.text)){
					$value = StatevectorMap.get($var.text);
					
					if(Inputs.contains($var.text)){
						List<String> assignmentVars = InputInitializationMap.get($var.text);
						if(assignmentVars == null){
							assignmentVars = new LinkedList<String>();
							InputInitializationMap.put($var.text, assignmentVars);
						}
						
						assignmentVars.add($lhs);
						
						$atomic = false;
					}
					else{
						error("error on line " + $var.getLine() + ": only input varaibles can be used for initialization");
					}
					
					ClassReadSet.add($var.text);
				}
				else if(VarNodeMap.containsKey($var.text)){
					error("error on line " + $var.getLine() + ": incorrect usage of array variable " + $var.text);
				}
				else{
					error("error on line " + $var.getLine() + ": " + $var.text + " is not defined"); 
				}
			}
		| (arrayVar=ID 
			{
				List<Integer> indexList = new ArrayList<Integer>();
			}
		('[' expression
			{
				indexList.add($expression.value);
			} 
		']')+
			{
				// check for valid ID (make sure its an array variable)
				if(GlobalConstHashMap.containsKey($arrayVar.text)){
					error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array"); 
				}
				else if(ConstHashMap.containsKey($arrayVar.text)){
					error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array"); 
				}
				else if(VarNodeMap.containsKey($arrayVar.text)){
					VarNode node = VarNodeMap.get($arrayVar.text);
					if(!ArrayNode.class.isAssignableFrom(node.getClass())){
						error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array"); 
					}
					
					ArrayNode array = (ArrayNode) node;
					if(indexList.size() != array.numDimensions()){
						error("error on line " + $arrayVar.getLine() + ": invalid number of dimensions");
					}
				}
				else if(StatevectorMap.containsKey($arrayVar.text)){
					error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array");
				}
				else{
					error("error on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not defined"); 
				}
				
				// build array element's name
				String arrayElement = $arrayVar.text;
				for(int i = 0; i < indexList.size(); i++){
					arrayElement = arrayElement + "[" + indexList.get(i) + "]";
				}
				
				// get value from StatevectorMap
				Integer val = StatevectorMap.get(arrayElement);
				
				if(val == null){
					System.err.println("error on line " + $arrayVar.getLine() + ": invalid bounds"); 
    				System.exit(1);
				}
				
				// check if the array is an input
				if(Inputs.contains($arrayVar)){
					List<String> assignmentVars = InputInitializationMap.get(arrayElement);
					if(assignmentVars == null){
						assignmentVars = new LinkedList<String>();
						InputInitializationMap.put(arrayElement, assignmentVars);
					}
					
					assignmentVars.add($lhs);
				}
				else{
					error("error on line " + $arrayVar.getLine() + ": only input varaibles can be used for initialization");
				}
				
				$value = val;
				$atomic = false;
				
				ClassReadSet.add($arrayVar.text);
			}
		)
	;
	
// parses the instantiation of a module	
instantiation
    :	(modName=ID instName=ID 
    		{
    			List<String> argList = new ArrayList<String>();
    			List<String> moduleList = new ArrayList<String>();
    		}
    	'(' arguments[$modName.text, argList, moduleList]? ')' ';'
    		{
    			Instance inst = new Instance($modName.text, $instName.text, argList, moduleList);
    			inst.setLineNumber($modName.getLine());
    			InstanceList.add(inst);
    		}
    	)+
    ;
    
// parses the arguments passed during instantiation
arguments[String modName, List<String> argList, List<String> moduleList]
	:	(id1=ID // main variable
    		{
    			if(GlobalConstHashMap.containsKey($id1.text)){
    				moduleList.add(null);
    				argList.add(Integer.toString(GlobalConstHashMap.get($id1.text)));
    			}
    			else if(ConstHashMap.containsKey($id1.text)){
    				moduleList.add(null);
    				argList.add(Integer.toString(ConstHashMap.get($id1.text)));
    			}
    			else if(GlobalVarHashMap.containsKey($id1.text)){
    				moduleList.add(null);
    				argList.add($id1.text);
    			}
    			else if(GlobalVarNodeMap.containsKey($id1.text)){
    				moduleList.add(null);
    				argList.add($id1.text);
    			}
    			else{
    				error("error on line " + $id1.getLine() + ": variable " + $id1.text + " is not declared");
    			}
    			
    		}
    	| ({boolean negative = false;} ('-' {negative = !negative;})* int1=INT
    		{
    			int value = Integer.parseInt($int1.text);
    			if(negative)
    				value = -value;
    				
    			moduleList.add(null);
    			argList.add(Integer.toString(value));
    		}
    	) | var=MEMBER // output variable
    		{
    			// get module variable belongs to
    			String buffer = $var.text;
        		StringTokenizer tk = new StringTokenizer(buffer, ".");
    			String module = tk.nextToken();
    			
    			if(modName.equals(module)){
    				error("error on line " + $var.getLine() + ": variable from the same module cannot be used as an input");
    			}
    			
    			moduleList.add(module);
    			argList.add($var.text);
    		}
    	)( ',' ( id2=ID // main variable
    		{
    			if(GlobalConstHashMap.containsKey($id2.text)){
    				moduleList.add(null);
    				argList.add(Integer.toString(GlobalConstHashMap.get($id2.text)));
    			}
    			else if(ConstHashMap.containsKey($id2.text)){
    				moduleList.add(null);
    				argList.add(Integer.toString(ConstHashMap.get($id2.text)));
    			}
    			else if(GlobalVarHashMap.containsKey($id2.text)){
    				moduleList.add(null);
    				argList.add($id2.text);
    			}
    			else if(GlobalVarNodeMap.containsKey($id2.text)){
    				moduleList.add(null);
    				argList.add($id2.text);
    			}
    			else{
    				error("error on line " + $id2.getLine() + ": variable " + $id2.text + " is not declared");
    			};
    		}
    	| ({boolean negative = false;} ('-' {negative = !negative;})* int2=INT
    		{
    			int value = Integer.parseInt($int2.text);
    			if(negative)
    				value = -value;
    				
    			moduleList.add(null);
    			argList.add(Integer.toString(value));
    		}
    	) | var2=MEMBER // output variable
    		{
    			// get module variable belongs to
    			String buffer2 = $var2.text;
        		StringTokenizer tk2 = new StringTokenizer(buffer2, ".");
        		
    			String module2 = tk2.nextToken();
    			String variable2 = tk2.nextToken();
    			
    			if(modName.equals(module2)){
    				error("error on line " + $var2.getLine() + ": variable from the same module cannot be used as an input");
    			}
    			
    			moduleList.add(module2);
    			argList.add(module2 + "." + variable2);	
    		}
    	) )*
	;

// parses the logic of a module class
logic returns [List<Integer> initMarking, LpnTranList lpnTranSet]
    :   {$lpnTranSet = new LpnTranList();}
    	marking (transition {$lpnTranSet.add($transition.lpnTran);})+
        {
            $initMarking = $marking.mark;
        }
    ; 
    
// parses the markings of a module class
marking returns [List<Integer> mark]
    :   {$mark = new LinkedList<Integer>();}
        ('<' 'm' '>' (m1=intValue
        	{
        		$mark.add($m1.value);
        	} 
        (',' m2=intValue
        	{
        		$mark.add($m2.value);
        	}
       	)*)? '<' '/m' '>')?
    ;
 
// parses the transitions of a module class
transition returns [LPNTran lpnTran]
    :   	{
	    		ArrayList<Integer> presetList = new ArrayList<Integer>();  
	    		ArrayList<Integer> postsetList = new ArrayList<Integer>(); 
	    		VarExprList assignmentList = new VarExprList();
	    		ArrayList<Expression> assertionList = new ArrayList<Expression>();
	    		Expression guardExpr = new Expression(new TrueNode()); 
	    		String label = "";
	    		int delayLB = 0; 
	    		int delayUB = INFINITY;
	    		boolean local = true;
	    	}
    	'<' 'tr' lbl='label' '=' '"' (lblInt=INT
    		{
    			label = $lblInt.text;
    			if(!TransitionLabelSet.add(label)){
    				error("error on line " + $lbl.getLine() + ": duplicate label " + label);
    			}
    		} 
    	| lblString=LABELSTRING
    		{
    			label = $lblString.text;
    			if(!TransitionLabelSet.add(label)){
    				error("error on line " + $lbl.getLine() + ": duplicate label " + label);
    			}
    		}
    	| lblID=ID
    		{
    			label = $lblID.text;
    			if(!TransitionLabelSet.add(label)){
    				error("error on line " + $lbl.getLine() + ": duplicate label " + label);
    			}
    		}
    	) '"' preset[presetList] postset[postsetList] '>' (guard 
    		{
    			guardExpr = $guard.expr;
    		}
    	)? (delay 
    		{
    			delayLB = $delay.delayLB; 
    			delayUB = $delay.delayUB;
    		}
    	)? ((assertion
    		{
    			if($assertion.booleanExpr != null){		
  					assertionList.add($assertion.booleanExpr);
  				}
    		}
    	) | (assignment 
    		{
    			assignmentList.addAll($assignment.assign);
    		}
    	))* '<' '/tr' '>'
        {
        	// create new lpn transitions and add assertions
        	$lpnTran = new LPNTran(label, TransitionIndex++, presetList, postsetList, guardExpr, assignmentList, delayLB, delayUB, local);
        	if(assertionList.size() > 0){
        		$lpnTran.addAllAssertions(assertionList);
        	}
        }
    ;
   
// parses the preset of a transition in a module class 
preset[List<Integer> presetList]
	:	'preset' '=' ('"' '"' | ('"' pre=constIntValue
    		{
    			presetList.add($pre.value);
   			} 
 		( ',' pre2=constIntValue
    		{
    			presetList.add($pre2.value);
   			} 
 		)* '"'))
	;
	
// parses the postset of a transitioin in a module class
postset[List<Integer> postsetList]
	:	'postset' '=' ( '"' '"' | ('"' post=constIntValue
    		{
    			postsetList.add($post.value);
    		} 
    	( ',' post2=constIntValue
    		{
    			postsetList.add($post2.value);
    		} 
    	)* '"' ))
	;
  
// parses a transition's assertion
assertion returns [Expression booleanExpr]
	:	{booleanExpr = null;}
		'assert' '(' expression ')' ';'
			{
				$booleanExpr = new Expression($expression.expr);
			}
	;
	
// parses a transition's guard
guard returns [Expression expr]
    :  'guard' '(' expression ')' ';'
    		{
   				$expr = new Expression($expression.expr);
    		}
    ;
   
// parses a transition's delay 
delay returns [int delayLB, int delayUB]
    : 	'delay' '(' lb=intValue
    		{
    			$delayLB = $lb.value;
   			} 
 		',' (ub=intValue
 			{
 				$delayUB = $ub.value;
 				// make sure delays are >= 0 and upper bound is >= lower bound
 				if($delayLB < 0){
 					error("error on line ??: lower bound " + $delayLB + " must be >= 0");
 				}
 				else if($delayLB == INFINITY){
 					error("error on line ??: lower bound " + $delayUB + " must be a non-negative finite number");
 				}
 				else if($delayUB < $delayLB){
 					error("error on line ??: upper bound " + $delayUB + " < lower bound " + $delayLB);
 				} 
 			} 
 		| 'inf' 
 			{
 				$delayUB = INFINITY;
			}
		) ')' ';'
    ;

// parses a transition's assignment
assignment returns [List<VarExpr> assign]
    :   	{
    			$assign = new LinkedList<VarExpr>();
    		}
    	var1=ID '=' var2=ID
    		{
    			// make sure only global, internal and output variables are assigned
    			if(GlobalConstHashMap.containsKey($var1.text)){
    				error("error on line " + $var1.getLine() + ": global constant " + $var1.text + " cannot be assigned");
    			}
    			else if(ConstHashMap.containsKey($var1.text)){
    				error("error on line " + $var1.getLine() + ": constant " + $var1.text + " cannot be assigned");
    			}
    			else if(!VarNodeMap.containsKey($var1.text)){
    				error("error on line " + $var1.getLine() + ": variable " + $var1.text + " was not declared");
    			}
    			else if(!Internals.contains($var.text)){
					VarNode node = VarNodeMap.get($var1.text);
					node.setShared();
    			}
    			
				ExpressionNode node2 = null;
    			if(GlobalConstHashMap.containsKey($var2.text)){
    				node2 = new ConstNode($var2.text, GlobalConstHashMap.get($var2.text));
    			}
    			else if(ConstHashMap.containsKey($var2.text)){
    				node2 = new ConstNode($var2.text, ConstHashMap.get($var2.text));
    			}
    			else if(VarNodeMap.containsKey($var2.text)){
    				node2 = VarNodeMap.get($var2.text);
    			}
    			else{
    				error("error on line " + $var2.getLine() + ": variable " + $var2.text + " was not declared");
    			}
	    		
	    		VarNode node1 = VarNodeMap.get($var1.text);
	    		
	    		// make sure var2 can be assigned to var1
	    		if(ArrayNode.class.isAssignableFrom(node1.getClass())){
	    			if(!ArrayNode.class.isAssignableFrom(node2.getClass())){
	   					error("aaerror on line " + $var2.getLine() + ": variable " + $var2.text + " is an array");
   					}
   					
   					ArrayNode arrayNode1 = (ArrayNode) node1;
   					ArrayNode arrayNode2 = (ArrayNode) node2;
   					
   					List<Integer> dimensionList1 = arrayNode1.getDimensionList();
   					List<Integer> dimensionList2 = arrayNode2.getDimensionList();
   					
   					// make sure the number of dimensions match
   					if(dimensionList1.size() != dimensionList2.size()){
   						error("error on line " + $var1.getLine() + ": incompatible array dimensions");
   					}
   					
   					// make sure each dimension size matches
   					for(int i = 0; i < dimensionList1.size(); i++){
   						if(dimensionList1.get(i) != dimensionList2.get(i)){
   							error("error on line " + $var1.getLine() + ": incompatible array dimensions");
   						}
   					}
   					
   					List<VarNode> elementNodes1 = arrayNode1.getElementList();
   					List<VarNode> elementNodes2 = arrayNode2.getElementList();
   					
   					// assign each associated array element
   					for(int i = 0; i < elementNodes1.size(); i++){
   						VarNode element1 = elementNodes1.get(i);
   						VarNode element2 = elementNodes2.get(i);
   						
   						Expression expr = new Expression(element2);
	    				$assign.add(new VarExpr(element1, expr));
   					}
   				}
   				else if(ArrayNode.class.isAssignableFrom(node2.getClass())){
   					error("bberror on line " + $var2.getLine() + ": variable " + $var2.text + " is an array");
   				}
   				else{
   					// non-array variable assignment
   					Expression expr = new Expression(node2);
	    			$assign.add(new VarExpr(node1, expr));
   				}
	    		
	    		ClassReadSet.add($var2.text);
	    		ClassWriteSet.add($var1.text);
    		}	
    |		
    		{
    			$assign = new LinkedList<VarExpr>();
    		}
		(arrayVar=ID 
	   		{
	   			List<Expression> indexList = new ArrayList<Expression>();
	   			
    			if(GlobalConstHashMap.containsKey($arrayVar.text)){
    				error("error on line " + $arrayVar.getLine() + ": global constant " + $arrayVar.text + " cannot be assigned");
    			}
    			else if(ConstHashMap.containsKey($arrayVar.text)){
    				error("error on line " + $arrayVar.getLine() + ": constant " + $arrayVar.text + " cannot be assigned");
    			}
    			else if(!VarNodeMap.containsKey($arrayVar.text)){
    				error("error on line " + $arrayVar.getLine() + ": variable " + $arrayVar.text + " was not declared");
    			}
    			else if(!Internals.contains($arrayVar.text)){
					VarNode node = VarNodeMap.get($arrayVar.text);
					node.setShared();
    			}
	   		}
	   	('[' (arrayExpr=expression
	   		{
    			ExpressionNode node = $arrayExpr.expr;
				indexList.add(new Expression(node));
	   		}
	   	) ']')+ '=' assignExpr=expression
	   		{
	    		Expression expr = new Expression($assignExpr.expr);
	    		VarNode arrayNode = VarNodeMap.get($arrayVar.text);
	    		if(!ArrayNode.class.isAssignableFrom(arrayNode.getClass())){
	    			error("dderror on line " + $arrayVar.getLine() + ": " + $arrayVar.text + " is not an array");
	    		}
	    		
	    		$assign.add(new VarExpr(new ArrayElement((ArrayNode) arrayNode, indexList), expr));
	    		
	    		ClassWriteSet.add($arrayVar.text);
	   		}
	   	';')
	|		{
    			$assign = new LinkedList<VarExpr>();
    		}
    	(var=ID '=' 
    		{	
    			if(GlobalConstHashMap.containsKey($var.text)){
    				error("error on line " + $var.getLine() + ": global constant " + $var.text + " cannot be assigned");
    			}
    			else if(ConstHashMap.containsKey($var.text)){
    				error("error on line " + $var.getLine() + ": constant " + $var.text + " cannot be assigned");
    			}
    			else if(!VarNodeMap.containsKey($var.text)){
    				error("error on line " + $var.getLine() + ": variable " + $var.text + " was not declared");
    			}
    			else if(!Internals.contains($var.text)){
					VarNode node = VarNodeMap.get($var.text);
					node.setShared();
    			}
    		}
    	varExpr=expression
	    	{
	    		Expression expr = new Expression($varExpr.expr);
	    		VarNode node = VarNodeMap.get($var.text);
	    		if(ArrayNode.class.isAssignableFrom(node.getClass())){
   					error("ccerror on line " + $var.getLine() + ": variable " + $var.text + " is an array");
   				}
   				
	    		$assign.add(new VarExpr(node, expr));
	    		ClassWriteSet.add($var.text);
	   		}
	   	';')
    ;
    
/*
 * Expressions
 */
term returns [ExpressionNode expr, int value]
    :   var=ID 
    		{
    			if(ConstHashMap.containsKey($var.text)){
    				$value = ConstHashMap.get($var.text);
    				$expr = new ConstNode($var.text, $value);
    			}
    			else if(GlobalConstHashMap.containsKey($var.text)){
    				$value = GlobalConstHashMap.get($var.text);
    				$expr = new ConstNode($var.text, $value);
    			}
    			else if(StatevectorMap.containsKey($var.text)){ 
    				$value = StatevectorMap.get($var.text);
					$expr = VarNodeMap.get($var.text);
    			}
    			else if(VarNodeMap.containsKey($var.text)){
   					error("error on line " + $var.getLine() + ": variable " + $var.text + " is an array");
    			}
    			else{
					error("error on line " + $var.getLine() + ": variable " + $var.text + " is not valid");
    			}
    			
    			ClassReadSet.add($var.text);
   			}
  	|	(array=ID 
  			{
  				List<Expression> indexList = new ArrayList<Expression>();
  				List<Integer> valueList = new ArrayList<Integer>();
  				VarNode arrayNode = null;
  				
  				if(!VarNodeMap.containsKey($array.text)){
  					error("error on line " + $array.getLine() + ": " + $array.text + " is not a valid array");
  				}
  				
  				arrayNode = VarNodeMap.get($array.text);
  				if(!ArrayNode.class.isAssignableFrom(arrayNode.getClass())){
   					error("error on line " + $array.getLine() + ": " + $array.text + " is not a valid array");
   				}
   			}
   		('[' (arrayExpr=expression
  			{
  				ExpressionNode node = $arrayExpr.expr;
  				indexList.add(new Expression(node));
  				valueList.add($arrayExpr.value);
  			}
  		) ']')+
  			{
  				ArrayNode arrayVar = (ArrayNode) arrayNode;
  				if(valueList.size() != arrayVar.numDimensions()){
  					error("error on line " + $array.getLine() + ": invalid number of dimensions");
  				}
  				
  				ExpressionError errorCode = ExpressionError.NONE;
  				String name = ((ArrayNode) arrayNode).getElement(valueList, errorCode).getName();
  				$value = StatevectorMap.get(name);
  				$expr = new ArrayElement((ArrayNode) arrayNode, indexList);
  				ClassReadSet.add($array.text);
  			}
  		)
    |   LPAREN expression RPAREN {$expr = new ParenNode($expression.expr); $value = $expression.value;}
    |   INT {$value = Integer.parseInt($INT.text); $expr = new ConstNode("name", $value);}
    |   TRUE {$expr = new TrueNode(); $value = 1;}
    |   FALSE {$expr = new FalseNode(); $value = 0;}
    ;
    
unary returns [ExpressionNode expr, int value]
    :   {boolean positive = true;}
    	('+' | ('-' {if(positive){ positive = false;} else {positive = true;}}))* term
	    	{
	    		if(!positive){
	    			$expr = new MinNode($term.expr);
	    			$value = -$term.value;
	    		}
	    		else{
	    			$expr = $term.expr;
	    			$value = $term.value;
	   			}
	    	}
    ;

bitwiseNegation returns [ExpressionNode expr, int value]
	: 	{boolean neg = false;}
		('~' {if(neg){neg = false;} else{neg = true;}})* unary
			{
				if(neg){
					$expr = new BitNegNode($unary.expr);
					$value = ~$unary.value;
				}
				else{
					$expr = $unary.expr;
					$value = $unary.value;
				}
			}
	;
	
negation returns [ExpressionNode expr, int value]
	:	{boolean neg = false;}
		('!' {if(neg){neg = false;} else{neg = true;}})* bitwiseNegation
			{
				if(neg){
					$expr = new NegNode($bitwiseNegation.expr);
					$value = $bitwiseNegation.value == 0 ? 1 : 0;
				}
				else{
					$expr = $bitwiseNegation.expr;
					$value = $bitwiseNegation.value;
				}
			}
	;
	
mult returns [ExpressionNode expr, int value]
    :   op1=negation {$expr = $op1.expr; $value = $op1.value;} 
    	(	'*' op2=negation {$expr = new MultNode($expr, $op2.expr); $value = $value * $op2.value;}
    	|	'/' op2=negation {$expr = new DivNode($expr, $op2.expr); $value = $value / $op2.value;}
    	|	'%' op2=negation {$expr = new ModNode($expr, $op2.expr); $value = $value \% $op2.value;}
    	)*
    ;
    
add returns [ExpressionNode expr, int value]
    :   op1=mult {$expr = $op1.expr; $value = $op1.value;}
    	(	'+' op2=mult {$expr = new AddNode($expr, $op2.expr); $value = $value + $op2.value;}
    	| 	'-' op2=mult {$expr = new SubNode($expr, $op2.expr); $value = $value - $op2.value;}
    	)*
    ;
    
shift returns [ExpressionNode expr, int value]
    :   op1=add {$expr = $op1.expr; $value = $op1.value;}
    	(	'<<' op2=add {$expr = new LeftShiftNode($expr, $op2.expr); $value = $value << $op2.value;}
    	| 	'>>' op2=add {$expr = new RightShiftNode($expr, $op2.expr); $value = $value >> $op2.value;}
    	)*
    ;

relation returns [ExpressionNode expr, int value]
    :   op1=shift {$expr = $op1.expr; $value = $op1.value;}
    	(	'<' op2=shift {$expr = new LessNode($expr, $op2.expr); $value = ($value < $op2.value) ? 1 : 0;}
    	| 	'<=' op2=shift {$expr = new LessEqualNode($expr, $op2.expr); $value = ($value <= $op2.value) ? 1 : 0;}
    	| 	'>=' op2=shift {$expr = new GreatEqualNode($expr, $op2.expr); $value = ($value >= $op2.value) ? 1 : 0;}
    	| 	'>' op2=shift {$expr = new GreatNode($expr, $op2.expr); $value = ($value > $op2.value) ? 1 : 0;}
    	)*
    ;
    
equivalence returns [ExpressionNode expr, int value]
    :   op1=relation {$expr = $op1.expr; $value = $op1.value;}
    	(	'==' op2=relation {$expr = new EquivNode($expr, $op2.expr); $value = ($value == $op2.value) ? 1 : 0;}
    	|	'!=' op2=relation {$expr = new NotEquivNode($expr, $op2.expr); $value = ($value != $op2.value) ? 1 : 0;}
    	)*
    ;
    
bitwiseAnd returns [ExpressionNode expr, int value]
    :   op1=equivalence {$expr = $op1.expr; $value = $op1.value;} 
    	(	'&' op2=equivalence {$expr = new BitAndNode($expr, $op2.expr); $value = $value & $op2.value;}
    	)*
    ;
    
bitwiseXor returns [ExpressionNode expr, int value]
    :   op1=bitwiseAnd {$expr = $op1.expr; $value = $op1.value;}
    	(	'^' op2=bitwiseAnd {$expr = new BitXorNode($expr, $op2.expr); $value = $value ^ $op2.value;}
    	)*
    ;
    
bitwiseOr returns [ExpressionNode expr, int value]
    :   op1=bitwiseXor {$expr = $op1.expr; $value = $op1.value;}
    	(	'|' op2=bitwiseXor {$expr = new BitOrNode($expr, $op2.expr); $value = $value | $op2.value;}
    	)*
    ;
    
and returns [ExpressionNode expr, int value]
    :   op1=bitwiseOr {$expr = $op1.expr; $value = $op1.value;}
    	(	'&&' op2=bitwiseOr {$expr = new AndNode($expr, $op2.expr); $value = ($value != 0 && $op2.value != 0) ? 1 : 0;}
    	)*
    ;
    
or returns [ExpressionNode expr, int value]
    :   op1=and {$expr = $op1.expr; $value = $op1.value;}
    	(	'||' op2=and {$expr = new OrNode($expr, $op2.expr); $value = ($value != 0 || $op2.value != 0) ? 1 : 0;}
    	)*
    ;
    
implication returns [ExpressionNode expr, int value]
    :	op1=or {$expr = $op1.expr; $value = $op1.value;}
    	(	'->' op2=or  {$expr = new ImplicationNode($expr, $op2.expr); $value = ($value == 0 || $op2.value != 0) ? 1 : 0;}
    	)*
    ;
    
expression returns [ExpressionNode expr, int value]
    :   op1=implication {$expr = $op1.expr; $value = $op1.value;}
    	('?' op2=expression ':' op3=expression 
    		{
    			$value = ($value != 0) ? $op2.value : $op3.value;
    			$expr = new TernaryNode($expr, $op2.expr, $op3.expr);
    		}
    	)?
    ;
	

/*
 * Tokens
 */
LPAREN: '(';
RPAREN: ')';
LBRACK: '{';
RBRACK: '}';
QMARK: '?';
COLON: ':';
SEMICOLON: ';';
PERIOD: '.';
UNDERSCORE: '_';
COMMA: ',';
QUOTE: '"';

// Reserved Words
MODULE: 'mod';
MAIN: 'main';
NAME: 'name';
INPUT: 'input';
OUTPUT: 'output';
INTERNAL: 'var';
MARKING: 'marking';
STATE_VECTOR: 'statevector';
TRANSITION: 'transition';
LABEL: 'label';
PRESET: 'preset';
POSTSET: 'postset';
TRUE: 'true';
FALSE: 'false';

// Arithmetic Operators
PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
MOD: '%';
EQUALS: '=';

// Comparison Operators
GREATER: '>';
LESS: '<';
GREATER_EQUAL: '>=';
LESS_EQUAL: '<=';
EQUIV: '==';
NOT_EQUIV: '!=';

// Logical Operators
NEGATION: '!';
AND: '&&';
OR: '||';
IMPLICATION: '->';

// Bitwise Operators
BITWISE_NEGATION: '~';
BITWISE_AND: '&';
BITWISE_OR: '|';
BITWISE_XOR: '^';
BITWISE_LSHIFT: '<<';
BITWISE_RSHIFT: '>>';

//CLOSE_INCLUDE : '<' '/include' '>';
//CLOSE_CONST : '<' '/const' '>';
//CLOSE_VAR : '<' '/var' '>';
//CLOSE_MODULE : '<' '/mod' '>';
//CLOSE_CLASS : '<' '/class' '>';
//CLOSE_MARKING : '<' '/marking' '>';
//CLOSE_TRANSITION : '<' '/transition' '>';

fragment LETTER: ('a'..'z' | 'A'..'Z');
fragment DIGIT: '0'..'9';
fragment FILE: (LETTER | DIGIT) ('_'? (LETTER | DIGIT))*;
INT: DIGIT+;
ID: LETTER (UNDERSCORE? (LETTER | DIGIT))*;
PATH: ((LETTER ':') | '/')? (FILE ('/' | '\\'))* FILE '.lpn';
MEMBER: ID '.' ID;
LABELSTRING:  DIGIT ((LETTER) | (UNDERSCORE (LETTER | DIGIT))) ('_'? (LETTER|DIGIT))*;

WS: (' ' | '\t' | '\n' | '\r' | '\f')+ {$channel = HIDDEN;};
COMMENT: '//' .* ('\n' | '\r') {$channel = HIDDEN;};
MULTILINECOMMENT: '/*' .* '*/' {$channel = HIDDEN;};
XMLCOMMENT: ('<' '!' '-' '-') .* ('-' '-' '>') {$channel = HIDDEN;};
IGNORE: '<' '?' .* '?' '>' {$channel = HIDDEN;};

