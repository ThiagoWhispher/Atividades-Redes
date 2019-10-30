/**
 * Code is taken from Computer Networking: A Top-Down Approach Featuring 
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross, 
 * All Rights Reserved.
 **/

import java.io.*; 
import java.net.*; 

class UDPServer { 
    public static void main(String args[]) throws Exception 
    { 
	
	DatagramSocket serverSocket = new DatagramSocket(9876); 
	
	while(true) 
	    { 
		
		byte[] receiveData = new byte[1024]; 
		byte[] sendData  = new byte[1024]; 
		
		DatagramPacket receivePacket = 
		    new DatagramPacket(receiveData, receiveData.length); 
		serverSocket.receive(receivePacket); 
		String sentence = new String(receivePacket.getData()); 
		
		InetAddress IPAddress = receivePacket.getAddress(); 
		
		int port = receivePacket.getPort(); 
		
		String recvOp = sentence.toUpperCase(); 

		System.out.println("Operação recebida [" + recvOp + "]");

		String[] operacao = recvOp.split(" ");

		int OPERACAO_TIPO = 0;
     		int OPERANDO_1 = 1;
     		int OPERANDO_2 = 2;

		String response = "";

		if(operacao[OPERACAO_TIPO].equals("ADD"))
			response = String.valueOf(Double.parseDouble(operacao[OPERANDO_1]) + Double.parseDouble(operacao[OPERANDO_2]));
		else if(operacao[OPERACAO_TIPO].equals("SUB"))
			response = String.valueOf(Double.parseDouble(operacao[OPERANDO_1]) - Double.parseDouble(operacao[OPERANDO_2]));
		else if(operacao[OPERACAO_TIPO].equals("MULT"))
			response = String.valueOf(Double.parseDouble(operacao[OPERANDO_1]) * Double.parseDouble(operacao[OPERANDO_2]));
		else if(operacao[OPERACAO_TIPO].equals("DIV")){
			if(Double.parseDouble(operacao[OPERANDO_2]) != 0.0)
				response = String.valueOf(Double.parseDouble(operacao[OPERANDO_1]) / Double.parseDouble(operacao[OPERANDO_2]));
			else
				response = "nao eh possivel dividir";
		}else if(operacao[OPERACAO_TIPO].equals("EXP"))
			response = String.valueOf(Math.pow(Double.parseDouble(operacao[OPERANDO_1]), Double.parseDouble(operacao[OPERANDO_2])));
		else
			response = "erro na sintaxe da operacao";
		
		sendData = response.getBytes();
		
		DatagramPacket sendPacket = 
		    new DatagramPacket(sendData, sendData.length, IPAddress, 
				       port); 
		
		serverSocket.send(sendPacket); 
	    } 
    } 
}  
