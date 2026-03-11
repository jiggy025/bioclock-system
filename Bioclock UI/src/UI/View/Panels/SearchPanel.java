package UI.View.Panels;

import UI.Listener.IEmployeeSuggestionListener;
import UI.Listener.PlaceholderFocusListener;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SearchPanel extends JPanel {

    private JTextField searchField;
    private JPopupMenu suggestionPopup;
    private JPanel suggestionPanel;

    public SearchPanel() {
        setLayout(new BorderLayout(8, 0));
        setOpaque(false);
        setPreferredSize(new Dimension(400, 45));
        setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        suggestionPopup = new JPopupMenu();
        suggestionPopup.setBorder(BorderFactory.createEmptyBorder());
        suggestionPopup.setFocusable(false);
        suggestionPopup.setOpaque(false);
        suggestionPopup.setBackground(new Color(0,0,0,0));
        
        suggestionPanel = new JPanel();
        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
        suggestionPanel.setBackground(Color.WHITE);
        
        suggestionPanel.setBorder(
        BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(6,6,6,6)
            )
        );
        
        suggestionPopup.add(suggestionPanel);
        
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("/bioclock/main/resources/search.png"));
        
        Image searchImg = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        
        searchIcon = new ImageIcon(searchImg);
        
        JLabel iconLabel = new JLabel(searchIcon);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        searchField = new JTextField();
        searchField.setBorder(null);
        searchField.setOpaque(false);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        suggestionPopup.setVisible(false);
                    }
                });
            }
        });

        new PlaceholderFocusListener(searchField, "Search employee here");
        
        add(iconLabel, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
    }
    
    public JTextField getSearchField() {
        return searchField;
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
    
    public void showSuggestion(List<UserDataDTO> users,
                               final IEmployeeSuggestionListener listener) {

        suggestionPopup.setVisible(false);
        
        suggestionPanel.removeAll();
        
        if(users == null || users.isEmpty()){
            suggestionPopup.setVisible(false);
            return;
        }

        for(final UserDataDTO user : users){

            final JLabel item = new JLabel(user.getEmpName());
            item.setBorder(BorderFactory.createEmptyBorder(8,12,8,12));
            item.setOpaque(true);
            item.setBackground(Color.WHITE);

            item.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            item.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseEntered(MouseEvent e){
                    item.setBackground(new Color(245,245,245));
                }

                @Override
                public void mouseExited(MouseEvent e){
                    item.setBackground(Color.WHITE);
                }

                @Override
                public void mouseClicked(MouseEvent e){
                    suggestionPopup.setVisible(false);
                    listener.onEmployeeSelected(user);
                }
            });
            suggestionPanel.add(item);
        }
        suggestionPanel.setPreferredSize(
                new Dimension(searchField.getWidth(), users.size()*36)
        );
        suggestionPopup.show(searchField, 0, searchField.getHeight());
    }
    
    public void hideSuggestions() {
        suggestionPopup.setVisible(false);
    }
}