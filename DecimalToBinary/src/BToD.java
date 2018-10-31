import java.io.*;
import java.util.Scanner;
public class BToD 
{
public static void main (String [] args) throws IOException
	{
		int t=0,a=0;
		String a2,qw="";
		System.out.println ("Enter the no.");
		Scanner in =new Scanner (System.in);
		a=in.nextInt();
		while(a>0)
		{
		    t=a%2;
			a=a/2;
			qw=qw+t;
			//System.out.println("t="+t);
		}
		//System.out.println("t="+qw);
		
		
		   
		    //String a1=(String)Integer.toString(qw);
		    a2=new StringBuffer(qw).reverse().toString();
		    System.out.print(a2);
		 
	    
		//System.out.print(a2);
		
}
}