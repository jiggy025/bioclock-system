package Service;

import bioclock.server.entity.ActivityLog;
import java.util.List;

public interface IActivityLogRepository {
    
    void save(ActivityLog log);
    
    List<ActivityLog> findAll();
    
}
