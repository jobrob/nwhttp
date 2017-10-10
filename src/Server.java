import java.io.*;
import java.net.*;
import java.nio.file.*;

/**
 * This is the main web server
 * @author james_cope
 */
public class Server {
	public static void main(String[] args) throws Exception {
		final int PORT = 4000;
		
		System.err.print("Opening master socket on port "+PORT+" ... ");
		ServerSocket ss = new ServerSocket(PORT);
		System.err.println("done.\n");
		
		while (true) {
			System.err.print("Listening for connection on port "+PORT+" ... ");
			Socket client = ss.accept();
			System.err.println("made connection with "+client.getInetAddress());
			
			handleHTTPRequest(client);
			
			System.err.print("Terminating connection ... ");
			client.close();
			System.err.println("done.\n");
		}
	}
	
	public static void handleHTTPRequest(Socket client) throws Exception {
		System.err.print("Creating input stream ... ");
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.err.println("done.");
		
		System.err.print("Creating output stream ... ");
		PrintStream out = new PrintStream(client.getOutputStream());
		System.err.println("done.");
		
		String[] request = in.readLine().split(" ");
		switch (request[0]) {
			case "OPTIONS":
				break;
			case "GET":
				try {
					Files.copy(Paths.get("./site"+request[1]), out);
				} catch (Exception e) {
					System.err.println("ERROR: Could not process GET request: "+request[1]);
				}
				break;
			case "HEAD":
				break;
			case "POST":
				break;
			case "PUT":
				break;
			case "DELETE":
				break;
			case "TRACE":
				break;
			case "CONNECT":
				break;
			default:
				break;
		}
	}
}
