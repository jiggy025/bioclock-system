package UI.Listener;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class PlaceholderFocusListener extends FocusAdapter {
    private final JTextField textField;
    private final String placeholder;
    private final Color placeholderColor;
    private final Color textColor;

    public PlaceholderFocusListener(JTextField textField, String placeholder) {
        this(textField, placeholder, Color.GRAY, Color.BLACK);
    }

    public PlaceholderFocusListener(JTextField textField, String placeholder,
                                    Color placeholderColor, Color textColor) {
        this.textField = textField;
        this.placeholder = placeholder;
        this.placeholderColor = placeholderColor;
        this.textColor = textColor;

        // Initialize with placeholder
        textField.setText(placeholder);
        textField.setForeground(placeholderColor);

        // Attach the listener
        textField.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (textField.getText().equals(placeholder)) {
            textField.setText("");
            textField.setForeground(textColor);
        }
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        if (textField.getText().isEmpty()) {
            textField.setText(placeholder);
            textField.setForeground(placeholderColor);
        }
    }
}

