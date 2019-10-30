
/**
 * Code is taken from Computer Networking: A Top-Down Approach Featuring 
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross, 
 * All Rights Reserved.
 **/

import java.io.*;
import java.net.*;
import java.time.*;

class UDPClient {
	public static void main(String args[]) throws Exception {

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket clientSocket = new DatagramSocket();
		clientSocket.setSoTimeout((int) Duration.ofSeconds(2).toMillis());

		InetAddress IPAddress = InetAddress.getByName("localhost");

		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();

		DatagramPacket sendPacket = null, receivePacket = null;

		while (true) {
			try {
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				clientSocket.send(sendPacket);
				System.out.println("#1. Enviando operação [" + sentence + "] para servidor");
				
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());
				System.out.println("#4. Resposta do servidor [" + modifiedSentence + "]");
				
				String confirmacao = "ACK " + sentence;
				sendPacket = new DatagramPacket(confirmacao.getBytes(), confirmacao.getBytes().length, IPAddress, 9876);
				clientSocket.send(sendPacket);
				System.out.println("#5. Confirmando que recebeu resultado com [" + confirmacao + "]");
				
				break;
			} catch (SocketTimeoutException e) {
				System.err.println("Erro ao enviar o segmento UDP");
			}
		}

		clientSocket.close();
	}
}
