import java.io.*; 
import java.net.*; 

class ServerProgA { 	

	public static void main(String argv[]) throws Exception 
	{ 
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)); 		
		System.out.println("Server Connection Established");
		ServerSocket serverSocket = new ServerSocket(1124); 
		String message;
        String clientMessage; 
		String acknowledgment=""; 
		while(true) {
			
			Socket socket = serverSocket.accept(); 
			BufferedReader clientResponse = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream  serverResponse = new DataOutputStream(socket.getOutputStream()); 

			clientMessage = clientResponse.readLine(); 

			System.out.println("From Client : "+clientMessage);

			if(clientMessage.equals("exit")){
				message="exit";
				acknowledgment = message+ '\n'; 

				serverResponse.writeBytes(acknowledgment);
				socket.close(); 
				serverSocket.close();
				return;
			}
			else{
				message = bufferReader.readLine();
				acknowledgment = message+ '\n'; 
				serverResponse.writeBytes(acknowledgment); 
			}

		} 
	} 
} 

