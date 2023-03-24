package ca3_GUI_Project;

import javax.swing.JFrame;
import java.lang.Thread;

// main driver class
public class GUIDriver
{
	private static GeneralGUI currentGUI;
	
	public static void main(String[] args)
    {
		// initialise login screen
		currentGUI  = new GeneralGUI("Temp");
		currentGUI.commandString = "Login";
		initialiseNewGUI();
		while (true)
		{
			try
			{
				// checks if a new gui is set
				if (!currentGUI.commandString.equals(""))
				{
					initialiseNewGUI();
				}
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// make new gui screen
	private static void initialiseNewGUI()
	{
		GeneralGUI tempGUI = null;
		if (currentGUI.commandString.equals("Login"))
		{
			tempGUI = new LoginGUI();
		}
		else if (currentGUI.commandString.equals("Sign Up"))
		{
			tempGUI = new SignUpGUI();
		}
		else if (currentGUI.commandString.equals("Admin Products"))
		{
			tempGUI = new AdminProductsGUI();
		}
		else if (currentGUI.commandString.equals("New Order"))
		{
			tempGUI = new NewOrderGUI();
		}
		else if (tempGUI == null)
		{
			// error in gui name
			print("Error. Not found:");
			print(currentGUI.commandString);
			return;
		}
		
		tempGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tempGUI.pack();
		tempGUI.setResizable(false);
		tempGUI.setLocation(500,400);
		tempGUI.setVisible(true);
		
		// close old gui
		if (currentGUI != null)
		{
			currentGUI.dispose();
		}
		currentGUI = tempGUI;
	}
	
	// print
	private static void print(Object x)
    {
    	System.out.println(x);
    }
}
