
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aliasbind
 */
public class Receiver extends Thread implements Serializable {
    Socket s;
    Receiver(Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            InputStream iss = s.getInputStream();
            Scanner sc = new Scanner(iss);
            while(true)
                System.out.println("CAUGHT: " + sc.next());
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
