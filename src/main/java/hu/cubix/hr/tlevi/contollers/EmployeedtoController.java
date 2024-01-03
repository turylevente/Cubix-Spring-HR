package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employees")
public class EmployeedtoController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeedtoController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeedtoController() {
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EmployeeDto>> listEmployeesWithHigherSalary(@RequestParam int limit) {
        return ResponseEntity.ok(employeeService.listEmployeesWithHigherSalary(limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable long id) {
        return ResponseEntity.ok(employeeService.modifyEmployeeId(employeeDto, id));

    }
}
