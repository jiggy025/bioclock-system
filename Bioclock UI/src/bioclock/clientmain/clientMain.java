package bioclock.clientmain;

import RMIConnector.RMIConnector;
import UI.Controller.ApplicationController;
import UI.Controller.DeviceController;
import UI.Controller.EmployeeController;
import UI.Controller.LogsController;
import UI.MainUI;
import bioclock.client.service.EmployeeService;
import bioclock.client.service.RMIActivityLogService;
import bioclock.client.service.RMIDeviceService;
import bioclock.client.service.RMIEmployeeService;
import bioclock.common.ActivityLogService;
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
                    
                    //Remote services
                    DeviceService remoteDeviceService = RMIConnector.getDeviceService();
                    UserService remoteService = RMIConnector.getService();
                    ActivityLogService remoteLogService = RMIConnector.getActivityLogService();
                    
                    RMIDeviceService deviceService = new RMIDeviceService(remoteDeviceService);
                    EmployeeService employeeService = new RMIEmployeeService(remoteService);
                    RMIActivityLogService logService = new RMIActivityLogService(remoteLogService);
                    
                    //Controller
                    DeviceController deviceController = new DeviceController(deviceService);
                    LogsController logsController = new LogsController(logService);
                    final EmployeeController controller = new EmployeeController(employeeService, logsController);
                    
                    final MainUI view = new MainUI(controller, deviceController, logsController);

                    new ApplicationController(view, controller, deviceController, logsController);
                    
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