package hu.cubix.hr.tlevi.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public class EmployeeDto {

    @PositiveOrZero
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String job;
    @Positive
    private Integer salary;
    @Past
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

