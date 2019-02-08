import java.io.*;
class FromBinaryFile
{
 public static void main(String arg[])throws IOException
 {
   try
   {
	   FileReader frb=new FileReader(new File("E://binary text.txt"));
	   DataInputStream dis=new DataInputStream(System.in);
	   int kbi,rbi,i=1;
	   char[] a=new char[1000];
	   FileWriter fw=new FileWriter("E://secret1.txt");
	   PrintWriter pw=new PrintWriter(fw);
	   
	   while(frb.ready())
	   {
	     rbi=20;
		 kbi=frb.read(a,0,rbi);
		 String s=new String(a,0,kbi);
		 
		 if(s.contains("\n"))
		 {
			 s=s.replace("\n","").replace("\r","");
			 System.out.println(s.length());
			 kbi=s.length();
		 }
		 
		 if(s.charAt(kbi-1)=='?')
		 {
			 System.out.println(i+"th image can hide only"+(kbi)+"bits");
			 String s1=new String(a,0,kbi-1);
			 fw.write(s1);
			 pw.println();
			 break;
		 }

		 fw.write(s);
		 pw.println();
		 System.out.println(s);
		 
		 i++;
		}
		fw.close();
		System.out.println("binary data is divided,stored in secret1.txt");
	}
	catch(Exception e){}
  }
}