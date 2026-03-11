package UI;

import UI.Components.LoadingOverlay;
import UI.Controller.ApplicationController;
import UI.Controller.DeviceController;
import UI.Layout.DashboardView;
import UI.Layout.SideBar;
import UI.Controller.EmployeeController;
<<<<<<< HEAD
import UI.Controller.LogsController;
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
import UI.View.Dialogs.AddEmployeeDialog;
import UI.Listener.IDeviceClickListener;
import UI.Listener.IDeviceStatusListener;
import UI.Listener.IEmployeeClickListener;
import UI.Listener.IEmployeeSearchResult;
import UI.View.Panels.EmployeeDetailPanel;
import UI.View.Panels.EmployeeListPanel;
import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainUI extends javax.swing.JFrame {

    Object[] options = {"Agree", "Disagree"};
    private final EmployeeListPanel employeeListPanel;
    private final CardLayout mainLayout;
    
    private IDeviceClickListener onEmployeeCardClick;
    private IDeviceStatusListener onDeviceStatusChange;

    private LoadingOverlay loadingOverlay;

    private static ApplicationController appController;
    private static DeviceController deviceController;
    private static EmployeeController employeeController;
<<<<<<< HEAD
    private static LogsController logsController;
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
    
    private EmployeeDetailPanel employeeDetailPanel;
    
    private SideBar sideBar;
    private DashboardView dashBoardView;
    
<<<<<<< HEAD
    public MainUI(EmployeeController employeeController, DeviceController deviceController, LogsController logsController) {
        initComponents();
        this.deviceController = deviceController;
        this.employeeController = employeeController;
        this.logsController = logsController;
=======
    public MainUI(EmployeeController employeeController, DeviceController deviceController) {
        initComponents();
        this.deviceController = deviceController;
        this.employeeController = employeeController;
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
        
        createLoadingOverlay();
        
        setTitle("Bioclock UI");
        setSize(600, 600);
        
        mainLayout = new CardLayout();
        mainContainer.setLayout(mainLayout);
        
        employeeListPanel = new EmployeeListPanel(mainLayout, mainContainer);
        
<<<<<<< HEAD
        employeeDetailPanel = new EmployeeDetailPanel(mainLayout, mainContainer);
=======
        employeeDetailPanel = new EmployeeDetailPanel();
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
        
        sideBar = new SideBar(new Runnable() {
            @Override
            public void run() {
                int choice = JOptionPane.showConfirmDialog(
                    getContentPane(),
                    "Are you sure you want to logout?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });  

        dashBoardView = new DashboardView(deviceController, employeeController,
        new IEmployeeSearchResult(){ 
            @Override
            public void showEmployees(List<UserDataDTO> users, DeviceDTO device) {
                setEmployees(users, device);
                mainLayout.show(mainContainer, "employees");
            }     
        },
        new IDeviceClickListener(){
            @Override
            public void onDeviceClick(int deviceId) {
                if(onEmployeeCardClick != null) {
                    onEmployeeCardClick.onDeviceClick(deviceId);
                }
            }
        },
        new IDeviceStatusListener() {
            @Override
            public void onStatusChange(int deviceId, String status) {
                if(onDeviceStatusChange != null) {
                    onDeviceStatusChange.onStatusChange(deviceId, status);
                }
            }
            
        });
        
        JPanel dashboardPage = new JPanel(new BorderLayout());
        dashboardPage.add(sideBar, BorderLayout.WEST);
        dashboardPage.add(dashBoardView, BorderLayout.CENTER);
        
        mainContainer.add(dashboardPage, "dashboard");
        mainContainer.add(employeeListPanel, "employees");
        mainContainer.add(employeeDetailPanel, "EMPLOYEE_DETAILS");
        
<<<<<<< HEAD
        appController = new ApplicationController(this, employeeController, deviceController, logsController);
=======
        appController = new ApplicationController(this, employeeController, deviceController);
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
        
        employeeListPanel.setEmployeeClickListener(new IEmployeeClickListener() {
            @Override
            public void onEmployeeClick(int employeeId) {
                appController.openEmployeeDetails(employeeId);
            }
        });
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                getContentPane().requestFocusInWindow();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContainer.setLayout(new java.awt.BorderLayout());
        getContentPane().add(mainContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
<<<<<<< HEAD
                new MainUI(employeeController, deviceController, logsController).setVisible(true);
=======
                new MainUI(employeeController, deviceController).setVisible(true);
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
            }
        });
    }
    
    public void setOnEmployeeCardClick(IDeviceClickListener action) {
        this.onEmployeeCardClick = action;
    }
    
    public void showEmployees() {
        mainLayout.show(mainContainer, "employees");
    }
    
    public void setEmployees(List<UserDataDTO> users, DeviceDTO device) {
        employeeListPanel.setEmployees(users, device);
    }
    
    public void setOnAddUserClick(Runnable action){
        sideBar.setOnAddUserClick(action);
    }
    
    private void createLoadingOverlay() {
        loadingOverlay = new LoadingOverlay();
        loadingOverlay.setVisible(false);
        setGlassPane(loadingOverlay);
    }
    
    public void showLoading(boolean loading) {
        loadingOverlay.setVisible(loading);
    }
    
    public void setDevices(List<DeviceDTO> devices) {
        dashBoardView.setDevices(devices, new IDeviceClickListener(){
            @Override
            public void onDeviceClick(int DeviceId) {
                if(onEmployeeCardClick != null) {
                    onEmployeeCardClick.onDeviceClick(DeviceId);
                }
            }
        },
        new IDeviceStatusListener() {
            @Override
            public void onStatusChange(int deviceId, String status) {
                if(onDeviceStatusChange != null) {
                    onDeviceStatusChange.onStatusChange(deviceId, status);
                }
            }
            
        });
    }
    
    public void showAddUserDialog(List<DeviceDTO> devices,
                              AddEmployeeDialog.SaveListener listener) {

            AddEmployeeDialog dialog =
                new AddEmployeeDialog(this, devices, listener);

            dialog.setVisible(true);
    }
    
    public void setOnDeviceStatusChange(IDeviceStatusListener listener) {
        this.onDeviceStatusChange = listener;
    }
    
    public void showEmployeeDetails(UserDataDTO employee, DeviceDTO device) {
        employeeDetailPanel.setEmployee(employee, device);
        
        mainLayout.show(mainContainer, "EMPLOYEE_DETAILS");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainContainer;
    // End of variables declaration//GEN-END:variables

}
