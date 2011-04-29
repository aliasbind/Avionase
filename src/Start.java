import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Start extends javax.swing.JFrame {

    MyMap MMap;
    OpponentMap OMap;
    public Start() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("HOST");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HostHandler(evt);
            }
        });

        jButton2.setText("JOIN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinHandler(evt);
            }
        });

        jButton3.setText("Server List");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3JoinHandler(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HostHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HostHandler
        ServerSocket ssocket = null;
        try {
            String portstr = JOptionPane.showInputDialog(null, "Port: (default 6666)");
            if(!portstr.isEmpty())
                ssocket = new ServerSocket(Integer.parseInt(portstr));
            else
                ssocket = new ServerSocket(6666);
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        Socket socket=null;
        try {
            JOptionPane.showMessageDialog(null,"Waiting for client. ");
            socket = ssocket.accept();
            JOptionPane.showMessageDialog(null,"Connected. ");
            this.setVisible(false);
            MMap = new MyMap(socket);
            MMap.setVisible(true);
            OMap=new OpponentMap(socket);
            OMap.setVisible(true);

            new Receiver(socket, this).start();
            
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_HostHandler

    private void JoinHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinHandler
       Socket clientSocket = null;
        try {
            String str = JOptionPane.showInputDialog(null, "Introdu ip-ul host-ului: ");
            int port = 6666;
            if(str.contains(":")) {
                String portstr = str.substring(str.lastIndexOf(":") + 1);
                port = Integer.parseInt(portstr);
                str = str.substring(0, str.lastIndexOf(":"));
            }
            clientSocket = new Socket(str, port);
            this.setVisible(false);
            MMap = new MyMap(clientSocket);
            MMap.setVisible(true);
            OMap=new OpponentMap(clientSocket);
            OMap.setVisible(true);

            new Receiver(clientSocket, this).start();
               
        } catch (UnknownHostException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_JoinHandler

    private void jButton3JoinHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3JoinHandler
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3JoinHandler


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables

}
