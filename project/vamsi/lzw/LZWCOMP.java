import java.io.*;
import java.lang.Object;
import java.io.ObjectOutputStream;
import java.util.*;
import java.net.*;

class LZWCOMP
{
  public static int dictionary[][]=new int[10000][10000];
  public static int pattern[]=new int[10000];
  public static int dfree,psize;
    public static void ini()    
    {
          for(int i=0;i<10000;i++)   
          {
              for(int j=0;j<10000;j++)     
                   dictionary[i][j]=-1;
          }
          for(int i=0;i<10000;i++)
          {     
		     pattern[i]=-1;
          }
          for(int i=48;i<=58;i++)
          {
             dictionary[i][0]=i;
          }
          dfree=59;
		  psize=0;
    }
	
	public static boolean indict(int x)
    {  
         pattern[psize]=x;
         psize++;
         for(int i=0;i<10000;i++)   
          {
             if(pattern[0]==dictionary[i][0])
              {
                  int n=1;
                  for(int j=1;j<psize;j++)
                   {
                        if(pattern[j]==dictionary[i][j])
                            n++;  
                   }
                  if(n==psize)
                     return true;  
               }
           }     
        pattern[psize-1]=-1;    
        psize--;      
       return false;   
   }
   
   public static void index(DataOutputStream out)throws IOException
   {
         for(int i=0;i<10000;i++)  
          {            
              if(pattern[0]==dictionary[i][0])   
               {             
                  int n=1;       
                  for(int j=1;j<psize;j++)
                   {
                      if(pattern[j]==dictionary[i][j])
                          n++;
                   }           
                  if(n==psize)            
                   {                
                      if(i>58)
                       {          
                             int k=0;              
                             out.write(244);
                             while(i>58)           
                             {
                                 i-=58;
                                 k++;
                             }
                             out.write(k);
                       }
                      out.write(i);      
                      return;      
                   }
               }       
          }   
    }

	public static void add(int x)
    {
         for(int i=0;i<psize;i++)           
            dictionary[dfree][i]=pattern[i];   
         dictionary[dfree][psize]=x;  
         dfree++;
    }
 
   public static void clear()    
    {     
         for(int i=0;i<psize;i++)        
              pattern[i]=-1;   
         psize=0;   
    }
	
	public static void compress(String infile,String outfile)
    {      
         try     
          {         
              DataInputStream in=new DataInputStream(new FileInputStream(infile));      
              DataOutputStream out=new DataOutputStream(new FileOutputStream(outfile));     
              int x=in.read();       
              while(x!=-1)          
               {       
                  if(indict(x));
                  else
                   {                 
                     index(out);
                     add(x);    
                     clear();    
                     psize=0;      
                     pattern[0]=x;    
                     psize++;       
                   }
                  x=in.read();    
               }
              index(out);     
              in.close();      
              out.close();
          }
   
         catch(FileNotFoundException ex)
          {
           
          }
      
         catch(IOException ex)
          {
             
          }

   }
   
   public static void main(String args[])throws IOException
   {
       String infile,outfile;    
       //Scanner cons=new Scanner(System.in);
	   //System.out.print("Input file: ");
       infile="rsaoutput.txt";
       //System.out.print("Output file: "); 
       outfile="transfer.txt";
       ini();
       System.out.println("\n\nCompressing......\n");	   
       compress(infile,outfile);
       System.out.println("\n\nCOMPRESSION IS DONE SUCCESSFULLY\n");
	   
   }
}   