package Service;

import bioclock.dto.DeviceDTO;
import java.util.List;

public interface IDeviceDataService {
    List<DeviceDTO> getAllDevicesDTO();
    
    void updateStatus(int deviceId, String status);
}
