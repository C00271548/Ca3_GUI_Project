package ca3_GUI_Project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

//class for the admin add new product page
@SuppressWarnings("serial")
public class AdminAddNewProductGUI extends GeneralGUI
{
	private JTextField nameField;
	private JTextArea descriptionTextArea;
	private JTextField stockAmountField;
	private JTextField sellingPriceField;
	private JTextField reorderLevelField;
	
	// constructor
	public AdminAddNewProductGUI(String email)
	{
		super("Admin Add New Product", email);
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;
		
		// set up the panels for the page and add them
		add(setUpLeftPanel(), gridBagConstraints);
		gridBagConstraints.gridx = 1;
		add(setUpDescriptionPanel(), gridBagConstraints);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets.bottom = 20;
		add(setUpButtonsPanel(), gridBagConstraints);
	}
	
	// set up the left panel
	private JPanel setUpLeftPanel()
	{
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		leftPanel.add(setUpNamePanel(), gridBagConstraints);
		gridBagConstraints.insets.top = 10;
		gridBagConstraints.gridy = 1;
		leftPanel.add(setUpStockAmountPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 2;
		leftPanel.add(setUpSellingPricePanel(), gridBagConstraints);
		gridBagConstraints.gridy = 3;
		leftPanel.add(setUpReorderLevelPanel(), gridBagConstraints);
		
		return leftPanel;
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
		
		JLabel nameLabel = new JLabel("Name");
		gridBagConstraints.insets.bottom = 5;
		namePanel.add(nameLabel, gridBagConstraints);
		
		nameField = new JTextField();
		nameField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		namePanel.add(nameField, gridBagConstraints);
		return namePanel;
	}
	
	// set up the panel for the name
	private JPanel setUpDescriptionPanel()
	{
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 200);
		
		JLabel descriptionLabel = new JLabel("Description");
		gridBagConstraints.insets.bottom = 5;
		descriptionPanel.add(descriptionLabel, gridBagConstraints);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
		scrollPane.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		descriptionPanel.add(scrollPane, gridBagConstraints);
		
		return descriptionPanel;
	}
	
	// set up the panel for the name
	private JPanel setUpStockAmountPanel()
	{
		JPanel stockAmountPanel = new JPanel();
		stockAmountPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel stockAmountLabel = new JLabel("Stock Amount");
		gridBagConstraints.insets.bottom = 5;
		stockAmountPanel.add(stockAmountLabel, gridBagConstraints);
		
		stockAmountField = new JTextField();
		stockAmountField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		stockAmountPanel.add(stockAmountField, gridBagConstraints);
		
		return stockAmountPanel;
	}
	
	// set up the panel for the name
	private JPanel setUpSellingPricePanel()
	{
		JPanel sellingPricePanel = new JPanel();
		sellingPricePanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel sellingPriceLabel = new JLabel("Selling Price");
		gridBagConstraints.insets.bottom = 5;
		sellingPricePanel.add(sellingPriceLabel, gridBagConstraints);
		
		sellingPriceField = new JTextField();
		sellingPriceField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		sellingPricePanel.add(sellingPriceField, gridBagConstraints);
		
		return sellingPricePanel;
	}
	
	// set up the panel for the name
	private JPanel setUpReorderLevelPanel()
	{
		JPanel reorderLevelPanel = new JPanel();
		reorderLevelPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel reorderLevelLabel = new JLabel("Reorder Level");
		gridBagConstraints.insets.bottom = 5;
		reorderLevelPanel.add(reorderLevelLabel, gridBagConstraints);
		
		reorderLevelField = new JTextField();
		reorderLevelField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 0;
		reorderLevelPanel.add(reorderLevelField, gridBagConstraints);
		
		return reorderLevelPanel;
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
				commandString = "Admin Products";
			}
		});
		buttonsPanel.add(cancelButton, gridBagConstraints);
		
		JButton addNewProductButton = new JButton("Add New Product");
		addNewProductButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (checkAllInputsCorrect())
				{
					addNewProduct();
					commandString = "Admin Products";
				}
			}
		});
		gridBagConstraints.gridx = 1;
		gridBagConstraints.insets.left = 60;
		buttonsPanel.add(addNewProductButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// checks all of the inputs
	private boolean checkAllInputsCorrect()
	{
		// checks if all of the required fields have been input
		if (nameField.getText().length() == 0 ||
			descriptionTextArea.getText().length() == 0 ||
			stockAmountField.getText().length() == 0 ||
			sellingPriceField.getText().length() == 0 ||
			reorderLevelField.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Input all fields!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// checks if stock amount correct
		if (!checkStringIsInteger(stockAmountField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Stock Amount not correct!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// checks if selling price correct
		if (!checkStringIsDouble(sellingPriceField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Selling Price not correct!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// checks if reorder level correct
		if (!checkStringIsInteger(reorderLevelField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Reorder Level not correct!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	// adds a new product into the database
	private void addNewProduct()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("""
															  INSERT INTO products (Name, Description, StockAmount, SellingPrice, ReorderLevel)
															  VALUES (?, ?, ?, ?, ?)""");
			
	    	stmt.setString(1, nameField.getText());
	    	stmt.setString(2, descriptionTextArea.getText());
	    	stmt.setInt(3, Integer.parseInt(stockAmountField.getText()));
			stmt.setDouble(4, Math.round(Double.parseDouble(sellingPriceField.getText()) * 100) / 100.0);
			stmt.setInt(5, Integer.parseInt(reorderLevelField.getText()));
			
	    	stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
