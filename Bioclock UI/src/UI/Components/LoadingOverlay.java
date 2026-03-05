package UI.Components;

import UI.Helper.LoadingSpinnerHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
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
        
        LoadingSpinnerHelper spinner = new LoadingSpinnerHelper();
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel label = new JLabel("Loading...");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(90,90,90));

        box.add(spinner);
        box.add(Box.createVerticalStrut(1));
        box.add(label);

        add(box, new GridBagConstraints());
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