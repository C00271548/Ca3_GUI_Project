package ca3_GUI_Project;

import javax.swing.JFrame;

import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.lang.Thread;

// main driver class
public class GUIDriver
{
	private static GeneralGUI currentGUI;
	private static String emailLoggedIn = null;
	private static String[][] orderProductsData = null;
	
	public static void main(String[] args)
    {
		// initialise login screen
		currentGUI = new GeneralGUI("Temp", "");
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
			emailLoggedIn = "";
		}
		else if (currentGUI.commandString.equals("Sign Up"))
		{
			tempGUI = new SignUpGUI();
		}
		else if (currentGUI.commandString.equals("Admin Products"))
		{
			tempGUI = new AdminProductsGUI(emailLoggedIn);
			if (currentGUI instanceof LoginGUI)
			{
				emailLoggedIn = ((LoginGUI) currentGUI).emailLoginField.getText();
			}
		}
		else if (currentGUI.commandString.equals("Customer New Order"))
		{
			tempGUI = new CustomerNewOrderGUI(emailLoggedIn, orderProductsData);
			if (currentGUI instanceof LoginGUI)
			{
				emailLoggedIn = ((LoginGUI) currentGUI).emailLoginField.getText();
			}
		}
		else if (currentGUI.commandString.equals("Admin Orders"))
		{
			tempGUI = new AdminOrdersGUI(emailLoggedIn);
		}
		else if (currentGUI.commandString.equals("Customer Orders"))
		{
			tempGUI = new CustomerOrdersGUI(emailLoggedIn);
		}
		else if (currentGUI.commandString.equals("Admin Invoices"))
		{
			tempGUI = new AdminInvoicesGUI(emailLoggedIn);
		}
		else if (currentGUI.commandString.equals("Customer Invoices"))
		{
			tempGUI = new CustomerInvoicesGUI(emailLoggedIn);
		}
		else if (currentGUI.commandString.equals("Admin Add New Product"))
		{
			tempGUI = new AdminAddUpdateProductGUI(emailLoggedIn);
		}
		else if (currentGUI.commandString.equals("Admin Update Product"))
		{
			tempGUI = new AdminAddUpdateProductGUI(emailLoggedIn, ((AdminProductsGUI) currentGUI).productID);
		}
		else if (currentGUI.commandString.equals("Customer Add Product"))
		{
			tempGUI = new CustomerAddProductGUI(emailLoggedIn, orderProductsData);
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
		
		Point location;
		try
		{
			location = currentGUI.getLocationOnScreen();
		}
		catch (IllegalComponentStateException e)
		{
			location = new Point(0, 0);
		}
		Double middleX = location.x + (currentGUI.getSize().width / 2.0);
		int newX = (int) (middleX - (tempGUI.getSize().width / 2.0));
		
		if (newX < 0)
		{
			newX = 0;
		}
		
		tempGUI.setLocation(newX, location.y);
		tempGUI.setVisible(true);
		
		// close old gui
		if (currentGUI != null)
		{
			if (currentGUI instanceof CustomerNewOrderGUI)
			{
				orderProductsData = ((CustomerNewOrderGUI) currentGUI).orderProductsData;
			}
			else if (currentGUI instanceof CustomerAddProductGUI)
			{
				orderProductsData = ((CustomerAddProductGUI) currentGUI).orderProductsData;
			}
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
