  
import java.io.*;
import java.net.*;

public class FileServer {
	
	
	private Socket clientSocket = null;
	private ServerSocket serverSocket = null;
	private DataInputStream dataIn = null;
	private FileOutputStream fileOut = null;
	
	public FileServer(int port)
	{
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server Started");
			System.out.println("Waiting for a client ...");
			
			
			clientSocket = serverSocket.accept();
			System.out.println("Client accepted");
			DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
			
			String str, size;
			str = (String)dataIn.readUTF();
			System.out.println("File  name " + str);

			size=(String)dataIn.readUTF();
			System.out.println("File size " + size);
			
			fileOut = new FileOutputStream(str);
			byte [] buffer = new byte[4096];
			
			int filesize = Integer.parseInt(size) *8;
			int read=0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = dataIn.read(buffer,0,Math.min(buffer.length,remaining)))>0){
				totalRead += read;
				remaining -= read;
				System.out.println("read " + totalRead + " bytes.");
				fileOut.write(buffer,0,read);
			}
			
			
			System.out.println("Closing connection");
			fileOut.close();
			dataIn.close();
			serverSocket.close();
			clientSocket.close();
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	public static void main(String[] args) {
		FileServer fileServer = new FileServer(5000);
	}

}