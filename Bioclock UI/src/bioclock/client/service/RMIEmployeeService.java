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
}