import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Color;
import javax.imageio.ImageIO;

public class Extract {
	
	public static void main(String arg[])throws IOException
	{
		try
		{  
			BufferedReader br1 = new BufferedReader(new FileReader("E://no. of seg.txt"));
			int i;
			int s=Integer.parseInt(br1.readLine());
			FileWriter fw=new FileWriter("E://output.txt");
			
			for(i=1;i<=s;i++)
			{			
				BufferedReader br = new BufferedReader(new FileReader("E://secrettext1//"+i+".txt"));
				BufferedImage img=ImageIO.read(new File("E://file//"+i+".bmp"));
				String line;
				int x,y,d,red,green,blue;
				String colo,reds="",greens="",blues="";
				
				while ((line = br.readLine()) != null)
				{
				   
				   x=Integer.parseInt(line.substring(0,line.indexOf(',')));
				  
				   y=Integer.parseInt(line.substring(line.indexOf(',')+1,line.indexOf(':')));
				   
				   colo=line.substring(line.indexOf(':')+1,line.lastIndexOf(':'));
				   
				   d=Integer.parseInt(line.substring(line.lastIndexOf(':')+1),line.length());
				   
				   System.out.println(x+"*"+y+"*"+colo+"*"+d);
				   
				   Color col=new Color(img.getRGB(x,y),true);
				   
				   if(colo.equals("red"))
				   {
					   red=col.getRed();
					   reds=toBina(red);
					   System.out.println(reds);
					   if(d==2)
					   {
						  fw.write(reds.charAt(reds.length()-2));
						  fw.write(reds.charAt(reds.length()-1));
					   }
					   if(d==1)
					   {
						   fw.write(reds.charAt(reds.length()-1));
					   }
					   
				   }
				   
				   if(colo.equals("green"))
				   {
					   green=col.getGreen();
					   greens=toBina(green);
					   System.out.println(greens);
					   if(d==2)
					   {
					      fw.write(greens.charAt(greens.length()-2));
						  fw.write(greens.charAt(greens.length()-1));
					   }
					   if(d==1)
					   {
						   fw.write(greens.charAt(greens.length()-1));
					   }
				   }
				   
				   if(colo.equals("blue"))
				   {
					   blue=col.getBlue();
					   blues=toBina(blue);
					   System.out.println(blues);
					   if(d==2)
					   {
						  fw.write(blues.charAt(blues.length()-2));
						  fw.write(blues.charAt(blues.length()-1));
					   }
					   if(d==1)
					   {
						   fw.write(blues.charAt(blues.length()-1));
					   }
					  
				   }
				    
				}//while close
			}//for close
		  fw.close();
		}//try close
		
		catch(Exception e){}
	}
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
}
