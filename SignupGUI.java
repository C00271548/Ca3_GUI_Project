package ca3_GUI_Project;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

// class for the sign up page
@SuppressWarnings("serial")
public class SignUpGUI extends GeneralGUI
{
	// gui component variables
	private JTextField forenameField;
	private JTextField surnameField;
	
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	
	private JTextField emailField;
	private JTextField confirmEmailField;
	
	private JFormattedTextField phoneNumberField;
	private JRadioButton landlineButton;
	private JRadioButton mobilePhoneButton;
	
	private JCheckBox emailContactCheckBox;
	private JCheckBox phoneContactCheckBox;
	
	private JTextField addressNameField;
	private JComboBox<String> addressCountryComboBox;
	private JTextField addressStreetField;
	private JComboBox<String> addressCountyComboBox;
	private JSpinner dublinPostalCodeSpinner;
	
	// constructor
	public SignUpGUI()
	{
		super("Sign Up", "");
		
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
		add(setUpButtonsPanel(), gridBagConstraints);
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
		gridBagConstraints.insets.bottom = 5;
		namePanel.add(forenameLabel, gridBagConstraints);
		
		forenameField = new JTextField();
		forenameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		namePanel.add(forenameField, gridBagConstraints);
		
		JLabel surnameLabel = new JLabel("Surname*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.bottom = 5;
		namePanel.add(surnameLabel, gridBagConstraints);
		
		surnameField = new JTextField();
		surnameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
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
		gridBagConstraints.insets.bottom = 5;
		passwordPanel.add(passwordLabel, gridBagConstraints);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		passwordPanel.add(passwordField, gridBagConstraints);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.bottom = 5;
		passwordPanel.add(confirmPasswordLabel, gridBagConstraints);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
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
		gridBagConstraints.insets.bottom = 5;
		emailPanel.add(emailLabel, gridBagConstraints);
		
		emailField = new JTextField();
		emailField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		emailPanel.add(emailField, gridBagConstraints);
		
		JLabel confirmEmailLabel = new JLabel("Confirm Email*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.bottom = 5;
		emailPanel.add(confirmEmailLabel, gridBagConstraints);
		
		confirmEmailField = new JTextField();
		confirmEmailField.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
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
		gridBagConstraints.insets.bottom = 5;
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
			JOptionPane.showMessageDialog(null, e, "Format Error", JOptionPane.ERROR_MESSAGE);
		}
		phoneNumberField = new JFormattedTextField(maskFormatter);
		phoneNumberField.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent event)
			{
				checkInput();
			}
			public void removeUpdate(DocumentEvent event)
			{
				checkInput();
			}
			public void changedUpdate(DocumentEvent event)
			{
				checkInput();
			}
			private void checkInput()
			{
				if (checkStringIsDouble(phoneNumberField.getText().replace("-", "")) || phoneNumberField.getText().equals(""))
				{
					landlineButton.setEnabled(true);
					mobilePhoneButton.setEnabled(true);
					phoneContactCheckBox.setEnabled(true);
				}
				else
				{
					landlineButton.setEnabled(false);
					mobilePhoneButton.setEnabled(false);
					phoneContactCheckBox.setSelected(false);
					phoneContactCheckBox.setEnabled(false);
				}
			}
		});
		phoneNumberField.setHorizontalAlignment(JTextField.CENTER);
		phoneNumberField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		phonePanel.add(phoneNumberField, gridBagConstraints);
		
		JLabel phoneTypeLabel = new JLabel("Phone Type");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.bottom = 5;
		phonePanel.add(phoneTypeLabel, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		phonePanel.add(setUpPhoneRadioButtonPanel(), gridBagConstraints);
		
		return phonePanel;
	}
	
	// set up the panel for the phone radio button
	private JPanel setUpPhoneRadioButtonPanel()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		JPanel phoneRadioButtonPanel = new JPanel();
		phoneRadioButtonPanel.setLayout(new GridBagLayout());
		
		landlineButton = new JRadioButton("Landline");
		landlineButton.setSelected(true);
		landlineButton.setEnabled(false);
		phoneRadioButtonPanel.add(landlineButton, gridBagConstraints);
		
		mobilePhoneButton = new JRadioButton("Mobile Phone");
		mobilePhoneButton.setEnabled(false);
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
		
		emailContactCheckBox = new JCheckBox("Email");
		contactCheckBoxesPanel.add(emailContactCheckBox, gridBagConstraints);
		
		phoneContactCheckBox = new JCheckBox("Phone");
		phoneContactCheckBox.setEnabled(false);
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
		gridBagConstraints.insets.bottom = 5;
		addressPanel.add(addressLabel, gridBagConstraints);
		
		JLabel addressNameLabel = new JLabel("Name/Number*");
		gridBagConstraints.gridy = 1;
		addressPanel.add(addressNameLabel, gridBagConstraints);
		
		addressNameField = new JTextField();
		addressNameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets.bottom = 0;
		addressPanel.add(addressNameField, gridBagConstraints);

		JLabel addressCountryLabel = new JLabel("Country*");
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 5;
		addressPanel.add(addressCountryLabel, gridBagConstraints);
		
		String[] countriesArray = {"", "Great Britain", "Ireland", "Northern Ireland", "United States of America"};
		addressCountryComboBox = new JComboBox<String>(countriesArray);
		addressCountryComboBox.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{
				// checks if Ireland is selected
				if (((JComboBox<String>) e.getSource()).getSelectedItem().equals("Ireland"))
				{
					addressCountyComboBox.setEnabled(true);
				}
				else
				{
					addressCountyComboBox.setEnabled(false);
					addressCountyComboBox.setSelectedItem("");
				}
			}
		});
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets.bottom = 0;
		addressPanel.add(addressCountryComboBox, gridBagConstraints);
		
		JLabel addressStreetLabel = new JLabel("Street*");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.bottom = 5;
		addressPanel.add(addressStreetLabel, gridBagConstraints);
		
		addressStreetField = new JTextField();
		addressStreetField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets.bottom = 0;
		addressPanel.add(addressStreetField, gridBagConstraints);
		
		JLabel addressCountyLabel = new JLabel("Irish County*");
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 5;
		addressPanel.add(addressCountyLabel, gridBagConstraints);
		
		String[] countiesArray = {"", "Antrim", "Armagh", "Carlow", "Cavan", "Clare", "Cork", "Donegal", "Down", "Dublin", "Fermanagh", "Galway", "Kerry", "Kildare", "Kilkenny", "Laois", "Leitrim", "Limerick", "Londonderry", "Longford", "Louth", "Mayo", "Meath", "Monaghan", "Offaly", "Roscommon", "Sligo", "Tiggerary", "Tyrone", "Waterford", "Westmeath", "Wexford", "Wicklow"};
		addressCountyComboBox = new JComboBox<String>(countiesArray);
		addressCountyComboBox.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{
				// checks if Dublin is selected
				if (((JComboBox<String>) e.getSource()).getSelectedItem().equals("Dublin"))
				{
					dublinPostalCodeSpinner.setEnabled(true);
				}
				else
				{
					dublinPostalCodeSpinner.setEnabled(false);
					dublinPostalCodeSpinner.setValue(1);
				}
			}
		});
		addressCountyComboBox.setEnabled(false);
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets.bottom = 0;
		addressPanel.add(addressCountyComboBox, gridBagConstraints);
		
		dublinPostalCodeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
		dublinPostalCodeSpinner.setEnabled(false);
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		addressPanel.add(dublinPostalCodeSpinner, gridBagConstraints);
		
		return addressPanel;
	}
	
	// set up the panel for the buttons
	private JPanel setUpButtonsPanel()
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
				commandString = "Login";
			}
		});
		buttonsPanel.add(cancelButton, gridBagConstraints);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (checkAllInputsCorrect())
				{
					addCustomer();
					commandString = "Login";
				}
			}
		});
		gridBagConstraints.gridx = 1;
		gridBagConstraints.insets.left = 60;
		buttonsPanel.add(signUpButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// checks all of the inputs
	private boolean checkAllInputsCorrect()
	{
		// checks if all of the required fields have been input
		if (forenameField.getText().length() == 0 ||
			surnameField.getText().length() == 0 ||
			passwordField.getPassword().length == 0 ||
			emailField.getText().length() == 0 ||
			!(emailContactCheckBox.isSelected() || phoneContactCheckBox.isSelected()) ||
			addressNameField.getText().length() == 0 ||
			addressCountryComboBox.getSelectedItem().equals("") ||
			addressStreetField.getText().length() == 0 ||
			(addressCountryComboBox.getSelectedItem().equals("Ireland") && addressCountyComboBox.getSelectedItem().equals("")))
		{
			JOptionPane.showMessageDialog(null, "Input all required* fields!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// checks if passwords match
		if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword()))
		{
			JOptionPane.showMessageDialog(null, "Passwords do not match!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// checks if emails match
		if (!emailField.getText().equals(confirmEmailField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Emails do not match!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// checks if emails correct format match
		if (!emailValidityCheck(emailField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Email format incorrect!\nFormat: a@b.c", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// checks if the email is already in use
		if (emailUsed())
		{
			JOptionPane.showMessageDialog(null, "Email already used!", "Duplicate", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// checks if the phone number is in correct format
		if (!phoneNumberField.getText().equals("XXX-XXX-XXXX"))
		{
			if (!checkStringIsDouble(phoneNumberField.getText().replace("-", "")))
			{
				JOptionPane.showMessageDialog(null, "Phone number is not a number!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		return true;
	}
	
	private boolean emailValidityCheck(String emailString)
	{
		String[] emailArrayString = emailString.split("@");
		// checks for @
		if (emailArrayString.length != 2)
		{
			return false;
		}
		// checks for characters before @
		if (emailArrayString[0].equals(""))
		{
			return false;
		}
		emailArrayString = emailArrayString[1].split("\\.");
		// checks for .
		if (emailArrayString.length != 2)
		{
			return false;
		}
		// checks for characters before .
		if (emailArrayString[0].equals(""))
		{
			return false;
		}
		// checks for characters after .
		if (emailArrayString[1].equals(""))
		{
			return false;
		}
		return true;
	}
	
	private boolean emailUsed()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT CustomerID FROM customers WHERE Email = ?");
	    	stmt.setString(1, emailField.getText());

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
			return true;
		}
	}
	
	// adds a new customer into the database
	private void addCustomer()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("""
															  INSERT INTO customers (Forename, Surname, Email, PhoneNumber,
																				     PhoneNumberType, ContactType, Password, AddressName,
																				     AddressStreet, AddressCounty, AddressCountry)
															  VALUES (?, ?, ?, ?,
															  		  ?, ?, ?, ?,
																	  ?, ?, ?)""");
			
	    	stmt.setString(1, forenameField.getText());
	    	stmt.setString(2, surnameField.getText());
	    	stmt.setString(3, emailField.getText());
			stmt.setString(4, phoneNumberField.getText());
			if (landlineButton.isSelected())
			{
				stmt.setString(5, "Landline");
			}
			else
			{
				stmt.setString(5, "Mobile");
			}
			if (emailContactCheckBox.isSelected())
			{
				if (phoneContactCheckBox.isSelected())
				{
					stmt.setString(6, "Both");
				}
				else
				{
					stmt.setString(6, "Email");
				}
			}
			else if (phoneContactCheckBox.isSelected())
			{
				stmt.setString(6, "Phone");
			}
			stmt.setString(7, new String(passwordField.getPassword()));
	    	stmt.setString(8, addressNameField.getText());
	    	stmt.setString(9, addressStreetField.getText());
			String county =  (String) addressCountyComboBox.getSelectedItem();
			if (county.equals("Dublin"))
			{
				stmt.setString(10, county + " " + dublinPostalCodeSpinner.getValue());
			}
			else
			{
				stmt.setString(10, county);
			}
	    	stmt.setString(11, (String) addressCountryComboBox.getSelectedItem());

	    	stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
