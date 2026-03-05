package UI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadingOverlay extends JPanel {

    public LoadingOverlay() {
        setOpaque(false);
        setLayout(new GridBagLayout());   // center everything
        setBackground(new Color(255,255,255,180)); // transparent overlay

        JPanel box = new JPanel();
        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        addMouseListener(new MouseAdapter() {});
        addMouseMotionListener(new MouseMotionAdapter() {});
        
        JProgressBar spinner = new JProgressBar();
        spinner.setIndeterminate(true);
        spinner.setPreferredSize(new Dimension(80,8));
        spinner.setBorderPainted(false);
        spinner.setStringPainted(false);

        JLabel label = new JLabel("Loading...");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(90,90,90));

        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(spinner);
        box.add(Box.createVerticalStrut(10));
        box.add(label);

        add(box);
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(new Color(224, 224, 224, 160)); // grey dim effect
        g2.fillRect(0,0,getWidth(),getHeight());

        g2.dispose();

        super.paintComponent(g);
    }
}