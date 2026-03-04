package RMIConnector;

import bioclock.common.UserService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
    public static UserService getService() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        UserService service = (UserService) registry.lookup("UserService");
        return service;
    }
}