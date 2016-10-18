import java.net.*;
import java.io.*;
public class UDPServer {

	public static void main (String [] args ) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(1568);
		System.out.println("Initialized the Server");
		Socket socketserver = serverSocket.accept();
		System.out.println("Connection has been established with Client : " + socketserver);
		
		FileWriter filewriter;	
		int filesize=2000000; 
		int bytes=0;
		int total = 0;
		byte [] barray  = new byte [filesize];
		InputStream inputstream = socketserver.getInputStream();
		FileOutputStream fos = new FileOutputStream("OutputFile.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bytes = inputstream.read(barray,0,barray.length);
		total = bytes;
		bos.write(barray, 0 , total);
		bos.flush();
		bos.close();
		System.out.println("File received from client");

		BufferedReader br = new BufferedReader(new FileReader("OutputFile.txt")); 

		filewriter = new FileWriter("OutputFile.txt" ,true); 
		
		filewriter.append("\n A line is added from server side to the input file sent from client side");
		filewriter.append("\n This is an extra line added to the source file");
		filewriter.close();

		File outputfile = new File ("OutputFile.txt");
		
		barray  = new byte [(int)transferFile.length()];
		FileInputStream fins = new FileInputStream(outputfile);
		BufferedInputStream bins = new BufferedInputStream(fins);
		bins.read(barray,0,barray.length);
		OutputStream outstream = socketserver.getOutputStream();		
		outstream.write(barray,0,barray.length);
		outstream.flush();
		socketserver.close();
		System.out.println("Modified file has been sent to the Client.");
	}
	
}
