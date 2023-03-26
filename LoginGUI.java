package ca3_GUI_Project;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

// class for the login page
@SuppressWarnings("serial")
public class LoginGUI extends GeneralGUI
{
	public JTextField emailLoginField;
	private JPasswordField passwordLoginField;
	
	// constructor
	public LoginGUI()
	{
		super("Login", "");
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		// sets up the two main panels and adds them to the root
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;
		add(setUpInputPanel(), gridBagConstraints);
		
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.top = 10;
		gridBagConstraints.insets.bottom = 20;
		add(setUpButtonsPanel(), gridBagConstraints);
	}
	
	// sets up the top panel and components for the input
	private JPanel setUpInputPanel()
	{
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins

		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel emailLoginLabel = new JLabel("Email");
		gridBagConstraints.insets.bottom = 5;
		inputPanel.add(emailLoginLabel, gridBagConstraints);
		
		emailLoginField = new JTextField();
		emailLoginField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 10;
		inputPanel.add(emailLoginField, gridBagConstraints);
		
		
		JLabel passwordLoginLabel = new JLabel("Password");
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets.bottom = 5;
		inputPanel.add(passwordLoginLabel, gridBagConstraints);
		
		passwordLoginField = new JPasswordField();
		passwordLoginField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 10;
		inputPanel.add(passwordLoginField, gridBagConstraints);
		
		return inputPanel;
	}
	
	// sets up the bottom panel and components for the buttons
	private JPanel setUpButtonsPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Sign Up";
			}
		});
		buttonsPanel.add(signUpButton, gridBagConstraints);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (checkLogin())
				{
					if (emailLoginField.getText().equals("admin"))
					{
						commandString = "Admin Products";
					}
					else
					{
						commandString = "New Order";
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Email or Password Incorrect!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gridBagConstraints.gridx = 1;
		gridBagConstraints.insets.left = 20;
		buttonsPanel.add(loginButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// check if the input data matches a row in the database
	private boolean checkLogin()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT CustomerID FROM customers WHERE Email = ? AND Password = ?");
	    	stmt.setString(1, emailLoginField.getText());
			stmt.setString(2, new String(passwordLoginField.getPassword()));

	    	ResultSet result = stmt.executeQuery();
	    	if (!result.next())
	    	{
				result.close();
	    		return false;
	    	}
			else
			{
				result.close();
	    		return true;
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
