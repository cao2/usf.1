package platu.por1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import platu.lpn.LPN;
import platu.lpn.LPNTran;
import platu.lpn.LPNTranRelation;
import platu.lpn.LpnTranList;
import platu.stategraph.State;
import platu.stategraph.StateGraph;

public class AmpleSet {
	
	/*interleaving transitions set: used to easily find out independent transitions(allTrans - allInterleavings) */
	private HashSet<LPNTran> allInterleavingTrans = new HashSet<LPNTran>();
	/*interleaving transition pairs mapping set*/
	private HashMap<LPNTran, HashSet<LPNTran>> interleavingSet = new HashMap<LPNTran, HashSet<LPNTran>>();
	/*independent transition pairs*/
	private HashMap<LPNTran, HashSet<LPNTran>> indepTranSet = new HashMap<LPNTran, HashSet<LPNTran>>();
	/*records sg's state with true interleaving transition pairs*/
	private List<HashMap<State, Set<Integer>>> trueInterleavingMap = null;
	/*each interleaving transition has a unique label number*/
	private HashMap<LPNTran, Set<Integer>> interleavingLabel = new HashMap<LPNTran, Set<Integer>>();
	
	/**
	 * get independent transition set from behavior analysis approach
	 * @param lpnList
	 * @param lpnTranRelation
	 * @return
	 */
	public HashMap<LPNTran,HashSet<LPNTran>> getIndepSet_FromState(StateGraph[] sgArray,
			                                                           LPNTranRelation lpnTranRelation)
   {		
		/**get dependent and interleaving transitions*/
		lpnTranRelation.findCompositionalDependencies();
		HashMap<LPNTran, HashSet<LPNTran>> initialDepTrans = (HashMap)lpnTranRelation.getTransitiveDependenceMap();
		interleavingSet = (HashMap)lpnTranRelation.getInterleavingTransMap();
		allInterleavingTrans = lpnTranRelation.getInterleavingTransSet();
		if(initialDepTrans.size() !=0  || interleavingSet.size() !=0)
			this.setIndepTrans(sgArray,initialDepTrans,interleavingSet);    
		else
		{
			System.out.println("do not call lpnTranRelation.getDep seccessful!");
			indepTranSet = null;
		}
		this.trueInterleavingMap = lpnTranRelation.getTrueInterleavingMap();
		this.interleavingLabel = lpnTranRelation.getInterleavingTranLabel();
		return indepTranSet;
		
   }
	/**
	 * get independent transition set from static analysis approach
	 * @param lpnList
	 * @return
	 */
	public HashMap<LPNTran,HashSet<LPNTran>> getIndepSet_FromLPN(StateGraph[] lpnList)
    {
		SearchDepFromLPN sg = new SearchDepFromLPN();
        sg.setIndep(lpnList);
        indepTranSet = sg.getIndepSet();
        allInterleavingTrans = sg.getAllInterleavingTrans();
        interleavingSet = sg.getInterleavingSet();
		return indepTranSet;
    }

