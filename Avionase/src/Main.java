
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main {

    static public void createAndShowGUI() {
//        Start startFrame=new Start();
//        startFrame.setVisible(true);
        AuthFrame auth = new AuthFrame();
        auth.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    createAndShowGUI();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Could not load database driver.", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

}
