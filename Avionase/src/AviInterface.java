
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface AviInterface extends Remote {
    public ArrayList<String> getServers() throws RemoteException;

}
