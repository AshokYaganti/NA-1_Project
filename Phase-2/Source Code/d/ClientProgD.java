import java.net.*;
import java.io.*;

public class ClientProgD implements Runnable
{  

   private Thread Clientthread = null;
   private BufferedReader  bufferReader = null;
   private DataOutputStream outputStream = null;
   private ThreadClient client = null;
   private Socket socket= null;
  
   public static void main(String args[])
   { 
	   ClientProgD client = null;

	   client = new ClientProgD("Server.ayktb.ch-geni-net.geni.it.cornell.edu",12345);
	   
	   System.out.println("Client Connection Establishment");
	   
   }
   public ClientProgD(String serverName, int serverPort)
   { 

      try
      {  socket = new Socket(serverName, serverPort);
        
         start();
      }
      catch(Exception e)
      {  
    	  e.printStackTrace();
      }
   }
   public void run()
   {  while (Clientthread != null)
      {  try
         {  outputStream.writeUTF(bufferReader.readLine());
            outputStream.flush();
         }
         catch(IOException e)
         { 
        	 e.printStackTrace();
        	 stop();
         }
      }
   }
   public void handle(String message)
   {  
         System.out.println("Server message to all Clients is : "+message);
   }
   public void start() throws IOException
   {  
      bufferReader   = new BufferedReader(new InputStreamReader(System.in)); 
      outputStream = new DataOutputStream(socket.getOutputStream());
      if (Clientthread == null)
      {  
    	 client = new ThreadClient(this, socket);
         Clientthread = new Thread(this);                   
         Clientthread.start();
      }
   }
   public void stop()
   {  if (Clientthread != null)
      {  Clientthread.interrupt();  
         Clientthread = null;
      }
      try
      {  if (bufferReader   != null)  bufferReader.close();
         if (outputStream != null)  outputStream.close();
         if (socket    != null)  socket.close();
      }
      catch(IOException e)
      {  
    	  e.printStackTrace();
      }
      client.close();  
      client.interrupt();
   }
   public class ThreadClient extends Thread
   {  private Socket           socket   = null;
      private ClientProgD       client   = null;
      private DataInputStream  streamIn = null;

      public ThreadClient(ClientProgD _client, Socket _socket)
      {  client   = _client;
         socket   = _socket;
         open();  
         start();
      }
      public void open()
      {  try
         {  streamIn  = new DataInputStream(socket.getInputStream());
         }
         catch(IOException e)
         {  
       	  e.printStackTrace();
            client.stop();
         }
      }
      public void close()
      {  try
         {  if (streamIn != null) streamIn.close();
         }
         catch(IOException e)
         {  
       	  e.printStackTrace();
         }
      }
      public void run()
      {  while (true)
         {  try
            {  client.handle(streamIn.readUTF());
            }
            catch(IOException e)
            {  
           	 e.printStackTrace();
               client.stop();
            }
         }
      }
   }
}