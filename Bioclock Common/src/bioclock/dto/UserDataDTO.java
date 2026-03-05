package bioclock.dto;

import java.io.Serializable;

public class UserDataDTO implements Serializable {
    private String empName;
    private int empIdNum;
    private int empID;
    
    private int deviceId;
    private String deviceName;

    public UserDataDTO() {
    }
    
    public UserDataDTO(int i, String eN, int eIN){
        empID = i;
        empName = eN;
        empIdNum = eIN;
    }
    
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpIdNum() {
        return empIdNum;
    }

    public void setEmpIdNum(int empIdNum) {
        this.empIdNum = empIdNum;
    }
    public int getEmpId() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
