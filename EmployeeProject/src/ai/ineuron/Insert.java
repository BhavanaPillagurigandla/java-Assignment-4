package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Insert 
{
	public static void insert()
	{
		try 
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("enter emp_id : ");
			int emp_id=sc.nextInt();
			sc.nextLine();
			Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
					ps=connection.prepareStatement("select count(*) from employee where emp_id=?");
			}
			if(ps!=null)
			{
				ps.setInt(1, emp_id);
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)==0)
				{
					ps=null;
					System.out.println("enter emp_name : ");
					String emp_name=sc.nextLine();
					System.out.println("enter emp_salary : ");
					int emp_salary=sc.nextInt();
					if(ps==null)
					{
						ps=connection.prepareStatement("insert into employee values(?,?,?)");
					}
					if(ps!=null)
					{
						ps.setInt(1, emp_id);
						ps.setString(2,emp_name );
						ps.setInt(3, emp_salary);
						int rows=ps.executeUpdate();
						System.out.println(rows+ "row's affected");
					}
				}
				else
				{
					System.out.println("emp_id is already exists");
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
}
