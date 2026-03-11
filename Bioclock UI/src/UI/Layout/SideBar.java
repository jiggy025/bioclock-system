
package UI.Layout;

import UI.Components.CircleAvatar;
import UI.Components.NavItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SideBar extends JPanel {
    
    private Runnable onAddUserClick;
    
    public SideBar(final Runnable onLogout){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(248, 248, 252));
        add(Box.createRigidArea(new Dimension(0, 20)));
        setPreferredSize(new Dimension(225, 0));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(25, 15, 25, 15))
        );
        
        JLabel fullName = new JLabel("Jiggy Palconit");
        JLabel email = new JLabel("jigspals025@gmail.com");
        
        //Avatar
        CircleAvatar avatar = new CircleAvatar("/bioclock/main/resources/profile.png", 100);
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(avatar);
        add(Box.createVerticalStrut(6));
        fullName.setAlignmentX(Component.CENTER_ALIGNMENT);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        fullName.setFont(new Font("Helvetica Neue", Font.BOLD, 24));
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        email.setForeground(new Color(130, 130, 130));
        
        fullName.setHorizontalAlignment(SwingConstants.CENTER);
        email.setHorizontalAlignment(SwingConstants.CENTER);

        fullName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        email.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        
        JPanel dashBoard = new NavItem("Dashboard", "/bioclock/main/resources/dashboard.png");
        JPanel settings = new NavItem("Settings", "/bioclock/main/resources/settings.png");
        JPanel logout = new NavItem("Logout", "/bioclock/main/resources/logout.png");
        
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setOpaque(false);
        navPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel addUser = new JLabel("+ Add Employee");
        addUser.setForeground(new Color(34, 150, 80));
        addUser.setFont(new Font("SansSerif", Font.BOLD, 14));
        addUser.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addUser.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(onAddUserClick != null) {
                    onAddUserClick.run();
                }
            }
        });
        
        navPanel.add(fullName);
        navPanel.add(Box.createVerticalStrut(12));
        navPanel.add(email);
        navPanel.add(Box.createVerticalStrut(35));
        navPanel.add(addUser);
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(dashBoard);
        navPanel.add(Box.createVerticalStrut(12));
        navPanel.add(settings);
        navPanel.add(Box.createVerticalStrut(12));
        navPanel.add(logout);

        add(navPanel);
        
        logout.addMouseListener(new MouseAdapter(){
           @Override
            public void mouseClicked(MouseEvent e) {
                onLogout.run();
            }
        });
    }
    
    public void setOnAddUserClick(Runnable action) {
        this.onAddUserClick = action;
    }
}
