package ai.ineuron.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection
{
	public static Connection connection()
	{
		Connection connection=null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			try 
			{
				if(connection==null)
				{
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bhavana","root","root");
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}
}
