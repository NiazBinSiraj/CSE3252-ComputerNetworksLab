import java.io.*;
import java.net.*;

public class FileClient {

	private Socket clientSocket = null;
    private FileInputStream fileIn = null;
    private DataOutputStream dataOut = null;
	private BufferedReader bufferReader = null;
	
	public FileClient(String serverAddress, int port)
	{
		try {
			clientSocket = new Socket(serverAddress,port);
			System.out.println("Connected");
			
			
			dataOut = new DataOutputStream(clientSocket.getOutputStream());
			bufferReader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Type File Name : ");
			
			String str;
			str = bufferReader.readLine();
			dataOut.writeUTF(str);

			File file=new File(str);
			long file_size=(long) file.length() ;
			String siz=String.valueOf(file_size);
			dataOut.writeUTF(siz);

			dataOut.flush();
			fileIn = new FileInputStream(str);
			byte [] buffer = new byte[4096];
			
			while(fileIn.read(buffer)>0) {
				dataOut.write(buffer);
			}
			
			System.out.println("File Sent");
			
			dataOut.close();
			clientSocket.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}


	public static void main(String[] arg) {
		FileClient fileClient = new FileClient("127.0.0.1",5000);
	}
}