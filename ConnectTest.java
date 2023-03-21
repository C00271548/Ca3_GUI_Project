package ca3_GUI_Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectTest
{
    public static void main(String args[])
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/project_database","root", "");
            print("Database is connected !");

            //InsertIntotesting(conn, new String[]{"Bob", "Boberick", "Dublin"});
            //Modifytesting(conn, new String[]{"Murphy", "1"});
            //DeleteFromtesting(conn, 5);
            //RetreiveFromtesting(conn, 7);
            conn.close();
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }
    
    private static void InsertIntotesting(Connection conn, String[] values)
    {
		try
		{
	        // create a Statement from the connection
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO testing(`First Name`, `Last Name`, `Address`) VALUES (?, ?, ?)");
	    	stmt.setString(1, values[0]);
	    	stmt.setString(2, values[1]);
	    	stmt.setString(3, values[2]);
	    	
	    	print(stmt);

	    	stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
    private static void Modifytesting(Connection conn, String[] values)
    {
		try
		{
	        // create a Statement from the connection
			PreparedStatement stmt = conn.prepareStatement("UPDATE testing SET `Last Name` = ? WHERE ID = ?");
	    	stmt.setString(1, values[0]);
	    	stmt.setInt(2, Integer.parseInt(values[1]));
	    	
	    	print(stmt);

	    	stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
    private static void DeleteFromtesting(Connection conn, int id)
    {
		try
		{
	        // create a Statement from the connection
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM testing WHERE ID = ?");
	    	stmt.setInt(1, id);
	    	
	    	print(stmt);

	    	stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
    
    private static void RetreiveFromtesting(Connection conn, int id)
    {
		try
		{
	        // create a Statement from the connection
			PreparedStatement stmt = conn.prepareStatement("SELECT ID, `First Name`, `Last Name`, `Address` FROM testing WHERE ID = ?");
	    	stmt.setInt(1, id);
	    	
	    	print(stmt);

	    	ResultSet result = stmt.executeQuery();
	    	
	    	while (result.next())
	    	{
	    		int new_id = result.getInt("ID");
	    		String first_name = result.getString("First Name");
	    		String last_name = result.getString("Last Name");
	    		String address = result.getString("Address");
	    		
	    		print(new_id + first_name + last_name + address);
	    	}
	    	result.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
    }

	// print
    private static void print(Object x)
    {
    	System.out.println(x);
    }
}
