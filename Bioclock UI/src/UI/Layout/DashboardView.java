package UI.Layout;

import UI.Components.BioCard;
import UI.Controller.DeviceController;
import UI.Controller.EmployeeController;
import UI.Helper.ScrollPaneHelper;
import UI.Listener.IDeviceClickListener;
import UI.Listener.IDeviceStatusListener;
import UI.Listener.IEmployeeSearchResult;
import UI.Listener.IEmployeeSuggestionListener;
import UI.panels.EmployeeListPanel;
import UI.panels.SearchPanel;
import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;

public class DashboardView extends JPanel{
    
    private final DeviceController controller;
    private final EmployeeController empController;
    private final IEmployeeSearchResult searchResult;
    
    private JPanel wrapperPanel;
    private SearchPanel searchPanel;
    
    
    private List<UserDataDTO> allEmployees;
    
    private EmployeeListPanel employeeListPanel;
    
    
    public DashboardView (DeviceController controller, EmployeeController empController , 
            IEmployeeSearchResult searchResult, IDeviceClickListener listener, IDeviceStatusListener statusListener) {
        
        this.controller = controller;
        this.empController = empController;
        this.searchResult = searchResult;
        
        searchPanel = new SearchPanel();
        
        allEmployees = empController.loadEmployees();
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

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

        searchPanel.getSearchField().getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) { 
                        System.out.println("Type detect");
                        filterEmployees(); 
                    }
                   
                    @Override
                    public void removeUpdate(DocumentEvent e){ filterEmployees(); }
                    
                    @Override
                    public void changedUpdate(DocumentEvent e){ filterEmployees(); }
        });
        
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadDevices(listener, statusListener);
    }
    
    public void loadDevices(IDeviceClickListener listener, IDeviceStatusListener statusListener) {
        
        List<DeviceDTO> devices = controller.loadDevices();
        setDevices(devices, listener, statusListener); 
        
    }
    
    private void filterEmployees() {

        System.out.println("FILTER RUNNING");

        if(allEmployees == null) return;

        String keyword = searchPanel.getSearchField().getText().trim().toLowerCase();

        if(keyword.isEmpty()){
            searchPanel.hideSuggestions();
            return;
        }

        List<UserDataDTO> matches = new ArrayList<>();

        for(UserDataDTO user : allEmployees){

            if(user.getEmpName().toLowerCase().contains(keyword)){
                matches.add(user);

                if(matches.size() == 5) break;
            }
        }

        if(matches.isEmpty()){
            searchPanel.hideSuggestions();
            return;
        }

        searchPanel.showSuggestion(matches, new IEmployeeSuggestionListener(){
            @Override
            public void onEmployeeSelected(UserDataDTO user) {
                int deviceId = user.getDeviceId();
                
                List<UserDataDTO> users = empController.loadEmployeesByDevice(deviceId);
                DeviceDTO device = controller.getDeviceById(deviceId);
                
                searchResult.showEmployees(users, device);
                
                searchPanel.hideSuggestions();
            }
        });
    }
    
    public void onEmployeeSelected(UserDataDTO user, DeviceDTO device){

        int deviceId = user.getDeviceId();

        List<UserDataDTO> users =
                empController.loadEmployeesByDevice(deviceId);

        employeeListPanel.setEmployees(users, device);

        removeAll();
        add(searchPanel, BorderLayout.NORTH);
        add(employeeListPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
    
    public void setDevices(List<DeviceDTO> devices, IDeviceClickListener listener, IDeviceStatusListener statusListener) {
        
        wrapperPanel.removeAll();
        
        for(DeviceDTO device : devices) {
            wrapperPanel.add(new BioCard(device, listener, statusListener));
        }
        
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }
    
    public void setEmployees(List<UserDataDTO> users, DeviceDTO device) {

        allEmployees = users;                // store full list

        employeeListPanel.setEmployees(users, device); // display in UI
    }
}
