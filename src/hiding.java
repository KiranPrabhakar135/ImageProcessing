import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.Color;
public class Image1 
{
	public static String inputdata;
	public static int pos=0;
   
	public static void main(String arg[])throws IOException
	{
		Image1 img=new Image1();
		try{
			BufferedReader br1=new BufferedReader(new FileReader("E:/divisions.txt"));
			int rows=0,cols=0;
		    String line;
		   
		    line=br1.readLine();
		    rows=Integer.parseInt(line);
		    
		    line=br1.readLine();
		    cols=Integer.parseInt(line);
		    
		    int seg=1,maxseg=rows*cols;
		    BufferedReader br=new BufferedReader(new FileReader("E://secret1.txt"));		
		    FileWriter fw1=new FileWriter("E://no. of seg.txt");
		    
		   while((inputdata=br.readLine())!=null && seg<=maxseg)
		   {
			   BufferedImage img1=ImageIO.read(new File("E://file/"+seg+".bmp"));//image segment in which data will be hidden
			   
			   pos=0;
			   																	
			   System.out.println(inputdata);									//input binary data from binary text file
			   
			   FileWriter fw=new FileWriter("E://secrettext//secret"+seg+".txt");		   //pixels in which data is hidden -file
			   PrintWriter pw=new PrintWriter(fw);
			   
			   if(img1.getHeight()>300|| img1.getWidth()>300){System.out.println("too large input image");}
			   
			   int[][] maincase=new int[17][img1.getWidth()*img1.getHeight()];
			   int[] s2=new int[img1.getWidth()*img1.getHeight()];
			   int i,j,min=0,p=0,c1,x,y,mc=0,k=0,xr1=0,yr1=0,xr,yr,mc1=0,c,np=0,k1,r,g,b,aa=0,bb=0,sbrv=0,sbgv=0,sbbv=0;
			   int[] maincaseindex=new int[18];
			   int[] sc=new int[7];
			   int[] index=new int[3];
			   int[] coll=new int[3];
			   
			   String[][] whichcolor=new String[17][img1.getWidth()*img1.getHeight()];
			   String [][]position=new String[17][img1.getWidth()*img1.getHeight()];;
			   String sec="",fina="";
			   
			   System.out.println("inputdata length"+inputdata.length());
			   
		       
			   for(i=0;i<18;i++)
			     maincaseindex[i]=0;
			    
			   System.out.println("image width="+img1.getWidth()+"**image height="+img1.getHeight());
			 
			   
			   for(i=0;i<img1.getWidth();i++)
			   {
			    for(j=0;j<img1.getHeight();j++)
			    {
			    
			    Color col=new Color(img1.getRGB(i,j),true);
			    
				s2[k]=img1.getRGB(i,j);
				
			    Integer I=new Integer(i);
				Integer J=new Integer(j);
				
			    
				p=col.getRed();
				r=(int)(p/16)+1;	
							    
				p=col.getGreen();
				g=(int)(p/16)+1;
							    
				p=col.getBlue();
				b=(int)(p/16)+1;
			    					
				if(r<=g)
				{
				   if(r<=g)
				    min=r;
				     
				    else if(b<=g)
					  min=b;
				}
			
			    else if(g<=b)
			         min=g;	
			         
			    else
			        min=b;
					
				maincaseindex[min]+=1;
				maincase[min][maincaseindex[min]]=s2[k];
				position[min][maincaseindex[min]]=I.toString()+"*"+J.toString();
				
				if(min==r)
				whichcolor[min][maincaseindex[min]]="100";
				
				if(min==g)
				whichcolor[min][maincaseindex[min]]="010";
				
				if(min==b)
				whichcolor[min][maincaseindex[min]]="001";
				
				k++;
				
			    }
			    
			   }
		
			  
			  for(i=1;i<=16;i++)
			   {
			    System.out.println("maincase number="+i+"**"+maincaseindex[i]);
				
			     for(j=1;j<=maincaseindex[i];j++)
				  {  
			    	
			    	 if(pos>=inputdata.length())
			    	 {
			    		System.out.println("data is hidden till "+(j-1)+"th pixel of "+i+"th maincase");
			    	    break;
			    	 }
			    	
					 if(maincase[i][j]!='\0' && whichcolor[i][j]!=null && j!=maincaseindex[i] && position[i][j]!=null )
					 {
			            		
						aa=Integer.parseInt(position[i][j].substring(0,(position[i][j].indexOf('*'))));
						bb=Integer.parseInt(position[i][j].substring((position[i][j].indexOf('*'))+1,(position[i][j].length())));
						Integer AA=aa;
						Integer BB=bb;
						System.out.println("initial integer value of "+aa+"*"+bb+" pixrl is"+img1.getRGB(aa,bb));
											 
						//converting into 8 bit binary
					   Color colr=new Color(img1.getRGB(aa,bb),true);
					   coll[0]=colr.getRed();
					   coll[1]=colr.getGreen();
					   coll[2]=colr.getBlue();
					   
					   String[] bin=new String[3];
					   for(int ii=0;ii<3;ii++)
					   {
					   int numbb=coll[ii];
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
					     bin[ii]=buf2.toString()+binary;
					   }
					   
					   StringBuffer sbr=new StringBuffer(bin[0]);
					   StringBuffer sbg=new StringBuffer(bin[1]);
					   StringBuffer sbb=new StringBuffer(bin[2]);
					   System.out.println("inital pixel value in binary is"+sbr.toString()+sbg.toString()+sbb.toString());
					   
					   //finding mc,x,y
						  index[1]=whichcolor[i][j].indexOf('1');
					      index[0]=whichcolor[i][j].indexOf('0');
						  index[2]=whichcolor[i][j].lastIndexOf('0');
						  
						  for(int ii=0;ii<3;ii++)
						   {
						       switch(index[ii]){
							   case 0:
								   switch(ii){
								   case 0:p=colr.getRed();
								   break;
								   case 1:p=colr.getGreen();
								   break;
								   case 2:p=colr.getBlue();
								   break;}
								   xr1=(int)(p/16)+1;
							   break;
							   
							   case 1:
								   switch(ii){
								   case 0:p=colr.getRed();
								   break;
								   case 1:p=colr.getGreen();
								   break;
								   case 2:p=colr.getBlue();
								   break;}
								   mc1=(int)(p/16)+1;
								break;
							  
							   case 2:
								   switch(ii){
								   case 0:p=colr.getRed();
								   break;
								   case 1:p=colr.getGreen();
								   break;
								   case 2:p=colr.getBlue();
								   break;}
								   yr1=(int)(p/16)+1;
								break;
							   }
							}
						 
						  //determine subcase
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
						  
						//find mc,xr,yr
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
						  
						//start hiding after determining sub case 
						if(mc>=2 && sc[1]==1)continue;
												
						if(mc>=1 && mc<=16 && sc[3]==1)
						  {
							System.out.println("in sc3");
							
							if(pos<=inputdata.length()-1)
							{
									sbr=img.hide3(sbr,1);
									sec=AA.toString()+","+BB.toString()+":red"+":1";
									fw.write(sec);
									pw.println();
							}//sc3 blue close
								
							if(pos<=inputdata.length()-1)
							{
									sbg=img.hide3(sbg,1);
									sec=AA.toString()+","+BB.toString()+":Green"+":1";
									fw.write(sec);
									pw.println();
							}//sc3 green close
														 
							if(pos<=inputdata.length()-1)
							{
								sbb=img.hide3(sbb,2);
								
								if(pos==inputdata.length()-1)
								{
									sec=AA.toString()+","+BB.toString()+":blue"+":1";
								}
								if(pos<inputdata.length()-1)
								{
									sec=AA.toString()+","+BB.toString()+":blue"+":2";
								}
								
								fw.write(sec);
								pw.println();
							}//sc3 blue close
						    
						    fina=sbr.toString()+sbg.toString()+sbb.toString();
							k=new Color(sbrv,sbgv,sbbv,255).getRGB();
							img1.setRGB(aa,bb,k);
				
						  }//sc3 close
						 
						 if(sc[2]==1 || sc[4]==1)
						  {
							continue;
						  }
						
						if(mc>=1 && mc<=16 && (sc[5]==1 || sc[6]==1))
						  {
																					
							if(sc[5]==1)
							{
								System.out.println("in sc5");
							
								if(pos<=inputdata.length()-1)
								{
																		
									if(pos==inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":red"+":1";
										sbr=img.hide56(sbr,1);
									}
								
									if(pos<inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":red"+":2";
										sbr=img.hide56(sbr,2);
									}
									
									fw.write(sec);
									pw.println();
									sbrv=Integer.parseInt(sbr.toString(),2);
								}//sc5 red close
							
								if(xr>=mc && pos<=inputdata.length()-1)
								{
																		
									if(pos==inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":green"+":1";
										sbg=img.hide56(sbg,1);
									}
									
									if(pos<inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":green"+":2";
										sbg=img.hide56(sbg,2);
									}
									
									fw.write(sec);
									pw.println();
									sbgv=Integer.parseInt(sbg.toString(),2);
								}//sc5 green close
							
								if(yr>=mc && pos<=inputdata.length()-1)
								{
																		
									if(pos==inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":blue"+":1";
										sbb=img.hide56(sbb,1);
									}
									if(pos<inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":blue"+":2";
										sbb=img.hide56(sbb,2);
									}
									
									fw.write(sec);
									pw.println();
									sbbv=Integer.parseInt(sbb.toString(),2);
								}//sc5 blue close
							
							fina=sbr.toString()+sbg.toString()+sbb.toString();
							k=new Color(sbrv,sbgv,sbbv,255).getRGB();
							img1.setRGB(aa,bb,k);
							
							}
							
							else
							{
								System.out.println("in sc6");
							
								if( pos<=inputdata.length()-1)
								{
								    
								    if(pos==inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":red"+":1";
										sbr=img.hide56(sbr,1);
									}
									if(pos<inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":red"+":2";
										sbr=img.hide56(sbr,2);
									}
								    
									fw.write(sec);
									pw.println();
									sbrv=Integer.parseInt(sbr.toString(),2);
								}//sc6 red close						
								
								if( pos<=inputdata.length()-1)
								{
								    								    
								    if(pos==inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":green"+":1";
										sbg=img.hide56(sbg,1);
									}
									
								    if(pos<inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":green"+":2";
										sbg=img.hide56(sbg,2);
									}
								    
									
								    fw.write(sec);
									pw.println();
									sbgv=Integer.parseInt(sbg.toString(),2);
								}//sc6 green close
								
								if( pos<=inputdata.length()-1)
								{
								    								    
								    if(pos==inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":blue"+":1";
										sbb=img.hide56(sbb,1);
									}
									
								    if(pos<inputdata.length()-1)
									{
										sec=AA.toString()+","+BB.toString()+":blue"+":2";
										sbb=img.hide56(sbb,2);
									}
								    
								    fw.write(sec);
									pw.println();
								    sbbv=Integer.parseInt(sbb.toString(),2);
								}//sc6 blue close
					
							  fina=sbr.toString()+sbg.toString()+sbb.toString();
							  k=new Color(sbrv,sbgv,sbbv,255).getRGB();
							  img1.setRGB(aa,bb,k);
							  							
							}//sc6 close
						  }//sc5 & sc6 close					    
					  
			            }//if close
					
					 System.out.println(i+"**"+j+"**"+k+"**"+pos);
					 System.out.println("final binary value of"+aa+"*"+bb+"pixel is"+fina);
					 System.out.println("final integer value of"+aa+"*"+bb+"pixel is"+img1.getRGB(aa,bb));
			      }//j close
				  
				} //i close
				System.out.println(i);	
				FileImageOutputStream fo1=new FileImageOutputStream(new File("E://file//"+seg+".bmp"));	
				ImageIO.write(img1,"bmp",fo1);
				fw.close();
				seg++;
			   }//while close
		   
		   fw1.write(String.valueOf(seg-1));
		   fw1.close();
		   System.out.println("data is hidden till "+(seg-1)+" segments and secret text files created in secret text folder");
		}//try close
		catch(Exception e)
		{}
		   
	}
StringBuffer hide3(StringBuffer s,int x)
{
	    StringBuffer color=new StringBuffer(s);
	    
		if(x==1 && pos<=inputdata.length()-1)
	    {
		  color.setCharAt(color.length()-1,inputdata.charAt(pos));
		  pos++;
        }

		if(x==2 && pos<=inputdata.length()-1)
		{
		   if(pos==inputdata.length()-1)
		   {
			   color.setCharAt(color.length()-1,inputdata.charAt(pos)); 
			   pos++;
			   return(color);
		   }
		   
		   if(pos<=inputdata.length()-1)
		   {
			  color.setCharAt(color.length()-2,inputdata.charAt(pos)); 
			  pos++;
	 
	    	  if(pos<=inputdata.length()-1)
	    	  {
	          color.setCharAt(color.length()-1,inputdata.charAt(pos));
	          pos++;
	          }
		   }
	 
		}
		System.out.println(pos);
		return(color);
}
	  
StringBuffer hide56(StringBuffer s,int x)
{
     StringBuffer color=new StringBuffer(s);
     if(x==2)
     {
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
     }
     if(x==1)
     {
    	 if(pos<=inputdata.length()-1)
		 {
	      color.setCharAt(color.length()-1,inputdata.charAt(pos));
	      pos++;
		 }
     }
	System.out.println(pos);
	return(color);
	 
}
}
