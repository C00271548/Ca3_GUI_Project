package ca3_GUI_Project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JFrame;

// parent class for all gui classes
@SuppressWarnings("serial")
public class GeneralGUI extends JFrame
{
	// gives command to the GUIDriver class
	public String commandString = "";
	
	public GeneralGUI(String title)
	{
		super(title);
		addWindowListener(new WindowCloser());
	}

	// listener for window closing
	class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
			System.exit(0);
		}
	}
	
	// print
    @SuppressWarnings("unused")
	public static void print(Object x)
    {
    	System.out.println(x);
    }
	@SuppressWarnings("unused")
	public static void print(String[] x)
    {
    	System.out.println(Arrays.toString(x));
    }
	
	// checks if string is a double
	public boolean checkStringIsDouble(String inString)
	{
		try
		{
			Double.parseDouble(inString);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
