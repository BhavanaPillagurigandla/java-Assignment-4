package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Update 
{
	static int emp_id;
	public static void update()
	{
		try 
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("enter emp_id : ");
			emp_id=sc.nextInt();
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
				if(rs.getInt(1)==1)
				{
					System.out.println("1.name\t2.salary");
					System.out.println("choose your option : ");
					int option=sc.nextInt();
					switch(option)
					{
					case 1:toChangeName();
						break;
					case 2:toChangeSalary();
						break;
					}
				}
				else
				{
					System.out.println("emp_id is not exists");
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
	public static void toChangeName()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("enter emp_name");
			String emp_name=sc.nextLine();
			Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
					ps=connection.prepareStatement("update employee set emp_name=? where emp_id=?");
			}
			if(ps!=null)
			{
				ps.setString(1, emp_name);
				ps.setInt(2, emp_id);
				int rows=ps.executeUpdate();
				System.out.println(rows+" row's affected");
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void toChangeSalary()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("enter emp_salary");
			int emp_salary=sc.nextInt();
			Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
					ps=connection.prepareStatement("update employee set emp_salary=? where emp_id=?");
			}
			if(ps!=null)
			{
				ps.setInt(1, emp_salary);
				ps.setInt(2, emp_id);
				int rows=ps.executeUpdate();
				System.out.println(rows+" row's affected");
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
 }

