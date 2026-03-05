package bioclock.common;

import bioclock.dto.DeviceDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DeviceService extends Remote {
    List<DeviceDTO> getAllDevices() throws RemoteException;
}
