import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements Chat{
    private ArrayList<Notifiable> clientList = null;
    
    public ChatServer() throws RemoteException{
        super();
        clientList = new ArrayList<Notifiable>();
    }
    
    synchronized public void sendMessage(String message) throws RemoteException{
        for(Notifiable n : clientList){
            n.notifyNewMessage(message);
        }
    }
    
    synchronized public void registerForNotification(Notifiable n) throws RemoteException{
        clientList.add(n);
    }
    
    synchronized public void deRegisterForNotifications(Notifiable n) throws RemoteException{
        clientList.remove(n);
    }
    
    public static void main(String[] args){
        try{
            ChatServer chatServer = new ChatServer();
            Naming.rebind("chatserver",chatServer);
            System.out.println("Chatserver is running...");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}