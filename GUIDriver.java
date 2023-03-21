package ca3_GUI_Project;

import javax.swing.JFrame;
import java.lang.Thread;

public class GUIDriver
{
	private static GeneralGUI currentGUI;
	
	public static void main(String[] args)
    {
		// initialise login screen
		initialiseNewGUI("Login");
		while (true)
		{
			try
			{
				// login commands
				if (currentGUI.commandString.equals("OpenSignUp"))
				{
					// open up sign up screen
					initialiseNewGUI("Sign Up");
				}
				else if (currentGUI.commandString.equals("Login"))
				{
					// login
				}
				// sign up commands
				else if (currentGUI.commandString.equals("CancelSignUp"))
				{
					// cancel sign up
					initialiseNewGUI("Login");
				}
				else if (currentGUI.commandString.equals("SignUp"))
				{
					// sign up and update customer table
					initialiseNewGUI("Login");
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
	private static void initialiseNewGUI(String GUIName)
	{
		GeneralGUI tempGUI = null;
		if (GUIName.equals("Login"))
		{
			tempGUI = new LoginGUI(GUIName);
		}
		else if (GUIName.equals("Sign Up"))
		{
			tempGUI = new SignUpGUI("Sign Up");
		}
		else
		{
			// error in gui name
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
}
