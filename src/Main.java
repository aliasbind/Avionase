public class Main {

    static public void createAndShowGUI() {
        Start startFrame=new Start();
        startFrame.setVisible(true);
        startFrame.setSize(400,350);
        startFrame.setDefaultCloseOperation(Start.EXIT_ON_CLOSE);
        

    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
