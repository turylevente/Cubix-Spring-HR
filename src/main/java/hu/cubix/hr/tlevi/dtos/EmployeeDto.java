package hu.cubix.hr.tlevi.dtos;

import java.time.LocalDateTime;

public class EmployeeDto {
    private long id;
    private String name;
    private String job;
    private Integer salary;
    private LocalDateTime startOfTheWork;

    public EmployeeDto(long id, String name, String job, Integer salary, LocalDateTime startOfTheWork) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
    }

    public EmployeeDto(long id, String job, Integer salary, LocalDateTime startOfTheWork) {
        this.id = id;
        this.job = job;
        this.salary = salary;
        this.startOfTheWork = startOfTheWork;
    }

    public EmployeeDto() {
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

