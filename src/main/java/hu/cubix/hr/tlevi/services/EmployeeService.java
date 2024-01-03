package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.models.Employee;

import java.util.List;

public interface EmployeeService {
    int getPayRaisePercent(Employee employee);

    List<EmployeeDto> findAll();

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> listEmployeesWithHigherSalary(int limit);

    EmployeeDto getEmployeeById(long id);

    void deleteEmployeeById(long id);

    EmployeeDto modifyEmployeeId(EmployeeDto employeeDto, long id);
}
