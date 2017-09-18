import java.rmi.*;

public interface Chat extends Remote {
    public void sendMessage(String message) throws RemoteException;
    
    public void registerForNotification(Notifiable n) throws RemoteException;
    public void deRegisterForNotifications(Notifiable n) throws RemoteException;
}