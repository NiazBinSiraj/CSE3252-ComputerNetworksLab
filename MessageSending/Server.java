import java.io.*;
import java.net.*;

public class Server{
	
	private Socket clientSocket = null;
	private ServerSocket serverSocket = null;
	private DataInputStream dataIn = null;

	public Server(int port)
	{
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server Started");
			System.out.println("Waiting for a client ...");

			clientSocket = serverSocket.accept();
			System.out.println("Client accepted");
			
			dataIn = new DataInputStream(clientSocket.getInputStream());
			String message = (String) dataIn.readUTF();
			System.out.println("Message : " + message);

			System.out.println("Closing connection");
			clientSocket.close();
			dataIn.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args)
	{
		Server server = new Server(5000);
	}
}