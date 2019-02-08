import java.io.*;
import java.util.*;
import java.math.BigInteger;
class RSA
{
  public static void main(String s[]) throws IOException
  {
         
     DataInputStream ds=new DataInputStream(System.in);
     Random rand1=new Random();
     Random rand2=new Random();
     BigInteger p=BigInteger.probablePrime(16,rand1);
     BigInteger q=BigInteger.probablePrime(16,rand2);
     BigInteger r=p.multiply(q);
     BigInteger p1=p.subtract(new BigInteger("1"));
     BigInteger q1=q.subtract(new BigInteger("1"));
     BigInteger r1=p1.multiply(q1);
    
     System.out.println("Enter some sample public key value:");
     int pkey=Integer.parseInt(ds.readLine());
     	while(true)
     	{
        	BigInteger numgcd=r1.gcd(new BigInteger(""+pkey));
        	if(numgcd.equals(BigInteger.ONE))
           	break;
        	pkey++;
     	}
        
     	BigInteger pubkey=new BigInteger(""+pkey);
     	BigInteger prvkey=pubkey.modInverse(r1);
        
     	try
     	{
		        FileWriter fw=new FileWriter("keys.txt");
			FileWriter fw1=new FileWriter("rsaoutput.txt");
	          	String m=String.valueOf(prvkey);
			    fw.write(m);
			    fw.write("::");
			    String n=String.valueOf(r);
			    fw.write(n);
			    fw.close();
				
                FileInputStream fin=new FileInputStream("rsainput.txt");
        	int c;
        	while((c=fin.read())!=-1)
       		{ 
          			BigInteger val=new BigInteger(""+c);
         			BigInteger cival=val.modPow(pubkey,r);
                                String s1=String.valueOf(cival);
				
          			        fw1.write(s1);
					fw1.write("::");
         		
          	}
			fw1.close();
            System.out.println("\n\nENCRYPTION IS SUCCESSFULL\n\n");	
        }
        catch(Exception e)
        {
           
        }
	   
  }
}