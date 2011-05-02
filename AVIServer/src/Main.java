
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class Main {
    public static ArrayList<String> servers;
    final static int port = 5000;

    
    public static void main(String[] args) throws RemoteException,
						  MalformedURLException,
						  IOException {
        servers = new ArrayList<String> ();
        new ServerThread(servers).start();
	System.setSecurityManager(new RMISecurityManager());
	Registry registry = LocateRegistry.createRegistry(port);
	registry.rebind("AviClass", new AviClass(servers));
	System.out.println("Hello! server is ready.");
    }
        
    }


