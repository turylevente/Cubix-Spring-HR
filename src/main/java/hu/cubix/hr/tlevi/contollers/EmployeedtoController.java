package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.mapper.EmployeeMapper;
import hu.cubix.hr.tlevi.models.Job;
import hu.cubix.hr.tlevi.repositories.EmployeeRepository;
import hu.cubix.hr.tlevi.repositories.JobRepository;
import hu.cubix.hr.tlevi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/employees")
public class EmployeedtoController {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private JobRepository jobRepository;

    @Autowired
    public EmployeedtoController(EmployeeService employeeService, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, JobRepository jobRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.jobRepository = jobRepository;
    }

    public EmployeedtoController() {
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeMapper.employeesToDto(employeeService.findAll()));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeMapper.employeeToEmployeeDto(employeeService.createEmployee(employeeMapper.employeeDtoToEmployee(employeeDto))));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EmployeeDto>> listEmployeesWithHigherSalary(@RequestParam int limit) {
        return ResponseEntity.ok(employeeMapper.employeesToDto(employeeService.listEmployeesWithHigherSalary(limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeMapper.employeeToEmployeeDto(employeeService.getEmployeeById(id)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable long id) {
        return ResponseEntity.ok(employeeMapper.employeeToEmployeeDto(employeeService.modifyEmployeeId(employeeDto, id)));

    }

    @GetMapping("/findByJob/{jobId}")
    public ResponseEntity<List<EmployeeDto>> findByJob(@PathVariable long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(RuntimeException::new);
        return (ResponseEntity.ok(employeeMapper.employeesToDto(employeeRepository.findByJob_Id(job.getId()))));
    }

    @GetMapping("/findByName/{namePrefix}")
    public ResponseEntity<List<EmployeeDto>> findByNameIgnoreCaseStartingWith(@PathVariable String namePrefix) {
        return ResponseEntity.ok(employeeMapper.employeesToDto(employeeRepository.findByNameIgnoreCaseStartingWith(namePrefix)));
    }

    @GetMapping("/findByStartOfTheWorkBetween/{startDate}/{endDate}")
    public ResponseEntity<List<EmployeeDto>> findByStartOfTheWorkBetween(@PathVariable("startDate") LocalDateTime startDate, @PathVariable("endDate") LocalDateTime endDate) {
        return ResponseEntity.ok(employeeMapper.employeesToDto(employeeRepository.findByStartOfTheWorkBetween(startDate, endDate)));
    }
}
