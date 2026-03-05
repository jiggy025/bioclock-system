package UI.Controller;

import bioclock.client.service.RMIDeviceService;
import bioclock.dto.DeviceDTO;
import java.util.List;

public class DeviceController {
    
    private final RMIDeviceService service;
    
    public DeviceController(RMIDeviceService service) {
        this.service = service;
    }
    
    public List<DeviceDTO> loadDevices() {
        return service.getAllDevices();
    }
}
