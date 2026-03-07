package Service;

import bioclock.dto.DeviceDTO;
import bioclock.server.entity.BioDevice;
import java.util.List;

public interface IDeviceDataRepository {
    List<BioDevice> getAllDevices();
    
    BioDevice findById(int id);
    
    void updateStatus(int deviceId, String status);
    
    int getEmployeeCountByDevice(int deviceId);
    
    BioDevice getDeviceById(int deviceId);
}
