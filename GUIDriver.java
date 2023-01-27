package ca3_GUI_Project;

import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;


public class GUIDriver
{
	public static void main(String[] args)
    {
		LoginGUI  loginGUI = new LoginGUI("Login");
		loginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginGUI.setSize(250, 200);
		loginGUI.setLocation(500,400);
		loginGUI.setVisible(true);
		while (true)
		{
			System.out.println(loginGUI.getSize().width + " " + loginGUI.getSize().height);
			try
			{
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
