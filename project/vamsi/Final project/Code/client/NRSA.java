import java.io.*;
import java.util.*;
import java.math.BigInteger;
class NRSA
{
  public static void main(String args[]) throws IOException
  {
     int c;
     String s1="";
	 String s2="";
	 String s3="";
     DataInputStream ds=new DataInputStream(System.in);
	 FileInputStream fin=new FileInputStream("keys.txt");
	 FileInputStream fin2=new FileInputStream("decompress.txt");
	 FileOutputStream fout=new FileOutputStream("originaloutput.txt");
	 while((c=fin.read())!=-1)
	 {
	   if((char)c==':')
	   {
	     break;
	   }
	   else
	    s1+=(char)c;
	 }
      //System.out.println(" "+s1);
	  c=fin.read();
	  while((c=fin.read())!=-1)
	 {
	    s2+=(char)c;
	 }
      //System.out.println(" "+s2);
	 BigInteger prvkey=new BigInteger(s1);
     BigInteger r=new BigInteger(s2);	
	 
	 while((c=fin2.read())!=-1)
	 {
	   if((char)c!=':')
	   {
	     s3+=(char)c;
	   }
       else if(s3!="")
	   {
	     //System.out.println(""+s3);
	     BigInteger cival=new BigInteger(""+s3);
	     BigInteger plval=cival.modPow(prvkey,r);
         int d=plval.intValue();
	     fout.write(d);
		 s3="";
	   }
    }
	
	System.out.println("\n\nDECRYTED SUCCESSFULLY\n\n");
	System.out.println("ORIGINAL FILE RECEIVED:\n\n");
  }
}