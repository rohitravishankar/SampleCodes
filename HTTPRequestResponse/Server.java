/*
 *  Server.java
 * 
 *  Version: Server.java, v 1.0 2018/11/02
 *  
 *  Revisions:
 *      Revision 1.0 2018/11/02 10:51:09
 *      Initial Revision
 *      
 */

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

/**
 * This is a simple web server to service request from a client
 * 
 * @author 
 */

public class Server {
	private static ServerSocket aServerSocket;
	private static final String DIRECTORY_PATH = "/Users/rohitravishankar/eclipse-workspace/PracticeProblems/src/";

	public Server() {

	}

	public Server(int port) {
		try {
			aServerSocket = new ServerSocket(port);
			aServerSocket.setSoTimeout(10000000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  To open communication and serve the client
	 * 
	 * @return		no return value
	 */
	public void acceptRequest() {
		try {

			System.out.println ("Listening on port: " + aServerSocket.getLocalPort());

			// Creating a socket to listen to the client
			Socket server = aServerSocket.accept();

			// Connection to client
			System.out.println("Connected to " + server.getRemoteSocketAddress());

			// Reading the input from the client
			BufferedReader in = new BufferedReader( new InputStreamReader( server.getInputStream() ) );

			// Creating the PrintStream to send the response
			PrintStream out = new PrintStream( new BufferedOutputStream( server.getOutputStream() ) ); 

			// Getting the request
			String request = in.readLine();

			//
			String filename = "";

			// Tokenizer to help split the request into tokens and extract the filename for the server to lookup
			StringTokenizer tokens = new StringTokenizer( request );
			try {

				// Parse the filename from the GET request
				if ( tokens.hasMoreElements() && tokens.nextToken().equalsIgnoreCase( "GET" )
						&& tokens.hasMoreElements() ) {
					filename = tokens.nextToken();
				}
				else {
					throw new FileNotFoundException();
				}

				// To remove the preceding and succeeding '/' 
				filename = filename.substring( 1, filename.length()-1 );

				// Open the file 
				InputStream inputStream = new FileInputStream( DIRECTORY_PATH + filename );

				// Determine the MIME type and print HTTP header
				String mimeType="text/plain";
				if ( filename.endsWith( ".jpg" ) || filename.endsWith( ".jpeg" ) ) {
					mimeType="image/jpeg";
				}		          
				else if ( filename.endsWith( ".gif" ) ) {
					mimeType="image/gif";
				}
				else if ( filename.endsWith( ".html" ) || filename.endsWith( ".htm" ) ) {
					mimeType="text/html";
				} 

				out.print( "HTTP/1.0 200 OK\r\n"+
						"Content-type: "+mimeType+"\r\n\r\n" );

				// Send file contents to client, then close the connection
				byte[] a = new byte[4096];
				int n;
				while ( ( n = inputStream.read( a ) ) > 0 ) {
					out.write( a, 0, n );
				}
				out.close();

				inputStream.close();
			}
			catch ( FileNotFoundException x ) {
				out.println( "HTTP/1.0 404 Not Found\r\n"+
						"Content-type: text/html\r\n\r\n"+
						"<html><head></head><body> HTTP 404"+filename+" not found</body></html>\n" );
				out.close();
			}

			server.close();
			aServerSocket.close();
		}  catch ( SocketTimeoutException s ) {
			System.out.println( "Socket timed out!" );
		} catch ( Exception e ) {

		}
	}

	public static void main( String [] args ) {
		int port = Integer.parseInt(args[0]);
		try {
			Server server = new Server(port);
			server.acceptRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
