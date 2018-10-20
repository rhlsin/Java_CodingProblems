import java.io.*;

import java.util.Scanner;

public class seon {
	public static void main(String[] args) throws IOException
	{
		int a,i,b=0,c=0 ;
		for (i=1; i<=10; i++)
		{
			System.out.println("Enter the no.");
			Scanner in = new Scanner (System.in);
			a=in.nextInt();
			if (a%2==0)
			{
			     b=b+a;
				//System.out.println("Sum of even no.="+b);
			}
			else
			{
				 c=c+a;
				//System.out.println("Sum of odd no="+c);
			}
		}
		if (b>c)
		{
			System.out.println("even no. is greater"+b);
		}
		else
		{
			System.out.println("odd no. is greater"+c);
		}
	}
	
	

}

