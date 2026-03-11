package UI.Helper;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollPaneHelper {

    public static JScrollPane createStyledScrollPane(javax.swing.JComponent component) {

        JScrollPane scrollPane = new JScrollPane(component);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setPreferredSize(
                new Dimension(6, Integer.MAX_VALUE)
        );

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(210, 210, 210);
                trackColor = new Color(242, 242, 242);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0,0));
                button.setMinimumSize(new Dimension(0,0));
                button.setMaximumSize(new Dimension(0,0));
                return button;
            }
        });

        return scrollPane;
    }
}