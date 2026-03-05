package bioclock.clientmain;

import RMIConnector.RMIConnector;
import UI.Controller.ApplicationController;
import UI.Controller.DeviceController;
import UI.Controller.EmployeeController;
import UI.MainUI;
import bioclock.client.service.EmployeeService;
import bioclock.client.service.RMIDeviceService;
import bioclock.client.service.RMIEmployeeService;
import bioclock.common.DeviceService;
import bioclock.common.UserService;
import javax.swing.SwingUtilities;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class clientMain {

 public static void main(String[] args) throws RemoteException, NotBoundException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DeviceService remoteDeviceService = RMIConnector.getDeviceService();
                    
                    UserService remoteService = RMIConnector.getService();
                    
                    RMIDeviceService deviceService = new RMIDeviceService(remoteDeviceService);
                    
                    DeviceController deviceController = new DeviceController(deviceService);
                    
                    EmployeeService employeeService = new RMIEmployeeService(remoteService);
                    
                    final EmployeeController controller = new EmployeeController(employeeService);
                    
                    final MainUI view = new MainUI(controller, deviceController);

                    new ApplicationController(view, controller, deviceController);
                    
                    SwingUtilities.invokeLater( new Runnable(){
                        @Override
                        public void run() {
                            view.setVisible(true);
                        }     
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Cannot connect to server.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}