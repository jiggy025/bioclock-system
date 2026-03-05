package UI.Controller;

import bioclock.client.service.EmployeeService;
import bioclock.common.UserService;
import bioclock.dto.UserDataDTO;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;


public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    public List<UserDataDTO> loadEmployees()  {
        return employeeService.getAllUsers();
    }
    
    public void addEmployees(UserDataDTO user) {
        employeeService.addEmployee(user);
    }
}
