package ai.ineuron;

import java.util.Scanner;

public class Student 
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("1.Create\t2.Read\t3.update\t4.Delete");
			System.out.print("choose your option = ");
			int option=sc.nextInt();
			switch(option)
			{
				case 1:Create.create();
					break;
				case 2:Read.read();
					break;
				case 3:Update.update();
					break;
				case 4:Delete.delete();
					break;
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}

