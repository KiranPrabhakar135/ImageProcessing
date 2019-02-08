import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.*;

public class BinaryToText 
{
	public static void main(String arg[])throws IOException
	{
		try
		{
			 int c=0,d,r;
			 char ch[]=new char[8];
			 FileInputStream fin=new FileInputStream("E://output.txt");
			 FileWriter fw=new FileWriter("E://text.txt");
			 
			 while((d=fin.read())!=-1 )
			 {
				 if(c<8)
				 {
					 ch[c]=(char)d;
					 c++;
				 } 
				 
				 if(c==8)
				 {
					 String s=new String(ch);
					 System.out.println(s);
					 r=Integer.parseInt(s,2);
					 s=String.valueOf((char)r);
					 System.out.println(s);
					 fw.write(s);
					 c=0;
					 continue;
				 }
			 }
					
			 fw.close();
			 System.out.println("RSA output is obtained in text.txt");
		}
		catch(Exception e){}
	}

}
