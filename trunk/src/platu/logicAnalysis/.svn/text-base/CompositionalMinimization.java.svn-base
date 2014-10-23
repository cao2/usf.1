package platu.logicAnalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import platu.expression.ConstNode;
import platu.expression.Expression;
import platu.lpn.LPNTran;
import platu.lpn.VarExprList;
import platu.main.Options;
import platu.stategraph.StateError;
import platu.util.Pair;

public class CompositionalMinimization {
	// LPNTran used for self looping state transitions
	private static final LPNTran loopTran = new LPNTran("loop", -1, new int[0], new int[0], new Expression(new ConstNode("0", 0)), new VarExprList(), 0, 0, true);
	
	// code used to indicate a path that is still being traversed
	static final int traversing = 0;
	
	// code used to indicate a path has been traversed
	static final int complete = 1;

	
	/**
	 * Removes all the unreachable states from the specified state graph.
	 */
	public static void removeAllUnreachableStates(CompositeStateGraph sg){
		CompositeState init = sg.getInitState();
		
		for(CompositeState currentState : sg.getStateSet().toArray(new CompositeState[sg.numStates()])){
			if(currentState.numIncomingTrans() == 0 && currentState != init){
				removeUnreachableState(sg, currentState);
			}
		}
	}
	
	/**
	 * Performs state space simplification from the specified state.
	 * @param sg - State graph containing the current state from which simplification should be performed
	 * @param currentState - Current state from which simplification should be performed
	 */
	private static void simplifyTransitions(CompositeStateGraph sg, CompositeState currentState){
		CompositeStateTran[] outgoingTranArray = currentState.getOutgoingStateTranList().toArray(new CompositeStateTran[currentState.numOutgoingTrans()]);						
		
		/*
		 * Find two non-deterministic state transitions (s0 -t1-> s1 and s0 -t1-> s2).
		 * Then if an internal transition (s1 -t2-> s2) exists between next state s1 and s2, perform 
		 * simplification by removing state transition (s0 -t1-> s2).
		 */
		for(int i=0; i<outgoingTranArray.length; i++){
			CompositeStateTran tran1 = outgoingTranArray[i];
			CompositeState nextState1 = tran1.getNextState();
			List<CompositeStateTran> outgoingTrans1 = nextState1.getOutgoingStateTranList();
			
			for(int j = i+1; j < currentState.numOutgoingTrans(); j++){
				CompositeStateTran tran2 = outgoingTranArray[j];
				if(tran1.getLPNTran() != tran2.getLPNTran()){
					continue;
				}
				
				// state transition trans2's next state
				CompositeState nextState2 = tran2.getNextState();
				
				// indicates if simplification was performed
				boolean simFlag = false;
				
				/*
				 * Check if nextState1 has an outgoing internal transition to nextState2.
				 */
				for(int k=0; k<outgoingTrans1.size(); k++){
					CompositeStateTran stateTran = outgoingTrans1.get(k);
					if(!stateTran.visible() && stateTran.getNextState() == nextState2){
						simFlag = true;
						sg.removeStateTran(tran2);
						break;
					}
				}
				
				/*
				 * If simplification was not already performed, check if nextState2 has an 
				 * outgoing internal transition to nextState1.
				 */
				if(!simFlag){
					List<CompositeStateTran> stateTrans = nextState2.getOutgoingStateTranList();
					
					for(int n=0; n<stateTrans.size(); n++){
						CompositeStateTran stateTran = stateTrans.get(n);
						if(!stateTran.visible() && stateTran.getNextState() == nextState1){
							simFlag = true;
							sg.removeStateTran(tran1);
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Removes all of the invisible state transitions and the subsequent unreachable states 
	 * from the specified state graph.
	 * @param sg - State graph to remove the invisible state transitions from
	 */
	private static void removeAllInvisibleTransitions(CompositeStateGraph sg){
		CompositeState[] stateArray = sg.getStateSet().toArray(new CompositeState[sg.numStates()]);
		
		for(CompositeState currentState : stateArray){
			for(CompositeStateTran stateTran : currentState.getOutgoingStateTranList().toArray(new CompositeStateTran[currentState.numOutgoingTrans()])){
				if(!stateTran.visible()){
					sg.removeStateTran(stateTran);
				}
			}
		}
		
//		for(CompositeState currentState : stateArray){
//			if(currentState.numIncomingTrans() == 0){
//				sg.removeState(currentState);
//			}
//		}
	}
	
	/**
	 * Performs transition based abstraction by removing internal state transitions.
	 * Starting from the initial state, internal state transitions are traversed until
	 * visible transitions are reached, then they are propagated backwards.
	 * @param sg - Composite state graph to perform abstraction on
	 */
	public static void transitionBasedAbstraction(CompositeStateGraph sg){
		// time statistics
		long start = System.currentTimeMillis();
		
		// memory statistics
		long peakUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long peakTotal = Runtime.getRuntime().totalMemory();
		
		// traversal information identifies when a state was traversed
		int time = 1;
		
		// Queue containing the set of states need to processed
		Queue<CompositeState> stateQueue = new LinkedList<CompositeState>();
		
		// Stack used to traverse internal transitions
		Stack<CompositeStateTran> transitionStack = new Stack<CompositeStateTran>();
		
		// Stack used to identify loops
		Stack<CompositeState> loopStack = new Stack<CompositeState>();
		
		/*
		 * Initialize state queue with the initial state
		 */
		CompositeState initialState = sg.getInitState();
		stateQueue.add(initialState);
		
		/*
		 * Process each state, starting with the initial state, with an incoming visible state transition
		 */
		while(stateQueue.size() != 0){
			CompositeState currentState = stateQueue.remove();
			
			/*
			 * Skip if this state was processed before
			 */
			if(currentState.trav == true){
				continue;
			}
			
			/*
			 * Clear stacks used for processing
			 */
			transitionStack.clear();
			loopStack.clear();
			
			// initialize loop stack with the current state
			loopStack.push(currentState);
			
			/*
			 * setup processing information
			 */
			currentState.time = time;
			currentState.status = traversing;
			currentState.trav = true;
			
			/*
			 * Initialize transition stack with outgoing internal transitions.
			 */
			List<CompositeStateTran> currOutgoingStateTrans = currentState.getOutgoingStateTranList();
			for(int i=0; i< currOutgoingStateTrans.size(); i++){
				CompositeStateTran outgoingStateTran = currOutgoingStateTrans.get(i);
				
				if(!outgoingStateTran.visible()){
					// Skip self loops
					if(outgoingStateTran.getNextState() == outgoingStateTran.getCurrentState()){
						continue;
					}
					
					transitionStack.push(outgoingStateTran);
					outgoingStateTran.getNextState().time = time;
				}
			}
			
			/*
			 * Traverse invisible paths from the current state
			 */
			while(!transitionStack.empty()){
				/*
				 * invisible state transition
				 */
				CompositeStateTran stateTran = transitionStack.pop();
				CompositeState startState = stateTran.getCurrentState();
				CompositeState endState = stateTran.getNextState();
				
				/*
				 * When all of a state's outgoing internal paths have been traversed
				 * propagate all reachable visible transitions backwards, and set the
				 * state status to 'complete'.
				 */
				while(loopStack.peek() != startState){
					CompositeState completeState = loopStack.pop();
					completeState.status = complete;
					
					// propagate visible transitions backwards
					propagateBackwards(sg, loopStack.peek(), completeState);
				}
				
				/*
				 * Perform state space simplification if the option is set
				 */
				if(Options.getSimplificationFlag() == true){
					simplifyTransitions(sg, currentState);
				}
				
				// add next state to the loop stack
				loopStack.push(endState);
				
				
				if(endState.trav == true && endState.status == complete){
					continue;
				}
				
				// mark next state's status
				endState.status = traversing;
				
				/*
				 * Add outgoing internal transitions to the stack.
				 * If the next state is reachable from currentState, check if a loop exists.
				 * If a loop exists add a self loop to currentState and to each state within the loop.
				 */
				List<CompositeStateTran> outgoingStateTrans = endState.getOutgoingStateTranList();
				
				for(int i=0; i< outgoingStateTrans.size(); i++){
					CompositeStateTran outgoingStateTran = outgoingStateTrans.get(i);
					CompositeState outgoingNextState = outgoingStateTran.getNextState();
					
					if(!outgoingStateTran.visible()){
						/*
						 * If outgoingNextState. == time then outgoingNextState was previously 
						 * reached from currentState.
						 */
						if(outgoingNextState.time == time){
							 // Check if the outgoing paths if outgoingNextState are still being traversed.
							if(outgoingNextState.status == traversing){
								// add a self loop state transition to currentState
								CompositeStateTran newStateTran = new CompositeStateTran(currentState, loopTran, currentState);
								sg.addStateTran(newStateTran);
								
								// Set livelock state if the option is set
								if(Options.checkLivelock()){
									currentState.setErrorCode(StateError.LIVELOCK);
								}
								
								/*
								 * Add self loops to each state involved in the loop
								 */
								for(int j = 0; j<loopStack.size(); j++){
									CompositeState stackState = loopStack.get(j);
									
									CompositeStateTran loopStateTran = new CompositeStateTran(stackState, loopTran, stackState);
									sg.addStateTran(loopStateTran);
									
									if(Options.checkLivelock()){
										stackState.setErrorCode(StateError.LIVELOCK);
									}
									
									if(stackState == outgoingNextState){
										break;
									}
								}								
							}
						}
						else{
							// add internal transition to the transition stack
							transitionStack.push(outgoingStateTran);
							outgoingNextState.time = time;
						}
					}
				}
			}
			
			/*
			 * When currentState's outgoing internal paths have been traversed
			 * propagate all reachable visible transitions backwards, and set the
			 * state status to 'complete'.
			 */
			while(loopStack.peek() != currentState){
				CompositeState completeState = loopStack.pop();
				completeState.status = complete;
				
				// propagate visible transitions backwards
				propagateBackwards(sg, loopStack.peek(), completeState);
			}
			
			/*
			 * Perform state space simplification if the option is set
			 */
			if(Options.getSimplificationFlag() == true){
				simplifyTransitions(sg, currentState);
			}

			/*
			 * Add next state, reachable from a visible transition, to the state queue
			 * if it has not been processed before.
			 */
			List<CompositeStateTran> outgoingStateTrans = currentState.getOutgoingStateTranList();
			for(int i=0; i<outgoingStateTrans.size(); i++){
				CompositeStateTran outgoingStateTran = outgoingStateTrans.get(i);
				
				CompositeState nextState = outgoingStateTran.getNextState();
				if(outgoingStateTran.getNextState().trav == false && nextState.getErrorCode() == StateError.NONE){
					stateQueue.add(nextState);
				}
			}
			
			time++;
			currentState.status = complete;
			
			long curTotalMem = Runtime.getRuntime().totalMemory();
			if(curTotalMem > peakTotal){
				peakTotal = curTotalMem;
			}
			
			long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			if(curUsedMem > peakUsed){
				peakUsed = curUsedMem;
			}
		}
		
		// debugging info
		if(Options.getVerbosity() > 20){
			System.out.println("    # transitions before: " + sg.numStateTrans());
		}
		
		/*
		 * Remove all invisible transitions from the state graph
		 */
		removeAllInvisibleTransitions(sg);
		
		// debugging info
		if(Options.getVerbosity() > 20){
			System.out.println("    # transitions after: " + sg.numStateTrans());
		}
		
		/*
		 * Remove all unreachable states as a result of removing invisible transitions
		 */
		removeAllUnreachableStates(sg);
		
		/*
		 * Memory stats
		 */
		long curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		if(Options.getVerbosity() > 0){
			/*
			 * Output statistics
			 */
			System.out.println("      --> # states: " + sg.numStates());
			System.out.println("      --> # transitions: " + sg.numStateTrans());
			System.out.println("\n      --> Peak used memory: " + peakUsed/1000000F + " MB");
			System.out.println("      --> Peak total memory: " + peakTotal/1000000F + " MB");
			System.out.println("      --> Final used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000F + " MB");
			
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			System.out.println("      --> Elapsed time: " + elapsedTimeSec + " sec");
			
			if(elapsedTimeSec > 60){
				float elapsedTime = elapsedTimeSec/(float)60;
				System.out.println("      --> Elapsed time: " + elapsedTime + " min");
			}
		}
	}
	
	/**
	 * Performs transition based abstraction by removing internal state transitions.
	 * Starting from the initial state, internal state transitions are traversed until
	 * visible transitions are reached, then they are propagated backwards.
	 * @param sg - Composite state graph to perform abstraction on
	 */
	public static void globalSearchAbstraction(CompositeStateGraph sg){
		// traversal information identifies when a state was traversed
		int time = 1;
		
		// Queue containing the set of states need to processed
		Queue<CompositeState> stateQueue = new LinkedList<CompositeState>();
		
		// Stack used to traverse internal transitions
		Stack<CompositeStateTran> transitionStack = new Stack<CompositeStateTran>();
		
		// Stack used to identify loops
		Stack<CompositeState> loopStack = new Stack<CompositeState>();
		
		/*
		 * Initialize state queue with the initial state
		 */
		CompositeState initialState = sg.getInitState();
		stateQueue.add(initialState);
		
		List<CompositeStateTran> invisibleTranList = new LinkedList<CompositeStateTran>();
		
		/*
		 * Process each state, starting with the initial state, with an incoming visible state transition
		 */
		while(stateQueue.size() != 0){
			CompositeState currentState = stateQueue.remove();
			
			/*
			 * Skip if this state was processed before
			 */
			if(currentState.trav == true){
				continue;
			}
			
			/*
			 * Clear stacks used for processing
			 */
			transitionStack.clear();
			loopStack.clear();
			
			// initialize loop stack with the current state
			loopStack.push(currentState);
			
			/*
			 * setup processing information
			 */
			currentState.time = time;
			currentState.status = traversing;
			currentState.trav = true;
			
			/*
			 * Initialize transition stack with outgoing internal transitions.
			 */
			for(CompositeStateTran outgoingStateTran : currentState.getOutgoingStateTranList()){
				if(!outgoingStateTran.visible()){
					// Skip self loops
					if(outgoingStateTran.getNextState() == outgoingStateTran.getCurrentState()){
						continue;
					}
					
					transitionStack.push(outgoingStateTran);
					outgoingStateTran.getNextState().time = time;
				}
			}
			
			boolean containsVisibleTran = false;
			List<CompositeStateTran> invisiblePath = new LinkedList<CompositeStateTran>();
			
			/*
			 * Traverse invisible paths from the current state
			 */
			while(!transitionStack.empty()){
				/*
				 * invisible state transition
				 */
				CompositeStateTran stateTran = transitionStack.pop();
				CompositeState startState = stateTran.getCurrentState();
				CompositeState endState = stateTran.getNextState();
				
				invisiblePath.add(stateTran);
				
				/*
				 * When all of a state's outgoing internal paths have been traversed
				 * propagate all reachable visible transitions backwards, and set the
				 * state status to 'complete'.
				 */
				while(loopStack.peek() != startState){
					CompositeState completeState = loopStack.pop();
					completeState.status = complete;
					
					// propagate visible transitions backwards
					propagateBackwards(sg, loopStack.peek(), completeState, invisibleTranList);
				}
				
				// add next state to the loop stack
				loopStack.push(endState);
				
				
				if(endState.trav == true && endState.status == complete){
					continue;
				}
				
				// mark next state's status
				endState.status = traversing;
				
				/*
				 * Add outgoing internal transitions to the stack.
				 * If the next state is reachable from currentState, check if a loop exists.
				 * If a loop exists add a self loop to currentState and to each state within the loop.
				 */
				for(CompositeStateTran outgoingStateTran : endState.getOutgoingStateTranList().toArray(new CompositeStateTran[endState.numOutgoingTrans()])){
					CompositeState outgoingNextState = outgoingStateTran.getNextState();
					
					if(!outgoingStateTran.visible()){
						/*
						 * If outgoingNextState. == time then outgoingNextState was previously 
						 * reached from currentState.
						 */
						if(outgoingNextState.time != time){
							// add internal transition to the transition stack
							transitionStack.push(outgoingStateTran);
							outgoingNextState.time = time;
						}
					}
				}
			}
			
			if(containsVisibleTran){
				// add invisiblePath to remove list
			}
			
			/*
			 * When currentState's outgoing internal paths have been traversed
			 * propagate all reachable visible transitions backwards, and set the
			 * state status to 'complete'.
			 */
			while(loopStack.peek() != currentState){
				CompositeState completeState = loopStack.pop();
				completeState.status = complete;
				
				// propagate visible transitions backwards
				propagateBackwards(sg, loopStack.peek(), completeState, invisibleTranList);
			}

			/*
			 * Add next state, reachable from a visible transition, to the state queue
			 * if it has not been processed before.
			 */
			for(CompositeStateTran outgoingStateTran : currentState.getOutgoingStateTranList()){
				CompositeState nextState = outgoingStateTran.getNextState();
				if(outgoingStateTran.getNextState().trav == false && nextState.getErrorCode() == StateError.NONE){
					stateQueue.add(nextState);
				}
			}
			
			time++;
			currentState.status = complete;
		}
		
//		System.out.println("      invisibleTranList: " + invisibleTranList.size());
		for(CompositeStateTran invisibleTran : invisibleTranList){
//			System.out.println("         " + invisibleTran);
			sg.removeStateTran(invisibleTran);
		}

		/*
		 * Remove all unreachable states as a result of removing invisible transitions
		 */
		removeAllUnreachableStates(sg);
	}
	
	/**
	 * Given a state transition s -> s'.  This function propagates 
	 * the next state's (s') outgoing visible transitions backwards to the current state (s).
	 * Also, self loops and error states are propagated backwards.
	 * @param sg - State graph
	 * @param currentState - Current state
	 * @param nextState - Next state
	 */
	private static void propagateBackwards(CompositeStateGraph sg, CompositeState currentState, CompositeState nextState){
		/*
		 * For each visible outgoing state transition create an equivalent state transition
		 * starting from the current state.
		 * Also propagate self loops.
		 */
		List<CompositeStateTran> outgoingTrans = nextState.getOutgoingStateTranList();
		for(int i=0; i<outgoingTrans.size(); i++){
			CompositeStateTran outgoingTran = outgoingTrans.get(i);
			CompositeState endState = outgoingTran.getNextState();
			
			if(outgoingTran.visible()){
				CompositeStateTran newStateTran = new CompositeStateTran(currentState, outgoingTran.getLPNTran(), endState);
				newStateTran.setVisibility();
				
				sg.addStateTran(newStateTran);
			}
			else if(nextState == endState){
				// add self loop
				CompositeStateTran newStateTran = new CompositeStateTran(currentState, loopTran, currentState);
				sg.addStateTran(newStateTran);
				
				if(Options.checkLivelock()){
					currentState.setErrorCode(StateError.LIVELOCK);
				}
			}
		}
		
		// propagate error state backwards
		currentState.setErrorCode(nextState.getErrorCode());
		
		/*
		 * Perform state space simplification if the optioin is set
		 */
		if(Options.getSimplificationFlag() == true){
			simplifyTransitions(sg, currentState);
		}
	}
	
	/**
	 * Given a state transition s -> s'.  This function propagates 
	 * the next state's (s') outgoing visible transitions backwards to the current state (s).
	 * Also, self loops and error states are propagated backwards.
	 * @param sg - State graph
	 * @param currentState - Current state
	 * @param nextState - Next state
	 */
	private static void propagateBackwards(CompositeStateGraph sg, CompositeState currentState, CompositeState nextState, 
			List<CompositeStateTran> tranList){
		/*
		 * For each visible outgoing state transition create an equivalent state transition
		 * starting from the current state.
		 * Also propagate self loops.
		 */
		
		boolean visibleTran = false;
		
		for(CompositeStateTran outgoingTran : nextState.getOutgoingStateTranList()){
			CompositeState endState = outgoingTran.getNextState();
			
			if(outgoingTran.visible()){
				visibleTran = true;
				
				CompositeStateTran newStateTran = new CompositeStateTran(currentState, outgoingTran.getLPNTran(), endState);
				newStateTran.setVisibility();
				
				sg.addStateTran(newStateTran);
			}
//			else if(nextState == endState){
//				// add self loop
//				CompositeStateTran newStateTran = new CompositeStateTran(currentState, loopTran, currentState);
//				sg.addStateTran(newStateTran);
//				
//				if(Options.checkLivelock()){
//					currentState.setErrorCode(StateError.LIVELOCK);
//				}
//			}
		}
		
		if(visibleTran){
			for(CompositeStateTran outgoingTran : currentState.getOutgoingStateTranList()){
				CompositeState endState = outgoingTran.getNextState();
				LPNTran lpnTran = outgoingTran.getLPNTran();
				
				if(endState == nextState && lpnTran.local()){
					tranList.add(outgoingTran);
				}
			}
		}
		
		// propagate error state backwards
		currentState.setErrorCode(nextState.getErrorCode());
		
//		/*
//		 * Perform state space simplification if the option is set
//		 */
//		if(Options.getSimplificationFlag() == true){
//			simplifyTransitions(sg, currentState);
//		}
	}
		
	/**
	 * Removes the specified unreachable state from the state graph and subsequent unreachable states.
	 * @param sg - State graph containing the unreachable state
	 * @param unreachableState - Unreachable state
	 */
	public static void removeUnreachableState(CompositeStateGraph sg, CompositeState unreachableState){
		if(unreachableState == sg.getInitState()){
			return;
		}
		else if(unreachableState.numIncomingTrans() != 0){
			return;
		}
		
		for(CompositeStateTran stateTran : unreachableState.getOutgoingStateTranList().toArray(new CompositeStateTran[unreachableState.numOutgoingTrans()])){
			sg.removeStateTran(stateTran);
			
			CompositeState nextState = stateTran.getNextState();
			if(nextState.numIncomingTrans() == 0){
				removeUnreachableState(sg, nextState);
			}
		}
		
		sg.removeState(unreachableState);
	}
	
	/**
	 * Merge states, in the specified state graph, with equivalent outgoing state transitions.
	 * @param sg - State graph
	 */
	public static void mergeOutgoing(CompositeStateGraph sg){
		// Run time info
		long start = System.currentTimeMillis();
		
		// Memory usage info
		long peakUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long peakTotal = Runtime.getRuntime().totalMemory();
		
		/*
		 * Find the potential set of equivalent pairs
		 */
		HashMap<Pair<CompositeState, CompositeState>, Pair<CompositeState, CompositeState>> equivalentPairSet = findInitialEquivalentPairs(sg);
		
		/*
		 * Memory statistics
		 */
		long curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		// debugging info
		if(Options.getVerbosity() > 25){
			System.err.println("   fixed point");
		}
		
		// keeps track of the iterations for the fixed point operation
		int iter = 0;
		
		// used to signify when the fixed point is reached
		boolean stable = false;
		
		List<Pair<CompositeState, CompositeState>> pairRemovalList = new ArrayList<Pair<CompositeState, CompositeState>>();

		/*
		 * For each pair of states, check if the next states are the same state 
		 * or if they are are still considered equivalent states (within equivalentPairSet).  
		 * If the pair of states do not have the same or equivalent next states 
		 * (still within equivalentPairSet), then the pair is removed.
		 * This operation is performed until no more pairs have been removed and
		 * each pair of states left in equivalentPairSet are equivalent.
		 */
		while(!stable){
			stable = true;
			iter++;
			
			for(Pair<CompositeState, CompositeState> eqPair : equivalentPairSet.keySet()){
				CompositeState state1 = eqPair.getLeft();
				CompositeState state2 = eqPair.getRight();
				
				List<CompositeStateTran> outgoingTrans1 = state1.getOutgoingStateTranList();
				List<CompositeStateTran> outgoingTrans2 = state2.getOutgoingStateTranList();
				
				/*
				 * Check if state1 and state2 contain equivalent outgoing state transitions
				 */
				boolean eq = equivOutgoingTrans(state1, outgoingTrans1, state2, outgoingTrans2, equivalentPairSet);
					
				/*
				 * If they are not equivalent, remove the pair
				 */
				if(!eq){
					pairRemovalList.add(eqPair);
					
					stable = false;
					continue;
				}
			}
			
			for(int i=0; i<pairRemovalList.size(); i++){
				Pair<CompositeState, CompositeState> eqPair = pairRemovalList.get(i);
				
				if(Options.getImprovedEquivRemovalFlag() == true){
					removePair(eqPair, equivalentPairSet);
				}
				else{
					equivalentPairSet.remove(eqPair);
				}
			}
			
			pairRemovalList.clear();
			
			curTotalMem = Runtime.getRuntime().totalMemory();
			if(curTotalMem > peakTotal){
				peakTotal = curTotalMem;
			}
			
			curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			if(curUsedMem > peakUsed){
				peakUsed = curUsedMem;
			}
		}
		
		// debugging info
		if(Options.getVerbosity() > 25){
			System.err.println("   merge");
		}
		
		// Used to hold a state from each equivalent pair for removal later
		List<CompositeState> equivStateList = new ArrayList<CompositeState>(equivalentPairSet.size());
		
		/*
		 * For each equivalent pair (s1, s2) redirect each of s2's incoming state transitions
		 * to state s1.  Then remove state s2.
		 */
		for(Pair<CompositeState, CompositeState> equivPair : equivalentPairSet.keySet()){
			CompositeState state1 = equivPair.getLeft();
			CompositeState state2 = equivPair.getRight();

			if(state1.flag == true || state2.flag == true){
				continue;
			}
			
			// Merge incoming state transitions	
			for(CompositeStateTran incomingStateTran : state2.getIncomingStateTranList().toArray(new CompositeStateTran[state2.numIncomingTrans()])){
				sg.removeStateTran(incomingStateTran);
				
				if(incomingStateTran.getCurrentState() == incomingStateTran.getNextState()){
					continue;
				}
				
				incomingStateTran.setNextState(state1);
				sg.addStateTran(incomingStateTran);
			}
			
			state2.flag = true;
			equivStateList.add(state2);
		}

		/*
		 * Remove redundant states
		 */
		for(int i=0; i<equivStateList.size(); i++){
			CompositeState redundantState = equivStateList.get(i);
			
			removeUnreachableState(sg, redundantState);
		}
		
		/*
		 * Memory stats
		 */
		curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		if(Options.getVerbosity() > 0){
			System.out.println("      --> # states: " + sg.numStates());
			System.out.println("      --> # transitions: " + sg.numStateTrans());
			System.out.println("      --> # iterations: " + iter);
			
			System.out.println("\n      --> Peak used memory: " + peakUsed/1000000F + " MB");
			System.out.println("      --> Peak total memory: " + peakTotal/1000000F + " MB");
			System.out.println("      --> Final used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000F + " MB");
			
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			System.out.println("      --> Elapsed time: " + elapsedTimeSec + " sec");
			
			if(elapsedTimeSec > 60){
				float elapsedTime = elapsedTimeSec/(float)60;
				System.out.println("      --> Elapsed time: " + elapsedTime + " min");
			}
		}
	}
	
	private static int notChecked = 0;
	private static int eq = 1;
	private static int neq = 2;
	
	/**
	 * Merge states, in the specified state graph, with equivalent outgoing state transitions.
	 * @param sg - State graph
	 */
	public static void mergeOutgoing9(CompositeStateGraph sg){
		// Run time info
		long start = System.currentTimeMillis();
		
		// Memory usage info
		long peakUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long peakTotal = Runtime.getRuntime().totalMemory();
		
		/*
		 * Find the potential set of equivalent pairs
		 */
		HashMap<Pair<CompositeState, CompositeState>, Integer> equivalentPairSet = findInitialEquivalentPairs9(sg);
		
		/*
		 * Memory statistics
		 */
		long curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		// debugging info
		if(Options.getVerbosity() > 25){
			System.err.println("   fixed point");
		}
		
		// keeps track of the iterations for the fixed point operation
		int iter = 0;
		
		/*
		 * For each pair of states, check if the next states are the same state 
		 * or if they are are still considered equivalent states (within equivalentPairSet).  
		 * If the pair of states do not have the same or equivalent next states 
		 * (still within equivalentPairSet), then the pair is removed.
		 * This operation is performed until no more pairs have been removed and
		 * each pair of states left in equivalentPairSet are equivalent.
		 */
		
		Pair<CompositeState, CompositeState> testPair = new Pair<CompositeState, CompositeState>(null, null);
		
		for(Entry<Pair<CompositeState, CompositeState>, Integer> e : equivalentPairSet.entrySet()){
			if(e.getValue() != notChecked){
				continue;
			}
			
			Stack<Pair<CompositeState, CompositeState>> pairStack = new Stack<Pair<CompositeState, CompositeState>>();
			pairStack.push(e.getKey());
			
			while(!pairStack.empty()){
				Pair<CompositeState, CompositeState> p = pairStack.pop();
				CompositeState currentState1 = p.getLeft();
				CompositeState currentState2 = p.getRight();
				
				List<CompositeStateTran> outgoing1 = currentState1.getOutgoingStateTranList();
				List<CompositeStateTran> outgoing2 = currentState2.getOutgoingStateTranList();
				
				for(int i=0; i<outgoing1.size(); i++){
					CompositeStateTran stTran1 = outgoing1.get(i);
					LPNTran lpnTran1 = stTran1.getLPNTran();
					CompositeState nextState1 = stTran1.getNextState();
					boolean eq = true;
					
					for(int j=0; j<outgoing2.size(); j++){
						CompositeStateTran stTran2 = outgoing2.get(j);
						LPNTran lpnTran2 = stTran2.getLPNTran();
						
						if(lpnTran1 != lpnTran2){
							continue;
						}
						
						
						CompositeState s2 = stTran2.getNextState();
						CompositeState s1 = nextState1;
						
						if(s1 == s2){
							continue;
						}						
						
						if(s2.getIndex() < s1.getIndex()){
							CompositeState tmp = s1;
							s1 = s2;
							s2 = tmp;
						}
						
						testPair.setLeft(s1);
						testPair.setRight(s2);
						
						Integer retStatus = equivalentPairSet.get(testPair);
						if(retStatus == null || retStatus == neq){
							equivalentPairSet.put(p, neq);
							eq = false;
							break;
						}
						else if(retStatus == notChecked){
							pairStack.push(new Pair<CompositeState, CompositeState>(s1, s2));
						}
					}
					
					if(!eq){
						break;
					}
				}
				
				Integer value = equivalentPairSet.get(p);
				if(value == notChecked){
					equivalentPairSet.put(p, eq);
				}
			}
		}
		
		// debugging info
		if(Options.getVerbosity() > 25){
			System.err.println("   merge");
		}
		
		// Used to hold a state from each equivalent pair for removal later
		List<CompositeState> equivStateList = new ArrayList<CompositeState>(equivalentPairSet.size());
		
		/*
		 * For each equivalent pair (s1, s2) redirect each of s2's incoming state transitions
		 * to state s1.  Then remove state s2.
		 */
		for(Entry<Pair<CompositeState, CompositeState>, Integer> e : equivalentPairSet.entrySet()){
			if(e.getValue() != eq){
				continue;
			}
			
			Pair<CompositeState, CompositeState> equivPair = e.getKey();
			CompositeState state1 = equivPair.getLeft();
			CompositeState state2 = equivPair.getRight();

			if(state1.flag == true || state2.flag == true){
				continue;
			}
			
			// Merge incoming state transitions	
			for(CompositeStateTran incomingStateTran : state2.getIncomingStateTranList().toArray(new CompositeStateTran[state2.numIncomingTrans()])){
				sg.removeStateTran(incomingStateTran);
				
				if(incomingStateTran.getCurrentState() == incomingStateTran.getNextState()){
					continue;
				}
				
				incomingStateTran.setNextState(state1);
				sg.addStateTran(incomingStateTran);
			}
			
			state2.flag = true;
			equivStateList.add(state2);
		}

		/*
		 * Remove redundant states
		 */
		for(int i=0; i<equivStateList.size(); i++){
			CompositeState redundantState = equivStateList.get(i);
			
			removeUnreachableState(sg, redundantState);
		}
		
		/*
		 * Memory stats
		 */
		curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		if(Options.getVerbosity() > 0){
			System.out.println("      --> # states: " + sg.numStates());
			System.out.println("      --> # transitions: " + sg.numStateTrans());
			System.out.println("      --> # iterations: " + iter);
			
			System.out.println("\n      --> Peak used memory: " + peakUsed/1000000F + " MB");
			System.out.println("      --> Peak total memory: " + peakTotal/1000000F + " MB");
			System.out.println("      --> Final used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000F + " MB");
			
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			System.out.println("      --> Elapsed time: " + elapsedTimeSec + " sec");
			
			if(elapsedTimeSec > 60){
				float elapsedTime = elapsedTimeSec/(float)60;
				System.out.println("      --> Elapsed time: " + elapsedTime + " min");
			}
		}
	}
	
	private static long secondNumMask = 65535;
	
	static{
		secondNumMask = secondNumMask << 16;
		secondNumMask = secondNumMask | 65535;
	}
	
	/**
	 * Merge states, in the specified state graph, with equivalent outgoing state transitions.
	 * @param sg - State graph
	 */
	public static void mergeOutgoingLong(CompositeStateGraph sg){
		// Run time info
		long start = System.currentTimeMillis();
		
		// Memory usage info
		long peakUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long peakTotal = Runtime.getRuntime().totalMemory();
		
		/*
		 * Re-assign indices so that they are sequential
		 */
		int stateIndex = 1;
		CompositeState[] stateArray = new CompositeState[sg.numStates()];
		for(CompositeState s : sg.getStateSet()){
//			System.out.println(sg.numStates() + " - " + stateIndex + " - " + s.getIndex());
			if(s.getIndex() == 0){
				stateArray[s.getIndex()] = s;
			}
			else{
				stateArray[stateIndex] = s;
				s.setIndex(stateIndex++);
			}
		}
		
		/*
		 * Find the potential set of equivalent pairs
		 */
		HashMap<Long, Long> equivPairSet = findInitialEquivalentPairs5(sg);
		
		/*
		 * Memory statistics
		 */
		long curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		// debugging info
		if(Options.getVerbosity() > 25){
			System.err.println("   fixed point");
		}
		
		// keeps track of the iterations for the fixed point operation
		int iter = 0;
		
		// used to signify when the fixed point is reached
		boolean stable = false;
		
		/*
		 * For each pair of states, check if the next states are the same state 
		 * or if they are are still considered equivalent states (within equivalentPairSet).  
		 * If the pair of states do not have the same or equivalent next states 
		 * (still within equivalentPairSet), then the pair is removed.
		 * This operation is performed until no more pairs have been removed and
		 * each pair of states left in equivalentPairSet are equivalent.
		 */
		while(!stable){
			stable = true;
			iter++;
			
			for(Long eqPair : equivPairSet.keySet().toArray(new Long[equivPairSet.size()])){
				int index1 = (int)(eqPair >>> 32);
				int index2 = (int) (eqPair & secondNumMask);
				
				CompositeState state1 = stateArray[index1];
				CompositeState state2 = stateArray[index2];
				
				CompositeStateTran[] outgoingTrans1 = state1.getOutgoingStateTranList().toArray(new CompositeStateTran[state1.numOutgoingTrans()]);
				CompositeStateTran[] outgoingTrans2 = state2.getOutgoingStateTranList().toArray(new CompositeStateTran[state2.numOutgoingTrans()]);
				
				/*
				 * Check if state1 and state2 contain equivalent outgoing state transitions
				 */
				boolean eq = equivOutgoingTrans5(state1, outgoingTrans1, state2, outgoingTrans2, equivPairSet);
					
				/*
				 * If they are not equivalent, remove the pair
				 */
				if(!eq){
//					if(Options.getImprovedEquivRemovalFlag() == true){
//						removePair(eqPair, equivalentPairSet);
//					}
//					else{
						equivPairSet.remove(eqPair);
//					}
					
					stable = false;
					continue;
				}
			}
			
			curTotalMem = Runtime.getRuntime().totalMemory();
			if(curTotalMem > peakTotal){
				peakTotal = curTotalMem;
			}
			
			curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			if(curUsedMem > peakUsed){
				peakUsed = curUsedMem;
			}
		}
		
		// debugging info
		if(Options.getVerbosity() > 25){
			System.err.println("   merge");
		}
		
		// Used to hold a state from each equivalent pair for removal later
		List<CompositeState> equivStateList = new ArrayList<CompositeState>(equivPairSet.size());
		
		/*
		 * For each equivalent pair (s1, s2) redirect each of s2's incoming state transitions
		 * to state s1.  Then remove state s2.
		 */
		for(Long equivPair : equivPairSet.keySet()){
			int index1 = (int)(equivPair >>> 32);
			int index2 = (int) (equivPair & secondNumMask);
			
			CompositeState state1 = stateArray[index1];
			CompositeState state2 = stateArray[index2];

			if(state1.flag == true || state2.flag == true){
				continue;
			}
			
			// Merge incoming incoming state transitions			
			for(CompositeStateTran incomingStateTran : state2.getIncomingStateTranList().toArray(new CompositeStateTran[state2.numIncomingTrans()])){
				sg.removeStateTran(incomingStateTran);
				
				if(incomingStateTran.getCurrentState() == incomingStateTran.getNextState()){
					continue;
				}
				
				incomingStateTran.setNextState(state1);
				sg.addStateTran(incomingStateTran);
			}
			
			state2.flag = true;
			equivStateList.add(state2);
		}

		/*
		 * Remove redundant states
		 */
		for(CompositeState redundantState : equivStateList){
			removeUnreachableState(sg, redundantState);
		}
		
		/*
		 * Memory stats
		 */
		curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		if(Options.getVerbosity() > 0){
			System.out.println("      --> # states: " + sg.numStates());
			System.out.println("      --> # transitions: " + sg.numStateTrans());
			System.out.println("      --> # iterations: " + iter);
			
			System.out.println("\n      --> Peak used memory: " + peakUsed/1000000F + " MB");
			System.out.println("      --> Peak total memory: " + peakTotal/1000000F + " MB");
			System.out.println("      --> Final used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000F + " MB");
			
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			System.out.println("      --> Elapsed time: " + elapsedTimeSec + " sec");
			
			if(elapsedTimeSec > 60){
				float elapsedTime = elapsedTimeSec/(float)60;
				System.out.println("      --> Elapsed time: " + elapsedTime + " min");
			}
		}
	}
	
	/**
	 * Merge states, in the specified state graph, with equivalent outgoing state transitions.
	 * @param sg - State graph
	 */
	public static void mergeOutgoing2(CompositeStateGraph sg){
		// Run time info
		long start = System.currentTimeMillis();
		
		// Memory usage info
		long peakUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long peakTotal = Runtime.getRuntime().totalMemory();
		
		/*
		 * Find the potential set of equivalent pairs
		 */
		HashMap<CompositeState, List<CompositeState>> equivalentPairSet = findInitialEquivalentPairs2(sg);
		
		/*
		 * Memory statistics
		 */
		long curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		// debugging info
		if(Options.getVerbosity() > 20){
			System.err.println("   fixed point");
		}
		
		// keeps track of the iterations for the fixed point operation
		int iter = 0;
		
		// used to signify when the fixed point is reached
		boolean stable = false;
		
		/*
		 * For each pair of states, check if the next states are the same state 
		 * or if they are are still considered equivalent states (within equivalentPairSet).  
		 * If the pair of states do not have the same or equivalent next states 
		 * (still within equivalentPairSet), then the pair is removed.
		 * This operation is performed until no more pairs have been removed and
		 * each pair of states left in equivalentPairSet are equivalent.
		 */
		while(!stable){
			stable = true;
			iter++;
			
			for(CompositeState state1 : sg.getStateSet()) {
//			for(Entry<CompositeState, List<CompositeState>> e : equivalentPairSet.entrySet()){
//				CompositeState state1 = e.getKey();
				List<CompositeState> equivList = equivalentPairSet.get(state1);
				if(equivList == null)
					continue;
				
				for(CompositeState state2 : equivList.toArray(new CompositeState[equivList.size()])){				
					CompositeStateTran[] outgoingTrans1 = state1.getOutgoingStateTranList().toArray(new CompositeStateTran[state1.numOutgoingTrans()]);
					CompositeStateTran[] outgoingTrans2 = state2.getOutgoingStateTranList().toArray(new CompositeStateTran[state2.numOutgoingTrans()]);
					
					/*
					 * Check if state1 and state2 contain equivalent outgoing state transitions
					 */
					boolean eq = equivOutgoingTrans2(state1, outgoingTrans1, state2, outgoingTrans2, equivalentPairSet);
						
					/*
					 * If they are not equivalent, remove the pair
					 */
					if(!eq){
						if(Options.getImprovedEquivRemovalFlag() == true){
//							removePair(eqPair, equivalentPairSet);
						}
						else{
							equivList.remove(state2);
						}
						
						stable = false;
						continue;
					}
				}
			}
			
			curTotalMem = Runtime.getRuntime().totalMemory();
			if(curTotalMem > peakTotal){
				peakTotal = curTotalMem;
			}
			
			curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			if(curUsedMem > peakUsed){
				peakUsed = curUsedMem;
			}
		}
		
		// debugging info
		if(Options.getVerbosity() > 20){
			System.err.println("   merge");
		}
		
		// Used to hold a state from each equivalent pair for removal later
		List<CompositeState> equivStateList = new ArrayList<CompositeState>(equivalentPairSet.size());
		
		/*
		 * For each equivalent pair (s1, s2) redirect each of s2's incoming state transitions
		 * to state s1.  Then remove state s2.
		 */
		for(Entry<CompositeState, List<CompositeState>> e : equivalentPairSet.entrySet()){
			CompositeState state1 = e.getKey();
			List<CompositeState> equivList = e.getValue();
			
			for(CompositeState state2 : equivList){
				if(state1.flag == true || state2.flag == true){
					continue;
				}
				
				// Merge incoming incoming state transitions			
				for(CompositeStateTran incomingStateTran : state2.getIncomingStateTranList().toArray(new CompositeStateTran[state2.numIncomingTrans()])){
					sg.removeStateTran(incomingStateTran);
					
					if(incomingStateTran.getCurrentState() == incomingStateTran.getNextState()){
						continue;
					}
					
					incomingStateTran.setNextState(state1);
					sg.addStateTran(incomingStateTran);
				}
				
				state2.flag = true;
				equivStateList.add(state2);
			}
		}

		/*
		 * Remove redundant states
		 */
		for(CompositeState redundantState : equivStateList){
			removeUnreachableState(sg, redundantState);
		}
		
		/*
		 * Memory stats
		 */
		curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		if(Options.getVerbosity() > 0){
			System.out.println("      --> # states: " + sg.numStates());
			System.out.println("      --> # transitions: " + sg.numStateTrans());
			System.out.println("      --> # iterations: " + iter);
			
			System.out.println("\n      --> Peak used memory: " + peakUsed/1000000F + " MB");
			System.out.println("      --> Peak total memory: " + peakTotal/1000000F + " MB");
			System.out.println("      --> Final used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000F + " MB");
			
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			System.out.println("      --> Elapsed time: " + elapsedTimeSec + " sec");
			
			if(elapsedTimeSec > 60){
				float elapsedTime = elapsedTimeSec/(float)60;
				System.out.println("      --> Elapsed time: " + elapsedTime + " min");
			}
		}
	}
	
	/**
	 * Determines whether state1 and state2 have equivalent outgoing state transitions.
	 * That is for each outgoing state transition, the other state must contain a state transition 
	 * labeled with the same LPN transition and an equivalent next state.
	 * @return TRUE state1 and state2 contain equivalent outgoing state transitions, otherwise FALSE
	 */
	private static boolean equivOutgoingTrans(CompositeState state1, List<CompositeStateTran> outgoingTrans1, 
			CompositeState state2, List<CompositeStateTran> outgoingTrans2, 
			HashMap<Pair<CompositeState, CompositeState>, Pair<CompositeState, CompositeState>> equivalentPairSet){
		
		// State pair used temporarily to check for equal pairs
		Pair<CompositeState, CompositeState> statePair = new Pair<CompositeState, CompositeState>(null, null);
		
		/*
		 * Check that state2 contains an equivalent outgoing state transition for each of
		 * state1's outgoing state transitions.
		 */
		for(int i=0; i<outgoingTrans1.size(); i++){
			CompositeStateTran stateTran1 = outgoingTrans1.get(i);
			
			boolean succEq = false;
			CompositeState succState1 = stateTran1.getNextState();
			LPNTran enabledTran1 = stateTran1.getLPNTran();
			
			for(int j=0; j<outgoingTrans2.size(); j++){
				CompositeStateTran stateTran2 = outgoingTrans2.get(j);
				
				CompositeState nextState1 = succState1;
				CompositeState nextState2 = stateTran2.getNextState();
				LPNTran enabledTran2 = stateTran2.getLPNTran();

				if(enabledTran1 != enabledTran2){
					continue;
				}
				
				// check if the successor states are the same
				if(nextState1 == nextState2){
					succEq = true;
					break;
				}
				
				// check if the successor states are error states
				if(nextState1.getErrorCode() != StateError.NONE){
					if(nextState2.getErrorCode() != StateError.NONE){
						succEq = true;
						break;
					}
					else{
						continue;
					}
				}
				else if(nextState2.getErrorCode() != StateError.NONE){
					continue;
				}
				
				// Lower index state is always stored in the left of the pair
				if(nextState2.getIndex() < nextState1.getIndex()){
					CompositeState tmp = nextState2;
					nextState2 = nextState1;
					nextState1 = tmp;
				}
				
				statePair.setLeft(nextState1);
				statePair.setRight(nextState2);
				
				if(equivalentPairSet.containsKey(statePair)){
					succEq = true;
					break;
				}
			}
			
			if(!succEq){
				return false;
			}
		}
		
		/*
		 * Check that state1 contains an equivalent outgoing state transition for each of
		 * state2's outgoing state transitions.
		 */
		for(int i=0; i<outgoingTrans2.size(); i++){
			CompositeStateTran stateTran2 = outgoingTrans2.get(i);
			
			boolean succEq = false;
			CompositeState succState2 = stateTran2.getNextState();
			LPNTran enabledTran2 = stateTran2.getLPNTran();
			
			for(int j=0; j<outgoingTrans1.size(); j++){
				CompositeStateTran stateTran1 = outgoingTrans1.get(j);
				
				CompositeState nextState1 = stateTran1.getNextState();
				CompositeState nextState2 = succState2;
				LPNTran enabledTran1 = stateTran1.getLPNTran();
				
				if(enabledTran1 != enabledTran2){
					continue;
				}
				
				// check if the successor states are the same
				if(nextState1 == nextState2){
					succEq = true;
					break;
				}
				
				// check if the successor states are error states
				if(nextState1.getErrorCode() != StateError.NONE){
					if(nextState2.getErrorCode() != StateError.NONE){
						succEq = true;
						break;
					}
					else{
						continue;
					}
				}
				else if(nextState2.getErrorCode() != StateError.NONE){
					continue;
				}
				
				// Lower index state is always stored in the left of the pair
				if(nextState2.getIndex() < nextState1.getIndex()){
					CompositeState tmp = nextState2;
					nextState2 = nextState1;
					nextState1 = tmp;
				}
				
				statePair.setLeft(nextState1);
				statePair.setRight(nextState2);
				
				if(equivalentPairSet.containsKey(statePair)){
					succEq = true;
					break;
				}
			}
			
			if(!succEq){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Determines whether state1 and state2 have equivalent outgoing state transitions.
	 * That is for each outgoing state transition, the other state must contain a state transition 
	 * labeled with the same LPN transition and an equivalent next state.
	 * @return TRUE state1 and state2 contain equivalent outgoing state transitions, otherwise FALSE
	 */
	private static boolean equivOutgoingTrans5(CompositeState state1, CompositeStateTran[] outgoingTrans1, 
			CompositeState state2, CompositeStateTran[] outgoingTrans2, 
			HashMap<Long, Long> equivPairSet){
		
		/*
		 * Check that state2 contains an equivalent outgoing state transition for each of
		 * state1's outgoing state transitions.
		 */
		for(CompositeStateTran stateTran1 : outgoingTrans1){
			boolean succEq = false;
			CompositeState succState1 = stateTran1.getNextState();
			LPNTran enabledTran1 = stateTran1.getLPNTran();
			
			for(CompositeStateTran stateTran2 : outgoingTrans2){
				CompositeState nextState1 = succState1;
				CompositeState nextState2 = stateTran2.getNextState();
				LPNTran enabledTran2 = stateTran2.getLPNTran();

				if(enabledTran1 != enabledTran2){
					continue;
				}
				
				// check if the successor states are the same
				if(nextState1 == nextState2){
					succEq = true;
					break;
				}
				
				// check if the successor states are error states
				if(nextState1.getErrorCode() != StateError.NONE){
					if(nextState2.getErrorCode() != StateError.NONE){
						succEq = true;
						break;
					}
					else{
						continue;
					}
				}
				else if(nextState2.getErrorCode() != StateError.NONE){
					continue;
				}
				
				// Lower index state is always stored in the left of the pair
				if(nextState2.getIndex() < nextState1.getIndex()){
					CompositeState tmp = nextState2;
					nextState2 = nextState1;
					nextState1 = tmp;
				}
				
				Long l = (long)nextState1.getIndex() << 32;
				l = l | nextState2.getIndex();
				
				if(equivPairSet.containsKey(l)){
					succEq = true;
					break;
				}
			}
			
			if(!succEq){
				return false;
			}
		}
		
		/*
		 * Check that state1 contains an equivalent outgoing state transition for each of
		 * state2's outgoing state transitions.
		 */
		for(CompositeStateTran stateTran2 : outgoingTrans2){
			boolean succEq = false;
			CompositeState succState2 = stateTran2.getNextState();
			LPNTran enabledTran2 = stateTran2.getLPNTran();
			
			for(CompositeStateTran stateTran1 : outgoingTrans1){
				CompositeState nextState1 = stateTran1.getNextState();
				CompositeState nextState2 = succState2;
				LPNTran enabledTran1 = stateTran1.getLPNTran();
				
				if(enabledTran1 != enabledTran2){
					continue;
				}
				
				// check if the successor states are the same
				if(nextState1 == nextState2){
					succEq = true;
					break;
				}
				
				// check if the successor states are error states
				if(nextState1.getErrorCode() != StateError.NONE){
					if(nextState2.getErrorCode() != StateError.NONE){
						succEq = true;
						break;
					}
					else{
						continue;
					}
				}
				else if(nextState2.getErrorCode() != StateError.NONE){
					continue;
				}
				
				// Lower index state is always stored in the left of the pair
				if(nextState2.getIndex() < nextState1.getIndex()){
					CompositeState tmp = nextState2;
					nextState2 = nextState1;
					nextState1 = tmp;
				}
				
				Long l = (long)nextState1.getIndex() << 32;
				l = l | nextState2.getIndex();
				
				if(equivPairSet.containsKey(l)){
					succEq = true;
					break;
				}
			}
			
			if(!succEq){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Determines whether state1 and state2 have equivalent outgoing state transitions.
	 * That is for each outgoing state transition, the other state must contain a state transition 
	 * labeled with the same LPN transition and an equivalent next state.
	 * @return TRUE state1 and state2 contain equivalent outgoing state transitions, otherwise FALSE
	 */
	private static boolean equivOutgoingTrans2(CompositeState state1, CompositeStateTran[] outgoingTrans1, 
			CompositeState state2, CompositeStateTran[] outgoingTrans2, 
			HashMap<CompositeState, List<CompositeState>> equivalentPairSet2){
		
		/*
		 * Check that state2 contains an equivalent outgoing state transition for each of
		 * state1's outgoing state transitions.
		 */
		for(CompositeStateTran stateTran1 : outgoingTrans1){
			boolean succEq = false;
			CompositeState succState1 = stateTran1.getNextState();
			LPNTran enabledTran1 = stateTran1.getLPNTran();
			
			for(CompositeStateTran stateTran2 : outgoingTrans2){
				CompositeState nextState1 = succState1;
				CompositeState nextState2 = stateTran2.getNextState();
				LPNTran enabledTran2 = stateTran2.getLPNTran();

				if(enabledTran1 != enabledTran2){
					continue;
				}
				
				// check if the successor states are the same
				if(nextState1 == nextState2){
					succEq = true;
					break;
				}
				
				// check if the successor states are error states
				if(nextState1.getErrorCode() != StateError.NONE){
					if(nextState2.getErrorCode() != StateError.NONE){
						succEq = true;
						break;
					}
					else{
						continue;
					}
				}
				else if(nextState2.getErrorCode() != StateError.NONE){
					continue;
				}
				
				// Lower index state is always stored in the left of the pair
				if(nextState2.getIndex() < nextState1.getIndex()){
					CompositeState tmp = nextState2;
					nextState2 = nextState1;
					nextState1 = tmp;
				}
				
				List<CompositeState> equivList = equivalentPairSet2.get(nextState1);
				if(equivList != null){
					if(equivList.contains(nextState2)){
						succEq = true;
						break;
					}
				}
			}
			
			if(!succEq){
				return false;
			}
		}
		
		/*
		 * Check that state1 contains an equivalent outgoing state transition for each of
		 * state2's outgoing state transitions.
		 */
		for(CompositeStateTran stateTran2 : outgoingTrans2){
			boolean succEq = false;
			CompositeState succState2 = stateTran2.getNextState();
			LPNTran enabledTran2 = stateTran2.getLPNTran();
			
			for(CompositeStateTran stateTran1 : outgoingTrans1){
				CompositeState nextState1 = stateTran1.getNextState();
				CompositeState nextState2 = succState2;
				LPNTran enabledTran1 = stateTran1.getLPNTran();
				
				if(enabledTran1 != enabledTran2){
					continue;
				}
				
				// check if the successor states are the same
				if(nextState1 == nextState2){
					succEq = true;
					break;
				}
				
				// check if the successor states are error states
				if(nextState1.getErrorCode() != StateError.NONE){
					if(nextState2.getErrorCode() != StateError.NONE){
						succEq = true;
						break;
					}
					else{
						continue;
					}
				}
				else if(nextState2.getErrorCode() != StateError.NONE){
					continue;
				}
				
				// Lower index state is always stored in the left of the pair
				if(nextState2.getIndex() < nextState1.getIndex()){
					CompositeState tmp = nextState2;
					nextState2 = nextState1;
					nextState1 = tmp;
				}
				
				List<CompositeState> equivList = equivalentPairSet2.get(nextState1);
				if(equivList != null){
					if(equivList.contains(nextState2)){
						succEq = true;
						break;
					}
				}
			}
			
			if(!succEq){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Removes a pair of states from the set of equivalent pairs.  
	 * Also searches backwards and removes pairs of predecessor states.
	 * @param statePair - State pair to remove
	 * @param equivalentPairSet - Set of equivalent state pairs
	 */
	private static void removePair(Pair<CompositeState, CompositeState> statePair, HashMap<Pair<CompositeState, CompositeState>, Pair<CompositeState, CompositeState>> equivalentPairSet){
		Stack<Pair<CompositeState, CompositeState>> pairStack = new Stack<Pair<CompositeState, CompositeState>>();
		
		// Initialize stack with the pair passed as an argument
		pairStack.push(statePair);
		
		while(!pairStack.empty()){
			// Pop the stack
			statePair = pairStack.pop();
			
			/*
			 * Remove the state pair from the set of equivalent state pairs.
			 * If the pair does not exist in the set, continue.
			 */
			if(equivalentPairSet.remove(statePair) == null){
				continue;
			}
	
			CompositeState state1 = statePair.getLeft();
			CompositeState state2 = statePair.getRight();
			
			List<CompositeStateTran> incomingStateTrans1 = state1.getIncomingStateTranList();
			List<CompositeStateTran> incomingStateTrans2 = state2.getIncomingStateTranList();
			
			/*
			 * Remove each predecessor pairs with the same enabled LPN transition
			 */
			Pair<CompositeState, CompositeState> removePair = new Pair<CompositeState, CompositeState>(null, null);
			for(CompositeStateTran stateTran1 : incomingStateTrans1){
				CompositeState currentState1 = stateTran1.getCurrentState();
				LPNTran curEnabledTran1 = stateTran1.getLPNTran();
				
				for(CompositeStateTran stateTran2 : incomingStateTrans2){
					CompositeState s1 = currentState1;
					CompositeState s2 = stateTran2.getCurrentState();
					LPNTran curEnabledTran2 = stateTran2.getLPNTran();
	
					if(curEnabledTran1 != curEnabledTran2){
						continue;
					}
					
					if(s1 == s2){
						continue;
					}
					else if(s2.getIndex() < s1.getIndex()){
						CompositeState tmp = s1;
						s1 = s2;
						s2 = tmp;
					}
					
					removePair.setLeft(s1);
					removePair.setRight(s2);
					
					if(equivalentPairSet.containsKey(removePair)){
//						CompositeStateTran[] trans1 = s1.getOutgoingStateTranList().toArray(new CompositeStateTran[s1.numOutgoingTrans()]);
//						CompositeStateTran[] trans2 = s2.getOutgoingStateTranList().toArray(new CompositeStateTran[s2.numOutgoingTrans()]);
//						
//						if(equivOutgoingTrans(s1, trans1, s2, trans2, equivalentPairSet)){
//							System.out.println("\n EQUIVALENT PAIR " + removePair + "\n");
//						}
						
						pairStack.push(equivalentPairSet.get(removePair));
//						equivalentPairSet.remove(removePair);
					}
				}
			}
		}
	}
	
	/**
	 * Merge states, in the specified state graph, that contain the same incoming state transitions.
	 * @param sg - State graph
	 */
	public static void mergeIncoming(CompositeStateGraph sg){
		long start = System.currentTimeMillis();
		long peakUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long peakTotal = Runtime.getRuntime().totalMemory();
		
		int iter = 0;
		boolean stable = false;
		
		/*
		 * Keep finding pairs of states with the same set of incoming state transitions 
		 * until no states are merged.
		 */
		while(!stable){
			iter++;
			stable = true;
			
			/*
			 * Find the pairs of states that contain the equivalent incoming state transitions
			 */
			HashSet<Pair<CompositeState, CompositeState>> equivalentPairSet = findEquivalentPairs(sg);
	
			/*
			 * Memory stats
			 */
			long curTotalMem = Runtime.getRuntime().totalMemory();
			if(curTotalMem > peakTotal){
				peakTotal = curTotalMem;
			}
			
			long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			if(curUsedMem > peakUsed){
				peakUsed = curUsedMem;
			}
			
			/*
			 * For each pair <s1, s2> with equivalent incoming state transitions, 
			 * merge the outgoing state transitions into state s1 and remove state s2
			 */
			for(Pair<CompositeState, CompositeState> statePair : equivalentPairSet){
				CompositeState state1 = statePair.getLeft();
				CompositeState state2 = statePair.getRight();
	
				if(!sg.containsState(state1) || !sg.containsState(state2)){
					continue;
				}
				
				/*
				 * merge outgoing state transitions
				 */
				for(CompositeStateTran outgoingStateTran : state2.getOutgoingStateTranList().toArray(new CompositeStateTran[state2.numOutgoingTrans()])){
					sg.removeStateTran(outgoingStateTran);
					
					if(outgoingStateTran.getCurrentState() == outgoingStateTran.getNextState()){
						outgoingStateTran.setNextState(state1);
					}
					
					outgoingStateTran.setCurrentState(state1);
					sg.addStateTran(outgoingStateTran);
				}
				
				/*
				 * Remove state
				 */
				for(CompositeStateTran stateTran : state2.getIncomingStateTranList().toArray(new CompositeStateTran[state2.numIncomingTrans()])){
					sg.removeStateTran(stateTran);
				}
				
				sg.removeState(state2);
				
				stable = false;
			}
		}
		
		/*
		 * Memory stats
		 */
		long curTotalMem = Runtime.getRuntime().totalMemory();
		if(curTotalMem > peakTotal){
			peakTotal = curTotalMem;
		}
		
		long curUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if(curUsedMem > peakUsed){
			peakUsed = curUsedMem;
		}
		
		if(Options.getVerbosity() > 0){
			System.out.println("      --> # states: " + sg.numStates());
			System.out.println("      --> # transitions: " + sg.numStateTrans());
			System.out.println("      --> # iterations: " + iter);
			
			System.out.println("\n      --> Peak used memory: " + peakUsed/1000000F + " MB");
			System.out.println("      --> Peak total memory: " + peakTotal/1000000F + " MB");
			System.out.println("      --> Final used memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000F + " MB");
			
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			float elapsedTimeSec = elapsedTimeMillis/1000F;
			System.out.println("      --> Elapsed time: " + elapsedTimeSec + " sec");
			
			if(elapsedTimeSec > 60){
				float elapsedTime = elapsedTimeSec/(float)60;
				System.out.println("      --> Elapsed time: " + elapsedTime + " min");
			}
		}
	}
	
	/**
	 * Checks if the two specified states contain equivalent enabled transitions sets.
	 * @return TRUE if the specified states contain equivalent enabled transitions sets, otherwise FALSE
	 */
	private static boolean equivalentEnabledSet(CompositeState state1, CompositeState state2){
		boolean equiv = false;
		
		List<CompositeStateTran> outgoingTranList1 = state1.getOutgoingStateTranList();
		List<CompositeStateTran> outgoingTranList2 = state2.getOutgoingStateTranList();
		
		for(int i=0; i<outgoingTranList1.size(); i++){
			CompositeStateTran stTran1 = outgoingTranList1.get(i);
			
			equiv = false;
			LPNTran lpnTran = stTran1.getLPNTran();
			
			for(int j=0; j<outgoingTranList2.size(); j++){
				CompositeStateTran stTran2 = outgoingTranList2.get(j);
				
				if(lpnTran != stTran2.getLPNTran()){
					continue;
				}
				
				equiv = true;
				break;
			}
			
			if(equiv==false){
				break;
			}
		}
		
		if(!equiv){
			return false;
		}

		for(int i=0; i<outgoingTranList2.size(); i++){
			CompositeStateTran stTran1 = outgoingTranList2.get(i);
			
			equiv = false;
			LPNTran lpnTran = stTran1.getLPNTran();
			
			for(int j=0; j<outgoingTranList1.size(); j++){
				CompositeStateTran stTran2 = outgoingTranList1.get(j);
				
				if(lpnTran != stTran2.getLPNTran()){
					continue;
				}
				
				equiv = true;
				break;
			}
			
			if(equiv==false){
				break;
			}
		}
		
		return equiv;
	}
	
	private static int stateTime = 0;
	
	/**
	 * Finds and returns the set of potentially equivalent pairs of states in a given state graph.
	 * Potentially equivalent state pairs are identified as state pairs containing equivalent 
	 * enabled LPN transition sets.
	 * @param sg - State graph
	 * @return Set of potentially equivalent pairs of states
	 */
	private static HashMap<Pair<CompositeState, CompositeState>, Pair<CompositeState, CompositeState>> findInitialEquivalentPairs(CompositeStateGraph sg){
		// Holds the set of potentially equivalent state pairs
		HashMap<Pair<CompositeState, CompositeState>, Pair<CompositeState, CompositeState>> equivalentSet = new HashMap<Pair<CompositeState, CompositeState>, Pair<CompositeState, CompositeState>>();		
		
		/*
		 * Maps the integer hash value of a set of LPN transitions to the list of states
		 * containing the same value for their enabled LPN transition set.
		 */
		HashMap<Integer, List<CompositeState>> outgoingHashValueMap = new HashMap<Integer, List<CompositeState>>();

		/*
		 * Comparator used to determine order for a state's outgoing state transition set.
		 * the comparator orders state transitions by the LPN transition.
		 */
		Comparator<CompositeStateTran> lpnTranComparator = new Comparator<CompositeStateTran>(){
            public int compare(CompositeStateTran t1, CompositeStateTran t2) {
            	return t1.getLPNTran().getFullLabel().compareToIgnoreCase(t2.getLPNTran().getFullLabel());
            }
        };
        
		int prime = 31;
		stateTime++;
		
		/*
		 * For each state generate a hash value for it's enabled LPN transition set.
		 * Add state and value to outgoingHashValueMap.
		 */
		for(CompositeState currentState : sg.getStateSet()){
			if(currentState.getErrorCode() != StateError.NONE){
				continue;
			}			
			
			/*
			 * Sort the state's outgoing state transitions by the LPN transition in order
			 * to keep hash values consistent
			 */
			Collections.sort(currentState.getOutgoingStateTranList(), lpnTranComparator);
			
			/*
			 * Generate hash value for the state's enabled LPN transition set.
			 * Add hash value and state to outgoingHashValueMap.
			 */
			int hash = 1;
			List<CompositeStateTran> stTrans = currentState.getOutgoingStateTranList();
			for(int j=0; j<stTrans.size(); j++){
				CompositeStateTran stTran = stTrans.get(j);
				
				// if the hash of this LPN transition has already been added, skip
				if(stTran.getLPNTran().time == stateTime){
					continue;
				}

				stTran.getLPNTran().time = stateTime;
				hash = hash*prime + stTran.getLPNTran().hashCode(); 
			}
			
			List<CompositeState> stateList = outgoingHashValueMap.get(hash);
			if(stateList == null){
				stateList = new ArrayList<CompositeState>();
				stateList.add(currentState);
				outgoingHashValueMap.put(hash, stateList);
			}
			else{
				stateList.add(currentState);
			}
			
			stateTime++;
		}
		
		/*
		 * Check if two or more states have the same value.  
		 * If so, make sure that their enabled sets are equivalent and not just a collision.
		 * Create pairs if equivalent and add to equivalentSet.
		 */
		for(List<CompositeState> stateList : outgoingHashValueMap.values()){
			if(stateList.size() < 2){
				continue;
			}
			
			for(int i = 0; i < stateList.size(); i++){
				CompositeState state = stateList.get(i);
				
				for(int j = i+1; j < stateList.size(); j++){
					CompositeState state1 = state;
					CompositeState state2 = stateList.get(j);
					
					// make sure the two states have equivalent enabled transition sets
					if(!equivalentEnabledSet(state1, state2)){
						continue;
					}
					
					// The state on the left of the pair is always the state with the smallest index
					if(state2.getIndex() < state1.getIndex()){
						CompositeState temp = state1;
						state1 = state2;
						state2 = temp;
					}
					
					Pair<CompositeState, CompositeState> p = new Pair<CompositeState, CompositeState>(state1, state2);
					equivalentSet.put(p, p);
				}
			}
		}
		
		return equivalentSet;
	}
	
	/**
	 * Finds and returns the set of potentially equivalent pairs of states in a given state graph.
	 * Potentially equivalent state pairs are identified as state pairs containing equivalent 
	 * enabled LPN transition sets.
	 * @param sg - State graph
	 * @return Set of potentially equivalent pairs of states
	 */
	private static HashMap<Pair<CompositeState, CompositeState>, Integer> findInitialEquivalentPairs9(CompositeStateGraph sg){
		// Holds the set of potentially equivalent state pairs
		HashMap<Pair<CompositeState, CompositeState>, Integer> equivalentSet = new HashMap<Pair<CompositeState, CompositeState>, Integer>();		
		
		/*
		 * Maps the integer hash value of a set of LPN transitions to the list of states
		 * containing the same value for their enabled LPN transition set.
		 */
		HashMap<Integer, List<CompositeState>> outgoingHashValueMap = new HashMap<Integer, List<CompositeState>>();

		/*
		 * Comparator used to determine order for a state's outgoing state transition set.
		 * the comparator orders state transitions by the LPN transition.
		 */
		Comparator<CompositeStateTran> lpnTranComparator = new Comparator<CompositeStateTran>(){
            public int compare(CompositeStateTran t1, CompositeStateTran t2) {
            	return t1.getLPNTran().getFullLabel().compareToIgnoreCase(t2.getLPNTran().getFullLabel());
            }
        };
        
		int prime = 31;
		stateTime++;
		
		/*
		 * For each state generate a hash value for it's enabled LPN transition set.
		 * Add state and value to outgoingHashValueMap.
		 */
		for(CompositeState currentState : sg.getStateSet()){
			if(currentState.getErrorCode() != StateError.NONE){
				continue;
			}			
			
			/*
			 * Sort the state's outgoing state transitions by the LPN transition in order
			 * to keep hash values consistent
			 */
			Collections.sort(currentState.getOutgoingStateTranList(), lpnTranComparator);
			
			/*
			 * Generate hash value for the state's enabled LPN transition set.
			 * Add hash value and state to outgoingHashValueMap.
			 */
			int hash = 1;
			List<CompositeStateTran> stTrans = currentState.getOutgoingStateTranList();
			for(int j=0; j<stTrans.size(); j++){
				CompositeStateTran stTran = stTrans.get(j);
				
				// if the hash of this LPN transition has already been added, skip
				if(stTran.getLPNTran().time == stateTime){
					continue;
				}

				stTran.getLPNTran().time = stateTime;
				hash = hash*prime + stTran.getLPNTran().hashCode(); 
			}
			
			List<CompositeState> stateList = outgoingHashValueMap.get(hash);
			if(stateList == null){
				stateList = new ArrayList<CompositeState>();
				stateList.add(currentState);
				outgoingHashValueMap.put(hash, stateList);
			}
			else{
				stateList.add(currentState);
			}
			
			stateTime++;
		}
		
		/*
		 * Check if two or more states have the same value.  
		 * If so, make sure that their enabled sets are equivalent and not just a collision.
		 * Create pairs if equivalent and add to equivalentSet.
		 */
		for(List<CompositeState> stateList : outgoingHashValueMap.values()){
			if(stateList.size() < 2){
				continue;
			}
			
			for(int i = 0; i < stateList.size(); i++){
				CompositeState state = stateList.get(i);
				
				for(int j = i+1; j < stateList.size(); j++){
					CompositeState state1 = state;
					CompositeState state2 = stateList.get(j);
					
					// make sure the two states have equivalent enabled transition sets
					if(!equivalentEnabledSet(state1, state2)){
						continue;
					}
					
					// The state on the left of the pair is always the state with the smallest index
					if(state2.getIndex() < state1.getIndex()){
						CompositeState temp = state1;
						state1 = state2;
						state2 = temp;
					}
					
					Pair<CompositeState, CompositeState> p = new Pair<CompositeState, CompositeState>(state1, state2);
					equivalentSet.put(p, notChecked);
				}
			}
		}
		
		return equivalentSet;
	}
	
	/**
	 * Finds and returns the set of potentially equivalent pairs of states in a given state graph.
	 * Potentially equivalent state pairs are identified as state pairs containing equivalent 
	 * enabled LPN transition sets.
	 * @param sg - State graph
	 * @return Set of potentially equivalent pairs of states
	 */
	private static HashMap<Long, Long> findInitialEquivalentPairs5(CompositeStateGraph sg){
		// Holds the set of potentially equivalent state pairs
		HashMap<Long, Long> equivSet = new HashMap<Long, Long>();		
		
		/*
		 * Maps the integer hash value of a set of LPN transitions to the list of states
		 * containing the same value for their enabled LPN transition set.
		 */
		HashMap<Integer, List<CompositeState>> outgoingHashValueMap = new HashMap<Integer, List<CompositeState>>();
		
		// Holds the state graph's state set
		CompositeState[] stateSetArray = sg.getStateSet().toArray(new CompositeState[sg.numStates()]);

		/*
		 * Comparator used to determine order for a state's outgoing state transition set.
		 * the comparator orders state transitions by the LPN transition.
		 */
		Comparator<CompositeStateTran> lpnTranComparator = new Comparator<CompositeStateTran>(){
            public int compare(CompositeStateTran t1, CompositeStateTran t2) {
            	return t1.getLPNTran().getFullLabel().compareToIgnoreCase(t2.getLPNTran().getFullLabel());
            }
        };
        
		int prime = 31;
		stateTime++;
		
		/*
		 * For each state generate a hash value for it's enabled LPN transition set.
		 * Add state and value to outgoingHashValueMap.
		 */
		for(int i = 0; i < stateSetArray.length; i++){
			CompositeState currentState = stateSetArray[i];
			if(currentState.getErrorCode() != StateError.NONE){
				continue;
			}
			
			/*
			 * Sort the state's outgoing state transitions by the LPN transition in order
			 * to keep hash values consistent
			 */
			Collections.sort(currentState.getOutgoingStateTranList(), lpnTranComparator);
			
			/*
			 * Generate hash value for the state's enabled LPN transition set.
			 * Add hash value and state to outgoingHashValueMap.
			 */
			int hash = 1;
			for(CompositeStateTran stTran : currentState.getOutgoingStateTranList()){
				// if the hash of this LPN transition has already been added, skip
				if(stTran.getLPNTran().time == stateTime){
					continue;
				}

				stTran.getLPNTran().time = stateTime;
				hash = hash*prime + stTran.getLPNTran().hashCode(); 
			}
			
			List<CompositeState> stateList = outgoingHashValueMap.get(hash);
			if(stateList == null){
				stateList = new ArrayList<CompositeState>();
				stateList.add(currentState);
				outgoingHashValueMap.put(hash, stateList);
			}
			else{
				stateList.add(currentState);
			}
			
			stateTime++;
		}
		
		/*
		 * Check if two or more states have the same value.  
		 * If so, make sure that their enabled sets are equivalent and not just a collision.
		 * Create pairs if equivalent and add to equivalentSet.
		 */
		for(List<CompositeState> stateList : outgoingHashValueMap.values()){
			if(stateList.size() < 2){
				continue;
			}
			
			for(int i = 0; i < stateList.size(); i++){
				CompositeState state = stateList.get(i);
				
				for(int j = i+1; j < stateList.size(); j++){
					CompositeState state1 = state;
					CompositeState state2 = stateList.get(j);
					
					// make sure the two states have equivalent enabled transition sets
					if(!equivalentEnabledSet(state1, state2)){
						continue;
					}
					
					// The state on the left of the pair is always the state with the smallest index
					if(state2.getIndex() < state1.getIndex()){
						CompositeState temp = state1;
						state1 = state2;
						state2 = temp;
					}
					
					Long l = (long)state1.getIndex() << 32;
					l = l | state2.getIndex();
					
					equivSet.put(l, l);
				}
			}
		}
		
		return equivSet;
	}
	
	/**
	 * Finds and returns the set of potentially equivalent pairs of states in a given state graph.
	 * Potentially equivalent state pairs are identified as state pairs containing equivalent 
	 * enabled LPN transition sets.
	 * @param sg - State graph
	 * @return Set of potentially equivalent pairs of states
	 */
	private static HashMap<CompositeState, List<CompositeState>> findInitialEquivalentPairs2(CompositeStateGraph sg){
		// Holds the set of potentially equivalent state pairs
		HashMap<CompositeState, List<CompositeState>> equivalentSet = new HashMap<CompositeState, List<CompositeState>>();		
		
		/*
		 * Maps the integer hash value of a set of LPN transitions to the list of states
		 * containing the same value for their enabled LPN transition set.
		 */
		HashMap<Integer, List<CompositeState>> outgoingHashValueMap = new HashMap<Integer, List<CompositeState>>();
		
		// Holds the state graph's state set
//		CompositeState[] stateSetArray = sg.getStateSet().toArray(new CompositeState[sg.numStates()]);
		

		/*
		 * Comparator used to determine order for a state's outgoing state transition set.
		 * the comparator orders state transitions by the LPN transition.
		 */
		Comparator<CompositeStateTran> lpnTranComparator = new Comparator<CompositeStateTran>(){
            public int compare(CompositeStateTran t1, CompositeStateTran t2) {
            	return t1.getLPNTran().getFullLabel().compareToIgnoreCase(t2.getLPNTran().getFullLabel());
            }
        };
        
		int prime = 31;
		stateTime++;
		
		/*
		 * For each state generate a hash value for it's enabled LPN transition set.
		 * Add state and value to outgoingHashValueMap.
		 */
//		for(int i = 0; i < stateSetArray.length; i++){
//			CompositeState currentState = stateSetArray[i];
		for(CompositeState currentState : sg.getStateSet()){
			if(currentState.getErrorCode() != StateError.NONE){
				continue;
			}
			
			/*
			 * Sort the state's outgoing state transitions by the LPN transition in order
			 * to keep hash values consistent
			 */
			Collections.sort(currentState.getOutgoingStateTranList(), lpnTranComparator);
			
			/*
			 * Generate hash value for the state's enabled LPN transition set.
			 * Add hash value and state to outgoingHashValueMap.
			 */
			int hash = 1;
			for(CompositeStateTran stTran : currentState.getOutgoingStateTranList()){
				// if the hash of this LPN transition has already been added, skip
				if(stTran.getLPNTran().time == stateTime){
					continue;
				}

				stTran.getLPNTran().time = stateTime;
				hash = hash*prime + stTran.getLPNTran().hashCode(); 
			}
			
			List<CompositeState> stateList = outgoingHashValueMap.get(hash);
			if(stateList == null){
				stateList = new ArrayList<CompositeState>();
				stateList.add(currentState);
				outgoingHashValueMap.put(hash, stateList);
			}
			else{
				stateList.add(currentState);
			}
			
			stateTime++;
		}
		
		/*
		 * Check if two or more states have the same value.  
		 * If so, make sure that their enabled sets are equivalent and not just a collision.
		 * Create pairs if equivalent and add to equivalentSet.
		 */
		for(List<CompositeState> stateList : outgoingHashValueMap.values()){
			if(stateList.size() < 2){
				continue;
			}
			
			for(int i = 0; i < stateList.size(); i++){
				CompositeState state = stateList.get(i);
				
				for(int j = i+1; j < stateList.size(); j++){
					CompositeState state1 = state;
					CompositeState state2 = stateList.get(j);
					
					// make sure the two states have equivalent enabled transition sets
					if(!equivalentEnabledSet(state1, state2)){
						continue;
					}
					
					// The state on the left of the pair is always the state with the smallest index
					if(state2.getIndex() < state1.getIndex()){
						CompositeState temp = state1;
						state1 = state2;
						state2 = temp;
					}
					
					List<CompositeState> equivList = equivalentSet.get(state1);
					if(equivList == null){
						equivList = new LinkedList<CompositeState>();
						equivList.add(state2);
						equivalentSet.put(state1, equivList);
					}
					else{
						equivList.add(state2);
					}
				}
			}
		}
		
		return equivalentSet;
	}
	
	/**
	 * Checks if the two specified states contain equivalent incoming state transition sets.
	 * @return TRUE if the specified states contain equivalent incoming state transition sets, otherwise FALSE
	 */
	private static boolean equivalentIncomingTranSet(CompositeState state1, CompositeState state2){
		// get the number of incoming state transitions minus self loops
		int numIncoming2 = state2.numIncomingTrans();
		if(state2.containsLoop()){
			numIncoming2--;
		}
		
		// get the number of incoming state transitions minus self loops
		int numIncoming1 = state1.numIncomingTrans();
		if(state1.containsLoop()){
			numIncoming1--;
		}
		
		if(numIncoming1 != numIncoming2){
			return false;
		}
		
		boolean equiv = false;
		for(CompositeStateTran stTran1 : state1.getIncomingStateTranList()){
			LPNTran lpnTran = stTran1.getLPNTran();
			if(lpnTran == loopTran){
				continue;
			}
			
			equiv = false;
			for(CompositeStateTran stTran2 : state2.getIncomingStateTranList()){
				if(lpnTran != stTran2.getLPNTran()){
					continue;
				}
				else if(stTran1.getCurrentState() != stTran2.getCurrentState()){
					continue;
				}
				
				equiv = true;
				break;
			}
			
			if(equiv == false){
				break;
			}
		}
		
		if(!equiv){
			return false;
		}
		
		for(CompositeStateTran stTran1 : state2.getIncomingStateTranList()){
			LPNTran lpnTran = stTran1.getLPNTran();
			if(lpnTran == loopTran){
				continue;
			}
			
			equiv = false;
			for(CompositeStateTran stTran2 : state1.getIncomingStateTranList()){
				if(lpnTran != stTran2.getLPNTran())
					continue;
				else if(stTran1.getCurrentState() != stTran2.getCurrentState()){
					continue;
				}
				
				equiv = true;
				break;
			}
			
			if(equiv == false){
				break;
			}
		}
		
		return equiv;
	}
	
	/**
	 * Finds the set of state pairs containing equivalent incoming state transition sets for the specified state graph.
	 * @param sg - State graph to search
	 * @return Set of state pairs with equivalent incoming state transition sets
	 */
	private static HashSet<Pair<CompositeState, CompositeState>> findEquivalentPairs(CompositeStateGraph sg){
		// Holds the set of states with equivalent incoming state transition sets
		HashSet<Pair<CompositeState, CompositeState>> equivalentSet = new HashSet<Pair<CompositeState, CompositeState>>();
		
		// Holds the state set of the state graph
		CompositeState[] stateArray = sg.getStateSet().toArray(new CompositeState[sg.numStates()]);
		
		// Maps the hash value for a set of incoming state transitions to a list of states
		HashMap<Integer, List<CompositeState>> incomingHashValueMap = new HashMap<Integer, List<CompositeState>>();
		
		/*
		 * Comparator used to determine order for a state's incoming state transition set.
		 * The comparator orders state transitions by the the current state's index and then
		 * by the LPN transition
		 */
		Comparator<CompositeStateTran> incomingComparator = new Comparator<CompositeStateTran>(){ 
            public int compare(CompositeStateTran t1, CompositeStateTran t2) {
                if(t1.getCurrentState().getIndex() == t2.getCurrentState().getIndex()){
                	return t1.getLPNTran().getFullLabel().compareToIgnoreCase(t2.getLPNTran().getFullLabel());
                }
                else if(t1.getCurrentState().getIndex() < t2.getCurrentState().getIndex()){
                	return 1;
                }
                
                return -1;
            }
        };

        // prime number used to generate hash value
        int prime = 31;
		
		/*
		 * For each state generate a hash value it's incoming state transition set.
		 * Add the hash value and the state to incomingHashValueMap.
		 */
		for(int i = 0; i < stateArray.length; i++){
			CompositeState currentState = stateArray[i];
			if(currentState.getErrorCode() != StateError.NONE){
				continue;
			}
			
			/*
			 * Sort a state's incoming state transition set for consistent hash value generation
			 */
			Collections.sort(currentState.getIncomingStateTranList(), incomingComparator);
			
			int hash = 1;
			for(CompositeStateTran stTran : currentState.getIncomingStateTranList()){
				// don't include self loops
				if(stTran.getLPNTran() == loopTran){
					continue;
				}
				
				hash = hash*prime + stTran.getLPNTran().hashCode();
				hash = hash*prime + stTran.getCurrentState().hashCode(); 
			}
			
			List<CompositeState> stateList = incomingHashValueMap.get(hash);
			if(stateList == null){
				stateList = new ArrayList<CompositeState>();
				stateList.add(currentState);
				incomingHashValueMap.put(hash, stateList);
			}
			else{
				stateList.add(currentState);
			}
		}
		
		/*
		 * For each hash value check if two or more states contain the same value.
		 * If so, for each possible pair verify that their incoming state transition sets are equivalent.
		 * If they are equivalent add the pair to equivalentSet.
		 */
		for(List<CompositeState> stateList : incomingHashValueMap.values()){
			if(stateList.size() < 2){
				continue;
			}
			
			for(int i = 0; i < stateList.size(); i++){
				CompositeState state = stateList.get(i);
				
				for(int j = i+1; j < stateList.size(); j++){
					CompositeState state1 = state;
					CompositeState state2 = stateList.get(j);
					
					// verify incoming state transition sets are equivalent
					if(!equivalentIncomingTranSet(state1, state2)){
						continue;
					}
					
					// the state with the smallest index is always stored on the left in the pair
					if(state2.getIndex() < state1.getIndex()){
						CompositeState temp = state1;
						state1 = state2;
						state2 = temp;
					}
					
					equivalentSet.add(new Pair<CompositeState, CompositeState>(state1, state2));
				}
			}
		}
		
		return equivalentSet;
	}
}
