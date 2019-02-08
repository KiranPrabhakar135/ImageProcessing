import java.net.*;
import java.io.*;
public class Server {
 
     public static void main (String [] args ) throws IOException {
          
            ServerSocket serverSocket = new ServerSocket(8080);
              Socket socket = serverSocket.accept();
			  DataInputStream ds=new DataInputStream(System.in);
              System.out.println("Accepted connection : " + socket);
			  String s="transfer.txt";
              File transferFile = new File (s);
              byte [] bytearray  = new byte [(int)transferFile.length()];
              FileInputStream fin = new FileInputStream(transferFile);
              BufferedInputStream bin = new BufferedInputStream(fin);
              bin.read(bytearray,0,bytearray.length);
              OutputStream os = socket.getOutputStream();
              System.out.println("Sending Files...");
              os.write(bytearray,0,bytearray.length);
              os.flush();
              socket.close();
              System.out.println("\n\nFile transfer complete\n\n");
            }
}


