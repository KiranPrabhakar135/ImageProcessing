import java.io.*;
import java.util.*;
import java.math.BigInteger;
class NewRSA
{
  public static void main(String arg[]) throws IOException
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
        System.out.println("PUBLIC KEY:"+pkey+"\nP:"+p+"\nq:"+q+"\nR:"+r+"\nR1:"+r1+"\n");
     	BigInteger pubkey=new BigInteger(""+pkey);
     	BigInteger prvkey=pubkey.modInverse(r1);
        System.out.println("PRIVATE KEY:"+prvkey+"\n"); 
     	try
     	{
		        FileWriter fw=new FileWriter("Keys.txt");
				FileWriter fw1=new FileWriter(" RSA.txt");
			    String m=String.valueOf(prvkey);
			    fw.write(m);
				fw.write("xx");
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
					fw1.write("xx");
         		
          	}
			fw1.close();
        	
        }
        catch(Exception e)
        {
           
        }
  }
}