package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class Read 
{
	public static void read()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("1.To read specific student details\t2.To read all the student details");
			System.out.println("enter one num = ");
			int num=sc.nextInt();
			switch(num)
			{
			case 1:readOneStudentDetails();
				break;
			case 2: readAllStudentDetails();
				break;
			}
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
	}
	public static void readOneStudentDetails()
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
			System.out.println("enter rollno= ");
			String roll_no=sc.nextLine().toLowerCase();
			if(connection!=null)
			{
				ps=connection.prepareStatement("select count(*) from student where roll_no=?");
				if(ps!=null)
				{
					ps.setString(1, roll_no);
					rs=ps.executeQuery();
				}
				if(rs!=null)
				{
					rs.next();
				}
				if(rs.getInt(1)==1)
				{
					ps=null;
					if(ps==null)
					{
						ps=connection.prepareStatement("select * from student where roll_no=?");
					}
					if(ps!=null)
					{
						ps.setString(1, roll_no);
						rs=ps.executeQuery();
					}
					if(rs!=null)
					{
						System.out.println("roll_no\tname\tparent_mbl_no\tparent_name");
						while(rs.next())
						{
							System.out.println(rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getLong(4)+"\t"+rs.getString(5));
						}
					}
					
				}
				else
				{
					System.out.println("roll_no not Exists in the data base");
				}
			}
			connection.close();
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
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
	public static void readAllStudentDetails()
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
				ps=connection.prepareStatement("select count(*) from student");
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
						ps=connection.prepareStatement("select * from student");
					}
					if(ps!=null)
					{
						rs=ps.executeQuery();
					}
					if(rs!=null)
					{
						System.out.println("roll_no\tname\tparent_mbl_no\tparent_name");
						while(rs.next())
						{
							System.out.println(rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getLong(4)+"\t"+rs.getString(5));
						}
					}
				}
				else
				{
					System.out.println("student table is empty");
				}
			}
			connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
