import java.io.*;
class FromBinaryFile
{
 public static void main(String arg[])throws IOException
 {
   try
   {
	   FileReader frb=new FileReader(new File("E://text.txt"));
	   DataInputStream dis=new DataInputStream(System.in);
	   int kbi,rbi,i=1;
	   char[] a=new char[1000];
	   FileWriter fw=new FileWriter("E://secret1.txt");
	   PrintWriter pw=new PrintWriter(fw);
	   while(frb.ready())
	   {
	     
		 System.out.println("enter the number of characters to be hidden in"+i+"th image");
		 rbi=Integer.parseInt(dis.readLine());
		 kbi=frb.read(a,0,rbi);
		 String s=new String(a,0,kbi);
		 //String s1=System.getProperty(s);
		 System.out.println("kkkkbbbbiiiii"+kbi);
		 if(s.contains("\n"))
		 {
		 System.out.println("DDD");
		 s=s.replace("\n","").replace("\r","");
		 System.out.println(s.length());
		 kbi=s.length();
		 }
		 
		 if(s.charAt(kbi-1)=='?'){
		 System.out.println(i+"th image can hide only"+(kbi)+"bits");
		 String s1=new String(a,0,kbi-1);
		 fw.write(s1);
		 pw.println();
		 break;}
		 System.out.println("DDD");
		 fw.write(s);
		 pw.println();
		 System.out.println(s);
		 System.out.println();
		 i++;
		}
		fw.close();
		System.out.println("EEE");
	}
	catch(Exception e){}
  }
}