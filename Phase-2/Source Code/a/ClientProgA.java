import java.io.*; 
import java.net.*; 
import java.util.Scanner;
class ClientProgA { 

	public static void main(String argv[]) throws Exception 
	{ 
		
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Client Connection Established");		
        String message = ""; 
		String receivedMsg=""; 
		Socket clientSocket=null; 	
			

			while(!message.equals("exit")){
				
				System.out.println("ENTER MESSAGE : ");
				clientSocket= new Socket("Server.ayktb.ch-geni-net.geni.it.cornell.edu", 1124);
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream()); 
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
				message = userInput.readLine(); 
				outputStream.writeBytes(message + '\n'); 
				receivedMsg = bufferReader.readLine(); 
				System.out.println("SERVER Message: " + receivedMsg); 

			}           
			clientSocket.close(); 
	} 
} 