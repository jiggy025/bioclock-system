package UI;

import UI.Components.LoadingOverlay;
import UI.Controller.DeviceController;
import UI.Layout.DashboardView;
import UI.Layout.SideBar;
import UI.Controller.EmployeeController;
import UI.Dialog.AddEmployeeDialog;
import UI.panels.EmployeeListPanel;
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
    
    private Runnable onEmployeeCardClick;
    private Runnable setOnAddUserclick;
    
    private LoadingOverlay loadingOverlay;

    private static DeviceController deviceController;
    private static EmployeeController employeeController;
    
    private SideBar sideBar;
    private DashboardView dashBoardView;
    
    public MainUI(EmployeeController employeeController, DeviceController deviceController) {
        initComponents();
        this.deviceController = deviceController;
        this.employeeController = employeeController;
        
        createLoadingOverlay();
        
        setTitle("Bioclock UI");
        setSize(600, 600);
        
        mainLayout = new CardLayout();
        mainContainer.setLayout(mainLayout);
        
        employeeListPanel = new EmployeeListPanel(mainLayout, mainContainer);
        
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

        dashBoardView = new DashboardView(deviceController, new Runnable(){
            @Override
            public void run() {
                if(onEmployeeCardClick != null) {
                    onEmployeeCardClick.run();
                }
            }
        });
        
        JPanel dashboardPage = new JPanel(new BorderLayout());
        dashboardPage.add(sideBar, BorderLayout.WEST);
        dashboardPage.add(dashBoardView, BorderLayout.CENTER);
        
        mainContainer.add(dashboardPage, "dashboard");
        mainContainer.add(employeeListPanel, "employees");
        
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
                new MainUI(employeeController, deviceController).setVisible(true);
            }
        });
    }
    
    public void setOnEmployeeCardClick(Runnable action) {
        this.onEmployeeCardClick = action;
    }
    
    public void showEmployees() {
        mainLayout.show(mainContainer, "employees");
    }
    
    public void setEmployees(List<UserDataDTO> users) {
        employeeListPanel.setEmployees(users);
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
        dashBoardView.setDevices(devices, onEmployeeCardClick);
    }
    
    public void showAddUserDialog(List<DeviceDTO> devices,
                              AddEmployeeDialog.SaveListener listener) {

            AddEmployeeDialog dialog =
                new AddEmployeeDialog(this, devices, listener);

            dialog.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainContainer;
    // End of variables declaration//GEN-END:variables
}
