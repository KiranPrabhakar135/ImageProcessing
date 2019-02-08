import java.io.*;
import java.lang.Object;
import java.io.ObjectOutputStream;
import java.util.*;
import java.net.*;

class LZWDECOMP
{
  public static int dictionary[][]=new int[10000][10000];
  public static int pattern[]=new int[10000];
  private static int element[]=new int[10000];
  public static int dfree,psize;
  public static int esize=0;
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
			 element[i]=-1;
          }
          for(int i=48;i<=58;i++)
          {
             dictionary[i][0]=i;
          }
          dfree=59;
		  psize=esize=0;
    }
  public static void clear()    
    {     
         for(int i=0;i<psize;i++)        
              pattern[i]=-1;   
         psize=0;   
    }
	
  public static void decompress(String infile,String outfile)  
   {
        try
        {
            DataInputStream in=new DataInputStream(new FileInputStream(infile));   
            DataOutputStream out=new DataOutputStream(new FileOutputStream(outfile));        
            int x=in.read();   
            if(x!=-1)         
            {          
                element[0]=dictionary[x][0];       
                esize++;      
                out.write(dictionary[x][0]);     
            }      
           pattern[0]=element[0];       
           psize++;     
           while((x=in.read())!=-1)     
            {      
               if(x==244)        
                {     
                   int k=in.read();      
                   int l=in.read();             
                   x=k*58+l;           
                }                 
               if(x>=dfree)         
                {        
                     for(int i=0;i<esize;i++)
                          element[i]=-1;       
                     esize=0;   
                     for(int i=0;i<psize;i++)         
                      {                
                          element[i]=pattern[i];   
                          esize++;       
                      }         
                     element[esize]=pattern[0];         
                     esize++;     
                }   
               else 
               {        
                    for(int i=0;i<esize;i++)
                        element[i]=-1;
                    esize=0;        
                    for(int i=0;i<10000;i++)      
                     {           
                         if(dictionary[x][i]!=-1)      
                          {                   
                              element[i]=dictionary[x][i];         
                              esize++;           
                          }   
                         else 
                           break;
                     }
               }
               for(int i=0;i<esize;i++)
                    out.write(element[i]);      
               for(int i=0;i<psize;i++)
                     dictionary[dfree][i]=pattern[i];         
               dictionary[dfree][psize]=element[0];      
               dfree++;     
               clear();        
               for(int i=0;i<esize;i++)
                {              
                      pattern[i]=element[i];            
                      psize++;         
                }  
            }         
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
       infile="receive.txt";
       //System.out.print("Output file: "); 
       outfile="decompress.txt";
	   ini();
       decompress(infile,outfile);
       System.out.println("\n\nDECOMPRESSION IS DONE SUCCESSFULLY");           
       System.out.print("\n\n");
	}
}	