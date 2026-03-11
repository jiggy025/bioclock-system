package UI.Controller;

import UI.View.Dialogs.AddEmployeeDialog;
import UI.Listener.IDeviceClickListener;
import UI.Listener.IDeviceStatusListener;
import UI.MainUI;
import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class ApplicationController {
    
    private final MainUI view;
<<<<<<< HEAD
    
    private final EmployeeController controller;
    private final DeviceController deviceController;
    private final LogsController logsController;
    
    private int currentDeviceId;
    
    public ApplicationController(MainUI view, EmployeeController controller, DeviceController deviceController, LogsController logsController) {
        this.view = view;
        this.controller = controller;
        this.deviceController = deviceController;
        this.logsController = logsController;
=======
    private final EmployeeController controller;
    private final DeviceController deviceController;
    private int currentDeviceId;
    
    public ApplicationController(MainUI view, EmployeeController controller, DeviceController deviceController) {
        this.view = view;
        this.controller = controller;
        this.deviceController = deviceController;
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
        wireEvents();
        loadDevices();
    }
    
    private void wireEvents() {
        view.setOnEmployeeCardClick(new IDeviceClickListener(){
            @Override
            public void onDeviceClick(int deviceId) {
                loadEmployees(deviceId);
            }
        });
        
        view.setOnAddUserClick(new Runnable(){
            @Override
            public void run() {
                openAddUserDialog();
            }
        });
        
        view.setOnDeviceStatusChange(new IDeviceStatusListener() {
           @Override
           public void onStatusChange(int deviceId, String status){
               deviceController.updateStatus(deviceId, status);
           }
        });
    }
    
    private void loadDevices() {
        new SwingWorker<List<DeviceDTO>, Void>() {
            @Override
            protected List<DeviceDTO> doInBackground() {
                return deviceController.loadDevices();
            }
            
            @Override
            protected void done() {
                try {
                    List<DeviceDTO> devices = get();
                    view.setDevices(devices);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    
    private void loadEmployees(int deviceId) {
        
        currentDeviceId = deviceId;
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                view.showLoading(true);
            }
        });
        
        new SwingWorker<List<UserDataDTO>, Void>() {
            
            protected List<UserDataDTO> doInBackground() {
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return controller.loadEmployeesByDevice(currentDeviceId);
            }
            
            protected void done(){
                try {
                    List<UserDataDTO> users = get();
                    
                    DeviceDTO device = deviceController.getDeviceById(currentDeviceId);
                    view.setEmployees(users, device);
                    view.showEmployees();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    view.showLoading(false);
                }
            }
        }.execute();
    }
    
    private void openAddUserDialog() {
        List<DeviceDTO> devices = deviceController.loadDevices();

        view.showAddUserDialog(devices, new AddEmployeeDialog.SaveListener() {

            @Override
            public void onSave(UserDataDTO user) {

                controller.addEmployees(user);

                loadEmployees(user.getDeviceId()); // refresh table
                
                loadDevices(); //refresh emp count
            }
        });
    }
    
    public void openEmployeeDetails(int employeeId) {
        UserDataDTO employee = controller.getEmployeeById(employeeId);
<<<<<<< HEAD
        DeviceDTO device = deviceController.getDeviceById(employee.getDeviceId());
=======
        DeviceDTO device = deviceController.getDeviceById(employeeId);
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
        view.showEmployeeDetails(employee, device);
    }
    
    public void updateDeviceStatus(int deviceId, String status) {
        deviceController.updateStatus(deviceId, status);
    }
}
