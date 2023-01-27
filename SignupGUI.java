package ca3_GUI_Project;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.MaskFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

// class for the login page
@SuppressWarnings("serial")
public class SignupGUI extends JFrame
{
	// constructor
	public SignupGUI(String title)
	{
		super(title);
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;
		
		// set up the panels for the page and add them
		add(setUpNamePanel(), gridBagConstraints);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.top = 10;
		add(setUpPasswordPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 2;
		add(setUpEmailPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 3;
		add(setUpPhonePanel(), gridBagConstraints);
	}
	
	// set up the panel for the name
	private JPanel setUpNamePanel()
	{
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(150, 25);
		
		JLabel forenameLabel = new JLabel("Forename:");
		namePanel.add(forenameLabel, gridBagConstraints);
		
		JTextField foreNameField = new JTextField();
		foreNameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		namePanel.add(foreNameField, gridBagConstraints);
		
		JLabel surnameLabel = new JLabel("Surname:");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		namePanel.add(surnameLabel, gridBagConstraints);
		
		JTextField surnameField = new JTextField();
		surnameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		namePanel.add(surnameField, gridBagConstraints);
		
		return namePanel;
	}
	
	// set up the panel for the password
	private JPanel setUpPasswordPanel()
	{
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(150, 25);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordPanel.add(passwordLabel, gridBagConstraints);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		passwordPanel.add(passwordField, gridBagConstraints);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm:");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		passwordPanel.add(confirmPasswordLabel, gridBagConstraints);
		
		JPasswordField confirmPasswordField = new JPasswordField();
		confirmPasswordField.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		passwordPanel.add(confirmPasswordField, gridBagConstraints);
		
		return passwordPanel;
	}
	
	// set up the panel for the email
	private JPanel setUpEmailPanel()
	{
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(150, 25);
		
		JLabel emailLabel = new JLabel("Email:");
		emailPanel.add(emailLabel, gridBagConstraints);
		
		JTextField emailField = new JTextField();
		emailField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		emailPanel.add(emailField, gridBagConstraints);
		
		JLabel confirmEmailLabel = new JLabel("Confirm:");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		emailPanel.add(confirmEmailLabel, gridBagConstraints);
		
		JTextField confirmEmailField = new JTextField();
		confirmEmailField.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		emailPanel.add(confirmEmailField, gridBagConstraints);
		
		return emailPanel;
	}
	
	// set up the panel for the phone
	private JPanel setUpPhonePanel()
	{
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(100, 25);
		
		JLabel phoneNumberLabel = new JLabel("Phone Number:");
		phonePanel.add(phoneNumberLabel, gridBagConstraints);
		
		// formats the input according to the mask string
		MaskFormatter maskFormatter = null;
		try
		{
			maskFormatter = new MaskFormatter("###-###-####");
			maskFormatter.setPlaceholderCharacter('X');
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		JFormattedTextField phoneNumberField = new JFormattedTextField(maskFormatter);
		phoneNumberField.setHorizontalAlignment(JTextField.CENTER);
		phoneNumberField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		phonePanel.add(phoneNumberField, gridBagConstraints);
		
		JLabel phoneTypeLabel = new JLabel("Phone Type:");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		phonePanel.add(phoneTypeLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		phonePanel.add(setUpPhoneRadioButtonPanel(), gridBagConstraints);
		
		return phonePanel;
	}
	
	// set up the panel for the phone radio button
	private JPanel setUpPhoneRadioButtonPanel()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		JPanel phoneRadioButtonPanel = new JPanel();
		phoneRadioButtonPanel.setLayout(new GridBagLayout());
		
		JRadioButton landlineButton = new JRadioButton("Landline");
		phoneRadioButtonPanel.add(landlineButton, gridBagConstraints);
		
		JRadioButton mobilePhoneButton = new JRadioButton("Mobile Phone");
		gridBagConstraints.gridx = 1;
		phoneRadioButtonPanel.add(mobilePhoneButton, gridBagConstraints);
		
		ButtonGroup phoneButtonGroup = new ButtonGroup();
		phoneButtonGroup.add(landlineButton);
		phoneButtonGroup.add(mobilePhoneButton);
		
		return phoneRadioButtonPanel;
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
