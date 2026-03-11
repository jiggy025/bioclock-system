package UI.Controller;

import bioclock.client.service.EmployeeService;
import bioclock.common.UserService;
import bioclock.dto.UserDataDTO;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;


public class EmployeeController {
    
    private final EmployeeService employeeService;
<<<<<<< HEAD
    private final LogsController logsController;
    
    public EmployeeController(EmployeeService employeeService, LogsController logsController) {
        this.employeeService = employeeService;
        this.logsController = logsController;
=======
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
    }
    
    public List<UserDataDTO> loadEmployees()  {
        return employeeService.getAllUsers();
    }
    
    public void addEmployees(UserDataDTO user) {
        employeeService.addEmployee(user);
<<<<<<< HEAD
        logsController.log(ActvityActions.ADD_EMPLOYEE,
                "Added employee " + user.getEmpName());
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
    }
    
    public List<UserDataDTO> loadEmployeesByDevice(int deviceId) {
        return employeeService.loadEmployeesByDevice(deviceId);
    }
    
    public UserDataDTO getEmployeeById(int employeeId){
        return employeeService.getEmployeeById(employeeId);
    }
}
