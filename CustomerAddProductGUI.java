package ca3_GUI_Project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

//class for the customer add product page
@SuppressWarnings("serial")
public class CustomerAddProductGUI extends GeneralGUI
{
	private JComboBox<String> productsComboBox;
	private JTextField orderAmountField;
	
	private String[][] allProductsData;
	
	public String[][] orderProductsData;
	
	// constructor
	public CustomerAddProductGUI(String email, String[][] previousData)
	{
		super("Add Product", email);
		
		if (previousData == null || previousData[0][0].equals("No Products"))
		{
			orderProductsData = null;
		}
		else
		{
			orderProductsData = previousData;
		}
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;

		add(setUpDataPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 20;
		add(setUpButtonsPanel(), gridBagConstraints);
	}
	
	// set up the panel for the combobox and number input
	private JPanel setUpDataPanel()
	{
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		// set up the size for the input fields
		DimensionUIResource preferredSize = new DimensionUIResource(170, 25);
		
		JLabel productLabel = new JLabel("Select Product");
		gridBagConstraints.insets.bottom = 5;
		dataPanel.add(productLabel, gridBagConstraints);
		
		productsComboBox = new JComboBox<String>(getProductsInfo());
		productsComboBox.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets.bottom = 10;
		dataPanel.add(productsComboBox, gridBagConstraints);
		
		JLabel orderAmountLabel = new JLabel("Order Amount");
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets.bottom = 5;
		dataPanel.add(orderAmountLabel, gridBagConstraints);
		
		orderAmountField = new JTextField();
		orderAmountField.setPreferredSize(preferredSize);
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 0;
		dataPanel.add(orderAmountField, gridBagConstraints);

		return dataPanel;
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
				commandString = "Customer New Order";
			}
		});
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		buttonsPanel.add(cancelButton, gridBagConstraints);
		
		JButton signUpButton = new JButton("Add Product");
		signUpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (checkAllInputsCorrect())
				{
					addProduct();
					commandString = "Customer New Order";
				}
			}
		});
		gridBagConstraints.gridx = 1;
		gridBagConstraints.insets.left = 5;
		gridBagConstraints.insets.right = 10;
		buttonsPanel.add(signUpButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// gets all of the products form the database
	private String[] getProductsInfo()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
			
	    	ResultSet result = stmt.executeQuery();
			int totalRows = 0;
			while (result.next())
			{
				totalRows += 1;
			}
			
			allProductsData = new String[totalRows][];
			if (totalRows != 0)
			{
				String[] comboBoxStrings = new String[totalRows];
				result = stmt.executeQuery();
				
				int rowIndex = 0;
				while (result.next())
				{
					allProductsData[rowIndex] = new String[]{String.valueOf(result.getInt("ProductID")),
															 result.getString("Name"),
															 result.getString("Description"),
															 String.valueOf(result.getDouble("SellingPrice"))};
					comboBoxStrings[rowIndex] = String.valueOf(result.getInt("ProductID")) + " - " + result.getString("Name") + " - " + result.getString("Description");						 
					rowIndex += 1;
					
				}
				result.close();
				return comboBoxStrings;
			}
			else
			{
				result.close();
				return new String[]{"No Products"};
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
			return new String[]{"No Products"};
		}
	}
	
	// checks all of the inputs
	private boolean checkAllInputsCorrect()
	{
		// checks if all of the required fields have been input
		if (productsComboBox.getSelectedIndex() == -1 ||
			orderAmountField.getText().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Input all fields!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// checks if stock amount correct
		if (!checkStringIsInteger(orderAmountField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Order Amount not correct!", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	// adds a product into the orderProductsData array
	private void addProduct()
	{
		int index = 0;
		String[][] tempData = new String[1][];
		if (orderProductsData != null)
		{
			tempData = new String[orderProductsData.length + 1][];
			for (index = 0; index < orderProductsData.length; index++)
			{
				tempData[index] = orderProductsData[index];
			}
		}
		String[] productData = new String[6];
		for (int index2 = 0; index2 < 4; index2++)
		{
			productData[index2] = allProductsData[productsComboBox.getSelectedIndex()][index2];
		}
		productData[4] = orderAmountField.getText();
		productData[5] = String.valueOf(Integer.parseInt(orderAmountField.getText()) * Double.parseDouble(productData[3]));
		tempData[index] = productData;
		
		orderProductsData = tempData;
	}
}
