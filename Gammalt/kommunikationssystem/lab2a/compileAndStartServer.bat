javac *.java
rmic ChatServer
COPY /Y "Chat.class" Chatserver
COPY /Y "ChatServer.class" Chatserver
COPY /Y "Notifiable.class" Chatserver
COPY /Y "Chat.class" Chatclient
COPY /Y "ChatClient.class" Chatclient
COPY /Y "ChatServer_Stub.class" Chatclient
COPY /Y "Notifiable.class" Chatclient
cd Chatserver
start rmiregistry
java ChatServer