package hu.cubix.hr.tlevi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hu.cubix.hr.tlevi.enums.neededQualificationTypes;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue
    private long id;
    private String nameOfPosition;
    @Enumerated(EnumType.STRING)
    private neededQualificationTypes neededQualification;
    private int minimalSalary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job", fetch = FetchType.LAZY)
    @JsonBackReference("employee-job")
    private List<Employee> emList;

    public Job(long id, String nameOfPosition, neededQualificationTypes neededQualification, int minimalSalary) {
        this.id = id;
        this.nameOfPosition = nameOfPosition;
        this.neededQualification = neededQualification;
        this.minimalSalary = minimalSalary;
    }

    public Job() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfPosition() {
        return nameOfPosition;
    }

    public void setNameOfPosition(String nameOfPosition) {
        this.nameOfPosition = nameOfPosition;
    }

    public neededQualificationTypes getNeededQualification() {
        return neededQualification;
    }

    public void setNeededQualification(neededQualificationTypes neededQualification) {
        this.neededQualification = neededQualification;
    }

    public int getMinimalSalary() {
        return minimalSalary;
    }

    public void setMinimalSalary(int minimalSalary) {
        this.minimalSalary = minimalSalary;
    }

    public List<Employee> getEmList() {
        return emList;
    }

    public void setEmList(List<Employee> emList) {
        this.emList = emList;

    }
}
