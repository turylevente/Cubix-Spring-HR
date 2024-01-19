package hu.cubix.hr.tlevi.dtos;

import hu.cubix.hr.tlevi.enums.CompanyTypes;
import hu.cubix.hr.tlevi.models.Employee;

import java.util.List;

public class CompanyWithoutEmployeesDTO {
    private long id;
    private long registationNumber;
    private String name;
    private String adress;
    private CompanyTypes type;


    public CompanyWithoutEmployeesDTO() {
    }

    public CompanyWithoutEmployeesDTO(long id, int registationNumber, String name, String adress, List<Employee> employeeList) {
        this.id = id;
        this.registationNumber = registationNumber;
        this.name = name;
        this.adress = adress;

    }

    public CompanyWithoutEmployeesDTO(long id, long registationNumber, String name, String adress, List<Employee> employeeList, CompanyTypes type) {
        this.id = id;
        this.registationNumber = registationNumber;
        this.name = name;
        this.adress = adress;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRegistationNumber() {
        return registationNumber;
    }

    public void setRegistationNumber(long registationNumber) {
        this.registationNumber = registationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public CompanyTypes getType() {
        return type;
    }

    public void setType(CompanyTypes type) {
        this.type = type;
    }
}
