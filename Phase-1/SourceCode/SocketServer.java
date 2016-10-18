import java.io.*; 
import java.net.*; 

class SocketServer { 

	public static void main(String argv[]) throws Exception 
	{ 
		 
		System.out.println("Intialized the Server Connection");
		ServerSocket tcpServer = new ServerSocket(64111); 
		String[] username;

		while(true) 
		{ 		
		      String receivedMsg; 
		      String sendMsg;
			try
			{
			   Socket tcpServerSocket = tcpServer.accept();
              try{			
			     BufferedReader ClientResponse = new BufferedReader(new InputStreamReader(tcpServerSocket.getInputStream()));
			     PrintWriter  MsgToClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);; 
			     receivedMsg = ClientResponse.readLine(); 
			     System.out.println("CLIENT Message : "+receivedMsg);
				 
				 switch(receivedMsg.subString(0,17))
				 {
					 case "Hello from Client-":
					 username = receivedMsg.split("-");
				     sendMsg = "Hello from Server-"+ username[1] + '\n'; 
					 break;
					 
					 case "Bye from Client-"
					  username = receivedMsg.split("-");
				      sendMsg = "Bye from Server-" + username[1] + '\n'; 
				      MsgToClient.writeBytes(sendMsg);
					  tcpServerSocket.close();
				      break;
					  
					 default					  
					  sendMsg = receivedMsg+ '\n'; 
				 }
				   MsgToClient.writeBytes(sendMsg); 
		         } 
				 finally 
			     {
                  tcpServerSocket.close();
                 }
			}
			 finally 
		     {
              tcpServer.close();
             }
		}
	} 
} 

