package platu.lpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import platu.stategraph.State;
import platu.stategraph.StateGraph;
import platu.stategraph.StateTran;

/**
 * The LPNTranRelation class stores information about LPN transitions such  as dependence, 
 * interleaving, and independence.
 */
public class LPNTranRelation {
	private StateGraph[] lpnList = null;
	
	private Map<LPNTran, Set<LPNTran>> transitiveDependence = new HashMap<LPNTran, Set<LPNTran>>();  // type 1 transitive
	private Map<LPNTran, Set<LPNTran>> interleavingDependence = new HashMap<LPNTran, Set<LPNTran>>();  // type 2 interleaving
	
	
	/*save type 2 transitions to a hashSet*/
	private HashSet<LPNTran> interleavingTrans = new HashSet<LPNTran>();
	
	/*each pair of interleaving transition has a unique label number*/
	private Integer labelNum = 0;
	private HashMap<LPNTran, Set<Integer>> interleavingDependenceLabel = new HashMap<LPNTran, Set<Integer>>();
	private List<HashMap<State, Set<Integer>>> trueInterleavingState = new ArrayList<HashMap<State, Set<Integer>>>();
	private List<String> searchedPair = new ArrayList<String>();
	
	public LPNTranRelation(StateGraph[] lpnList){
		this.lpnList = lpnList;
	}
	
	/**
	 * Finds pairs of transitions which are dependent locally and between modules.  Identifies two types of dependencies:
	 * 1) transitive
	 * 2) interleaving
	 * This function assumes findSgCompositional() has been called.
	 * Type 1 pairs are stored in Map transitiveDependence, and type 2 pairs are stored in Map interleavingDependence.
	 */
	public void findCompositionalDependencies(){
		for(StateGraph sg : this.lpnList){
			//record each sg's trueInterleavingMap, fatherSet
			HashMap<State, Set<Integer>> trueInterleavingState_sg = new HashMap<State, Set<Integer>>();
			HashMap<State,List<State>> fatherSet = sg.getIncomingStateMap();

			HashMap<State, List<StateTran>> outgoingTranMap = this.genOutgoingTranMap(sg);
			
			for(int i = 0; i < sg.reachSize(); i++){
				State currentState = sg.getState(i);
				
				List<StateTran> stateTranList = outgoingTranMap.get(currentState);
				if(stateTranList == null) {
					continue;
				}
					
				LpnTranList currentEnabledTransitions = new LpnTranList();
				for(StateTran stTran: stateTranList){
					currentEnabledTransitions.add(stTran.getLpnTran());
				}
				
				for(StateTran stTran: stateTranList){
					State endState = stTran.getNextState();
					LPNTran lpnTran = stTran.getLpnTran();
		        	
					LpnTranList nextEnabledTransitions = new LpnTranList();
		        	List<StateTran> nextStateTranList = outgoingTranMap.get(endState);
		        	if(nextStateTranList !=null){
		        		for(StateTran stTran_next: nextStateTranList){
							nextEnabledTransitions.add(stTran_next.getLpnTran());
						}
		        	}		
		        	// disabled trans
		        	LpnTranList current_minus_next = currentEnabledTransitions.clone();
		        	current_minus_next.removeAll(nextEnabledTransitions);
		        	current_minus_next.remove(lpnTran);
		        	
		        	// type 2		 
		        	for(LPNTran disabledTran: current_minus_next){
		        		
		        		if(lpnTran.getLpn().equals(sg.getLpn())||disabledTran.getLpn().equals(sg.getLpn())){
	
			        		// t1 -> t2
			        		if(interleavingDependence.containsKey(lpnTran)){
			        			interleavingDependence.get(lpnTran).add(disabledTran);
			        		}
			        		else{
			        			Set<LPNTran> tranSet = new HashSet<LPNTran>();
			        			tranSet.add(disabledTran);
			    				interleavingDependence.put(lpnTran, tranSet);
			        		}
			        		
			        		if(interleavingDependence.containsKey(disabledTran)){
			        			interleavingDependence.get(disabledTran).add(lpnTran);
			        		}
			        		else{
			        			Set<LPNTran> tranSet = new HashSet<LPNTran>();
			        			tranSet.add(lpnTran);
			    				interleavingDependence.put(disabledTran, tranSet);
			        		}
			        		
			        		this.genTranLabel(lpnTran, disabledTran);
			        		this.genTrueDep_eachPair(sg, currentState, lpnTran, disabledTran, trueInterleavingState_sg, fatherSet, outgoingTranMap);
		        			
		        		}
		        		
		        	}
		        	
		        	// enabled trans
		        	LpnTranList next_minus_current = nextEnabledTransitions.clone();
		        	next_minus_current.removeAll(currentEnabledTransitions);
		        	
		        	// type 1
		        	for(LPNTran enabledTran: next_minus_current){
		        			// t1 -> t2
		        			if(transitiveDependence.containsKey(lpnTran)){
		        				transitiveDependence.get(lpnTran).add(enabledTran);
		        			}
		        			else{
		        				Set<LPNTran> tranSet = new HashSet<LPNTran>();
		        				tranSet.add(enabledTran);
		        				transitiveDependence.put(lpnTran, tranSet);
		        			}
		        	}
		        	
		        	LpnTranList remainEnabled = currentEnabledTransitions.clone();
		        	remainEnabled.removeAll(current_minus_next);
		        	for(LPNTran remainTran : remainEnabled){
		        		State s1 = sg.getNextState(currentState, remainTran);
		        		if(s1 == null){
		        			continue;
		        		}
		        		
		        		LpnTranList en = sg.getEnabled(s1, false);
		        		if(!en.contains(lpnTran)){
		        			continue;
		        		}
		        		
		        		State s3 = sg.getNextState(s1, lpnTran);
		        		if(s3 == null) {
		        			continue;
		        		}
		        		
		        		State s2 = sg.getNextState(endState, remainTran);
		        		if(s2 == null) {
		        			continue;
		        		}
		        		
		        		if(s2 != s3){
		        			if(lpnTran.getLpn().equals(sg.getLpn())||remainTran.getLpn().equals(sg.getLpn())){
		        			
		        				if(interleavingDependence.containsKey(lpnTran)){
		        					interleavingDependence.get(lpnTran).add(remainTran);
		        				}
		        				else{
		        					Set<LPNTran> tranSet = new HashSet<LPNTran>();
		        					tranSet.add(remainTran);
		        					interleavingDependence.put(lpnTran, tranSet);
		        				}

								if (interleavingDependence
										.containsKey(remainTran)) {
									interleavingDependence.get(remainTran).add(
											lpnTran);
								} else {
									Set<LPNTran> tranSet = new HashSet<LPNTran>();
									tranSet.add(lpnTran);
									interleavingDependence.put(remainTran,
											tranSet);
								}
		        			}
			        		
			        		
			        		this.genTranLabel(lpnTran, remainTran);			      
			        		this.genTrueDep_eachPair(sg, currentState, lpnTran, remainTran, trueInterleavingState_sg, fatherSet,outgoingTranMap);
		        		}
		        	}
				}
			}

			this.trueInterleavingState.add(trueInterleavingState_sg);
		}
		//transfer Map<LPNTran, Set<LPNTran>> to HashSet<LPNTran>
		this.setInterleavingTransSet(interleavingDependence);
	}
	
