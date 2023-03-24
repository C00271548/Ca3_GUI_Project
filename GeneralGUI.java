package ca3_GUI_Project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

// parent class for all gui classes
@SuppressWarnings("serial")
public class GeneralGUI extends JFrame
{
	// gives command to the GUIDriver class
	public String commandString = "";
	
	// database connection
	Connection conn = null;
	
	public GeneralGUI(String title)
	{
		super(title);
		addWindowListener(new WindowCloser());
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project_database","root", "");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
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
    public static void print(Object x)
    {
    	System.out.println(x);
    }
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
	
	// called before getting garbage collected
	protected void finalize()
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
