package bioclock.clientmain;

import UI.MainUI;
import javax.swing.SwingUtilities;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class clientMain {

 public static void main(String[] args) throws RemoteException, NotBoundException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainUI mainUI = new MainUI();
                mainUI.setVisible(true);
            }
        });
    }
}