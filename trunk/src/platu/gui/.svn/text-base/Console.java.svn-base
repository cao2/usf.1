package platu.gui;

import java.util.ArrayList;
import java.util.HashMap;
import platu.main.Options;


//The Console class transfers information from one class to another
public class Console 
{
	//This hashmap associates event types with specific data
	private static HashMap<MessageType, ArrayList<Object>> informationList = new HashMap<MessageType, ArrayList<Object>>();
	
	//Create list of listeners
	protected static javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
	
	public enum MessageType {staticInfo, dynamicInfo, errorInfo, finalInfo};
	
	//Adds an object to the hashmap, and associates it with a specific event type
	public static void print(String O, Console.MessageType ev, int verbosity)
	{
		if (Options.getVerbosity() < verbosity)	return;
		
		if(FrontEnd.GUILoaded)
		{
			try
			{
				informationList.get(ev).add(O);	
			}
			
			catch(NullPointerException ex)
			{
				informationList.put(ev, new ArrayList<Object>());
				informationList.get(ev).add(O);
			}
			
			fireConsoleEvents(new ConsoleEvent(Console.class, ev));
		}
		
		else
		{
			System.out.println(O);
		}
	}
	
	//Pulls the oldest object for a specific event type from the Hashmap, and removes it
	public static Object getInformation(MessageType ev)
	{
		Object data;
		try
		{
			data = informationList.get(ev).get(0);
			informationList.get(ev).remove(0);
		}
		catch(IndexOutOfBoundsException ex)
		{
			return null;
		}
		
		return data;
	}
	
	//Allows classes to register for console events
	public static void addConsoleEventListener(ConsoleListener listener)
	{
		listenerList.add(ConsoleListener.class, listener);
	}
	
	//Allows classes to unregister for ConsoleEvents
	public static void removeConsoleListener(ConsoleListener listener)
	{
		listenerList.remove(ConsoleListener.class, listener);
	}
	
	//Private method informs all listeners that an event has been added
	private static void fireConsoleEvents(ConsoleEvent evt)
	{
		Object[] listeners = listenerList.getListenerList();
		
		//Each listener occupies two elements
		//First is the class of the listener
		//Second is the listener instance
		
		for(int i = 0; i < listeners.length; i+=2)
		{
			if(listeners[i] == ConsoleListener.class)
			{
				((ConsoleListener) listeners[i+1]).ConsoleEventOccurred(evt);
			}
		}
	}

}
