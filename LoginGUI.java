package ca3_GUI_Project;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

// class for the login page
@SuppressWarnings("serial")
public class LoginGUI extends GeneralGUI
{
	// constructor
	public LoginGUI(String title)
	{
		super(title);
		
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
		add(setUpButtonPanel(), gridBagConstraints);
		
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
		
		JLabel emailLoginLabel = new JLabel("Email:");
		gridBagConstraints.insets.top = 5;
		gridBagConstraints.insets.bottom = 5;
		inputPanel.add(emailLoginLabel, gridBagConstraints);
		
		JTextField emailLoginField = new JTextField();
		emailLoginField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.top = 0;
		gridBagConstraints.insets.bottom = 10;
		inputPanel.add(emailLoginField, gridBagConstraints);
		
		
		JLabel passwordLoginLabel = new JLabel("Password:");
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets.bottom = 5;
		inputPanel.add(passwordLoginLabel, gridBagConstraints);
		
		JTextField passwordLoginField = new JPasswordField();
		passwordLoginField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 10;
		inputPanel.add(passwordLoginField, gridBagConstraints);
		
		return inputPanel;
	}
	
	// sets up the bottom panel and components for the buttons
	private JPanel setUpButtonPanel()
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
				commandString = "OpenSignUp";
			} 
		});
		buttonsPanel.add(signUpButton, gridBagConstraints);
		
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{ 
				commandString = "Login";
			} 
		});
		gridBagConstraints.gridx = 1;
		gridBagConstraints.insets.left = 20;
		buttonsPanel.add(loginButton, gridBagConstraints);
		
		return buttonsPanel;
	}

	// listener for window closing
	class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent evt)
		{
			System.exit(0);
		}
	}
}
