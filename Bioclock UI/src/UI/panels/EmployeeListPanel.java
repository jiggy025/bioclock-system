package UI.panels;

import UI.Controller.DeviceController;
import UI.Helper.ScrollPaneHelper;
import bioclock.client.service.RMIDeviceService;
import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EmployeeListPanel extends JPanel {

    private JPanel listContainer;
    private JLabel deviceLabel;
    private JLabel locationLabel;
    private JLabel employeeCountLabel;

    public EmployeeListPanel(final CardLayout layout, final JPanel parent) {

        setLayout(new BorderLayout());
        setBackground(new Color(242, 242, 242));

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(8,20,6,20));
        header.setBorder(
            BorderFactory.createMatteBorder(0,0,1,0,new Color(230,230,230))
        );
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        
        deviceLabel = new JLabel("BIOCLOCK");
        deviceLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        deviceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        locationLabel = new JLabel("Location");
        locationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationLabel.setForeground(new Color(120, 120, 120));
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.add(deviceLabel);
        titlePanel.add(locationLabel);
        
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        backButton.setForeground(new Color(70, 70, 70));
        backButton.setBackground(new Color(245, 245, 245));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(parent, "dashboard");
            }
        });
        
        JPanel rightSpacer = new JPanel();
        rightSpacer.setOpaque(false);
        rightSpacer.setPreferredSize(backButton.getPreferredSize());
        
        
        header.add(backButton, BorderLayout.WEST);
        header.add(titlePanel, BorderLayout.CENTER);
        header.add(rightSpacer, BorderLayout.EAST);
        
        // ===== employee count panel =====
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(new Color(245,245,245));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5,25,10,25));

        employeeCountLabel = new JLabel("Employees");
        employeeCountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        employeeCountLabel.setForeground(new Color(90,90,90));

        infoPanel.add(employeeCountLabel);

        // ===== LIST CONTAINER =====
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(Color.WHITE);
        listContainer.setBorder(
            BorderFactory.createEmptyBorder(10, 0, 20, 0)
        );
        
        JScrollPane scrollPane = ScrollPaneHelper.createStyledScrollPane(listContainer);

        listContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setOpaque(false);

        topContainer.add(header);
        topContainer.add(infoPanel);

        add(topContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void setEmployees(List<UserDataDTO> users, DeviceDTO device) {

    listContainer.removeAll();
    
    
    deviceLabel.setText(device.getName());
    locationLabel.setText(device.getLocation());
    employeeCountLabel.setText("Employee(s): " + users.size());
    
    for (UserDataDTO user : users) {

        EmployeeItemPanel item = new EmployeeItemPanel(
            user.getEmpName(),
            String.valueOf(user.getEmpIdNum()),
            String.valueOf(user.getEmpId())
        );
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        wrapper.add(item);
        
        
        
        listContainer.add(wrapper);
        listContainer.add(Box.createVerticalStrut(22));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    listContainer.revalidate();
    listContainer.repaint();
    }
}