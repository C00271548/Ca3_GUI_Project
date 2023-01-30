package ca3_GUI_Project;

import javax.swing.JFrame;
import java.lang.Thread;

public class GUIDriver
{
	private static LoginGUI loginGUI;
	
	public static void main(String[] args)
    {
		renderLoginGUI();
		while (true)
		{
			try
			{
				System.out.println(loginGUI.commandInt);
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void renderLoginGUI()
	{
		loginGUI = new LoginGUI("Login");
		loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginGUI.pack();
		loginGUI.setResizable(false);
		loginGUI.setLocation(500,400);
		loginGUI.setVisible(true);
	}

	public static void renderSignUpGUI()
	{
		SignUpGUI signUpGUI = new SignUpGUI("Sign Up");
		signUpGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUpGUI.pack();
		signUpGUI.setResizable(false);
		signUpGUI.setLocation(500,400);
		signUpGUI.setVisible(true);
	}
}
