package ca3_GUI_Project;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

// class for the login page
@SuppressWarnings("serial")
public class SignUpGUI extends GeneralGUI
{
	// constructor
	public SignUpGUI(String title)
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
		gridBagConstraints.gridy = 4;
		add(setUpContactPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 5;
		add(setUpAddressPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.bottom = 20;
		add(setUpButtonPanel(), gridBagConstraints);
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
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel forenameLabel = new JLabel("Forename*");
		namePanel.add(forenameLabel, gridBagConstraints);
		
		JTextField foreNameField = new JTextField();
		foreNameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		namePanel.add(foreNameField, gridBagConstraints);
		
		JLabel surnameLabel = new JLabel("Surname*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
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
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel passwordLabel = new JLabel("Password*");
		passwordPanel.add(passwordLabel, gridBagConstraints);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		passwordPanel.add(passwordField, gridBagConstraints);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
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
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel emailLabel = new JLabel("Email*");
		emailPanel.add(emailLabel, gridBagConstraints);
		
		JTextField emailField = new JTextField();
		emailField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		emailPanel.add(emailField, gridBagConstraints);
		
		JLabel confirmEmailLabel = new JLabel("Confirm Email*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
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
		
		JLabel phoneNumberLabel = new JLabel("Phone Number");
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
		
		JLabel phoneTypeLabel = new JLabel("Phone Type");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
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
	
	// set up the panel for the contact check boxes
	private JPanel setUpContactPanel()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		JPanel contactCheckBoxesPanel = new JPanel();
		contactCheckBoxesPanel.setLayout(new GridBagLayout());
		
		JLabel contactLabel = new JLabel("Contact Preference*");
		contactCheckBoxesPanel.add(contactLabel, gridBagConstraints);
		
		JCheckBox emailContactCheckBox = new JCheckBox("Email");
		contactCheckBoxesPanel.add(emailContactCheckBox, gridBagConstraints);
		
		JCheckBox phoneContactCheckBox = new JCheckBox("Phone");
		contactCheckBoxesPanel.add(phoneContactCheckBox, gridBagConstraints);
		
		return contactCheckBoxesPanel;
	}

	// set up the panel for the address
	private JPanel setUpAddressPanel()
	{
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel addressLabel = new JLabel("Address");
		addressPanel.add(addressLabel, gridBagConstraints);
		
		JLabel addressNameLabel = new JLabel("Name/Number*");
		gridBagConstraints.gridy = 1;
		addressPanel.add(addressNameLabel, gridBagConstraints);
		
		JTextField addressNameField = new JTextField();
		addressNameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 2;
		addressPanel.add(addressNameField, gridBagConstraints);

		JLabel addressCountryLabel = new JLabel("Country*");
		gridBagConstraints.gridy = 3;
		addressPanel.add(addressCountryLabel, gridBagConstraints);
		
		String[] countriesArray = {"", "Great Britain", "Ireland", "Northern Ireland", "United States of America"};
		JComboBox<String> addCountryComboBox = new JComboBox<String>(countriesArray);
		gridBagConstraints.gridy = 4;
		addressPanel.add(addCountryComboBox, gridBagConstraints);
		
		JLabel addressStreetLabel = new JLabel("Street*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.left = 10;
		addressPanel.add(addressStreetLabel, gridBagConstraints);
		
		JTextField addressStreetField = new JTextField();
		addressStreetField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 2;
		addressPanel.add(addressStreetField, gridBagConstraints);
		
		JLabel addressCountyLabel = new JLabel("County*");
		gridBagConstraints.gridy = 3;
		addressPanel.add(addressCountyLabel, gridBagConstraints);
		
		String[] countiesArray = {"", "Antrim", "Armagh", "Carlow", "Cavan", "Clare", "Cork", "Donegal", "Down", "Dublin", "Fermanagh", "Galway", "Kerry", "Kildare", "Kilkenny", "Laois", "Leitrim", "Limerick", "Londonderry", "Longford", "Louth", "Mayo", "Meath", "Monaghan", "Offaly", "Roscommon", "Sligo", "Tiggerary", "Tyrone", "Waterford", "Westmeath", "Wexford", "Wicklow"};
		JComboBox<String> addressCountyComboBox = new JComboBox<String>(countiesArray);
		addressCountyComboBox.setEnabled(false);
		gridBagConstraints.gridy = 4;
		addressPanel.add(addressCountyComboBox, gridBagConstraints);
		
		JSpinner dublinPostalCodeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
		dublinPostalCodeSpinner.setEnabled(false);
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		addressPanel.add(dublinPostalCodeSpinner, gridBagConstraints);
		
		return addressPanel;
	}
	
	// set up the panel for the buttons
	private JPanel setUpButtonPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{ 
				commandString = "CancelSignUp";
			} 
		});
		buttonsPanel.add(cancelButton, gridBagConstraints);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{ 
				commandString = "SignUp";
			} 
		});
		gridBagConstraints.gridx = 1;
		gridBagConstraints.insets.left = 60;
		buttonsPanel.add(signUpButton, gridBagConstraints);
		
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
