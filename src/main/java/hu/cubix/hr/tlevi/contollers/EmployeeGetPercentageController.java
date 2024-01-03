package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.models.Employee;
import hu.cubix.hr.tlevi.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/companies/get-pay-raise-percent")

public class EmployeeGetPercentageController {
    @Autowired
    SalaryService salaryService;

    @GetMapping
    public ResponseEntity<Integer> getPayRaisePercent(@RequestBody Employee employee) {
        salaryService.setNewSalary(employee);
        return ResponseEntity.ok(employee.getSalary());
    }
}
