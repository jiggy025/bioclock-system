package UI.Controller;

import UI.Dialog.AddEmployeeDialog;
import UI.MainUI;
import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.util.List;
import javax.swing.SwingWorker;

public class ApplicationController {
    
    private final MainUI view;
    private final EmployeeController controller;
    private final DeviceController deviceController;
    
    public ApplicationController(MainUI view, EmployeeController controller, DeviceController deviceController) {
        this.view = view;
        this.controller = controller;
        this.deviceController = deviceController;
        wireEvents();
        loadDevices();
    }
    
    private void wireEvents() {
        view.setOnEmployeeCardClick(new Runnable(){
            @Override
            public void run() {
                loadEmployees();
            }
        });
        
        view.setOnAddUserClick(new Runnable(){
            @Override
            public void run() {
                openAddUserDialog();
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
    
    private void loadEmployees() {
        
        view.showLoading(true);
        
        new SwingWorker<List<UserDataDTO>, Void>() {
            
            protected List<UserDataDTO> doInBackground() {
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return controller.loadEmployees();
            }
            
            protected void done(){
                try {
                    List<UserDataDTO> users = get();
                    view.setEmployees(users);
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

                loadEmployees(); // refresh table
            }
        });
    }
}
