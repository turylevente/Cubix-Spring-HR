package hu.cubix.hr.tlevi.models;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;

import java.util.List;

public class Company {
    private long id;
    private long registationNumber;
    private String name;
    private String adress;
    private List<EmployeeDto> employeeDtoList;

    public Company() {
    }

    public Company(long id, int registationNumber, String name, String adress, List<EmployeeDto> employeeDtoList) {
        this.id = id;
        this.registationNumber = registationNumber;
        this.name = name;
        this.adress = adress;
        this.employeeDtoList = employeeDtoList;
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

    public void setRegistationNumber(int registationNumber) {
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

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}