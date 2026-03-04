package UI.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EmployeeItemPanel extends JPanel {

    private int cornerRadius = 16;
    private Color backgroundColor = new Color(255,255,255);
    
    public EmployeeItemPanel(String name, String number, String empId) {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 78));
        setOpaque(false);

        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                backgroundColor = new Color(248,248,248);
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backgroundColor = new Color(255,255,255);
                repaint();
            }
        });
        
        //Left side
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        numberLabel.setForeground(new Color(150, 150, 150));

        leftPanel.add(nameLabel);
        leftPanel.add(numberLabel);

        // RIGHT SIDE
        JLabel idLabel = new JLabel(empId);
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        idLabel.setForeground(new Color(200, 200, 200));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(idLabel, BorderLayout.NORTH);
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int shadowSize = 4;
        
        // Soft centered shadow
        g2.setColor(new Color(0, 0, 0, 12));
        g2.fillRoundRect(
            shadowSize,
            shadowSize,
            getWidth() - shadowSize * 2,
            getHeight() - shadowSize * 2,
            cornerRadius,
            cornerRadius
        );

        // White card
        g2.setColor(backgroundColor);
        g2.fillRoundRect(
            0,
            0,
            getWidth() - shadowSize * 2,
            getHeight() - shadowSize * 2,
            cornerRadius,
            cornerRadius
        );
        
        g2.dispose();
    }
}