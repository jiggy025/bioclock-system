package bioclock.server;

import Config.HibernateUtil;
<<<<<<< HEAD
import RMI.ActivityLogImpl;
import RMI.DeviceDataImpl;
import RMI.UserDataImpl;
import Repository.ActivityLogRepository;
import Repository.DeviceDataRepository;
import Repository.HibernateRepository;
import Service.ActivityLogServiceImpl;
import Service.DeviceDataServiceImpl;
import Service.IActivityLogRepository;
import Service.IActivityLogService;
=======
import RMI.DeviceDataImpl;
import RMI.UserDataImpl;
import Repository.DeviceDataRepository;
import Repository.HibernateRepository;
import Service.DeviceDataServiceImpl;
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
import Service.IDeviceDataRepository;
import Service.IDeviceDataService;
import Service.IUserDataRepository;
import Service.IUserDataService;
import Service.UserDataServiceImpl;
<<<<<<< HEAD
import bioclock.common.ActivityLogService;
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
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
            
<<<<<<< HEAD
            IActivityLogRepository logRepository = new ActivityLogRepository(sessionFactory);
            
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
            IUserDataService userDataService = new UserDataServiceImpl(userRepository, dataRepository);
            
            UserService userService = new UserDataImpl(userDataService);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            registry.rebind("UserService", userService);
            
            //DeviceService Registry
            IDeviceDataService deviceDataService = new DeviceDataServiceImpl(dataRepository);
            
            DeviceService deviceService = new DeviceDataImpl(deviceDataService);
            
            registry.rebind("DeviceService", deviceService);
            
<<<<<<< HEAD
            //ActivityLogService Registry
            IActivityLogService ILogService = new ActivityLogServiceImpl(logRepository);
            
            ActivityLogService logService = new ActivityLogImpl(ILogService);
            
            registry.rebind("LogService", logService);
            
=======
>>>>>>> a064c69378a230fb0314893b29d8f940bc002a34
            System.out.println("Server started successfully!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
