package RMIConnector;

<<<<<<< HEAD
import bioclock.common.ActivityLogService;
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
import bioclock.common.DeviceService;
import bioclock.common.UserService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
<<<<<<< HEAD
    
    private static final String host = "localhost";
    private static final int port = 1099;
    
    public static UserService getService() throws Exception {
        Registry registry = LocateRegistry.getRegistry(host, port);
=======
    public static UserService getService() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
        UserService service = (UserService) registry.lookup("UserService");
        return service;
    }
    
    public static DeviceService getDeviceService() throws Exception {
<<<<<<< HEAD
        Registry registry = LocateRegistry.getRegistry(host, port);
        DeviceService deviceService = (DeviceService) registry.lookup("DeviceService");
        return deviceService;
    }
    
    public static ActivityLogService getActivityLogService() throws Exception {
        Registry registry = LocateRegistry.getRegistry(host, port);
        ActivityLogService logService = (ActivityLogService) registry.lookup("LogService");
        return logService;
    }
=======
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        DeviceService deviceService = (DeviceService) registry.lookup("DeviceService");
        return deviceService;
    }
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
}