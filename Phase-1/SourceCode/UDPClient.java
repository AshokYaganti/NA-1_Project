import java.net.*;
import java.io.*;
class UDPClient {

	public static void main (String [] args ) throws IOException {
		
		Socket clientSocket = new Socket("192.168.0.1",1568);
		File inputFile = new File("InputFile.txt");
		byte [] barray  = new byte [(int)inputFile.length()];
		FileInputStream fin = new FileInputStream(inputFile);
		BufferedInputStream bufferInput = new BufferedInputStream(fin);
		bufferInput.read(barray,0,barray.length);
		OutputStream outstream = socket.getOutputStream();
		System.out.println("File is ready to send");
		outstream.write(barray,0,barray.length);
		outstream.flush();
		int total = 0;	
		int bytes;
		int filesize=34520000; 	
		System.out.println("File has been sent to the Server");
		barray  = new byte [filesize];
		InputStream inputstream = socket.getInputStream();
		FileOutputStream foutstream = new FileOutputStream("InputFile.txt");
		BufferedOutputStream bouts = new BufferedOutputStream(foutstream);
		bytes = inputstream.read(barray,0,barray.length);
		total = bytes;
		do {
			bytes =
					inputstream.read(barray, total, (barray.length-total));
			if(bytes >= 0) total += bytes;
		} while(bytes > -1);
		bouts.write(barray, 0 , total);
		bouts.flush();
		bouts.close();
		clientSocket.close();
		BufferedReader br = new BufferedReader(new FileReader("InputFile.txt")); 
		System.out.println("Updated file has been received from server.");
	}
}
