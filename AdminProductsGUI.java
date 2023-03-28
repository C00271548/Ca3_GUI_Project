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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;

//class for the admin products page
@SuppressWarnings("serial")
public class AdminProductsGUI extends GeneralGUI
{
	private JPanel tablePanel;
	
	private JTable productsTable;
	
	private int selectedRow = -1;
	public int productID = 0;
	
	// constructor
	public AdminProductsGUI(String email)
	{
		super("Admin Products", email);
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;

		add(setUpTopAdminNavBar(), gridBagConstraints);
		gridBagConstraints.gridy = 1;
		add(setUpTablePanel(), gridBagConstraints);
		gridBagConstraints.gridy = 2;
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
		
		String[] columnNames = {"Product ID", "Name", "Description", "Stock Amount", "Selling Price", "Reorder Level"};
		String[][] data = getProductsTableInfo();
		
		productsTable = new JTable(data, columnNames);
		if (selectedRow != -1)
		{
			productsTable.setRowSelectionInterval(selectedRow, selectedRow);
		}
		productsTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				selectedRow = productsTable.getSelectedRow();
				updateTable();
			}
		});
		// makes the table not be editable
		productsTable.setDefaultEditor(Object.class, null);
		// sets the cell data to be centered
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		productsTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(productsTable);
		scrollPane.setPreferredSize(new DimensionUIResource(553, 423));
		productsTable.setFillsViewportHeight(true);
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
	
	// set up panel for the buttons
	private JPanel setUpButtonsPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JButton addNewButton = new JButton("Add New Product");
		addNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				commandString = "Admin Add New Product";
			}
		});
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		buttonsPanel.add(addNewButton, gridBagConstraints);
		
		JButton changeButton = new JButton("Change Product");
		changeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				productID = 0;
				if (productsTable != null && productsTable.getSelectedRow() != -1 && (String) productsTable.getValueAt(productsTable.getSelectedRow(), 0) != "No Products")
				{
					productID = Integer.parseInt((String) productsTable.getValueAt(productsTable.getSelectedRow(), 0));
					commandString = "Admin Update Product";
				}
			}
		});
		buttonsPanel.add(changeButton, gridBagConstraints);
		
		JButton deleteButton = new JButton("Delete Product");
		deleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				deleteProduct();
				updateTable();
			}
		});
		gridBagConstraints.insets.left = 5;
		gridBagConstraints.insets.right = 10;
		buttonsPanel.add(deleteButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// get products table info
	private String[][] getProductsTableInfo()
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
			
			String[][] outData = {{"No Products", "", "", "", "", ""}};
			if (selectedRow >= totalRows)
			{
				selectedRow = totalRows - 1;
			}
			if (totalRows != 0)
			{
				outData = new String[totalRows][];
				
				result = stmt.executeQuery();
				int rowIndex = 0;
				while (result.next())
				{
					outData[rowIndex] = new String[]{result.getString("ProductID"),
													 result.getString("Name"),
													 result.getString("Description"),
													 result.getString("StockAmount"),
													 result.getString("SellingPrice"),
													 result.getString("ReorderLevel"),};
					rowIndex += 1;
				}
			}
			
			result.close();
			return outData;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	// get the selected product's description
	private String getProductDescription()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT Description FROM products WHERE ProductID = ?");
			int productID = 0;
			if (productsTable != null && productsTable.getSelectedRow() != -1 && (String) productsTable.getValueAt(productsTable.getSelectedRow(), 0) != "No Products")
			{
				productID = Integer.parseInt((String) productsTable.getValueAt(productsTable.getSelectedRow(), 0));
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
	
	// deletes the selected row from the database
	private void deleteProduct()
	{
		try
		{
			// create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE productID = ?");
			int productID = 0;
			if (productsTable != null && productsTable.getSelectedRow() != -1 && (String) productsTable.getValueAt(productsTable.getSelectedRow(), 0) != "No Products")
			{
				productID = Integer.parseInt((String) productsTable.getValueAt(productsTable.getSelectedRow(), 0));
			}
			stmt.setInt(1, productID);

			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
