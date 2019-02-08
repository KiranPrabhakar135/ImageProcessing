import java.lang.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class Image1 
{

  public String inputdata=new String("kkkkkkkkkkkkkkkkkkkkkkk");
  int pos=1;

  public static void main(String a[])throws IOException
  {
   BufferedImage img1=ImageIO.read(new File("E://Project/Kiran1.jpg"));
   Image1 img=new Image1();
   
   String[][] maincase=new String[18][img1.getWidth()*img1.getHeight()];
   String[][] whichcolor=new String[18][img1.getWidth()*img1.getHeight()];
   int[][] flag=new int[18][img1.getWidth()*img1.getHeight()];
   int[][] whichposition=new int[img1.getHeight()][img1.getWidth()];
   String [][]position=new String[18][img1.getWidth()*img1.getHeight()];;
   try
  {
   String[] s2=new String[img1.getWidth()*img1.getHeight()];
   int i,j,min=0,p,c1,x,y,mc=0,k=0,xr1,yr1,xr,yr,mc1,c,mcs1,xrs1,yrs1,np=0,k1;
   int[] maincaseindex=new int[18];
   System.out.println(img1.getHeight());
   int[] sc=new int[7];
   
   for(i=0;i<18;i++)
     maincaseindex[i]=0;
    
   //System.out.println("KKKKKKK");
  
   for(i=0;i<img1.getWidth();i++)
   {
    for(j=0;j<img1.getHeight();j++)
    {
    
	s2[k]=Integer.toBinaryString(img1.getRGB(i,j));
    Integer I=new Integer(i);
	Integer J=new Integer(j);
    p=Integer.parseInt(s2[k].substring(8,16),2);
	//System.out.println(s2[k].substring(8,16));
	//System.out.println(p);
    c=(int)(p/16)+1;	
	//System.out.println(c);
    
	p=Integer.parseInt(s2[k].substring(16,24),2);
	//System.out.println(s2[k].substring(16,24));
	//System.out.println(p);
    x=(int)(p/16)+1;
	//System.out.println(x);
    
	p=Integer.parseInt(s2[k].substring(24,32),2);
	//System.out.println(s2[k].substring(24,32));
	//System.out.println(p);
    y=(int)(p/16)+1;
    //System.out.println(y);
		
	if(c<=x)
	{
	   if(c<=y)
	    min=c;
	     
	    else if(y<=x)
		  min=y;
	}

    else if(x<=y)
         min=x;	
         
    else
        min=y;
		
    
	maincaseindex[min]+=1;
	maincase[min][maincaseindex[min]]=s2[k];
	position[min][maincaseindex[min]]=I.toString()+"*"+J.toString();
	//System.out.println(position[min][maincaseindex[min]]+"**"+min+"**"+maincaseindex[min]);
	if(min==c)
	whichcolor[min][maincaseindex[min]]="100";
	
	if(min==x)
	whichcolor[min][maincaseindex[min]]="010";
	
	if(min==y)
	whichcolor[min][maincaseindex[min]]="001";
	
	//flag[min][maincaseindex[min]]=0;
	
	//whichposition[i][j]=min;
	k++;
    }
   }
   //System.out.println("KKKKKKK");
  
  for(i=1;i<=1;i++)
   {
    System.out.println(i+"**"+maincaseindex[i]);
	//System.out.println(position[i][maincaseindex[i]]+"**"+i+"**"+maincaseindex[i]);
     for(j=1;j<=maincaseindex[i];j++)
	  {  
	     
		 if(maincase[i][j]!= null && whichcolor[i][j]!=null && j!=maincaseindex[i] && position[i][j]!=null )
		 {
            		
			int aa=Integer.parseInt(position[i][j].substring(0,(position[i][j].indexOf('*'))));
			int bb=Integer.parseInt(position[i][j].substring((position[i][j].indexOf('*'))+1,(position[i][j].length())));
			//System.out.println(maincase[i][j]);
		 
			 int q=whichcolor[i][j].indexOf('1');
			 StringBuffer sbc=new StringBuffer(maincase[i][j].substring((8*(q+1)),((8*(q+1))+8)));
			 //System.out.println(maincase[i][j].substring((8*(q+1)),((8*(q+1))+8)));
			  p=Integer.parseInt(sbc.toString(),2);
			  //System.out.println(p);
			  mc1=(int)(p/16)+1;
			  //System.out.println("mc1"+mc1);
			  
			  int qs1=whichcolor[i][j+1].indexOf('1');
			   //System.out.println(qs1);
			 StringBuffer sbcs1=new StringBuffer(maincase[i][j+1].substring((8*(qs1+1)),((8*(qs1+1))+8)));
			  if(q==qs1)
			  {
				// StringBuffer sbcs1=new StringBuffer(maincase[i][j+1].substring((8*(qs1+1)),((8*(qs1+1))+8)));
				// System.out.println(maincase[i][j+1].substring((8*(qs1+1)),((8*(qs1+1))+8)));
				  p=Integer.parseInt(sbcs1.toString(),2);
				  //System.out.println(p);
				  mcs1=(int)(p/16)+1;
				  //System.out.println("mcs1"+mcs1);
				  flag[i][j+1]=1;
			  }
				else
				mcs1=0;
				
			  
			  q=whichcolor[i][j].indexOf('0');
			  StringBuffer sbx=new StringBuffer(maincase[i][j].substring((8*(q+1)),((8*(q+1))+8)));
			   //System.out.println(maincase[i][j].substring((8*(q+1)),((8*(q+1))+8)));
			   p=Integer.parseInt(sbx.toString(),2);
			   //System.out.println(p);
			   xr1=(int)(p/16)+1;
			   //System.out.println("xr1"+xr1);
			   
			   qs1=whichcolor[i][j+1].indexOf('0');
			  if(q==qs1)
			  {
				 StringBuffer sbxs1=new StringBuffer(maincase[i][j+1].substring((8*(q+1)),((8*(q+1))+8)));
				 //System.out.println(maincase[i][j+1].substring((8*(q+1)),((8*(q+1))+8)));
				  p=Integer.parseInt(sbxs1.toString(),2);
				 // System.out.println(p);
				  xrs1=(int)(p/16)+1;
				 // System.out.println(xrs1);
				 flag[i][j+1]=1;
			   }
				else
				xrs1=0;
			
    		  q=whichcolor[i][j].lastIndexOf('0');
			  StringBuffer sby=new StringBuffer(maincase[i][j].substring((8*(q+1)),((8*(q+1))+8)));
			  //System.out.println(maincase[i][j].substring((8*(q+1)),((8*(q+1))+8)));
			  p=Integer.parseInt(sby.toString(),2);
			  yr1=(int)(p/16)+1;
			  
			  qs1=whichcolor[i][j+1].lastIndexOf('0');
			  if(q==qs1)
			  {
				 StringBuffer sbys1=new StringBuffer(maincase[i][j+1].substring((8*(q+1)),((8*(q+1))+8)));
				 //System.out.println(maincase[i][j+1].substring((8*(q+1)),((8*(q+1))+8)));
				  p=Integer.parseInt(sbys1.toString(),2);
				  //System.out.println(p);
				  yrs1=(int)(p/16)+1;
				  //System.out.println(yrs1);
				  flag[i][j+1]=1;
			   }
				else
				yrs1=0;
				
			  //System.out.println(mc1+"*"+xr1+"*"+yr1);
			  //System.out.println(mcs1+"*"+xrs1+"*"+yrs1);
							
			if(xr1<mc1 && yr1<mc1)
			 sc[1]=1;
			
			if((xr1==mc1 && yr1<mc1) || (xr1<mc1 && yr1==mc1))
			  sc[2]=1;
			  
			if(xr1==mc1 && yr1==mc1)
			  sc[3]=1;
			
			if((xr1>mc1 && yr1<mc1) || (xr1<mc1 && yr1>mc1))
			  sc[4]=1;
			
			if((xr1>mc1 && yr1==mc1) || (xr1==mc1 && yr1>mc1))
			  sc[5]=1;
			  
			if(xr1>mc1 && yr1>mc1)
			  sc[6]=1;
			  
			
			if(mc1<=xr1)
			 {
			  if(mc1<=yr1)
				 mc=mc1;
			  else if(yr1<=xr1)
				  mc=yr1;
			 }
			else
				 mc=xr1;
			 
			 if(xr1<=yr1)
			  {
				 xr=xr1;
				 yr=yr1;
			   }
			  else
			   {
				  xr=yr1;
				  yr=xr1;
			   }
			  
			if(mc>=2 && sc[1]==1)
			{
			  //System.out.println("sc1");
			 			
			   if((mc==mc1 && mc1==mcs1 ) || (mc==xr1 && xr1==xrs1) || (mc==yr1 && yr1==yrs1))
			   {
			      
				 sbc=img.hide56(sbc,2);
				 k=Integer.parseInt(sbc.toString(),2);
				 img1.setRGB(aa,bb,k);
				 sbcs1=img.hide56(sbcs1,2);
				 
				 k1=Integer.parseInt(sbcs1.toString(),2);
				 img1.setRGB(aa,bb+1,k1);
				 //System.out.println(sbc.toString()+sbcs1.toString());
				
				}
			}
			
			if(mc>=1 && mc<=16 && sc[3]==1)
			  {
				   // orginalvalue[i][j]=sbc.charAt(Sbc.lenght()-1
					sbc=img.hide3(sbc,1);
				   
					sbx=img.hide3(sbx,1);
				 
					sby=img.hide3(sby,2);
					k=Integer.parseInt((sbc+sbx+sby).toString(),2);
					img1.setRGB(aa,bb,k);
					//System.out.println(sbc.toString()+sbx.toString()+sby.toString());
			  }
			 
			 if(sc[2]==1 || sc[4]==1)
			  {
				//System.out.println("sc2 or sc4 is considered so take nexty pixel");
				continue;
			  }
			
			if(mc>=1 && mc<=16 && (sc[5]==1 || sc[6]==1))
			  {
				if(sc[5]==1)
				{
				 sbc=img.hide56(sbc,2);
				
				if(xr>=mc)
				 sbx=img.hide56(sbx,2);
				
				if(yr>=mc)
				  sby=img.hide56(sby,2);
				 
				 k=Integer.parseInt((sbc+sbx+sby).toString(),2);
					img1.setRGB(aa,bb,k);
				}
				else
				{
				  sbc=img.hide56(sbc,2);
				 
				  sbx=img.hide56(sbx,2);
				 
				  sby=img.hide56(sby,2);
				
				k=Integer.parseInt((sbc+sbx+sby).toString(),2);
					img1.setRGB(aa,bb,k);
				
				}
				//System.out.println(sbc.toString()+sbx.toString()+sby.toString());
			  }  
		  
            }
		//System.out.println(j);
		
      }
	  np=np+j;
     //System.out.println("KKKKKKK"+i+np);  
	}  
//System.out.println(i);	
}
catch(Exception e)
{}

}
  StringBuffer hide3(StringBuffer s,int x)
  {
    StringBuffer color=new StringBuffer(s);
    
	if(x==1 && pos<=inputdata.length()-1)
    {
	  color.setCharAt(color.length()-1,inputdata.charAt(pos));
	  //System.out.println(color.toString());
	  pos++;
    }
    
	if(x==2 && pos<=inputdata.length()-1)
    {
	   
      color.setCharAt(color.length()-2,inputdata.charAt(pos)); 
      pos++;
	 
	 if(pos<=inputdata.length()-1)
	   {
        color.setCharAt(color.length()-1,inputdata.charAt(pos));
        pos++;
		}
     
    }
	
	 return(color);
  }
  
  StringBuffer hide56(StringBuffer s,int x)
   {
     StringBuffer color=new StringBuffer(s);
  
    if(pos<=inputdata.length()-1)
	 {
      color.setCharAt(color.length()-2,inputdata.charAt(pos)); 
      pos++;
	 }
	
	if(pos<=inputdata.length()-1)
	 {
      color.setCharAt(color.length()-1,inputdata.charAt(pos));
      pos++;
	 }
	 return(color);
   }
}
