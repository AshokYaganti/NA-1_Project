import java.io.*; 
import java.net.*; 
class SocketClient { 

	public static void main(String argv[]) throws Exception 
	{ 
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Socket tcpClient=null; 			
		String acknowledgement=""; 
		String userinput="";	
		try
	    {
			tcpClient= new Socket("Server.ayktb.ch-geni-net.geni.it.cornell.edu", 64111);
		    System.out.println("Server accepted the client Connection");		
   		    System.out.println("Please enter your message : ");
			userinput = input.readLine(); 	
			PrintWriter output = new PrintWriter(new BufferedWriter (new OutputStreamWriter(socket.getOutputStream())),true);  
			BufferedReader ServerInput = new BufferedReader(new InputStreamReader(tcpClient.getInputStream())); 				
		   
		   if(userinput.startsWith("Hello from Client"))
		   {		
		
			   while(!acknowledgement.startsWith("Bye from Server" ))
			   {						
				output.writeBytes(userinput + '\n'); 
				acknowledgement = ServerInput.readLine(); 
				System.out.println("Server Response Received: " + acknowledgement); 			    	
			   }           
			   tcpClient.close(); 
		   }
           else
		   {
            System.out.println("Something you have entered incorrect. Please check the intial message");
		   }
		}
		finally 
		{
          tcpClient.close();   
        }
	}
}
