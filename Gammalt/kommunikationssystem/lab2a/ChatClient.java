import static java.lang.System.exit;
import java.rmi.*;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements Notifiable {
    private Chat chat;
    private String name;
    
    public ChatClient(Chat chat) throws RemoteException{
        super();
        this.chat = chat;
    }
    public void notifyNewMessage(String message) throws RemoteException{
        System.out.println(message);
    }
    
    public void changeName(String name) throws RemoteException{
        this.name = name;
    }
    
    public String getName() throws RemoteException{
        return name;
    }
    
    public static void main(String[] args){
       
        try{
            if(args.length < 2){
                System.out.println("Please specify a ip and rmi server name (in the bash script)");
              //  exit(1);
            }
            String url = "rmi://" + args[0] + "/"+args[1];
            //String url = "rmi://localhost/chatserver";
            Chat chat = (Chat) Naming.lookup(url);
            ChatClient client = new ChatClient(chat);
            
            chat.registerForNotification(client);
            client.runClient();
        }catch(NotBoundException nbe){
            System.out.println(nbe.toString());
            
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("stänger av");
        
    }
    
    
    private void runClient() throws RemoteException{
        Scanner scan = new Scanner(System.in);
        while(true){
            String s = scan.nextLine();
            try{
                String str = chat.sendMessage(s,this);
                if(!str.equals("")){
                    System.out.println(str);
                }
                if(str.equals("Bye bye")){
                    chat.deRegisterForNotifications(this);
                    return;
                }
            }catch(RemoteException re){
                System.out.println("An error occurred. Shutting down: " + re);
                return;
            }
        }
    }
}