package bioclock.client.service;

import bioclock.dto.UserDataDTO;
import java.util.List;

public interface EmployeeService {
    List<UserDataDTO> getAllUsers();
}
