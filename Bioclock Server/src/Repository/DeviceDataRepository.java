package Repository;

import Service.IDeviceDataRepository;
import bioclock.dto.DeviceDTO;
import bioclock.server.entity.BioDevice;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeviceDataRepository implements IDeviceDataRepository {
    
    private SessionFactory sessionFactory;
    
    public DeviceDataRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<BioDevice> getAllDevices() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<BioDevice> devices = null;

        try {
            tx = session.beginTransaction();
            devices = session.createQuery("FROM BioDevice").list();
            tx.commit();
            return devices;
            
        } catch (Throwable e) {
            if (tx != null) 
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    @Override
    public BioDevice findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        BioDevice device = null;
        
        try {
            tx = session.beginTransaction();
            
            device = (BioDevice) session.get(BioDevice.class, id);
            
            tx.commit();
            return device;
        } catch (Throwable e) {
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    public void updateStatus(int deviceId, String status) {
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Query query = session.createQuery(
                "UPDATE BioDevice SET status = :status WHERE id = :id");
            
            query.setParameter("status", status);
            query.setParameter("id", deviceId);
            
            query.executeUpdate();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null) 
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    @Override
    public int getEmployeeCountByDevice(int deviceId) {
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Long count = (Long) session.createQuery(
                    "SELECT COUNT(u) FROM UserData u WHERE u.device.id = :deviceId")
                    .setParameter("deviceId", deviceId)
                    .uniqueResult();
            
            tx.commit();
            return count.intValue();
        } catch (Exception e) {
            if (tx != null) 
                tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public BioDevice getDeviceById(int deviceId) {
        Session session = sessionFactory.openSession();
        
        try {
            
            return (BioDevice) session.createQuery(
                "FROM BioDevice WHERE id = :deviceId")
                .setParameter("deviceId", deviceId)
                .uniqueResult();
            
        } finally {
            session.close();
        }
    }
}
