import java.io.*;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.color.*;
public class ExtractSecretHiding 
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
		BufferedReader br=new BufferedReader(new FileReader("E://no. of seg.txt"));
		int xw=Integer.parseInt(br.readLine());
		int seg=xw,j,p,i,index=0,x,y,n,txt=1,q=0,fin=0;
		
		try
		{
			for(txt=1;txt<=seg;txt++)
			{	
				BufferedImage img=ImageIO.read(new File("E://secretimage//"+(txt+seg)+".bmp"));
				
				FileWriter fw=new FileWriter("E://binarysecrettext1//"+txt+".txt");
											
				char a1[]=new char[6];
				char a[]=new char[24];
				index=0;
				
				for(i=0;i<=3;i++)
				{
					Color coll=new Color(img.getRGB(0,i),true);
									
				     String[] s=new String[3];
					 s[0]=new String(toBina(coll.getRed()));
					 s[1]=new String(toBina(coll.getGreen()));
					 s[2]=new String(toBina(coll.getBlue()));
				
					for(j=0;j<3;j++)
					{
						a[index]=s[j].charAt(s[j].length()-2);
						index++;
						a[index]=s[j].charAt(s[j].length()-1);
						index++;
					}
					
				}
				String sf=new String(a);
				x=Integer.parseInt(sf.substring(0,8),2);
				y=Integer.parseInt(sf.substring(9,16),2);
				n=Integer.parseInt(sf.substring(17,24),2);
				System.out.println(x+"*"+y+"*"+"*"+n);
				
				int min=img.getWidth()<img.getHeight()?img.getWidth():img.getHeight();
				fin=x*(min)+y-4;
				q=0;
				
				for(i=0;i<=x;i++)
				{
					for(j=0;j<min && q<=fin;j++)
					{
						if(i==0 && j==0 || i==0 && j==1 || i==0 && j==2 || i==0 && j==3){continue;}
						
						index=0;
						Color coll=new Color(img.getRGB(i,j),true);
						
						String[] s=new String[3];
						 s[0]=new String(toBina(coll.getRed()));
						 s[1]=new String(toBina(coll.getGreen()));
						 s[2]=new String(toBina(coll.getBlue()));
												 
						for(p=0;p<3;p++)
						{
							if(q==fin && index>n)break;
							a1[index]=s[p].charAt(s[p].length()-2);
							index++;
							
							if(q==fin && index>n)break;
							a1[index]=s[p].charAt(s[p].length()-1);
							index++;
						}
						
					String sf1=new String(String.valueOf(a1));
					
					if(q==fin && index>n)
					{
						 sf1=new String(String.valueOf(a1).substring(0,n));
					}
					
					fw.write(sf1);
					q=q+1;
					System.out.println(j+"*"+i+"*"+sf1);
										
					}
				}
				
				fw.close();
								
			}
			System.out.println("secret data extracted in binary form and stored in binary secrettext1 folder");
		}
		catch(Exception e){}
	}
}
