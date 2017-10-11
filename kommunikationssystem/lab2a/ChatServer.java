
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements Chat {

    private  ArrayList<Notifiable> clientList = null;
    private ArrayList<String> clientNames;
    private int clientNr;

    public ChatServer() throws RemoteException {
        super();
        clientList = new ArrayList<Notifiable>();
        clientNr = 1;
        clientNames = new ArrayList<>();

    }

    private String changeName(String message) {
        String[] s = message.split(" ");
        if (s.length < 2) {
            return "";
        }
        return s[1];
    }

    synchronized public String sendMessage(String message, Notifiable client) throws RemoteException {
        if (message.equals("/help")) {
            return helpMessage();
        } else if (message.equals("/quit")) {
            return "Bye bye";
        } else if (message.startsWith("/nick ")) {
            String name = changeName(message);
            if (message.equals("")) {
                return "Please specify a name.";
            }
            client.changeName(name);
            clientNames.set(clientList.indexOf(client), name);
            return "Hello " + name;
        } else if (message.equals("/who")) {
            return getConnectedClients();
        } else if (message.startsWith("/")) {
            return "Invalid command";
        } else {
            ArrayList<Notifiable> toRemove = new ArrayList<>();
            for (Notifiable n : clientList) {
                try {
                    n.notifyNewMessage(client.getName() + ": " + message);
                } catch (RemoteException re) {
                    toRemove.add(n);
                }
            }
            for (Notifiable n : toRemove) {
                System.out.println("removing failed client");
                deRegisterForNotifications(n);
            }
        }
        return "";
    }

    synchronized public void registerForNotification(Notifiable n) throws RemoteException {
        clientList.add(n);
        n.changeName("Client " + clientNr);
        clientNames.add("Client " + clientNr);
        clientNr += 1;
        n.notifyNewMessage("Hi and welcome to the chat!\n" + helpMessage());
    }

    synchronized public void deRegisterForNotifications(Notifiable n) throws RemoteException {
        System.out.println("tar bort");
        int x = clientList.indexOf(n);
        clientList.remove(n);
        String name = clientNames.get(x);
        clientNames.remove(x);

        ArrayList<Notifiable> toRemove = new ArrayList<>();
        for (Notifiable no : clientList) {
            try {
                no.notifyNewMessage("Server: " + name + " has disconnected from the chat.");
            } catch (RemoteException re) {

                toRemove.add(n);
                //deRegisterForNotifications(no);
            }
        }
        for (Notifiable no : toRemove) {
            System.out.println("removing failed client");
            deRegisterForNotifications(no);
        }
    }

    public static void main(String[] args) {
        try {
            ChatServer chatServer = new ChatServer();
            Naming.rebind("chatserver", chatServer);
            System.out.println("Chatserver is running...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String helpMessage() {
        String s = "";
        s += "The following commands are supported:\n";
        s += "/quit - disconnect from the chat\n";
        s += "/who - list all connected clients\n";
        s += "/nick <nickname> - give yourself a nickname\n";
        s += "/help - list all available commands\n";

        return s;
    }

    private String getConnectedClients() throws RemoteException {
        String s = "Connected clients: [";

        ArrayList<Notifiable> toRemove = new ArrayList<>();
        for (Notifiable n : clientList) {
            try {
                s += n.getName();
                s += ", ";
            } catch (RemoteException re) {
                
               // deRegisterForNotifications(n);
                toRemove.add(n);
            }
        }
        s += "]";

        for (Notifiable n : toRemove) {
            System.out.println("removing failed client");
            deRegisterForNotifications(n);
        }
        return s;
    }

}
