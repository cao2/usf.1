package platu.gui;

import java.util.EventObject;

import platu.gui.Console.MessageType;

//Generated by Console to inform ConsoleListeners that an event has occurred
public class ConsoleEvent extends EventObject 
{
	private MessageType myType;
	
	public ConsoleEvent(Object source, Console.MessageType ev)
	{
		super(source);
		myType = ev;
	}
	
	public MessageType getEventType()
	{
		return myType;
	}
}
