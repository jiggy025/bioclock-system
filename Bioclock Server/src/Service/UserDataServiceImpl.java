package Service;

import Repository.DeviceDataRepository;
import bioclock.dto.UserDataDTO;
import bioclock.server.entity.BioDevice;
import bioclock.server.entity.UserData;
import java.util.ArrayList;
import java.util.List;

public class UserDataServiceImpl implements IUserDataService {
    
    private IUserDataRepository repository;
    private IDeviceDataRepository deviceRepository;
    
    public UserDataServiceImpl(IUserDataRepository repository, IDeviceDataRepository deviceRepository){
        this.repository = repository;
        this.deviceRepository = deviceRepository;
    }   

    public void addEmployee(UserDataDTO dto) {
        BioDevice device = deviceRepository.findById(dto.getDeviceId());
        
        UserData user = new UserData();
        user.setEmpName(dto.getEmpName());
        user.setEmpIdNum(dto.getEmpIdNum());
        user.setDevice(device);
        
        repository.save(user);
    }
    
    public List<UserDataDTO> getAllUsersDTO() {
        // Fetch all users
        System.out.println("Service layer called");
        List<UserData> users = repository.findAll();
        List<UserDataDTO> dtoList = new ArrayList<UserDataDTO>();

        for (UserData user : users) {
            dtoList.add(convertToDTO(user));
        }
        return dtoList;
    }

    @Override
    public List<UserDataDTO> loadEmployeesByDevice(int deviceId) {
        List<UserData> users = repository.findByDeviceId(deviceId);
        
        List<UserDataDTO> dtoList = new ArrayList<>();
        
        for(UserData user : users) {
            dtoList.add(convertToDTO(user));
        }
        
        return dtoList;
    }
    
    private UserDataDTO convertToDTO(UserData user) {
        UserDataDTO dto = new UserDataDTO();
        
        dto.setEmpID(user.getEmpId());
        dto.setEmpName(user.getEmpName());
        dto.setEmpIdNum(user.getEmpIdNum());
        
        if(user.getDevice() != null) {
            dto.setDeviceId(user.getDevice().getId());
        }
        
        return dto;
    }
    
    public UserDataDTO getEmployeeById(int employeeId) {
        UserData users = repository.getEmployeeById(employeeId);
        
        return convertToDTO(users);
    }
}
