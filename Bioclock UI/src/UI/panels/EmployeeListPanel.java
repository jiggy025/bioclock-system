package UI.panels;

import UI.Helper.ScrollPaneHelper;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class EmployeeListPanel extends JPanel {

    private JPanel listContainer;

    public EmployeeListPanel(final CardLayout layout, final JPanel parent) {

        setLayout(new BorderLayout());
        setBackground(new Color(242, 242, 242));

        // ===== HEADER =====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

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

        header.add(backButton);

        // ===== LIST CONTAINER =====
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(Color.WHITE);
        listContainer.setBorder(
            BorderFactory.createEmptyBorder(10, 0, 20, 0)
        );
        
        JScrollPane scrollPane = ScrollPaneHelper.createStyledScrollPane(listContainer);

        listContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void setEmployees(List<UserDataDTO> users) {

    listContainer.removeAll();

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