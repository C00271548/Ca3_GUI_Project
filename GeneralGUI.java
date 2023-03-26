package ca3_GUI_Project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// parent class for all gui classes
@SuppressWarnings("serial")
public class GeneralGUI extends JFrame
{
	// gives command to the GUIDriver class
	public String commandString = "";
	
	// currently logged in email
	private String emailLoggedIn;
	protected int customerID;
	
	// database connection
	Connection conn = null;
	
	public GeneralGUI(String title, String email)
	{
		super(title);
		emailLoggedIn = email;
		
		addWindowListener(new WindowCloser());
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project_database","root", "");
			customerID = getCustomerID();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Cannot connect to database!", "Connection Error", JOptionPane.ERROR_MESSAGE);
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
    protected static void print(Object x)
    {
    	System.out.println(x);
    }
    protected static void print(String[] x)
    {
    	System.out.println(Arrays.toString(x));
    }
	
	// checks if string is a double
    protected boolean checkStringIsDouble(String inString)
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
	
	// checks if string is an integer
    protected boolean checkStringIsInteger(String inString)
	{
		try
		{
			Integer.parseInt(inString);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	// converts int to boolean
    protected boolean convertIntToBoolean(int number)
	{
		if (number == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    
    // converts int to boolean to String
    protected String convertIntToBooleanToString(int number)
    {
    	if (convertIntToBoolean(number))
    	{
    		return "Yes";
    	}
    	else
    	{
    		return "No";
    	}
    }
    
    // makes the top nav bar for the customer
    protected JPanel setUpTopCustomerNavBar()
	{
		JPanel navBarPanel = new JPanel();
		navBarPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JButton newOrderButton = new JButton("New Order");
		newOrderButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "New Order";
			}
		});
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		navBarPanel.add(newOrderButton, gridBagConstraints);
		
		JButton ordersButton = new JButton("Orders");
		ordersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Customer Orders";
			}
		});
		gridBagConstraints.insets.left = 5;
		navBarPanel.add(ordersButton, gridBagConstraints);
		
		JButton invoicesButton = new JButton("Invoices");
		invoicesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Customer Invoices";
			}
		});
		navBarPanel.add(invoicesButton, gridBagConstraints);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Login";
			}
		});
		gridBagConstraints.insets.right = 10;
		navBarPanel.add(logoutButton, gridBagConstraints);
		
		return navBarPanel;
    }
    
    // makes the top nav bar for the admin
    protected JPanel setUpTopAdminNavBar()
	{
		JPanel navBarPanel = new JPanel();
		navBarPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JButton adminProductsButton = new JButton("Admin Products");
		adminProductsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Admin Products";
			}
		});
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		navBarPanel.add(adminProductsButton, gridBagConstraints);
		
		JButton adminOrdersButton = new JButton("Admin Orders");
		adminOrdersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Admin Orders";
			}
		});
		gridBagConstraints.insets.left = 5;
		navBarPanel.add(adminOrdersButton, gridBagConstraints);
		
		JButton adminInvoicesButton = new JButton("Admin Invoices");
		adminInvoicesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Admin Invoices";
			}
		});
		navBarPanel.add(adminInvoicesButton, gridBagConstraints);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Login";
			}
		});
		gridBagConstraints.insets.right = 10;
		navBarPanel.add(logoutButton, gridBagConstraints);
		
		return navBarPanel;
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
			JOptionPane.showMessageDialog(null, e, "Connection Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// gets the customer id based on the email
	private int getCustomerID()
	{
		try
		{
	        // create a statement from the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT CustomerID FROM customers WHERE Email = ?");
	    	stmt.setString(1, emailLoggedIn);
			
	    	ResultSet result = stmt.executeQuery();
	    	
	    	if (!result.next())
	    	{
				result.close();
	    		return 0;
	    	}
			else
			{
				int id = result.getInt("CustomerID");
				result.close();
	    		return id;
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}
}
