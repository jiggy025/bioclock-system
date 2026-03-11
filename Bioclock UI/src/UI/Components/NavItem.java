package UI.Components;

import UI.MainUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavItem extends JPanel {
    
    public NavItem(String text, String iconPath) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        setBackground(new Color(248, 248, 252));
        setOpaque(false);

        ImageIcon icon = new ImageIcon(
            NavItem.class.getResource(iconPath)
        );

        // Scale icon
        Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        JLabel label = new JLabel(text, icon, JLabel.LEFT);
        label.setIconTextGap(12);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(60, 60, 80));
        
        add(label);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(225, 225, 240));
                setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOpaque(false);
                repaint();
            }
        });
    }
}
