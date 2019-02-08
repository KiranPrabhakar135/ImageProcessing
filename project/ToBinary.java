import java.io.*;
class ToBinary
{
  public static void main(String arg[])throws IOException
  {
   try{
    int p,i,x;
   FileInputStream fin=new FileInputStream("transfer.txt");
   FileWriter fw1=new FileWriter("tobiinary.txt");
   int c;
	StringBuffer s1=new StringBuffer();
	while((c=fin.read())!=-1)
	{
		//p=Character.getNumericValue(encrypted.charAt(i));
		p=(int)c;
		//System.out.println(p);
		s1=s1.append(Integer.toBinaryString(p));
               //x=Integer.toBinaryString(p)
               fw1.write(s1.toString());
             
		//System.out.println(s1);
	}
         fw1.close();
        System.out.println("convertion to binary is succesfull");
       }
   catch(Exception e){}
  }
}