import java.rmi.*;

public interface Notifiable extends Remote {
    public void notifyNewMessage(String message) throws RemoteException;
}