package Service;

import bioclock.dto.UserDataDTO;
import java.util.List;

public interface IUserDataService {
    List<UserDataDTO> getAllUsersDTO();

    void addEmployee(UserDataDTO user);
    
    List<UserDataDTO> loadEmployeesByDevice(int deviceId);
    
    UserDataDTO getEmployeeById(int employeeId);
}
