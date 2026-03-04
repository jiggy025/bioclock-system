package Service;

import bioclock.dto.UserDataDTO;
import java.util.List;

public interface IUserDataService {
    List<UserDataDTO> getAllUsersDTO();
}
