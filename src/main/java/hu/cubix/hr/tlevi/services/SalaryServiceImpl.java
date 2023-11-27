package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void setNewSalary(Employee employee) {
        employee.setSalary(employee.getSalary() / 100 * (100 + employeeService.getPayRaisePercent(employee)));
    }
}
