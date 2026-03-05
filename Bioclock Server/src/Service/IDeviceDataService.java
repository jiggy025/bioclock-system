package Service;

import bioclock.dto.DeviceDTO;
import java.util.List;

public interface IDeviceDataService {
    List<DeviceDTO> getAllDevicesDTO();
}
