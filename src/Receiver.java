
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    Start mainWindow;
    Receiver(Socket s, Start mainWindow) {
        this.mainWindow = mainWindow;
        this.s = s;
    }
    
    @Override
    public void run() {
        try {
            InputStream iss = s.getInputStream();
            Scanner sc = new Scanner(iss);
            while(true) {
                String msg = sc.next();

                System.out.println("Received: " + msg);
                if(msg.compareTo("READY") == 0) {
                    mainWindow.MMap.setReadyLabel(true);
                }

                if(msg.compareTo("NOTREADY") == 0) {
                    mainWindow.MMap.setReadyLabel(false);
                }

                if(msg.startsWith("B")) {
                    mainWindow.MMap.hitTarget(msg);
                }

                if(msg.compareTo("DIRECTHIT") == 0) {
                    mainWindow.OMap.hitResult(true);
                }

                if(msg.compareTo("MISS") == 0) {
                    mainWindow.OMap.hitResult(false);
                }

                if(msg.compareTo("LOST") == 0) {
                        JOptionPane.showMessageDialog(null, "You won!");
                        s.close();
                        iss.close();
                        mainWindow.OMap.dispose();
                        mainWindow.MMap.dispose();
                        mainWindow.dispose();
                        System.exit(0);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
