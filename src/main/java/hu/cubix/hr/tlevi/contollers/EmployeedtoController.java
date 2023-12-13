package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/employees")
public class EmployeedtoController {
    private final Map<Long, EmployeeDto> employees = new HashMap<>();
    Employee István = new Employee(1, "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0));
    Employee Géza = new Employee(2, "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0));
    Employee Elemér = new Employee(3, "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0));

    {
        employees.put(István.getId(), new EmployeeDto(1, "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
        employees.put(Géza.getId(), new EmployeeDto(2, "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
        employees.put(Elemér.getId(), new EmployeeDto(3, "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
    }

    @GetMapping
    public List<EmployeeDto> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        if (employees.containsKey(employeeDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        employees.put(employeeDto.getId(), employeeDto);
        return ResponseEntity.ok(employees.get(employeeDto.getId()));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EmployeeDto>> listEmployeesWithHigherSalary(@RequestParam int limit) {
        List<EmployeeDto> list = new ArrayList<>();
        for (EmployeeDto employee : employees.values()) {
            if (employee.getSalary() > limit) {
                list.add(employee);
            }
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        if (!employees.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable long id) {
        employees.remove(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable long id) {
        EmployeeDto employee = employees.get(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employees.put(id, employeeDto);
        return ResponseEntity.ok(employees.get(id));
    }
}
