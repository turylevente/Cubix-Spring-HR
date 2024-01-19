package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.models.Employee;

import java.util.List;

public interface EmployeeService {

    int getPayRaisePercent(Employee employee);

    List<Employee> findAll();

    Employee createEmployee(Employee employee);

    List<Employee> listEmployeesWithHigherSalary(int limit);

    Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

    Employee modifyEmployeeId(EmployeeDto employeeDto, long id);
}
