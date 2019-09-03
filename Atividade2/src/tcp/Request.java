package tcp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class Request {
	private String requestMessageLine;

	public Request(String requestMessageLine) {
		this.requestMessageLine = requestMessageLine;
	}

	private String getExtention(String fileName) {
		String extension = "";
		
		int i = fileName.lastIndexOf('.');
		if (i >= 0) { extension = fileName.substring(i+1); }
		
		return extension;
		
	}
	
	public Response getResponse() {
		StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

		String fileName, statusLine = null, contentTypeLine = null, contentLengthLine = null;
		byte bite[] = null;
		
		if (tokenizedLine.nextToken().equals("GET")) {
			fileName = tokenizedLine.nextToken();

			if (fileName.startsWith("/"))
				fileName = fileName.substring(1);

			BufferedReader objReader = null;
			try {
				File file = new File(fileName);
				if(file.exists()) {
					bite = Files.readAllBytes(file.toPath());
					statusLine = "HTTP/1.0 200 OK";					
					contentLengthLine = "Content-Length: " + bite.length;
					
					switch(getExtention(fileName)) {
						case "txt":
							contentTypeLine = "Content-Type: text/plain";
							break;
						case "html":
							contentTypeLine = "Content-Type: text/html";
							break;
						case "json":
							contentTypeLine = "Content-Type: application/json";
							break;
						case "xml":
							contentTypeLine = "Content-Type: application/xml";
							break;
						case "jpeg":
							contentTypeLine = "Content-Type: image/jpeg";
							break;
						case "pdf":
							contentTypeLine = "Content-Type: application/pdf";
							break;
					}
				
				}else {
					statusLine = "HTTP/1.0 404 Not Found";
					contentTypeLine = "Content-Type: text/html";
					bite = new String("<HTML>" + 
							"<HEAD><TITLE> Não encontrado.</TITLE></HEAD>" +
							"<BODY> Arquivo " + fileName + " não encontrado.</BODY></HTML>").getBytes();
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (objReader != null)
						objReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return new Response(statusLine, contentTypeLine, contentLengthLine, bite);
	}
}
