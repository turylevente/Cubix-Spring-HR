package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.mapper.EmployeeMapper;
import hu.cubix.hr.tlevi.models.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Validated
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>();
    private Employee employeeToSet;
    @Autowired
    private EmployeeMapper employeeMapper;

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
    public String addEmployee(@Valid EmployeeDto employeedto) {
        employees.add(employeeMapper.employeeDtoToEmployee(employeedto));
        return "redirect:/";
    }

    @DeleteMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employees.removeIf(employee -> employee.getId() == id);
        return "redirect:/";
    }

    @GetMapping("/modify-employee/{id}")
    public String showModifyEmployeeForm(@PathVariable int id, Model model) {
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst().ifPresent(employeeToSet -> model.addAttribute("setEmployee", employeeToSet));

        return "modify";
    }

    @PutMapping("/modify-employee")
    public String modifyEmployee(@ModelAttribute("setEmployee") @Valid EmployeeDto employeeToSet) {
        Employee employeeToSetDto = employeeMapper.employeeDtoToEmployee(employeeToSet);

        employees.stream()
                .filter(employee -> employee.getId() == employeeToSetDto.getId())
                .findFirst()
                .ifPresent(setEmployee -> {
                    if (setEmployee.getName() != null && !setEmployee.getName().isEmpty()) {
                        setEmployee.setName(employeeToSet.getName());
                    }
                    if (setEmployee.getJob() != null && setEmployee.getJob() != null) {
                        setEmployee.setJob(employeeToSet.getJob());
                    }
                    if (setEmployee.getSalary() != null) {
                        setEmployee.setSalary(employeeToSet.getSalary());
                    }
                    if (setEmployee.getStartOfTheWork() != null) {
                        setEmployee.setStartOfTheWork(employeeToSet.getStartOfTheWork());
                    }
                });

        return "redirect:/";
    }
}