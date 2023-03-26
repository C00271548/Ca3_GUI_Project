package ca3_GUI_Project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;

//class for the customer new order page
@SuppressWarnings("serial")
public class CustomerNewOrderGUI extends GeneralGUI
{
	private JPanel tablePanel;
	
	private JTable orderTable;
	
	private JLabel totalPriceLabel;
	
	public String[][] orderProductsData;
	private int selectedRow = -1;

	// constructor
	public CustomerNewOrderGUI(String email, String[][] previousData)
	{
		super("New Order", email);
		if (previousData == null)
		{
			orderProductsData = new String[][]{{"No Products", "", "", "", "", ""}};
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

		add(setUpTopCustomerNavBar(), gridBagConstraints);
		gridBagConstraints.gridy = 1;
		add(setUpTablePanel(), gridBagConstraints);
		gridBagConstraints.gridy = 2;
		add(setUpTotalPriceLabelPanel(), gridBagConstraints);
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 20;
		add(setUpButtonsPanel(), gridBagConstraints);
	}
	
	// set up panel for the table
	private JPanel setUpTablePanel()
	{
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		String[] columnNames = {"Product ID", "Name", "Description", "Selling Price", "Buy Amount", "Total Price"};
		
		orderTable = new JTable(orderProductsData, columnNames);
		if (selectedRow != -1)
		{
			orderTable.setRowSelectionInterval(selectedRow, selectedRow);
		}
		orderTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				selectedRow = orderTable.getSelectedRow();
				updateTable();
			}
		});
		// makes the table not be editable
		orderTable.setDefaultEditor(Object.class, null);
		// sets the cell data to be centered
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		orderTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setPreferredSize(new DimensionUIResource(553, 423));
		orderTable.setFillsViewportHeight(true);
		tablePanel.add(scrollPane, gridBagConstraints);
		
		
		JTextArea productDescriptionTextArea = new JTextArea(getProductDescription());
		productDescriptionTextArea.setLineWrap(true);
		// makes the text area not be editable
		productDescriptionTextArea.setEditable(false);
		
		JScrollPane scrollPane2 = new JScrollPane(productDescriptionTextArea);
		scrollPane2.setPreferredSize(new DimensionUIResource(200, 423));
		gridBagConstraints.insets.left = 5;
		gridBagConstraints.insets.right = 0;
		tablePanel.add(scrollPane2, gridBagConstraints);
		
		return tablePanel;
	}
	
	// set up panel for the total price label
	private JPanel setUpTotalPriceLabelPanel()
	{
		JPanel totalPriceLabelPanel = new JPanel();
		totalPriceLabelPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JLabel totalPriceTextLabel = new JLabel("Total Price: €");
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		totalPriceLabelPanel.add(totalPriceTextLabel, gridBagConstraints);
		
		Double totalPrice = 0.0;
		for (String[] productData : orderProductsData)
		{
			if (productData[0].equals("No Products"))
			{
				break;
			}
			totalPrice += Double.parseDouble(productData[5]);
		}
		
		JLabel totalPriceLabel = new JLabel(String.valueOf(totalPrice));
		gridBagConstraints.insets.left = 5;
		gridBagConstraints.insets.right = 10;
		totalPriceLabelPanel.add(totalPriceLabel, gridBagConstraints);
		
		return totalPriceLabelPanel;
	}
	
	// set up panel for the buttons
	private JPanel setUpButtonsPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JButton addProductButton = new JButton("Add Product");
		addProductButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Customer Add Product";
			}
		});
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		buttonsPanel.add(addProductButton, gridBagConstraints);
		
		JButton removeProductButton = new JButton("Remove Product");
		removeProductButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (orderTable != null && orderTable.getSelectedRow() != -1 && (String) orderTable.getValueAt(orderTable.getSelectedRow(), 0) != "No Products")
				{
					removeRowFromData();
				}
			}
		});
		buttonsPanel.add(removeProductButton, gridBagConstraints);
		
		JButton finishOrderButton = new JButton("Finish Order");
		finishOrderButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				finishOrder();
				commandString = "Customer New Order";
			}
		});
		gridBagConstraints.insets.left = 5;
		gridBagConstraints.insets.right = 10;
		buttonsPanel.add(finishOrderButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// get the selected product's description
	private String getProductDescription()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT Description FROM products WHERE ProductID = ?");
			int productID = 0;
			if (orderTable != null && orderTable.getSelectedRow() != -1 && (String) orderTable.getValueAt(orderTable.getSelectedRow(), 0) != "No Products")
			{
				productID = Integer.parseInt((String) orderTable.getValueAt(orderTable.getSelectedRow(), 0));
			}
			stmt.setInt(1, productID);
			
	    	ResultSet result = stmt.executeQuery();
			if (result.next())
			{
				String description = result.getString("Description");
				result.close();
				return description;
			}
			else
			{
				result.close();
				return "No Product Selected";
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	// deletes the old table and makes a new one with the filter applied
	private void updateTable()
	{
		remove(tablePanel);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;
		gridBagConstraints.gridy = 1;
		
		add(setUpTablePanel(), gridBagConstraints);
		pack();
	}
	
	// removes a row from the order data array
	private void removeRowFromData()
	{
		String[][] tempData = new String[orderProductsData.length - 1][];
		
		for (int index = 0; index < orderProductsData.length - 1; index++)
		{
			if (index < orderTable.getSelectedRow())
			{
				tempData[index] = orderProductsData[index];
			}
			else
			{
				tempData[index] = orderProductsData[index + 1];
			}
		}
		
		orderProductsData = tempData;
	}
	
	// saves the order in the database
	private void finishOrder()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders (DateOrdered, CustomerID) VALUES (?, ?)");
	    	
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			stmt.setString(1, df.format(LocalDateTime.now()));
	    	stmt.setInt(2, customerID);
			
	    	stmt.executeUpdate();
			
			// create a statement using the connection
			stmt = conn.prepareStatement("SELECT OrderID FROM orders WHERE CustomerID = ? ORDER BY OrderID DESC");
			stmt.setInt(1, customerID);
			
	    	ResultSet result = stmt.executeQuery();
			int orderID;
			if (result.next())
			{
				orderID = result.getInt("OrderID");
				result.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "SQL Retreive Error!", "SQL Error", JOptionPane.ERROR_MESSAGE);
				result.close();
				return;
			}
			
			for (String[] productData : orderProductsData)
			{
				// create a statement using the connection
				stmt = conn.prepareStatement("INSERT INTO `orders/products` (OrderID, ProductID, OrderAmount) VALUES (?, ?, ?)");
				
				stmt.setInt(1, orderID);
				stmt.setString(2, productData[0]);
				stmt.setString(2, productData[4]);
				
				stmt.executeUpdate();
			}
			
			// create a statement using the connection
			stmt = conn.prepareStatement("INSERT INTO invoices (TotalCostAmount, CustomerID, OrderID) VALUES (?, ?, ?)");
			
			stmt.setDouble(1, Double.parseDouble(totalPriceLabel.getText()));
			stmt.setInt(2, customerID);
			stmt.setInt(2, orderID);
			
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
