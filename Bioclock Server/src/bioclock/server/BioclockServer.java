package bioclock.server;

import Config.HibernateUtil;
import RMI.UserDataImpl;
import Repository.HibernateRepository;
import Service.IUserDataRepository;
import Service.IUserDataService;
import Service.UserDataServiceImpl;
import bioclock.common.UserService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.hibernate.SessionFactory;

public class BioclockServer {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            
            IUserDataRepository userRepository = new HibernateRepository(sessionFactory);
            
            IUserDataService userDataService = new UserDataServiceImpl(userRepository);
            
            UserService userService = new UserDataImpl(userDataService);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            registry.rebind("UserService", userService);
            
            System.out.println("Server started successfully!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
