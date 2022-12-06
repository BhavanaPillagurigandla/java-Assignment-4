package ai.ineuron;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CrudOperations 
{
	public static void main(String[] args) 
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("1.Insert\t2.Update\t3.Select\t4.Delete");
			System.out.print("choose your option : ");
			int option=sc.nextInt();
			switch(option)
			{
			case 1:Insert.insert();
				break;
			case 2:Update.update();
				break;
			case 3:Select.select();
				break;
			case 4:Delete.delete();
				break;
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
