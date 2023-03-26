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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//class for the admin orders page
@SuppressWarnings("serial")
public class AdminOrdersGUI extends GeneralGUI
{
	private JPanel tablePanel;
	
	private JTable ordersTable;
	
	private JCheckBox deliveredCheckBox;
	private JCheckBox notDeliveredCheckBox;
	
	private int selectedRow = -1;
	
	// constructor
	public AdminOrdersGUI(String email)
	{
		super("Admin Orders", email);
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;

		add(setUpTopAdminNavBar(), gridBagConstraints);
		gridBagConstraints.gridy = 1;
		add(setUpFilterCheckboxes(), gridBagConstraints);
		gridBagConstraints.gridy = 2;
		add(setUpTablePanel(), gridBagConstraints);
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets.bottom = 20;
		add(setUpButtonsPanel(), gridBagConstraints);
	}
	
	// set up panel for the filter checkboxes
	private JPanel setUpFilterCheckboxes()
	{
		JPanel filterCheckboxesPanel = new JPanel();
		filterCheckboxesPanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		JLabel filterLabel = new JLabel("Filter: ");
		filterCheckboxesPanel.add(filterLabel, gridBagConstraints);
		
		deliveredCheckBox = new JCheckBox("Delivered");
		deliveredCheckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateTable();
			}
		});
		filterCheckboxesPanel.add(deliveredCheckBox, gridBagConstraints);
		
		notDeliveredCheckBox = new JCheckBox("Not Delivered");
		notDeliveredCheckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateTable();
			}
		});
		filterCheckboxesPanel.add(notDeliveredCheckBox, gridBagConstraints);
		
		return filterCheckboxesPanel;
	}
	
	// set up panel for the table
	private JPanel setUpTablePanel()
	{
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridBagLayout());
		
		// set up item constraints in the grid
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		// gridBagConstraints.insets is used for margins
		
		String[] columnNames = {"Order ID", "Date Ordered", "Customer ID", "Delivered"};
		String[][] data = getOrdersTableInfo();
		
		ordersTable = new JTable(data, columnNames);
		if (selectedRow != -1)
		{
			ordersTable.setRowSelectionInterval(selectedRow, selectedRow);
		}
		ordersTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				selectedRow = ordersTable.getSelectedRow();
				updateTable();
			}
		});
		// makes the table not be editable
		ordersTable.setDefaultEditor(Object.class, null);
		// sets the cell data to be centered
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		ordersTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(ordersTable);
		ordersTable.setFillsViewportHeight(true);
		tablePanel.add(scrollPane, gridBagConstraints);
		
		
		columnNames = new String[]{"Product ID", "Name", "Description", "Price", "Order Amount"};
		data = getOrderDetailsTableInfo();
		
		JTable orderDetailsTable = new JTable(data, columnNames);
		// makes the table not be editable
		orderDetailsTable.setDefaultEditor(Object.class, null);
		orderDetailsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		// sets the cell data to be centered
		orderDetailsTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scrollPane2 = new JScrollPane(orderDetailsTable);
		orderDetailsTable.setFillsViewportHeight(true);
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
		
		JButton toggleButton = new JButton("Toggle Delivered");
		toggleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateOrdersDelivered();
				updateTable();
			}
		});
		gridBagConstraints.insets.left = 10;
		gridBagConstraints.insets.right = 5;
		buttonsPanel.add(toggleButton, gridBagConstraints);
		
		JButton deleteButton = new JButton("Delete Order");
		deleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				deleteOrder();
				updateTable();
			}
		});
		gridBagConstraints.insets.left = 5;
		gridBagConstraints.insets.right = 10;
		buttonsPanel.add(deleteButton, gridBagConstraints);
		
		return buttonsPanel;
	}
	
	// get orders table info
	private String[][] getOrdersTableInfo()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders");

	    	ResultSet result = stmt.executeQuery();
			int totalRows = 0;
			while (result.next())
			{
				if (deliveredCheckBox != null && ((deliveredCheckBox.isSelected() && result.getInt("Delivered") == 1) || (notDeliveredCheckBox.isSelected() && result.getInt("Delivered") == 0)))
				{
					totalRows += 1;
				}
			}
			
			String[][] outData = {{"No Orders", "", "", ""}};
			if (totalRows != 0)
			{
				outData = new String[totalRows][];
				
				result = stmt.executeQuery();
				int rowIndex = 0;
				while (result.next())
				{
					if (deliveredCheckBox != null && ((deliveredCheckBox.isSelected() && result.getInt("Delivered") == 1) || (notDeliveredCheckBox.isSelected() && result.getInt("Delivered") == 0)))
					{
						outData[rowIndex] = new String[]{result.getString("OrderID"),
														 result.getString("DateOrdered"),
														 result.getString("CustomerID"),
														 convertIntToBooleanToString(result.getInt("Delivered"))};
						rowIndex += 1;
					}
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
	
	// get order details table info
	private String[][] getOrderDetailsTableInfo()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("""
															  SELECT * FROM ((orders
															  INNER JOIN `orders/products` ON orders.OrderID = `orders/products`.OrderID)
															  INNER JOIN products ON `orders/products`.ProductID = products.ProductID)
															  WHERE orders.OrderID = ?""");
			int orderID = 0;
			if (ordersTable != null && ordersTable.getSelectedRow() != -1 && (String) ordersTable.getValueAt(ordersTable.getSelectedRow(), 0) != "No Orders")
			{
				orderID = Integer.parseInt((String) ordersTable.getValueAt(ordersTable.getSelectedRow(), 0));
			}
			stmt.setInt(1, orderID);

	    	ResultSet result = stmt.executeQuery();
			int totalRows = 0;
			while (result.next())
			{
				totalRows += 1;
			}
			
			String[][] outData = {{"", "", "No Order Selected", "", ""}};
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
													 result.getString("SellingPrice"),
													 result.getString("OrderAmount")};
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
	
	// deletes the old table and makes a new one with the filter applied
	private void updateTable()
	{
		remove(tablePanel);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;
		gridBagConstraints.gridy = 2;
		
		add(setUpTablePanel(), gridBagConstraints);
		pack();
	}
	
	// updates the selected rows delivered in database
	private void updateOrdersDelivered()
	{
		int[] selectedRowsIndexes = ordersTable.getSelectedRows();
		
		for (int rowIndex : selectedRowsIndexes)
		{
			try
			{
				// create a statement using the connection
				PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET Delivered = ? WHERE orderID = ?");
				String delivered = (String) ordersTable.getValueAt(rowIndex, 3);
				if (delivered.equals("Yes"))
				{
					stmt.setInt(1, 0);
				}
				else
				{
					stmt.setInt(1, 1);
				}
				stmt.setInt(2, Integer.parseInt((String) ordersTable.getValueAt(rowIndex, 0)));

				stmt.executeUpdate();
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// deletes the selected row from the database
	private void deleteOrder()
	{
		int rowIndex = ordersTable.getSelectedRow();
		try
		{
			// create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE orderID = ?");
			stmt.setInt(1, Integer.parseInt((String) ordersTable.getValueAt(rowIndex, 0)));

			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e, "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
