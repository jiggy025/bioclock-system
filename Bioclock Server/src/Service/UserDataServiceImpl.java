package Service;

import bioclock.dto.UserDataDTO;
import bioclock.server.entity.UserData;
import java.util.ArrayList;
import java.util.List;

public class UserDataServiceImpl implements IUserDataService {
    
    private IUserDataRepository repository;
    
    public UserDataServiceImpl(IUserDataRepository repository){
        System.out.println("Service layer called");
        this.repository = repository;
    }   

    public List<UserDataDTO> getAllUsersDTO() {
        // Fetch all users
        System.out.println("Service layer called");
        List<UserData> users = repository.findAll();
        List<UserDataDTO> dtoList = new ArrayList<UserDataDTO>();

        for (UserData user : users) {
            UserDataDTO dto = new UserDataDTO();
            dto.setEmpName(user.getEmpName());
            dto.setEmpID(user.getEmpId());
            dto.setEmpIdNum(user.getEmpIdNum());
            
            dtoList.add(dto);
        }
        return dtoList;
    }
}
