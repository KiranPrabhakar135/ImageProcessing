import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.Raster;
import javax.imageio.stream.FileImageOutputStream;

class Chunks
{
 public static void main(String arg[])throws IOException
 {
   try{
	
        int rows = 19;   //we assume the no. of rows and cols are known and each chunk has equal width and height  
        int cols = 24;  
        int chunks = rows * cols; 
		
		int s1=0,s2=0;
        int chunkWidth[]=new int[chunks];
		int chunkHeight[]=new int[chunks];  
        int type[]=new int[chunks];  
        //fetching image files  
        File[] imgFiles = new File[chunks+1];
		
        for (int i = 1; i <= chunks; i++) {
			
            imgFiles[i] = new File("E:/file/"+ i + ".jpg");  
        } 
		//BufferedImage bufferedImage = new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB);

		//bufferedImage.getGraphics().drawImage(imgFiles[0], 0, 0, null);
		
	System.out.println("KKK");
       //creating a bufferd image array from image files  
        BufferedImage[] buffImages = new BufferedImage[chunks+1];  
		System.out.println(chunks);
        for (int i = 1; i <= chunks; i++)
		{  
			//Integer I=i;
		    buffImages[i] = ImageIO.read(imgFiles[i]);  
			System.out.println("KKK"+i);
			type[i] = buffImages[i].getType();  
			chunkWidth[i] = buffImages[i].getWidth();  
			s1+=chunkWidth[i];
			chunkHeight[i] = buffImages[i].getHeight();  
			s2+=chunkHeight[i];
		}
		System.out.println("KKK111");
        //Initializing the final image  
        BufferedImage finalImg = new BufferedImage(s1,s2,type[0]);  
  
        int num = 0,l=1;  
		int p=chunkWidth[0];
		int q=chunkHeight[0];
        for (int i = 1; i <=cols; i++) {  
			p=0;
            for (int j = 1; j <=rows; j++) {  
                finalImg.createGraphics().drawImage(buffImages[num], p,q, null);  
                num++;  
				p+=chunkWidth[l];
				l++;
            } 
				q+=chunkHeight[i+rows];
        }  
        System.out.println("Image concatenated.....");  
        ImageIO.write(finalImg, "jpeg", new File("finalImg.jpg"));  
   }
   
   catch(Exception e){}
   }
   }