	/**
	 * Generates and returns a hash map containing each state's outgoing state transitions.
	 * @param sg - State graph
	 */
	private HashMap<State, List<StateTran>> genOutgoingTranMap(StateGraph sg){
		HashMap<State, List<StateTran>> outgoingTranMap = new HashMap<State, List<StateTran>>();
		
		for(StateTran stTran : sg.getStateTranMap().values()){
			State currentState = stTran.getCurrentState();
			List<StateTran> outgoingList = outgoingTranMap.get(currentState);
			
			if(outgoingList == null){
				outgoingList = new ArrayList<StateTran>();
				outgoingList.add(stTran);
				outgoingTranMap.put(currentState, outgoingList);
			}
			else{
				outgoingList.add(stTran);
			}
		}
		
		return outgoingTranMap;
	}

	/**
	 * Generate interleaving transition's unique label.
	 * @param tran1
	 * @param tran2
	 */
	private Integer genTranLabel(LPNTran tran1, LPNTran tran2){
		if(searchedPair == null){
			labelNum++;
			searchedPair.add(tran1.getFullLabel()+tran2.getFullLabel());
			this.addTranLabel(tran1, this.labelNum);
			this.addTranLabel(tran2, this.labelNum);
			return labelNum;
		}
		else{
			if(!(searchedPair.contains(tran1.getFullLabel()+tran2.getFullLabel()) ||
					searchedPair.contains(tran2.getFullLabel()+tran1.getFullLabel()))){
				labelNum++;
				searchedPair.add(tran1.getFullLabel()+tran2.getFullLabel());
				this.addTranLabel(tran1, this.labelNum);
				this.addTranLabel(tran2, this.labelNum);
				return labelNum;
			}
			else
				return 0;
		}
	}
    /**
     * records each transition's interleaving label to interleavingDependenceLabel(HashMap<LPNTran, Set<Integer>>)
     * @param tran
     * @param labelNum
     */
	private void addTranLabel(LPNTran tran, Integer labelNum){
		if(interleavingDependenceLabel.get(tran) == null)
		{
			Set<Integer> labels = new HashSet<Integer>();
			labels.add(labelNum);
			interleavingDependenceLabel.put(tran, labels);
		}
		else
			interleavingDependenceLabel.get(tran).add(labelNum);
	}
	
	private Integer getTranPairLabel(LPNTran tran1, LPNTran tran2){
		for(Integer i : interleavingDependenceLabel.get(tran1)){
			if(interleavingDependenceLabel.get(tran2).contains(i))
				return i;
		}
		return 0;
	}
	
	
	/**
	 * For each state, add the interleaving transitions pair's label
	 * which means this pair's dependence is true at this state
	 * @param trueInterleavingState_sg
	 * @param s
	 * @param number
	 */
	private void addcurrStateLabel(HashMap<State, Set<Integer>> trueInterleavingState_sg, State s, Integer labelNum){
		Set<Integer> labels = trueInterleavingState_sg.get(s);
		if(labels == null)
		{
			labels = new HashSet<Integer>();
			labels.add(labelNum);
			trueInterleavingState_sg.put(s, labels);
		}
		else
			labels.add(labelNum);
	}
	
