package ai.ineuron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import ai.ineuron.utility.JdbcConnection;

public class DateTimeOperations 
{
	public static void main(String[] args) throws ParseException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Insertion\t2.Retrival");
		System.out.print("choose your option : ");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:DateTimeOperations.insertaion();
			break;
		case 2:DateTimeOperations.retrival();
			break;
		}
		
	}
	public static void  insertaion()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("enter name : ");
		String name=sc.nextLine();
		System.out.print("enter address :");
		String address=sc.nextLine();
		System.out.print("enter gender : ");
		String gender=sc.nextLine();
		System.out.print("enter dob (dd-MM-yyyy) : ");
		String dob=sc.nextLine();
		System.out.print("enter doj (MM-dd-yyyy) : ");
		String doj=sc.nextLine();
		System.out.print("enter dom (yyyy-MM-dd) : ");
		String dom=sc.nextLine();
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
			if(connection!=null)
			{
				ps=connection.prepareStatement("insert into student_1 values(?,?,?,?,?,?)");
			}
			if(ps!=null)
			{
				ps.setString(1, name);
				ps.setString(2, address);
				ps.setString(3, gender);
				SimpleDateFormat sd1=new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sd2=new SimpleDateFormat("MM-dd-yyyy");
				SimpleDateFormat sd3=new SimpleDateFormat("yyyy-MM-dd");
				
				java.util.Date jud1=sd1.parse(dob);
				java.util.Date jud2=sd2.parse(doj);
				java.util.Date jud3=sd3.parse(dom);
				
				java.sql.Date jsd1=new java.sql.Date(jud1.getTime());
				java.sql.Date jsd2=new java.sql.Date(jud2.getTime());
				java.sql.Date jsd3=new java.sql.Date(jud3.getTime());
				
				ps.setDate(4, jsd1);
				ps.setDate(5, jsd2);
				ps.setDate(6, jsd3);
				
				int rows=ps.executeUpdate();
				System.out.println(rows+"row's affected");
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
	public static void retrival()
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
				ps=connection.prepareStatement("select count(*) from student_1 ");
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
						ps=connection.prepareStatement("select * from student_1");
					}
					if(ps!=null)
					{
						rs=ps.executeQuery();
					}
					if(rs!=null)
					{
						System.out.println("name\taddress\tgender\tdob\tdom\tdom");
						while(rs.next())
						{
							SimpleDateFormat s1=new SimpleDateFormat("dd-MM-yyyy");
							SimpleDateFormat s2=new SimpleDateFormat("MM-dd-yyyy");
							SimpleDateFormat s3=new SimpleDateFormat("yyyy-MM-dd");
							
							String s11=s1.format(rs.getDate(4));
							String s12=s2.format(rs.getDate(5));
							String s13=s3.format(rs.getDate(6));
							
							System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+s11+"\t"+s12+"\t"+s13);
						}
					}	
				}
				else
				{
					System.out.println("data doesnot Exists");
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
}
