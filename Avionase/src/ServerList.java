
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class ServerList extends javax.swing.JFrame {
    final static int port = 5000;
    private Start mainWindow;

   
    public ServerList(Start mainWindow) throws RemoteException, NotBoundException {
        initComponents();
        this.mainWindow=mainWindow;


        Registry registry = LocateRegistry.getRegistry("localhost", port);
	AviInterface AviInt = (AviInterface) registry.lookup("AviClass");
        System.out.println(AviInt.getServers());

        ArrayList <String> servers = AviInt.getServers();
        model = new DefaultListModel();
        for (String s:servers)
            model.addElement(s);

        jList1.setModel(model);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        JoinGameButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        JoinGameButton.setText("Join");
        JoinGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGameButtonActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Refresh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JoinGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ExitButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JoinGameButton)
                    .addComponent(ExitButton)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
       System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void JoinGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGameButtonActionPerformed
       int optiune = jList1.getSelectedIndex();
       Socket clientSocket = null;
        try {
            String optiuneproasta=(String) model.elementAt(optiune);
            
            String str =  optiuneproasta.substring(1, optiuneproasta.length());
            System.out.println(str);
            
            clientSocket = new Socket(str, 6666);
            this.setVisible(false);
            mainWindow.MMap = new MyMap(clientSocket);
            mainWindow.MMap.setVisible(true);
            mainWindow.OMap=new OpponentMap(clientSocket);
            mainWindow.OMap.setVisible(true);

            new Receiver(clientSocket, mainWindow).start();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JoinGameButtonActionPerformed

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton JoinGameButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private DefaultListModel model;
}
