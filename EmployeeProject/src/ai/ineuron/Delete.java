package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Delete 
{
	public static void delete()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("1.To delte particular one employee details\t2.To delete entire employee details");
			System.out.println("choose your option : ");
			int option=sc.nextInt();
			switch(option)
			{
				case 1:deleteOneEmployeeDetails();
					break;
				case 2:deleteAllEmployeeDetails();
					break;
			}
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteOneEmployeeDetails()
	{
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try 
		{
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			Scanner sc=new Scanner(System.in);
			System.out.println("enter emp_id : ");
			int emp_id=sc.nextInt();
			if(connection!=null)
			{
				ps=connection.prepareStatement("select count(*) from Employee where emp_id=?");
			}
			if(ps!=null)
			{
				ps.setInt(1, emp_id);
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)==1)
				{
					ps=null;
					if(ps==null)
					{
						ps=connection.prepareStatement("delete from employee where emp_id=?");
					}
					if(ps!=null)
					{
						ps.setInt(1, emp_id);
						int rows=ps.executeUpdate();
						System.out.println(rows+" row's afeected");
					}
				}
				else
				{
					System.out.println("emp_id not Exists in the data base");
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteAllEmployeeDetails()
	{
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try 
		{
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
				ps=connection.prepareStatement("delete  from Employee");
			}
			if(ps!=null)
			{
				int rows=ps.executeUpdate();
				System.out.println(rows+" row's affected");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
}


