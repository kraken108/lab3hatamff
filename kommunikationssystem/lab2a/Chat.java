import java.rmi.*;

public interface Chat extends Remote {
    public String sendMessage(String message,Notifiable client) throws RemoteException;
            
            
            //notifications register/deregister
    public void registerForNotification(Notifiable n) throws RemoteException;
    public void deRegisterForNotifications(Notifiable n) throws RemoteException;
}