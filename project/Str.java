class Str
{
 public static void main(String arg[])
 {
   String s="100";
   int[] a=new int[3];
   int p=0,xr1,yr1,mc1;
   a[0]=s.indexOf('0');
   a[1]=s.indexOf('1');
   a[2]=s.lastIndexOf('0');
  
   for(int i=0;i<3;i++)
   {
       switch(a[i]){
	   case 0:
	   	   switch(i){
		   case 0:p=16;
		   break;
		   case 1:p=16;
		   break;
		   case 2:p=16;
		   break;}
		   xr1=p;
		 System.out.println("xr1"+i+a[i]);  
	   break;
	   case 1:
	   switch(i){
		   case 0:p=16;
		   break;
		   case 1:p=16;
		   break;
		   case 2:p=16;
		   break;}
		   mc1=p;
		 System.out.println("mc1"+i+a[i]);  
	   break;
	   case 2:
	   switch(i){
		   case 0:p=16;
		   break;
		   case 1:p=16;
		   break;
		   case 2:p=16;
		   break;}
		   yr1=p;
		 System.out.println("yr1"+i+a[i]);  
	   break;
	   }
	}
}
}