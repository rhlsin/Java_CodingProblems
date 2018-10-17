import java.util.Scanner; 
public class Problem_Source 
{

	public static void main(String[] args) 
	{
		int a,b,c,d;
		Scanner val = new Scanner(System.in); 
		a = val.nextInt();
		b = val.nextInt();
		c = val.nextInt();
		d = val.nextInt();
		int[] array = {a,b,c,d};
		int k = 0;
	  
		for(int i=0; i<array.length; i++)
		{
			int n = array[i];
			int x = n/3;
			int y = n%3;
			int z = x*35;
	 
			switch(y)
			{
				case 0: k = 0 + z; break;
				case 1: k = 20 + z; break;
				case 2: k = 30 + z; break;
				case 3: k = 35 + z; break;  
			}
			System.out.println(k+" cm"); 
		}
	  }
}
	 
	