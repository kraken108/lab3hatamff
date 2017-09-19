import java.rmi.*;

public interface Notifiable extends Remote {
    public void notifyNewMessage(String message) throws RemoteException;
    public void changeName(String name) throws RemoteException;
    public String getName() throws RemoteException;
}