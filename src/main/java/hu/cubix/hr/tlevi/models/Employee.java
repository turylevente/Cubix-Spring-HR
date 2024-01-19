package hu.cubix.hr.tlevi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "employee_name")
    private String name;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonBackReference("employee-job")

    private Job job;
    private Integer salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startOfTheWork;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference("employee-company")
    private Company company;

    public Employee(long id, String name, Job job, Integer salary, LocalDateTime startOfTheWork, Company company) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
        this.company = company;
    }

    public Employee(long id, String name, Job job, Integer salary, LocalDateTime startOfTheWork) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
    }

    public Employee(long id, Job job, Integer salary, LocalDateTime startOfTheWork) {
        this.id = id;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
    }

    public Employee() {

    }

    public Employee(int i, String elemér, String job3, int i1, LocalDateTime of) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getStartOfTheWork() {
        return startOfTheWork;
    }

    public void setStartOfTheWork(LocalDateTime startOfTheWork) {
        this.startOfTheWork = startOfTheWork;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}