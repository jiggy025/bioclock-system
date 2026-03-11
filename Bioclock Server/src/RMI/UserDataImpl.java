package RMI;

import bioclock.dto.UserDataDTO;
import Service.IUserDataService;
import bioclock.common.UserService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserDataImpl extends UnicastRemoteObject implements UserService {

    private final IUserDataService userDataService;
    
    public UserDataImpl(IUserDataService userDataService) throws RemoteException{
        super();
        System.out.println("RMI method invoked!");
        this.userDataService = userDataService;
    }

    @Override
    public List<UserDataDTO> getAllUsers() throws RemoteException {
        System.out.println("RMI method invoked!");
        return userDataService.getAllUsersDTO();
    }

    @Override
    public void addEmployee(UserDataDTO user) throws RemoteException {
        userDataService.addEmployee(user);
    }

    @Override
    public List<UserDataDTO> loadEmployeesByDevice(int deviceId) throws RemoteException {
        return userDataService.loadEmployeesByDevice(deviceId);
    }

    @Override
    public UserDataDTO getEmployeeById(int employeeId) throws RemoteException {
        return userDataService.getEmployeeById(employeeId);
    }
}