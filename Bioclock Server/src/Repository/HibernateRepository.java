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
    public List<UserData> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<UserData> users = null;

        System.out.println("DAO layer called");
        try {
            tx = session.beginTransaction();
            users = session.createQuery("from UserData").list();
            tx.commit();
            System.out.println("DAO layer called");
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
    
}
