
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class AviClass extends UnicastRemoteObject implements AviInterface {
    private ArrayList<String> servers;

    AviClass(ArrayList<String> servers) throws RemoteException{
        this.servers=servers;
    }

    public ArrayList<String> getServers() throws RemoteException {
        return servers;
    }


}
