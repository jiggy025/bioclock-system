package UI.Layout;

import UI.Components.BioCard;
import UI.Controller.EmployeeController;
import UI.panels.EmployeeListPanel;
import UI.panels.SearchPanel;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DashboardView extends JPanel{
    
    public DashboardView (final EmployeeController controller, 
                          final EmployeeListPanel employeeListPanel,
                          final CardLayout layout, 
                          final JPanel container) {
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        SearchPanel searchPanel = new SearchPanel();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        headerPanel.add(searchPanel, BorderLayout.CENTER);
        headerPanel.setOpaque(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(Color.WHITE);

        wrapperPanel.add(new BioCard("Bio 1", "Central", new Runnable() {
            @Override
            public void run() {
                try {
                List<UserDataDTO> users = controller.loadEmployees();
                employeeListPanel.setEmployees(users);
                layout.show(container, "employees");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            }
        }));

        wrapperPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
    }
}
