package Service;

import bioclock.server.entity.UserData;
import java.util.List;

public interface IUserDataRepository {
    List<UserData> findAll();
    
    void save(UserData user);
}