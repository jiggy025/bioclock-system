package UI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class StatusDot extends JPanel {

    private Color color = Color.RED;

    public StatusDot() {
        setPreferredSize(new Dimension(8,8));
        setOpaque(false);
    }

    public void setOnline(boolean online){
        color = online ? new Color(76,175,80) : new Color(220,70,70);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);
        g2.fillOval(0,0,8,8);

        g2.dispose();
    }
}