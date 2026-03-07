package Repository;

import bioclock.server.entity.UserData;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import Service.IUserDataRepository;

public class HibernateRepository implements IUserDataRepository {

    private SessionFactory sessionFactory;
    
    public HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(UserData user) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            session.save(user);
            
            tx.commit();
        } catch (Throwable e) {
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<UserData> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<UserData> users = null;

        try {
            tx = session.beginTransaction();
            users = session.createQuery("from UserData").list();
            tx.commit();
            return users;
            
        } catch (Throwable e) {
            if (tx != null) 
                tx.rollback();
                System.out.println("DAO layer called");
            throw e;
        } finally {
            session.close();
        }
    }
    
    public List<UserData> findByDeviceId(int deviceId) {
        Session session = sessionFactory.openSession();
        List<UserData> users = null;
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            users = (List<UserData>) session.createQuery("FROM UserData WHERE device.id = :deviceId")
                    .setParameter("deviceId", deviceId)
                    .list();
            tx.commit();
            return users;
        } catch (Throwable e) {
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    public UserData getEmployeeById(int employeeId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            UserData user = (UserData) session
                .createQuery("FROM UserData WHERE empID = :id")
                .setParameter("id", employeeId)
                .uniqueResult();
            
            tx.commit();
            return user;
        } catch (Throwable e) {
            if (tx != null) 
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
