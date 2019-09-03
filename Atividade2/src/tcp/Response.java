package tcp;

public class Response {

	private String statusLine;
	private String contentTypeLine;
	private String contentLengthLine;
	private byte[] entityBody;
	private static String CRLF="\r\n";
	
	public Response(String statusLine, String contentTypeLine, String contentLengthLine, byte[] entityBody) {
		this.statusLine = statusLine + CRLF;
		this.contentTypeLine = contentTypeLine + CRLF;
		this.contentLengthLine = contentLengthLine + CRLF;
		this.entityBody = entityBody;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public String getContentTypeLine() {
		return contentTypeLine;
	}

	public void setContentTypeLine(String contentTypeLine) {
		this.contentTypeLine = contentTypeLine;
	}

	public String getContentLengthLine() {
		return contentLengthLine;
	}

	public void setContentLengthLine(String contentLengthLine) {
		this.contentLengthLine = contentLengthLine;
	}

	public byte[] getEntityBody() {
		return entityBody;
	}

	public void setEntityBody(byte[] entityBody) {
		this.entityBody = entityBody;
	}
	
	
}
