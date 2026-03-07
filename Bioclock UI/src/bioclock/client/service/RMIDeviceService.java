package bioclock.client.service;

import bioclock.common.DeviceService;
import bioclock.dto.DeviceDTO;
import java.rmi.RemoteException;
import java.util.List;

public class RMIDeviceService {
    
    private final DeviceService remoteService;
    
    public RMIDeviceService(DeviceService remoteService) {
        this.remoteService = remoteService;
    }
    
    public List<DeviceDTO> getAllDevices() {
        try {
            return remoteService.getAllDevices();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateStatus(int deviceId, String status) {
        try {
            remoteService.updateStatus(deviceId, status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public DeviceDTO getDeviceById(int deviceId) {
        try {
            return remoteService.getDeviceById(deviceId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
