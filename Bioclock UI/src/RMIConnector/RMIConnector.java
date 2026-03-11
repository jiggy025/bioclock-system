package RMIConnector;

import bioclock.common.ActivityLogService;
import bioclock.common.DeviceService;
import bioclock.common.UserService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    
    private static final String host = "localhost";
    private static final int port = 1099;
    
    public static UserService getService() throws Exception {
        Registry registry = LocateRegistry.getRegistry(host, port);
        UserService service = (UserService) registry.lookup("UserService");
        return service;
    }
    
    public static DeviceService getDeviceService() throws Exception {
        Registry registry = LocateRegistry.getRegistry(host, port);
        DeviceService deviceService = (DeviceService) registry.lookup("DeviceService");
        return deviceService;
    }
    
    public static ActivityLogService getActivityLogService() throws Exception {
        Registry registry = LocateRegistry.getRegistry(host, port);
        ActivityLogService logService = (ActivityLogService) registry.lookup("LogService");
        return logService;
    }
}