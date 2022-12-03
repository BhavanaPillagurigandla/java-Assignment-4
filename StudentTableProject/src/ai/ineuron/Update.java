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
	public static void update()
	{
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try 
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("enter roll no : ");
			String roll_no=sc.nextLine().toLowerCase();
			if(connection==null)
			{
				connection=JdbcConnection.connection();
			}
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
					System.out.println("1.name\t2.parent_mbl_no\t3.parent_name");
					System.out.println("choose your option");
					int option=sc.nextInt();
					sc.nextLine();
					String column_name="";
					switch(option)
					{
					case 1:column_name="name";
						break;
					case 2:column_name="parent_mbl_no";
						break;
					case 3:column_name="parent_name";
						break;
					}
					System.out.println("enter the new data to be updated in the column="+ column_name);
					String new_data=sc.nextLine();
					if(column_name.equals("parent_mbl_no"))
					{
						ps=null;
						if(ps==null)
						{
							ps=connection.prepareStatement("update student set "+ column_name+"=? where roll_no=? ");
						}
						if(ps!=null)
						{
							ps.setLong(1, Long.parseLong(new_data));
							ps.setString(2, roll_no);
							int rows=ps.executeUpdate();
							System.out.println(rows+" rows affected");
						}
					}
					else
					{
						ps=null;
						if(ps==null)
						{
							ps=connection.prepareStatement("update student set "+ column_name+"=? where roll_no=? ");
						}
						if(ps!=null)
						{
							ps.setString(1, new_data);
							ps.setString(2, roll_no);
							int rows=ps.executeUpdate();
							System.out.println(rows+" rows affected");
						}
					}
				}
				else
				{
					System.out.println("roll_no not Exists in the data");
				}
			}
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
}
