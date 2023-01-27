package ca3_GUI_Project;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

// class for the login page
public class LoginGUI extends JFrame
{
	// constructor
	public LoginGUI(String title)
	{
		super(title);
		getContentPane().setLayout(new GridBagLayout());
		
		// sets up the two main panels and adds them to the root
		add(setUpInputPanel());
	}
	
	// sets up the top panel and components for the input
	private JPanel setUpInputPanel()
	{
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstrains.insets is used for margins
		
		JLabel emailLoginLabel = new JLabel("Email:");
		gridBagConstraints.insets.top = 5;
		gridBagConstraints.insets.bottom = 5;
		inputPanel.add(emailLoginLabel, gridBagConstraints);
		
		JTextField emailLoginField = new JTextField();
		DimensionUIResource preferredSize = new DimensionUIResource(150, 25);
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
	private void setUpButtons()
	{
		
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
