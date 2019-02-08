import java.io.*;
class TextToBinary
{
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
  public static void main(String arg[])throws IOException
  {
	   try
	   {
		    int p;
		    FileInputStream fin=new FileInputStream("E://input.txt");
		    FileWriter fw1=new FileWriter("E://binary text.txt");
		    int c;
			String s1=new String();
			while((c=fin.read())!=-1)
			{
				p=(int)c;
				s1=toBina(p);
				System.out.println(p);
				fw1.write(s1);
		    }
		        fw1.close();
		        System.out.println("convertion to binary is succesfull");
	   }
	   catch(Exception e){}
  }
}