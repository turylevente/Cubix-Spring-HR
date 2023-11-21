package hu.cubix.hr.tlevi;

import hu.cubix.hr.tlevi.Models.Employee;
import hu.cubix.hr.tlevi.Services.EmployeeService;
import hu.cubix.hr.tlevi.Services.SalaryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {
    EmployeeService employeeService;
    SalaryService salaryService;

    public HrApplication(EmployeeService employeeService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.salaryService = salaryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee István = new Employee(1, "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0));
        Employee Géza = new Employee(2, "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0));
        Employee Elemér = new Employee(3, "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0));

        salaryService.setNewSalary(István);
        salaryService.setNewSalary(Géza);
        salaryService.setNewSalary(Elemér);

        System.out.println(István.getSalary());
        System.out.println(Géza.getSalary());
        System.out.println(Elemér.getSalary());


    }
}
