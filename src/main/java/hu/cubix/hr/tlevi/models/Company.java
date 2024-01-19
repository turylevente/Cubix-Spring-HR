package hu.cubix.hr.tlevi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hu.cubix.hr.tlevi.enums.CompanyTypes;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue
    private long id;
    private long registationNumber;
    private String name;
    private String adress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference("employee-company")
    private List<Employee> employeeList;
    @Enumerated(EnumType.STRING)
    private CompanyTypes type;

    public Company() {
    }

    public Company(long id, int registationNumber, String name, String adress, List<Employee> employeeList) {
        this.id = id;
        this.registationNumber = registationNumber;
        this.name = name;
        this.adress = adress;
        this.employeeList = employeeList;
    }

    public Company(long id, long registationNumber, String name, String adress, List<Employee> employeeList, CompanyTypes type) {
        this.id = id;
        this.registationNumber = registationNumber;
        this.name = name;
        this.adress = adress;
        this.employeeList = employeeList;
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public CompanyTypes getType() {
        return type;
    }

    public void setType(CompanyTypes type) {
        this.type = type;
    }

}