	/**
	 * generate conditional dependence for each pair of interleaving transitions.
	 */
	private boolean genTrueDep_eachPair(StateGraph sg, State curr, LPNTran tran1, LPNTran tran2,
									 HashMap<State, Set<Integer>> trueInterleavingState_sg, 
									 HashMap<State,List<State>> fatherSet,
									 HashMap<State, List<StateTran>> outgoingTranMap){
		
		Integer label = this.getTranPairLabel(tran1, tran2);
		//already searched
		if(trueInterleavingState_sg.get(curr) != null && trueInterleavingState_sg.get(curr).contains(label))
			return false;
   	
		this.addcurrStateLabel(trueInterleavingState_sg, curr, label);
		
		//travel back from current state(BFS), if include tran1 or tran2, save (state, label);
		HashSet<State> flag = new HashSet<State>();
		List<State> fathers = new LinkedList<State>();
		List<State> currs = new LinkedList<State>();
		currs.add(curr);
		List<State> nextCurrs;
		flag.add(curr);
		
		while(currs.size()!=0){
			nextCurrs = new LinkedList<State>();
			for(State s : currs){
				//this state has not been visited
				if(flag.contains(s) == false){
					flag.add(s);
					if((fathers = fatherSet.get(s)) != null){
						for(State f : fathers){
							if(trueInterleavingState_sg.get(f) == null || !trueInterleavingState_sg.get(f).contains(label)){
								if(this.findfatherDep(sg, trueInterleavingState_sg, f, tran1, tran2, label,outgoingTranMap))
									nextCurrs.add(f);
								else
									flag.add(f);
							}
						}
					}
						
				}
			}
			currs = nextCurrs;
		}	
		return true;
	}
	
	/**
	 * if the father has conditional dependent transition then return true.
	 * @param sg
	 * @param trueInterleavingState_sg
	 * @param father
	 * @param tran1
	 * @param tran2
	 * @param label
	 * @param outgoingTranMap
	 * @return
	 */
	private boolean findfatherDep(StateGraph sg, HashMap<State, Set<Integer>> trueInterleavingState_sg, 
			                          State father, LPNTran tran1, LPNTran tran2, Integer label,
			                          HashMap<State, List<StateTran>> outgoingTranMap){

		LpnTranList trans = new LpnTranList();
		for(StateTran stTran: outgoingTranMap.get(father))
			trans.add(stTran.getLpnTran());
		
		if(trans.contains(tran1) || trans.contains(tran2)){
			this.addcurrStateLabel(trueInterleavingState_sg, father, label);
			return true;
		}
		return false;
	}
	
	/**
	 * transfer Map<LPNTran, Set<LPNTran>> to HashSet<LPNTran> for easy searching
	 * @param interleavingDependence
	 */
	private void setInterleavingTransSet(Map<LPNTran, Set<LPNTran>> interleavingDependence)
	{
		Iterator in = interleavingDependence.entrySet().iterator();  
		while(in.hasNext())
		{
			Map.Entry me = (Map.Entry)in.next();
			LPNTran key = (LPNTran)me.getKey();
			Set<LPNTran> value = (Set<LPNTran>)me.getValue();
			
			interleavingTrans.add(key);
			Iterator bb = value.iterator();
			while(bb.hasNext())
			{
				interleavingTrans.add((LPNTran)bb.next());
			}	
		}
	}
	
/**
 * get the conditional dependent transition label set for state
 * @return
 */
    public List<HashMap<State, Set<Integer>>> getTrueInterleavingMap(){
    	return this.trueInterleavingState;
    }
    
    
	public HashMap<LPNTran, Set<Integer>> getInterleavingTranLabel(){
		return this.interleavingDependenceLabel;
	}
	
	/**
	 * get interleaving transitions' set
	 * @return
	 */
	public HashSet<LPNTran> getInterleavingTransSet(){
		return this.interleavingTrans;
	}
	
	public Map<LPNTran, Set<LPNTran>> getInterleavingTransMap(){
		return this.interleavingDependence;
	}
	
	public Map<LPNTran, Set<LPNTran>> getTransitiveDependenceMap(){
		return this.transitiveDependence;
	}
    
 	
	/**
     * Returns entry set of Map transitiveDependence, where the key value is an LPNTran 
     * and the value is a list of LPNTran which have transitive dependence.
     * @return Map transitiveDependence entry set
     */
	public Set<Entry<LPNTran, Set<LPNTran>>> getDependentTrans(){
		return this.transitiveDependence.entrySet();
	}
	
	/**
     * Returns entry set of Map interleavingDependence, where the key value is an LPNTran 
     * and the value is a list of LPNTran which have interleaving dependence.
     * @return Map interleavingDependence entry set
     */
	public Set<Entry<LPNTran, Set<LPNTran>>> getInterleavingTrans(){
		return this.interleavingDependence.entrySet();
	}
}
