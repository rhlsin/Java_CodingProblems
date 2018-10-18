import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Insert_value

{
    public static void main(String args[]) throws IOException
    {
        BufferedReader BR=new BufferedReader(new InputStreamReader (System.in));
        
        int i,j,c=0,n;
        //String m;
        //System.out.println("Enter the Matrix ");
		//System.out.println("Enter the First Dimension");
		//m=Integer.parseInt(br.readLine());
		//System.out.println("Enter the Second Dimension");
		
        System.out.println("Enter Square Matrix value  :");
        n=Integer.parseInt(BR.readLine());
        int Number[][]=new int[n][n];
        for(i=0;i<=n-1;i++)
        {
            for(j=0;j<=n-1;j++)
            {
              
            	c=c+1;
				 if (i == j)
				 {
					 
					 Number[i][j]=1;
				 }
				 else 
				 {		
					 
					 Number[i][j]=c;
				 }
            }
        }
        System.out.println("Elements in Matrix are : ");
        System.out.println("Elements in Matrix are : ");
        System.out.println("");
        
        for(i=0;i<=n-1;i++)
        {
            for(j=0;j<=n-1;j++)
            {
                System.out.print(Number[i][j]+"\t");
            }
            System.out.println();
        }
    }
}