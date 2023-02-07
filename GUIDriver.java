package ca3_GUI_Project;

import javax.swing.JFrame;
import java.lang.Thread;

public class GUIDriver
{
	private static LoginGUI loginGUI;
	private static SignUpGUI signUpGUI;
	
	public static void main(String[] args)
    {
		renderLoginGUI();
		renderSignUpGUI();
		loginGUI.setVisible(true);
		while (true)
		{
			try
			{
				System.out.println(loginGUI.commandInt);
				if (loginGUI.commandInt == 1)
				{
					loginGUI.setVisible(false);
					signUpGUI.setVisible(true);
				}
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
		loginGUI.setVisible(false);
	}

	public static void renderSignUpGUI()
	{
		signUpGUI = new SignUpGUI("Sign Up");
		signUpGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUpGUI.pack();
		signUpGUI.setResizable(false);
		signUpGUI.setLocation(500,400);
		signUpGUI.setVisible(false);
	}
}
