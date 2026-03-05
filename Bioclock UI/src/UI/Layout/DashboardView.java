package UI.Layout;

import UI.Components.BioCard;
import UI.Controller.DeviceController;
import UI.Helper.ScrollPaneHelper;
import UI.Listener.IDeviceClickListener;
import UI.Listener.IDeviceStatusListener;
import UI.panels.SearchPanel;
import bioclock.dto.DeviceDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DashboardView extends JPanel{
    
    private final DeviceController controller;
    private JPanel wrapperPanel;
    
    public DashboardView (DeviceController controller, IDeviceClickListener listener, IDeviceStatusListener statusListener) {
        
        this.controller = controller;
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        SearchPanel searchPanel = new SearchPanel();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        headerPanel.add(searchPanel, BorderLayout.CENTER);
        headerPanel.setOpaque(false);

        wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.setOpaque(true);

        wrapperPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = ScrollPaneHelper.createStyledScrollPane(wrapperPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        loadDevices(listener, statusListener);
    }
    
    public void loadDevices(IDeviceClickListener listener, IDeviceStatusListener statusListener) {
        
        List<DeviceDTO> devices = controller.loadDevices();
        setDevices(devices, listener, statusListener); 
        
    }
    
    public void setDevices(List<DeviceDTO> devices, IDeviceClickListener listener, IDeviceStatusListener statusListener) {
        wrapperPanel.removeAll();
        
        for(DeviceDTO device : devices) {
            wrapperPanel.add(new BioCard(device, listener, statusListener));
        }
        
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }
}
