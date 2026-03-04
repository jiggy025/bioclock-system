package bioclock.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empinfo")
public class UserData {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int empID;
    
    @Column(name="empName")
    private String empName;
    
    @Column(name="empIdNum")
    private int empIdNum;

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

    public void setEmpId(int empID) {
        this.empID = empID;
    }
    
    
}
