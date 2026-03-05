package Service;

import bioclock.dto.DeviceDTO;
import bioclock.server.entity.BioDevice;
import java.util.ArrayList;
import java.util.List;

public class DeviceDataServiceImpl implements IDeviceDataService{

    private final IDeviceDataRepository repository;
    
    public DeviceDataServiceImpl(IDeviceDataRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<DeviceDTO> getAllDevicesDTO() {
        
        List<BioDevice> devices = repository.getAllDevices();
        
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        
        for(BioDevice device : devices) {
            DeviceDTO dto = new DeviceDTO();
            
            dto.setId(device.getId());
            dto.setName(device.getName());
            dto.setLocation(device.getLocation());
            dto.setStatus(device.getStatus());
            
            deviceDTOS.add(dto);
        }
        
        return deviceDTOS;
    }

    @Override
    public void updateStatus(int deviceId, String status) {
        repository.updateStatus(deviceId, status);
    }
}
