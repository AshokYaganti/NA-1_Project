import java.io.*; 
import java.net.*; 

class ServerProgB { 


	public static void main(String argv[]) throws Exception 
	{ 
		int flag=0;
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)); 
		String clientSentence=""; 
		String returnSentence=""; 
		System.out.println("Server Connection Established");
		
		while(true){
			String message;
			ServerSocket serverSocket = new ServerSocket(2034);
			while(true&flag==0) { 
				Socket socket = serverSocket.accept();
				BufferedReader ClientMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream  outputStream = new DataOutputStream(socket.getOutputStream());
				clientSentence = ClientMessage.readLine(); 
				System.out.println("Client Message : "+clientSentence);
				
				if(clientSentence.equals("exit")){
					message="exit";
					returnSentence = message+ '\n'; 
					outputStream.writeBytes(returnSentence);
					outputStream.close();
					socket.close(); 
					serverSocket.close();
					flag=1;
				}
				else{
				
					outputStream.close();
				}

			} 
			flag=0;
		} 
	}
} 

