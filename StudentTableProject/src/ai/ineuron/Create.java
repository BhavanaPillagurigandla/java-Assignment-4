package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Create 
{
	public static void create()
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
			
			System.out.print("enter roll : ");
			String roll_no=sc.next().toLowerCase();
			sc.nextLine();
			
			System.out.print("enter name : ");
			String name=sc.nextLine();
			
			System.out.print("enter parent mobile no : ");
			long parent_mbl_no=sc.nextLong();
			sc.nextLine();
			
			System.out.print("enter parent_name : ");
			String parent_name=sc.nextLine();
			try 
			{
				if(connection!=null)
				{
					ps=connection.prepareStatement("select count(*) from student where roll_no=?");
				}
				if(ps!=null)
				{
					ps.setString(1,roll_no);
					rs=ps.executeQuery();
				}
				if(rs!=null)
				{
					rs.next();
				}
				ps=null;
				if(rs.getInt(1)==1)
				{
					System.out.println("student roll_no already Exists");
				}
				else
				{
					if(connection!=null)
					{
					ps=connection.prepareStatement("insert into student(roll_no,name,parent_mbl_no,parent_name) values(?,?,?,?)");
					}
					if(ps!=null)
					{
						ps.setString(1, roll_no);
						ps.setString(2, name);
						ps.setLong(3, parent_mbl_no);
						ps.setString(4, parent_name);
						int rows=ps.executeUpdate();
						System.out.println(rows+" row's affected");
					}
				}
				connection.close();
					
			} 
			catch (SQLException e) 
			{
					e.printStackTrace();
			}
			
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
