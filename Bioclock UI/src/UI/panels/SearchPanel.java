package UI.panels;

import UI.Listener.PlaceholderFocusListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {

    private JTextField textField;

    public SearchPanel() {
        setLayout(new BorderLayout(8, 0));
        setOpaque(false);
        setPreferredSize(new Dimension(400, 45));
        setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        ImageIcon searchIcon = new ImageIcon(getClass().getResource("/bioclock/main/resources/search.png"));
        
        Image searchImg = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        
        searchIcon = new ImageIcon(searchImg);
        
        JLabel iconLabel = new JLabel(searchIcon);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        textField = new JTextField();
        textField.setBorder(null);
        textField.setOpaque(false);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        new PlaceholderFocusListener(textField, "Search employee here");
        
        add(iconLabel, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(240, 242, 245));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();
    }
}