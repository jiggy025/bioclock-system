package bioclock.client.service;

import bioclock.common.UserService;
import bioclock.dto.UserDataDTO;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

public class RMIEmployeeService implements EmployeeService {

    private final UserService remoteService;
    
    public RMIEmployeeService(UserService remoteService) {
        this.remoteService = remoteService;
    }
    
    @Override
    public List<UserDataDTO> getAllUsers() {
        try {
            return remoteService.getAllUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    @Override
    public void addEmployee(UserDataDTO user) {
        try {
            remoteService.addEmployee(user);
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to add employee", e);
        }
    }

    @Override
    public List<UserDataDTO> loadEmployeesByDevice(int deviceId) {
        
        try {
            return remoteService.loadEmployeesByDevice(deviceId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}