package hu.cubix.hr.tlevi.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Employee {
    private long id;
    private String name;
    private String job;
    private Integer salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startOfTheWork;

    public Employee(long id, String name, String job, Integer salary, LocalDateTime startOfTheWork) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
    }

    public Employee(long id, String job, Integer salary, LocalDateTime startOfTheWork) {
        this.id = id;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
    }

    public Employee() {

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
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
}