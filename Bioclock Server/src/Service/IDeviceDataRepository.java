package Service;

import bioclock.server.entity.BioDevice;
import java.util.List;

public interface IDeviceDataRepository {
    List<BioDevice> getAllDevices();
    
    BioDevice findById(int id);
    
    void updateStatus(int deviceId, String status);
}
