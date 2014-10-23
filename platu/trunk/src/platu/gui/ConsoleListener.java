package platu.gui;

import java.util.EventListener;

import platu.gui.Console.MessageType;

//This class is used to signify what classes are able to handle console events
public interface ConsoleListener extends EventListener 
{
	public void ConsoleEventOccurred(ConsoleEvent evt);
}
