package bioclock.client.service;

import bioclock.dto.UserDataDTO;
import java.util.List;

public interface EmployeeService {
    List<UserDataDTO> getAllUsers();
    
    void addEmployee(UserDataDTO user);
    
    List<UserDataDTO> loadEmployeesByDevice(int deviceId);
}
