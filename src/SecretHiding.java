import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.Color;
public class SecretHiding 
{
	public static void main(String arg[])throws IOException
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader("E://no. of seg.txt"));
			int maxt=Integer.parseInt(br.readLine());
			int t=1,i=0,j=0,c,c1=0,k,sbri,sbgi,sbbi,sbr1i=0,sbg1i=0,sbb1i=0,count=0,pos=0;
			StringBuffer sbr=new StringBuffer("");
			StringBuffer sbg=new StringBuffer("");
			StringBuffer sbb=new StringBuffer("");
			
			while(t<=maxt)
			{
				FileInputStream fis=new FileInputStream("E://binarysecrettext//bin"+t+".txt");
				BufferedImage img=ImageIO.read(new File("E://file//"+(t+26)+".bmp"));
				while((c=fis.read())!=-1)
				{
					for(i=0;i<img.getHeight() && i<img.getWidth();i++)
					{
						for(j=0;j<img.getWidth() && j<img.getHeight();j++)
						{
							c1=0;
							if(i==0 && j==0 || i==0 && j==1 || i==0 && j==2 || i==0 && j==3){continue;}
							Color col=new Color(img.getRGB(i,j),true);
					
							String s=new String();
							s=toBina(col.getRed());					
							sbr=new StringBuffer(s);
							s=toBina(col.getGreen());
							sbg=new StringBuffer(s);
							s=toBina(col.getBlue());
							sbb=new StringBuffer(s);
							
							sbr.setCharAt(sbr.length()-2,(char)c);
							c1++;
							count++;
							
							c=fis.read();
							if(c==-1)break;
							sbr.setCharAt(sbr.length()-1,(char)c);
							c1++;
							count++;
							
							c=fis.read();
							if(c==-1)break;
							sbg.setCharAt(sbr.length()-2,(char)c);
							c1++;
							count++;
							
							c=fis.read();
							if(c==-1)break;
							sbg.setCharAt(sbr.length()-1,(char)c);
							c1++;
							count++;
							
							c=fis.read();
							if(c==-1)break;
							sbb.setCharAt(sbr.length()-2,(char)c);
							c1++;
							count++;
							
							c=fis.read();
							if(c==-1)break;
							sbb.setCharAt(sbr.length()-1,(char)c);
							c1++;
							count++;
							
							c=fis.read();
							if(c==-1)break;
							
							sbri=Integer.parseInt(sbr.toString(),2);
							sbgi=Integer.parseInt(sbg.toString(),2);
							sbbi=Integer.parseInt(sbb.toString(),2);
							
							k=new Color(sbri,sbgi,sbbi).getRGB();
							img.setRGB(i,j,k);
							
						}//j loop end 
						
						if(c==-1)
							{
								sbri=Integer.parseInt(sbr.toString(),2);
								sbgi=Integer.parseInt(sbg.toString(),2);
								sbbi=Integer.parseInt(sbb.toString(),2);
								System.out.println("kkkend*"+t+"*"+i+"*"+j+"*"+sbri+"*"+sbgi+"*"+sbbi+"*"+count);
								k=new Color(sbri,sbgi,sbbi).getRGB();
								img.setRGB(i,j,k);
								break;
							}
												
					}//i loop end
										
				}	//while loop end
				
				String s1=new String(toBina(i)+toBina(j)+toBina(c1));
				System.out.println(s1);
				pos=0;
				
				for(j=0;j<=3;j++)
				{
					Color coll=new Color(img.getRGB(0,j),true);
					System.out.println(img.getRGB(0,j));
					StringBuffer sbr1=new StringBuffer(toBina(coll.getRed()));
					StringBuffer sbg1=new StringBuffer(toBina(coll.getGreen()));
					StringBuffer sbb1=new StringBuffer(toBina(coll.getBlue()));
					sbr1.setCharAt(sbr1.length()-2,s1.charAt(pos));
					pos++;
					sbr1.setCharAt(sbr1.length()-1,s1.charAt(pos));
					pos++;
					sbg1.setCharAt(sbg1.length()-2,s1.charAt(pos));
					pos++;
					sbg1.setCharAt(sbg1.length()-1,s1.charAt(pos));
					pos++;
					sbb1.setCharAt(sbb1.length()-2,s1.charAt(pos));
					pos++;
					sbb1.setCharAt(sbb1.length()-1,s1.charAt(pos));
					pos++;
					sbr1i=Integer.parseInt(sbr1.toString(),2);
					sbg1i=Integer.parseInt(sbg1.toString(),2);
					sbb1i=Integer.parseInt(sbb1.toString(),2);
					k=new Color(sbr1i,sbg1i,sbb1i).getRGB();
					img.setRGB(0,j,k);
					System.out.println(img.getRGB(0,j));
				}				
			
				FileImageOutputStream fo1=new FileImageOutputStream(new File("E://file//"+(t+26)+".bmp"));	
				ImageIO.write(img,"bmp",fo1);
				t++;
				count=0;
			}//t loop end
			System.out.println("secret data hidding over!!!!!");
		}
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
