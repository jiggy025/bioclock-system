package UI.View.Panels;

import UI.Components.CircleAvatar;
import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class EmployeeDetailPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel idNumLabel;
    private JLabel deviceLabel;
    private JLabel locationLabel;
    private JLabel statusValue;
    
    private final CardLayout layout;
    private final JPanel parent;
    
    public EmployeeDetailPanel(final CardLayout layout, final JPanel parent) {

        this.layout = layout;
        this.parent = parent;
        
        setLayout(new BorderLayout());
        setBackground(new Color(242,242,242));

        idNumLabel = new JLabel("-");
        deviceLabel = new JLabel("-");
        locationLabel = new JLabel("-");
        statusValue = new JLabel("● Online");

        statusValue.setForeground(new Color(60,170,80));
        
        JPanel card = new RoundedCard();
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(15,20,15,20));
        card.setMaximumSize(new Dimension(900, Integer.MAX_VALUE));
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(15, 30, 15, 30));

        wrapper.add(card, BorderLayout.CENTER);

        add(wrapper, BorderLayout.CENTER);;

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.setBorder(BorderFactory.createEmptyBorder(8,20,0,20));
        
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
                layout.show(parent, "employees");
            }
        });
        
        header.add(backButton, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);
        
        // HEADER
        JLabel title = new JLabel("Employee Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(new EmptyBorder(0,0,15,0));

        card.add(title, BorderLayout.NORTH);

        // MAIN CONTENT
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(20,20));
        content.setOpaque(false);

        card.add(content, BorderLayout.CENTER);

        //----------------------------------
        // PROFILE SECTION
        //----------------------------------

        JPanel profilePanel = new JPanel(new BorderLayout(30,0));
        profilePanel.setOpaque(false);

        CircleAvatar avatar = new CircleAvatar("/bioclock/main/resources/profile.png", 100);
        avatar.setPreferredSize(new Dimension(90,90));

        profilePanel.add(avatar, BorderLayout.WEST);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setOpaque(false);

        nameLabel = new JLabel("Employee Name");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        idLabel = new JLabel("Employee ID: -");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        idLabel.setForeground(new Color(120,120,120));

        namePanel.add(nameLabel);
        namePanel.add(idLabel);

        profilePanel.add(namePanel, BorderLayout.CENTER);

        content.add(profilePanel, BorderLayout.NORTH);

        //----------------------------------
        // INFO GRID
        //----------------------------------

        JPanel infoGrid = new JPanel(new GridBagLayout());
        infoGrid.setOpaque(false);
        infoGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 40, 16, 40);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.weightx = 1;   
        gbc.weighty = 1;   
        
        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        infoGrid.add(createField("ID Number", idNumLabel), gbc);

        gbc.gridx = 1;
        infoGrid.add(createField("Device", deviceLabel), gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoGrid.add(createField("Location", locationLabel), gbc);

        gbc.gridx = 1;
        infoGrid.add(createField("Status", statusValue), gbc);

        JPanel middle = new JPanel(new BorderLayout());
        middle.setOpaque(false);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(210,210,210));

        middle.add(separator, BorderLayout.NORTH);
        middle.add(infoGrid, BorderLayout.CENTER);

        content.add(middle, BorderLayout.CENTER);
        
        //----------------------------------
        // BUTTONS
        //----------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        buttonPanel.setOpaque(false);

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);

        editButton.setBackground(new Color(230,230,230));
        deleteButton.setBackground(new Color(220,70,70));
        deleteButton.setForeground(Color.WHITE);

        editButton.setBorder(BorderFactory.createEmptyBorder(6,16,6,16));
        deleteButton.setBorder(BorderFactory.createEmptyBorder(6,16,6,16));
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        buttonPanel.setBorder(new EmptyBorder(10,0,0,0));
        card.add(buttonPanel, BorderLayout.SOUTH);
    }

    //----------------------------------
    // Helper for label/value layout
    //----------------------------------

    private JPanel createField(String label, JLabel value) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel title = new JLabel(label);
        title.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        title.setForeground(new Color(120,120,120));

        value.setFont(new Font("Segoe UI", Font.BOLD, 15));

        panel.add(title);
        panel.add(Box.createVerticalStrut(3));
        panel.add(value);

        return panel;
    }

    public void setEmployee(UserDataDTO emp, DeviceDTO dev) {
        
        nameLabel.setText(emp.getEmpName());
        idLabel.setText("Employee ID: " + emp.getEmpId());
        idNumLabel.setText(String.valueOf(emp.getEmpIdNum()));
        deviceLabel.setText(dev.getName());
        locationLabel.setText(dev.getLocation());

        statusValue.setText("● Online");
    }
}

            //----------------------------------
    // Rounded card panel
    //----------------------------------

    class RoundedCard extends JPanel {

        private int radius = 14;

        public RoundedCard() {
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int shadow = 4;

            g2.setColor(new Color(0,0,0,10));
            g2.fillRoundRect(shadow,shadow,getWidth()-shadow*2,getHeight()-shadow*2,radius,radius);

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0,0,getWidth()-shadow*2,getHeight()-shadow*2,radius,radius);

            g2.dispose();
        }
    }