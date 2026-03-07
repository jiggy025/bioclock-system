package UI.Dialog;

import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddEmployeeDialog extends javax.swing.JDialog {

    private JComboBox<DeviceDTO> bioDropdown;
    private JTextField nameField;
    private JTextField numberField;

    private JButton saveButton;
    private JButton cancelButton;
    
    
    public interface SaveListener {
            void onSave(UserDataDTO user);
    }
    
    public AddEmployeeDialog(java.awt.Frame parent, List<DeviceDTO> devices, final SaveListener listener) {
        super(parent, "Add Employee", true);

        setLayout(new BorderLayout());
        setSize(430, 250);
        setLocationRelativeTo(parent);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // BIO DROPDOWN
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Bio Device:"), gbc);
        
        // BIO DROPDOWN
        gbc.gridx = 1;
        bioDropdown = new JComboBox<>();
        
        for(DeviceDTO device : devices) {
            bioDropdown.addItem(device);
        }
        
        formPanel.add(bioDropdown, gbc);
        
        // NAME FIELD
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Employee Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(15);
        formPanel.add(nameField, gbc);
        
        // NUMBER FIELD
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Employee Number:"), gbc);
        
        gbc.gridx = 1;
        numberField = new JTextField(15);
        formPanel.add(numberField, gbc);
        
        add(formPanel, BorderLayout.CENTER);
        
        //BUTTONS
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        cancelButton = new JButton("Cancel");
        saveButton = new JButton("Save");

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // BUTTON ACTIONS

        
        cancelButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
            

        saveButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            String name = nameField.getText();
            String number = numberField.getText();
            DeviceDTO selected = (DeviceDTO) bioDropdown.getSelectedItem();
            

            if(name.isEmpty() || number.isEmpty()){
                JOptionPane.showMessageDialog(getContentPane(), "Please fill all fields");
                return;
            }

            UserDataDTO dto = new UserDataDTO();
            dto.setEmpName(name);
            dto.setEmpIdNum(Integer.parseInt(number));
            dto.setDeviceId(selected.getId());

            listener.onSave(dto);

            dispose();
            }
        }); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                List<DeviceDTO> devices = new ArrayList<>();
                
                DeviceDTO d1 = new DeviceDTO();
                d1.setId(1);
                d1.setName("Bio 1");
                
                DeviceDTO d2 = new DeviceDTO();
                d2.setId(2);
                d2.setName("Bio 2");
                
                devices.add(d1);
                devices.add(d2);
                
                AddEmployeeDialog dialog = new AddEmployeeDialog(new javax.swing.JFrame(), devices, new SaveListener() {
                    @Override
                    public void onSave(UserDataDTO user) {
                        
                    }
                });
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
