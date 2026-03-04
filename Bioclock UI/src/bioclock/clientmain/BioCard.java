package UI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BioCard extends JPanel {
    
    public BioCard(String title, String description, final Runnable onClick){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setOpaque(false);
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        textPanel.setOpaque(false);
        
        JLabel bioLabel = new JLabel(title);
        JLabel descLabel = new JLabel(description);
        
        bioLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        bioLabel.setForeground(new Color(40, 40, 40));
        
        bioLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bioLabel.setForeground(new Color(120, 120, 120));
        
        textPanel.add(bioLabel);
        textPanel.add(Box.createVerticalStrut(8));
        textPanel.add(descLabel);
        
        add(textPanel, BorderLayout.CENTER);
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (onClick != null) {
                    onClick.run();
                }
            }
        });
        
        ToggleSwitch toggle = new ToggleSwitch();
        
        JPanel toggleWrapper = new JPanel(new BorderLayout());
        
        toggleWrapper.setOpaque(false);
        toggleWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        toggleWrapper.add(toggle, BorderLayout.CENTER);
        
        add(toggleWrapper, BorderLayout.EAST);
    }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            int arc = 20;
            
            g2.setColor(new Color(0, 0, 0, 15));
            g2.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 4, arc, arc);
            
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 8, arc, arc);
            
            g2.dispose();
            
            super.paintComponent(g);
        }
    class ToggleSwitch extends JPanel {
        
        private boolean on = false;
        private float progress = 0f;
        
        public ToggleSwitch() {
            setPreferredSize(new Dimension(70, 50));
            setOpaque(false);

            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                        on = !on;

                        final float start = progress;
                        final float end = on ? 1f : 0f;

                        Timer timer = new Timer(10, null);
                        timer.addActionListener(new ActionListener() {
                            float fraction = 0f;
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fraction += 0.1f;

                                progress = start + (end - start) * fraction;
                                repaint();

                                if (fraction >= 1f) {
                                    progress = end;
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        });
                        timer.start();
                    }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int trackWidth = 46;
            int trackHeight = 24;
            int trackX = (getWidth() - trackWidth) / 2;
            int trackY = (getHeight() - trackHeight) / 2;

            // Track
            g2.setColor(on ? new Color(76, 175, 80) : new Color(200, 200, 200));
            g2.fillRoundRect(trackX, trackY, trackWidth, trackHeight, 22, 22);

            // Knob
            int knobSize = 20;
            int knobX = (int) (trackX + 2 + (trackWidth - knobSize - 4) * progress);
            int knobY = trackY + (trackHeight - knobSize) / 2;

            g2.setColor(new Color(0,0,0,20));
            g2.fillOval(knobX + 1, knobY + 1, knobSize, knobSize);
            
            g2.setColor(Color.WHITE);
            g2.fillOval(knobX, knobY, knobSize, knobSize);
        }
    }
}
