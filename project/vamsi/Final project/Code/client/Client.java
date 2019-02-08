import java.net.*;
import java.io.*;
public class Client {
 
    public static void main (String [] args ) throws IOException {
        DataInputStream ds=new DataInputStream(System.in);
		int filesize=1022386;
        int bytesRead;
        int currentTot = 0;
        Socket socket = new Socket("127.0.0.1",8080);
        byte [] bytearray  = new byte [filesize];
        InputStream is = socket.getInputStream();
		String s;
		s="receive.txt";
        FileOutputStream fos = new FileOutputStream(s);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(bytearray,0,bytearray.length);
        currentTot = bytesRead;
 
        do {
           bytesRead =
              is.read(bytearray, currentTot, (bytearray.length-currentTot));
           if(bytesRead >= 0) currentTot += bytesRead;
        } while(bytesRead > -1);
 
        bos.write(bytearray, 0 , currentTot);
		System.out.println("\n\nFILE IS RECIEVED SUCCESSFULLY\n\n");
        bos.flush();
        bos.close();
        socket.close();
		
      }
	  
}


