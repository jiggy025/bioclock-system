package UI;

import UI.Layout.DashboardView;
import UI.Layout.SideBar;
import RMIConnector.RMIConnector;
import UI.Components.BioCard;
import UI.Components.CircleAvatar;
import UI.Components.NavItem;
import UI.Controller.EmployeeController;
import UI.panels.EmployeeListPanel;
import UI.panels.SearchPanel;
import bioclock.common.UserService;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class MainUI extends javax.swing.JFrame {

    Object[] options = {"Agree", "Disagree"};
    private EmployeeListPanel employeeListPanel;
    private CardLayout mainLayout;
    private UserService service;
    private EmployeeController employeeController;
    
    public MainUI() {
        initComponents();
        setTitle("Bioclock UI");
        setSize(600, 600);
        
        try {
            service = RMIConnector.getService();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Cannot connect to server.",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
        employeeController = new EmployeeController(service);
        
        mainLayout = new CardLayout();
        mainContainer.setLayout(mainLayout);
        
        employeeListPanel = new EmployeeListPanel(mainLayout, mainContainer);
        
        SideBar sideBar = new SideBar(new Runnable() {
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

        DashboardView dashboardView = new DashboardView(
                employeeController, employeeListPanel,
                mainLayout, mainContainer);
        
        JPanel dashboardPage = new JPanel(new BorderLayout());
        dashboardPage.add(sideBar, BorderLayout.WEST);
        dashboardPage.add(dashboardView, BorderLayout.CENTER);
        
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
                new MainUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainContainer;
    // End of variables declaration//GEN-END:variables
}
