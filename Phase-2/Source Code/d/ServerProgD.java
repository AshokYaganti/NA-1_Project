import java.net.*;
import java.io.*;

public class ServerProgD implements Runnable
{  
	
	private ServerSocket serversocket = null;
	private int count = 0;
	private Thread thread = null;
    private ServerThread serverThread[] = new ServerThread[20];
	public ServerProgD(int port)
	{ 
		try
		{  
			serversocket = new ServerSocket(port);  
			System.out.println("Server Connection Established");
			start(); 
		}
		catch(IOException ioe)
		{ 
			ioe.printStackTrace();			
		}
	}
   public void run()
   {  while (thread != null)
      {  try
         {  
            createThread(serversocket.accept()); }
         catch(IOException ioe)
         {  
        	stop(); 
         }
      }
   }
   public void start()  
   {
	   if (thread == null)
	      {  thread = new Thread(this); 
	         thread.start();
	      }
   }
   public void stop()   { 
	  
	   if (thread != null)
	      {  thread.interrupt(); 
	         thread = null;
	      }
	   }

   public synchronized void handle(int ID, String input)
   { 
         for (int i = 0; i < count; i++)
            serverThread[i].send( input);   
   }
  
   private void createThread(Socket socket)
   {  
        
         serverThread[count] = new ServerThread(this, socket);
         try
         {  serverThread[count].open(); 
         	serverThread[count].start();  
         	count++; 
         }
         catch(IOException e)
         { 
        	e.printStackTrace();
         } 
      
   }
   public static void main(String args[]) { 

	   ServerProgD server = null;

	   server = new ServerProgD(12345);
   }
   public class ServerThread extends Thread
   {  
	  private ServerProgD server    = null;
      private Socket           socket    = null;
      private int              ID        = -1;
      private DataInputStream  streamIn  =  null;
      private DataOutputStream streamOut = null;

      public ServerThread(ServerProgD _server, Socket _socket)
      {  super();
         server = _server;
         socket = _socket;
         ID     = socket.getPort();
      }
      public void send(String msg)
      {   try
          {  streamOut.writeUTF(msg);
             streamOut.flush();
          }
          catch(IOException ioe)
          { 
       	   ioe.printStackTrace();
             stop();
          }
      }
      public int getID()
      {  return ID;
      }
      public void run()
      { 
       	  System.out.println("Server Connected with all clients");
         while (true)
         {  try
            {  server.handle(ID, streamIn.readUTF());
            }
            catch(IOException ioe)
            { 
           	 ioe.printStackTrace();
               stop();
            }
         }
      }
      public void open() throws IOException
      {  
	     streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
         streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
      }
      public void close() throws IOException
      {  if (socket != null)    socket.close();
         if (streamIn != null)  streamIn.close();
         if (streamOut != null) streamOut.close();
      }
   }
}