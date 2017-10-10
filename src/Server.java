import java.io.*;
import java.net.*;

/**
 * This is the main web server
 * @author james_cope
 */
public class Server {
	public static void main(String[] args) throws Exception {
		final int PORT = 4000;
		
		System.err.print("Opening socket on port "+PORT+" ... ");
		ServerSocket ss = new ServerSocket(PORT);
		System.err.println("done.");
		
		while (true) {
			System.err.print("Listening for connection on port "+PORT+" ... ");
			Socket cxn = ss.accept();
			System.err.println("made connection with "+cxn.getInetAddress());
			
			System.err.print("Creating input stream ... ");
			BufferedReader in = new BufferedReader(new InputStreamReader(cxn.getInputStream()));
			System.err.println("done.");
			
			System.err.print("Creating output stream ... ");
			PrintStream out = new PrintStream(cxn.getOutputStream());
			System.err.println("done.");
			
			System.err.println("INPUT RECEIVED:");
			String input;
			while((input = in.readLine()) != null && !(input.equals(""))) {
				System.err.println("OK: "+input);
			}
			
			System.err.println("POSTING OUTPUT...");
			out.print(
				  "<!doctype html>"
			+	"\n<html>"
			+	"\n<head><title>A webpage</title></head>"
			+	"\n<body><h1>A heading</h1><p>Some paragraph text</p></body>"
			+	"\n</html>"
			);
			
			System.err.print("Terminating connection ... ");
			cxn.close();
			System.err.println("done.");
		}
	}
}
