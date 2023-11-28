package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    Employee István = new Employee(1, "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0));
    Employee Géza = new Employee(2, "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0));
    Employee Elemér = new Employee(3, "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0));
    private List<Employee> employees = new ArrayList<>();

    {
        employees.add(new Employee(1, "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
        employees.add(new Employee(2, "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
        employees.add(new Employee(3, "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
    }

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("newEmployee", new Employee());
        model.put("employees", employees);
        return "index";
    }

    @PostMapping("/employee")
    public String home(Employee employee) {
        employees.add(employee);
        return "redirect:/";
    }
}
