import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;

class Combine
{
 public static void main(String arg[])throws IOException
 {
   try
   {
		   BufferedReader br=new BufferedReader(new FileReader("E://divisions.txt"));
		   int rows=0,cols=0;
	       String line;
	       line=br.readLine();
	       
	       rows=Integer.parseInt(line);
	       System.out.println(rows);
	       line=br.readLine();
	       cols=Integer.parseInt(line);
	        System.out.println(cols);
	       
	        int segms = rows * cols; 
		    int fs1=0,s1=0,s2=0;
	        int segmWidth[]=new int[segms+1];
			int segmHeight[]=new int[segms+1]; 
			segmWidth[0]=0;
			segmHeight[0]=0;
	        int type[]=new int[segms+1];  
	          
	        File[] imgFiles = new File[segms+1];
			for (int i = 1; i <= segms; i++)
			{
				
	            imgFiles[i] = new File("E://file//"+ i + ".bmp");  
	        } 
			
	         
	        BufferedImage[] buffImages = new BufferedImage[segms+1];  
	        System.out.println(segms);
	        
	        for (int i = 1; i <= segms; i++)
			{
	        	buffImages[i] = ImageIO.read(imgFiles[i]);  
				type[i] = buffImages[i].getType(); 
				segmWidth[i] = buffImages[i].getWidth();  
				s1+=segmWidth[i];
				segmHeight[i] = buffImages[i].getHeight(); 
					
				if(i%rows==0)
				{
					if(i==rows)
					{ fs1=s1;}
					s2+=segmHeight[i];
				}
			}
			
	        System.out.println(fs1+"**"+s2);
	          
	        BufferedImage finalImg = new BufferedImage(fs1,s2,type[1]);  
	  
	        int num = 1,l=1;  
			int p=segmWidth[0];
			int q=segmHeight[0];
		
	        for (int i = 1; i <=cols; i++) {  
				p=segmWidth[0];
	            for (int j = 1; j <=rows; j++) {  
	                finalImg.createGraphics().drawImage(buffImages[num], p,q,segmWidth[l],segmHeight[i], null);  
	                num++;  
	              	p+=segmWidth[l];
					l++;
					
	            } 
					q+=segmHeight[i+rows];
	        }  
	        System.out.println("Image concatenated....."+(num-1));  
	        ImageIO.write(finalImg, "bmp", new File("E://finalImg.bmp"));
	       
   }
   
   catch(Exception e){}
   }
   }