package UI.Listener;

import bioclock.dto.DeviceDTO;
import bioclock.dto.UserDataDTO;
import java.util.List;

public interface IEmployeeSearchResult {
    void showEmployees(List<UserDataDTO> users, DeviceDTO device);
}
