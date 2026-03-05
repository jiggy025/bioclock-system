package bioclock.common;

import bioclock.dto.UserDataDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    List<UserDataDTO> getAllUsers() throws RemoteException;
    
    void addEmployee(UserDataDTO user) throws RemoteException;
    
    List<UserDataDTO> loadEmployeesByDevice(int deviceId) throws RemoteException;
}
