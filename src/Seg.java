import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
public class Seg
{
	public static void main(String[] args)throws IOException 
	{
		try
		{	
			DataInputStream dis=new DataInputStream(System.in);
			BufferedImage img=ImageIO.read(new File("E://finalImg.bmp"));
			FileWriter fw=new FileWriter("E://divisions.txt");
			PrintWriter pw=new PrintWriter(fw);
			System.out.println("enter the password");
			String password=new String(dis.readLine());
			
			System.out.println(img.getWidth());
			System.out.println(img.getHeight());
			System.out.println(password.length());
			
			int rw[]=new int[img.getWidth()];
			int rl[]=new int[img.getHeight()];
			rw[0]=0;
			rl[0]=0;
			int i,j,p=0,x=0,l=1,rws,rwsr,qs=0,rs=0,ts=0,ps=0;
			Integer file=1;
			
			for(i=0;i<password.length();i++)
			{   
				if(password.charAt(i)!='\0')
				  {
					p=(int)password.charAt(i);
					rw[l]=p;
					x=x+rw[l];
					l++;
				  }
				
				if(i+1==password.length()&& x<img.getWidth())
				{
					i=-1;
					continue ;
				}
				
				if(x>img.getWidth())
				{
					i=0;
				   break;
				}
			}
			
			rw[l-1]-=(x-img.getWidth());
			rws=l-1;
			System.out.println(rw[l-1]+"**"+(l-1));
			fw.write(String.valueOf(rws));
			pw.println();
			System.out.println("width*************************over");
			
			l=1;
			x=0;
			for(i=password.length()-1;i>=0;i--)
			{   
				if(password.charAt(i)!='\0')
				  {
					p=(int)password.charAt(i);
					rl[l]=p;
					x=x+rl[l];
					l++;
				  }
				
				if(i==0&&x<img.getHeight())
				{
					i=password.length();
					continue ;
				}
				
				if(x>img.getHeight())
				{
				   break;
				}
			}
			
			rl[l-1]-=(x-img.getHeight());
			System.out.println(rl[l-1]+"**"+(l-1));
			fw.write(String.valueOf(l-1));
			fw.close();
			System.out.println("height ***************************************************** over");
			
			BufferedImage img2; 
			j=1;
			rwsr=rws;
			for(i=1;rl[i]!='\0';i++)
			{  
			    qs+=rl[i-1];
			    ts=rl[i];
			    ps=rw[0];
			    
				while(rw[j]!='\0' && rws!=0)
				{
					
				  if(i%2!=0)
				   rs=rw[j];
				  
				  if(i%2==0)
				   {
					   rs= rw[rws];
					   rws--;
				   }
				 
				   img2=img.getSubimage(ps,qs,rs,ts);
				   FileImageOutputStream fo1=new FileImageOutputStream(new File("E://File1//" + file.toString()+ ".bmp"));	
				   ImageIO.write(img2,"bmp",fo1);
				   ps+=rs;
				   j++;
				   file++;
				}
				
				j=1;
				rws=rwsr;
				rs=0;
			}
			
			System.out.println("Segmentation over, divisions.txt is created and segments are stored in file folder");
		}
		catch(Exception e){}
	}

}
