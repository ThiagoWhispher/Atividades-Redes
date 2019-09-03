from socket import *

serverPort = 12000
serverSocket = socket(AF_INET,SOCK_STREAM)
serverSocket.bind(('localhost',serverPort))
serverSocket.listen(1)

print ("The server is ready to receive")

while 1:
     connectionSocket, addr = serverSocket.accept()
     
     sentence = connectionSocket.recv(1024)

     capitalizedSentence = sentence.decode('utf-8').upper()

     operacao = capitalizedSentence.split(' ')

     OPERACAO_TIPO = 0
     OPERANDO_1 = 1
     OPERANDO_2 = 2

     if(operacao[OPERACAO_TIPO] == 'ADD'):
       response = float(operacao[OPERANDO_1]) + float(operacao[OPERANDO_2])
     elif(operacao[OPERACAO_TIPO] == 'SUB'):
       response = float(operacao[OPERANDO_1]) - float(operacao[OPERANDO_2])
     elif(operacao[OPERACAO_TIPO] == 'MULT'):
       response = float(operacao[OPERANDO_1]) * float(operacao[OPERANDO_2])
     elif(operacao[OPERACAO_TIPO] == 'DIV'):
       if(abs(float(operacao[OPERANDO_2]) - 1e-9) <= 0):
         response = float(operacao[OPERANDO_1]) / float(operacao[OPERANDO_2])
       else:
         response = 'nao eh possivel dividir'
     elif(operacao[OPERACAO_TIPO] == 'EXP'):
       response = float(operacao[OPERANDO_1]) ** float(operacao[OPERANDO_2])
     else:
       response = 'erro na sintaxe da operacao'

     connectionSocket.send(str(response).encode('utf-8'))
     connectionSocket.close()
