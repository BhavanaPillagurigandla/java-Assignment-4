package ai.ineuron;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Select 
{
	public static void select()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.selectOneEmployee\t2.selectAllEmployees");
		System.out.println("choose your option : ");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:selectOneEmployee();
			break;
		case 2:selectAllEmployees();
			break;
		}
	}
	public static void selectOneEmployee()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println(" enter emp_id : ");
			int emp_id=sc.nextInt();
			Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
				ps=connection.prepareStatement("select count(*) from employee where emp_id=? ");
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
						ps=connection.prepareStatement("select * from employee where emp_id=? ");
					}
					if(ps!=null)
					{
						ps.setInt(1, emp_id);
						rs=ps.executeQuery();
					}
					if(rs!=null)
					{
						System.out.println("emp_id\temp_name\temp_salary");
						while(rs.next())
						{
							System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
						}
					}
				}
				else
				{
					System.out.println("emp_id is not exists");
				}
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
	public static void selectAllEmployees()
	{
		try
		{
			Connection connection=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
				ps=connection.prepareStatement("select count(*) from employee");
			}
			if(ps!=null)
			{
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				rs.next();
				if(rs.getInt(1)!=0)
				{
					ps=null;
					if(ps==null)
					{
						ps=connection.prepareStatement("select * from employee");
					}
					if(ps!=null)
					{
						rs=ps.executeQuery();
					}
					if(rs!=null)
					{
						while(rs.next())
						{
							System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
						}
					}
				}
				else
				{
					System.out.println("database not exists");
				}
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
