from socket import *

serverName = "localhost"
serverPort = 12000

clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName,serverPort))

sentence = input('')

clientSocket.send(sentence.encode('utf-8'))

modifiedSentence = clientSocket.recv(1024)

print("Resposta do servidor para a operacao:", modifiedSentence.decode('utf-8'))

clientSocket.close()