	/**
	 * get the smaller ample set form enabled transition set
	 * @param enabledArray
	 * @param lpnList
	 * @param currStateArray
	 * @param allIndepSet
	 * @return
	 */
	public LpnTranList getAmpleSet(LinkedList<LPNTran>[] enabledArray, StateGraph[] lpnList, State[] currStateArray,
            HashMap<LPNTran,HashSet<LPNTran>> allIndepSet)
	{
		/*translate enabled array to LpnTranList*/
		LpnTranList enableTranSet = new LpnTranList();
		for (int index = 0; index < enabledArray.length; index++) 
    	{
			if(enabledArray[index]!= null)
			{
				for(LPNTran t: enabledArray[index])
					enableTranSet.add(t);
			}
    	}
		/*analyse the transition relationship of enabled transition set*/ 
		if(enableTranSet.size()==0)           //empty enabled transition set
		{
			System.out.println("there is no enable transition need to be fired!");
			return enableTranSet;
		}
		else if(enableTranSet.size() == 1)    //only one transition in enabled transition set
			return enableTranSet;
			
		else                                  //multiple enabled transitions
		{
			//if did not calculate the independent transition set, no reduction
			if(allIndepSet == null || allIndepSet.size() == 0)
				return enableTranSet;
			else
			{
				/*Calculate if there exist one transition independent with all other enabled transitions*/
				LpnTranList oneIndepTran = new LpnTranList();
				if(allInterleavingTrans != null){
					for (int index = 0; index < enabledArray.length; index++){
						if(enabledArray[index]!= null){
							for(LPNTran t: enabledArray[index]){
								if(!allInterleavingTrans.contains(t)){
									oneIndepTran.add(t);
									return oneIndepTran;
								}
							}
						}
					}
				}
					
				if(oneIndepTran==null || oneIndepTran.size()==0){
					return this.subset_indepIsEmpty(lpnList,currStateArray, enableTranSet, enabledArray, interleavingSet,allIndepSet);
				}
				else
					return oneIndepTran;						
			}
		}
	}
	
/**
 * all transitions are interleaving transition, then check if there exist conditional dependence(can be seen as indep transitions), 
 * otherwise choose a smaller interleacing transtion set as an ample set
 * @param interleavingEnabledSet
 * @param interleavingSet
 * @param allIndepSet
 * @return
 */
	private LpnTranList subset_indepIsEmpty(StateGraph[] lpnList,State[] currStateArray, 
										    LpnTranList interleavingEnabledSet, LinkedList<LPNTran>[] enabledArray,
									       HashMap<LPNTran, HashSet<LPNTran>> interleavingSet,
										   HashMap<LPNTran,HashSet<LPNTran>> allIndepSet)
	{
		
		//check conditional dependence
		LpnTranList existFalseDep = new LpnTranList();

		//trueDepLables records current state's truely dep label, and depLabels records transition's all dep labels
		if(trueInterleavingMap != null){
			Set<Integer>  trueDepLables = new HashSet<Integer>();
			for(int i=0; i<lpnList.length;i++){
				HashMap<State, Set<Integer>> map = trueInterleavingMap.get(i);
				if((map != null || map.size() != 0) && (map.get(currStateArray[i])!=null)){
					for(Integer lable : map.get(currStateArray[i])){
						trueDepLables.add(lable);
					}
				}
			}
			
			for(int i=0; i<lpnList.length;i++){
				if(enabledArray[i].size()>0){
					for(LPNTran tran: enabledArray[i]){
						boolean wait = false;
						for(int label : interleavingLabel.get(tran)){
							if(trueDepLables != null && trueDepLables.contains(label)){
								wait = true;
								break;
							}
						}
						if(wait == false){
							existFalseDep.add(tran);
							//System.out.println("occur false dependent: "+tran.getFullLabel());
							return existFalseDep;
						}
					}	
				}
			}
		}
		

		//check out transition set B dependent on other transitions not in ample set, if so, need to wait.
		LpnTranList waitTrans = new LpnTranList();
		LpnTranList otherTrans = new LpnTranList();
		boolean wait;
		HashSet<LPNTran> term_depset = new HashSet<LPNTran>();
		for(LPNTran tran1 : interleavingEnabledSet){
			wait = false;
			term_depset = interleavingSet.get(tran1);
			for(LPNTran inter : term_depset){
				if(!interleavingEnabledSet.contains(inter)){
					waitTrans.add(tran1);
					wait = true;
					break;
				}
			}
			if(wait==false)
				otherTrans.add(tran1);
		}
		if(waitTrans.size() == 0)
		{
			//choose subset
			LpnTranList filterSet = new LpnTranList();
			LPNTran tran = interleavingEnabledSet.get(0);
			filterSet.add(tran);
			term_depset = interleavingSet.get(tran);
			for(LPNTran t : interleavingEnabledSet)
			{
				if(term_depset.contains(t))
					filterSet.add(t);
			}
			return filterSet;
		}
		//check out other transitions dependent on B in ample set
		if(otherTrans.size() != 0)
		{
			for(LPNTran tran1 : otherTrans){
				term_depset = interleavingSet.get(tran1);
				for(LPNTran inter : term_depset){
					if(waitTrans.contains(inter)){
						waitTrans.add(tran1);
						break;
					}
				}
			}
		}
		if(waitTrans.size() == interleavingEnabledSet.size())
			return interleavingEnabledSet;
		else
			return otherTrans;
			

	}
	
	/**
	 * get all transition, save to indepTranSet(Initial),independent number: n*(n-1)
	 * @param lpnList
	 * @return
	 */
	private void setIndepTrans(StateGraph[] lpnList, HashMap<LPNTran, HashSet<LPNTran>> interleavingSet,
			                                             HashMap<LPNTran, HashSet<LPNTran>> transitiveDepTrans)
	{
		//get all transitions
		LpnTranList allTran = new LpnTranList();
		for(StateGraph sg : lpnList)
		{
			LpnTranList trans = sg.getLpn().getTransitions();
			for(LPNTran tran: trans)
			{
				allTran.add(tran);
			}
		}
		//save to indepTranSet
		for(LPNTran key: allTran)
		{
			HashSet<LPNTran> value = indepTranSet.get(key);
			if(value == null)
			{
				value = new HashSet<LPNTran>();
				indepTranSet.put(key, value);
			}
			for(LPNTran t: allTran)
			{
				if(t!=key)
					value.add(t);
			}
		}
		
		//indep = allIndep - interleavingDepTrans - transitiveDepTrans
		this.reduceDepTran(interleavingSet);
		this.reduceDepTran(transitiveDepTrans);
		
	}

	/**
	 * reduce dependent transition pairs
	 * @param depTranSet
	 */
	private void reduceDepTran(HashMap<LPNTran, HashSet<LPNTran>> depTranSet){
		Iterator it = depTranSet.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)it.next();
			LPNTran key = (LPNTran)me.getKey();
			Set<LPNTran> value = (Set<LPNTran>)me.getValue();
			Iterator itor = value.iterator();
			while(itor.hasNext())
			{
				LPNTran tran = (LPNTran)itor.next();
				this.reduceIndepSet_pair(key, tran);
				this.reduceIndepSet_pair(tran, key);
			}
		}
	}

	/**
	 * refine independent set{minus (key,tran) and (tran, key)}
	 * @param key
	 * @param tran
	 */
	private void reduceIndepSet_pair(LPNTran key, LPNTran tran)
	{
		HashSet<LPNTran> value = indepTranSet.get(key);
		if(value != null)
		{
			Iterator it = value.iterator();
			while(it.hasNext())
			{
				LPNTran object = (LPNTran)it.next();
				if(object == tran)
				{
					value.remove(object);
					break;
				}
			}
		}
	}

	
	public HashSet<LPNTran> getAllInterleavingTrans(){
		return this.allInterleavingTrans;
	}

}
