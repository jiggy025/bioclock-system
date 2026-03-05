/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jiggyy
 */
public class LoadingSpinnerHelper extends JPanel {

    private int step = 0;

    public LoadingSpinnerHelper() {

        setPreferredSize(new Dimension(40,40));
        setOpaque(false);

        new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step = (step + 1) % 8;
                repaint();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        int radius = 12;
        int dotSize = 6;

        for (int i = 0; i < 8; i++) {

            double angle = Math.toRadians(i * 45);

            int x = cx + (int)(Math.cos(angle) * radius);
            int y = cy + (int)(Math.sin(angle) * radius);

            int alpha = (i == step) ? 255 : 80;

            g2.setColor(new Color(66,133,244, alpha));
            g2.fillOval(x - dotSize/2, y - dotSize/2, dotSize, dotSize);
        }

        g2.dispose();
    }
}