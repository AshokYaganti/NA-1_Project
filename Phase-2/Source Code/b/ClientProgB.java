import java.io.*; 
import java.net.*; 
import java.util.Scanner;
class ClientProgB { 

	public static void main(String argv[]) throws Exception 
	{ 
		
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Client Connection Established");
		Socket socketClient=null; 
		String message = ""; 
		String receivedMessage=""; 


			while(!message.equals("exit")){
				System.out.println("Input your Message : ");
				socketClient= new Socket("Server.ayktb.ch-geni-net.geni.it.cornell.edu", 2034);
				DataOutputStream outputStream = new DataOutputStream(socketClient.getOutputStream()); 
				BufferedReader serverMessage = new BufferedReader(new InputStreamReader(socketClient.getInputStream())); 
				message = bufferReader.readLine(); 
				outputStream.writeBytes(message + '\n'); 
				if(message.equals("exit")){
					receivedMessage = serverMessage.readLine(); 
					System.out.println("FROM SERVER: " + receivedMessage); 
				}				

			}   
			
			socketClient.close(); 
				

	} 
} 