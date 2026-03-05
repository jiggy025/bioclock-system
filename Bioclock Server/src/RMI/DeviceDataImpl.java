package RMI;

import Service.IDeviceDataService;
import bioclock.common.DeviceService;
import bioclock.dto.DeviceDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DeviceDataImpl extends UnicastRemoteObject implements DeviceService {

    private final IDeviceDataService deviceService;
    
    public DeviceDataImpl(IDeviceDataService deviceService) throws RemoteException {
        this.deviceService = deviceService;
    }
    
    @Override
    public List<DeviceDTO> getAllDevices() throws RemoteException {
        return deviceService.getAllDevicesDTO();
    }
    
}
