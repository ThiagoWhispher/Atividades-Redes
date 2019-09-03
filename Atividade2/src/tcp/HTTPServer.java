package tcp;

import java.io.*;
import java.net.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class HTTPServer {	
	
	public static void main(String[] args) throws Exception{		
		String requestMessageLine;
		String CRLF="\r\n";
		
		ServerSocket listenSocket = new ServerSocket(8087);
		
		while (true) {
			Socket connectionSocket = listenSocket.accept();
		
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			requestMessageLine = inFromClient.readLine();
			
			Request requestHTTP = new Request(requestMessageLine);
			Response responseHTTP = requestHTTP.getResponse();
			
			outToClient.writeBytes(responseHTTP.getStatusLine());
			outToClient.writeBytes(responseHTTP.getContentTypeLine());
			outToClient.writeBytes(CRLF);
			outToClient.write(responseHTTP.getEntityBody());

			connectionSocket.close();
		}		
		
	}
}
