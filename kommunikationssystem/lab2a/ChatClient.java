import java.rmi.*;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements Notifiable {
    private Chat chat;
    
    public ChatClient(Chat chat) throws RemoteException{
        super();
        this.chat = chat;
    }
    public void notifyNewMessage(String message) throws RemoteException{
        System.out.println(message);
    }
    
    public static void main(String[] args){
       
        try{
            String url = "rmi://localhost/chatserver";
            Chat chat = (Chat) Naming.lookup(url);
            ChatClient client = new ChatClient(chat);
            
            chat.registerForNotification(client);
            client.runClient();
        }catch(NotBoundException nbe){
            System.out.println(nbe.toString());
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    private void runClient() throws RemoteException{
        Scanner scan = new Scanner(System.in);
        while(true){
            String s = scan.nextLine();
            try{
                chat.sendMessage(s);
            }catch(RemoteException re){
                System.out.println("eghhhhh åhnej");
            }
        }
    }
}