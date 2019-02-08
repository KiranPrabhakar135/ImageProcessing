import java.io.*;
public class SecretBinary 
{
	public static void main(String arg[])throws IOException
	{
		try
		{
			BufferedReader b=new BufferedReader(new FileReader("E://no. of seg.txt"));
			
			int maxsec=Integer.parseInt(b.readLine());
			for(int sec=1;sec<=maxsec;sec++)
			{
				FileInputStream fis=new FileInputStream("E://secrettext//secret"+sec+".txt");
				FileWriter fw=new FileWriter("E://binarysecrettext//bin"+sec+".txt");
				int c,p;
				String s1=new String();
				while((c=fis.read())!=-1)
				{
					p=(int)c;
					s1=toBina(p);
					System.out.println(p);
					fw.write(s1);
			    }
			         fw.close();
			        System.out.println("convertion to binary is succesfull for "+sec+" files");
			}
		}
		catch(Exception e){}
	}
	public static String toBina(int x)
	{
		int numbb=x;
	    StringBuilder buf1=new StringBuilder();
	    StringBuilder buf2=new StringBuilder();
	    while (numbb != 0){
	        int digit = numbb % 2;
	        buf1.append(digit);
	        numbb = numbb/2;
	    }
	    String binary=buf1.reverse().toString();
	    int length=binary.length();
	    if(length<8){
	       while (8-length>0){
	           buf2.append("0");
	           length++;
	       }
	    }
	     String k=buf2.toString()+binary;
		return(k);
	}

}
