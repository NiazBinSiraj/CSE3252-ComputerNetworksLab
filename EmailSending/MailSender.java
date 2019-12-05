import java.io.*;
import java.net.*;

class MailSender {

	private Socket s = null;
	
	public MailSender(String IP, int port)
	{
		 try{
		 s=new Socket(IP,port);
         PrintWriter   os = new PrintWriter(s.getOutputStream(),true);
         BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));

         os.println("HELO " + "niazbinsiraj.com");
         System.out.println( "Response1: " + br.readLine());

        
         os.println("MAIL FROM:"+ " esha@niazbinsiraj.com");
         System.out.println( "Response2: " + br.readLine());
        
         os.println("RCPT TO:"+ " niaz@niazbinsiraj.com");
         System.out.println( "Response3: " + br.readLine());
                     

         os.println("DATA");
         System.out.println( "Response4: " + br.readLine());
        
         os.println("Subject: hMail Server Testing");
         os.println();
         os.println("This is a test message to test hmailserver with SMTP protocol. Thanks for using this. God Bless You.");
         os.println();
         os.println(".");
        
         System.out.println( "Response5: " + br.readLine());
                
                
                         
         os.println("QUIT");
         System.out.println( "Response6: " + br.readLine());
                
         System.out.println( "Response7: " + br.readLine());
		 	
		 }catch (IOException e) {
            System.out.println(e);
        }
	}
	
	
	public static void main(String argv[])
    {
		MailSender mailSender = new MailSender("127.0.0.1",25);
         
    }
}