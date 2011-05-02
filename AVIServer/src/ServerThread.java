
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ServerThread extends Thread implements Serializable {

    ServerSocket ssocket;
    ArrayList<String> servers;

    ServerThread (ArrayList<String> servers) throws IOException {
        ssocket=null;
        this.servers=servers;

    }

    @Override
    public void run() {
        System.out.println("Threadul porneste");
        try {
           
                ssocket = new ServerSocket(6667);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket socket=null;
        try {
            
            socket = ssocket.accept();
            servers.add(socket.getInetAddress().toString());
            //new ServerThread(servers).start();

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
