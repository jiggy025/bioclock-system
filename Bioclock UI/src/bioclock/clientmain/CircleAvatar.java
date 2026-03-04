package UI.Components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CircleAvatar extends JLabel{
    private Image image;

    public CircleAvatar(String path, int size) {
        setPreferredSize(new Dimension(size, size));
        setMinimumSize(new Dimension(size, size));
        setMaximumSize(new Dimension(size, size));

        java.net.URL url = getClass().getResource(path);

        if (url == null) {
            System.out.println("Image not found: " + path);
        } else {
            image = new ImageIcon(url).getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Enable smooth rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // Create circular clip
        Ellipse2D circle = new Ellipse2D.Float(0, 0, width, height);
        g2.setClip(circle);

        // Scale image to fit
        g2.drawImage(image, 0, 0, width, height, this);

        g2.setColor(new Color(210, 205, 255));
        g2.setStroke(new BasicStroke(4));
        g2.drawOval(2, 2, width - 4, height - 4);
        g2.dispose();
    }
}
