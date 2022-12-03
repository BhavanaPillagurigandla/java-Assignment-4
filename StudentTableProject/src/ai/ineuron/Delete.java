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
			System.out.println("1.To delte particular one student details\t2.To delete entire student details");
			System.out.println("choose your option : ");
			int option=sc.nextInt();
			switch(option)
			{
				case 1:deleteOneStudentDetails();
					break;
				case 2:deleteAllStudentDetails();
					break;
			}
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
	}
	public static void deleteOneStudentDetails()
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
			System.out.println("enter rollno : ");
			String roll_no=sc.nextLine().toLowerCase();
			if(connection!=null)
			{
				ps=connection.prepareStatement("select count(*) from student where roll_no=?");
			}
			if(ps!=null)
			{
				ps.setString(1, roll_no);
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
						ps=connection.prepareStatement("delete from student where roll_no=?");
					}
					if(ps!=null)
					{
						ps.setString(1, roll_no);
						int rows=ps.executeUpdate();
						System.out.println(rows+" row's afeected");
					}
				}
				else
				{
					System.out.println("roll_no not Exists in the data base");
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
	public static void deleteAllStudentDetails()
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
				ps=connection.prepareStatement("delete  from student");
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
