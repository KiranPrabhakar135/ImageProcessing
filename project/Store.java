import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Arrays;
class Store
{
 public static void main(String arg[]) throws IOException
 {
 try{
  BufferedImage img=ImageIO.read(new File("E://project/kiran1.jpg"));
  FileOutputStream fos=new FileOutputStream(new File("E://k.jpg"));
  for(int i=1;i<=10;i++)
  {
  int s=img.getRGB(i,i);
  System.out.println(s);
   img.setRGB(i,i+10,1000);
   System.out.println(img.getRGB(i,i));
   
   }
   ImageIO.write(img,"jpg",fos);
   }
   catch(Exception e){}
   }
 }