package ca3_GUI_Project;

import javax.swing.JFrame;


public class GUIDriver
{
	public static void main(String[] args)
    {
		SignupGUI loginGUI = new SignupGUI("SignUp");
		loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginGUI.pack();
		loginGUI.setResizable(false);
		loginGUI.setLocation(500,400);
		loginGUI.setVisible(true);
	}
}
