import java.io.*; 
import java.net.*; 
import java.util.Scanner;
class ClientProgC { 

	public static void main(String argv[]) throws Exception 
	{ 
		

		BufferedReader clientMessage = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Client Connection Established");		
		
			Socket socketClient=null; 
			String message = ""; 
		   	while(!message.equals("exit")){
				System.out.println("Input Your Message : ");
				socketClient= new Socket("Server.ayktb.ch-geni-net.geni.it.cornell.edu", 1816);
				DataOutputStream outputStream = new DataOutputStream(socketClient.getOutputStream()); 
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socketClient.getInputStream())); 
				message = clientMessage.readLine(); 
				outputStream.writeBytes(message + '\n'); 		

			}           
			socketClient.close(); 

	} 
} 