import java.io.*;
import java.net.*;

public class Client{
    
    private Socket clientSocket = null;
    private BufferedReader bufferReader = null;
    private DataOutputStream dataOut = null;

    public Client(String serverAddress, int port)
    {
        try {
            clientSocket = new Socket(serverAddress, port);
            System.out.println("Connected");

            bufferReader = new BufferedReader(new InputStreamReader(System.in));
            dataOut = new DataOutputStream(clientSocket.getOutputStream());
			
			System.out.print("Type Your Message : ");
			
            String message = bufferReader.readLine();
            dataOut.writeUTF(message);
            System.out.println("Message Sent");
            
            dataOut.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }



	public static void main(String[] args)
	{
		Client client = new Client("127.0.0.1",5000);
	}
}