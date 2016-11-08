import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgC {

	public static void main(String[] args) throws Exception {	
				
		ServerSocket socketServer = new ServerSocket(1816);
		System.out.println("The Multi-Client Server Connection Established");
		int flag=0;
		try {
			while (true) {
				new ClientThread(socketServer.accept(),"Client"+flag).start();
				flag++;
			}
		} finally {
			socketServer.close();
		}
	}

	private static class ClientThread extends Thread {
		
				
		private String Client;
		private Socket socket;
		private BufferedReader bufferReader;
		
		public ClientThread(Socket socket,String clientname) {
			this.socket = socket;
			Client=clientname;
		}

	
		public void run() {
			try {

			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
					String message = bufferReader.readLine(); 
					System.out.println("CLIENT Message : "+ Client+"  and the Received Message is: "+message);
				
			} catch (IOException e) {
				System.out.println(e);
			} finally {

				try {
					socket.close();
				} 
				catch (IOException e) {
				}
			}
		}
	}

}
