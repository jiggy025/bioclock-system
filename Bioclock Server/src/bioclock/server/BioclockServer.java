package bioclock.server;

import Config.HibernateUtil;
import RMI.DeviceDataImpl;
import RMI.UserDataImpl;
import Repository.DeviceDataRepository;
import Repository.HibernateRepository;
import Service.DeviceDataServiceImpl;
import Service.IDeviceDataRepository;
import Service.IDeviceDataService;
import Service.IUserDataRepository;
import Service.IUserDataService;
import Service.UserDataServiceImpl;
import bioclock.common.DeviceService;
import bioclock.common.UserService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.hibernate.SessionFactory;

public class BioclockServer {

    public static void main(String[] args) {
        try {
            
            //UserService Registry
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            
            IUserDataRepository userRepository = new HibernateRepository(sessionFactory);
            
            IDeviceDataRepository dataRepository = new DeviceDataRepository(sessionFactory);
            
            IUserDataService userDataService = new UserDataServiceImpl(userRepository, dataRepository);
            
            UserService userService = new UserDataImpl(userDataService);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            registry.rebind("UserService", userService);
            
            //DeviceService Registry
            IDeviceDataService deviceDataService = new DeviceDataServiceImpl(dataRepository);
            
            DeviceService deviceService = new DeviceDataImpl(deviceDataService);
            
            registry.rebind("DeviceService", deviceService);
            
            System.out.println("Server started successfully!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
