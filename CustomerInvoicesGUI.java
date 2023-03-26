package ca3_GUI_Project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//class for the customer invoices page
@SuppressWarnings("serial")
public class CustomerInvoicesGUI extends GeneralGUI
{
	private JPanel tablePanel;
	
	private JCheckBox paidCheckBox;
	private JCheckBox notPaidCheckBox;
	
	// constructor
	public CustomerInvoicesGUI(String email)
	{
		super("Invoices", email);
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets.top = 20;
		gridBagConstraints.insets.left = 20;
		gridBagConstraints.insets.right = 20;

		add(setUpTopCustomerNavBar(), gridBagConstraints);
		gridBagConstraints.gridy = 1;
		add(setUpFilterCheckboxes(), gridBagConstraints);
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets.bottom = 20;
		add(setUpTablePanel(), gridBagConstraints);
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
		
		paidCheckBox = new JCheckBox("Paid");
		paidCheckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateTable();
			}
		});
		filterCheckboxesPanel.add(paidCheckBox, gridBagConstraints);
		
		notPaidCheckBox = new JCheckBox("Not Paid");
		notPaidCheckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateTable();
			}
		});
		filterCheckboxesPanel.add(notPaidCheckBox, gridBagConstraints);
		
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
		
		String[] columnNames = {"Invoice ID", "Total Cost Amount", "Customer ID", "Order ID", "Paid"};
		String[][] data = getInvoiceTableInfo();
		
		JTable invoicesTable = new JTable(data, columnNames);
		// makes the table not be editable
		invoicesTable.setDefaultEditor(Object.class, null);
		invoicesTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		// sets the cell data to be centered
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		invoicesTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(invoicesTable);
		invoicesTable.setFillsViewportHeight(true);
		tablePanel.add(scrollPane, gridBagConstraints);
		
		return tablePanel;
	}
	
	// get invoice table info
	private String[][] getInvoiceTableInfo()
	{
		try
		{
	        // create a statement using the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM invoices WHERE CustomerID = ?");
	    	stmt.setInt(1, customerID);

	    	ResultSet result = stmt.executeQuery();
	    	if (!result.next())
	    	{
				result.close();
				String[][] outData = {{"No Invoices", "", "", "", ""}};
	    		return outData;
	    	}
			else
			{
				int totalRows = 0;
				do
				{
					if (paidCheckBox != null && ((paidCheckBox.isSelected() && result.getInt("Paid") == 1) || (notPaidCheckBox.isSelected() && result.getInt("Paid") == 0)))
					{
						totalRows += 1;
					}
				} while (result.next());
				String[][] outData = new String[totalRows][5];
				
				result = stmt.executeQuery();
				int rowIndex = 0;
				while (result.next())
				{
					if (paidCheckBox != null && ((paidCheckBox.isSelected() && result.getInt("Paid") == 1) || (notPaidCheckBox.isSelected() && result.getInt("Paid") == 0)))
					{
						outData[rowIndex] = new String[]{result.getString("InvoiceID"),
														 result.getString("TotalCostAmount"),
														 result.getString("CustomerID"),
														 result.getString("OrderID"),
														 convertIntToBooleanToString(result.getInt("Paid"))};
						rowIndex += 1;
					}
				}
	    		return outData;
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
		gridBagConstraints.gridy = 2;
		
		add(setUpTablePanel(), gridBagConstraints);
		pack();
	}
}
