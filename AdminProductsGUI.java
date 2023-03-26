package ca3_GUI_Project;

//class for the admin products page
@SuppressWarnings("serial")
public class AdminProductsGUI extends GeneralGUI
{

	// constructor
	public AdminProductsGUI(String email)
	{
		super("Admin Products", email);
		commandString = "Admin Orders";
	}
}
