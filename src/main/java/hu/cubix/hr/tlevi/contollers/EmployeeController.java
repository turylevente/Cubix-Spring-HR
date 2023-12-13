package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>();
    private Employee employeeToSet;

    {
        employees.add(new Employee(1, "István", "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
        employees.add(new Employee(2, "Géza", "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
        employees.add(new Employee(3, "Elemér", "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
    }

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("newEmployee", new Employee());
        model.put("employees", employees);
        return "index";
    }

    @PostMapping("/employee")
    public String addEmployee(Employee employee) {
        employees.add(employee);
        return "redirect:/";
    }

    @PostMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employees.removeIf(employee -> employee.getId() == id);
        return "redirect:/";
    }

    @GetMapping("/modify-employee/{id}")
    public String showModifyEmployeeForm(@PathVariable int id, Model model) {
        model.addAttribute("setEmployee", new Employee());
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToSet = employee;
                break;
            }
        }
        return "modify";
    }

    @PostMapping("/modify-employee")
    public String modifyEmployee(@ModelAttribute("setEmployee") Employee setEmployee) {
        if (setEmployee.getName() != null && !setEmployee.getName().isEmpty()) {
            employeeToSet.setName(setEmployee.getName());
        }
        if (setEmployee.getJob() != null && !setEmployee.getJob().isEmpty()) {
            employeeToSet.setJob(setEmployee.getJob());
        }
        if (setEmployee.getSalary() != null) {
            employeeToSet.setSalary(setEmployee.getSalary());
        }
        if (setEmployee.getStartOfTheWork() != null) {
            employeeToSet.setStartOfTheWork(setEmployee.getStartOfTheWork());
        }
        return "redirect:/";
    }
}