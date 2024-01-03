package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.exception.IncorrectIdException;
import hu.cubix.hr.tlevi.mapper.EmployeeMapper;
import hu.cubix.hr.tlevi.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EmployeeServiceImp implements EmployeeService {
    private final Map<Long, Employee> employees = new HashMap<>();
    @Autowired
    EmployeeMapper employeeMapper;
    Employee István = new Employee(1, "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0));
    Employee Géza = new Employee(2, "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0));
    Employee Elemér = new Employee(3, "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0));

    {
        employees.put(István.getId(), new Employee(1, "István", "job1", 100, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
        employees.put(Géza.getId(), new Employee(2, "Géza", "job2", 100, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
        employees.put(Elemér.getId(), new Employee(3, "Elemér", "job3", 100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
    }

    @Override
    public int getPayRaisePercent(Employee employee) {
        return 0;
    }

    public List<EmployeeDto> findAll() {
        return employeeMapper.employeesToDto(new ArrayList<>(employees.values()));
    }


    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employees.containsKey(employeeDto.getId()) || employeeDto.getId() < 0) {
            throw new IncorrectIdException();
        }
        employees.put(employeeDto.getId(), employeeMapper.employeeDtoToEmployee(employeeDto));
        return employeeMapper.employeeToEmployeeDto(employees.get(employeeDto.getId()));
    }

    public EmployeeDto getEmployeeById(long id) {
        if (!employees.containsKey(id)) {
            throw new IncorrectIdException();
        }
        return employeeMapper.employeeToEmployeeDto(employees.get(id));

    }

    public List<EmployeeDto> listEmployeesWithHigherSalary(int limit) {
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getSalary() > limit) {
                list.add(employeeMapper.employeeToEmployeeDto(employee));
            }
        }
        return list;
    }

    public void deleteEmployeeById(long id) {
        employees.remove(id);
    }

    public EmployeeDto modifyEmployeeId(EmployeeDto employeeDto, long id) {
        employeeDto.setId(id);
        Employee employee = employees.get(id);
        if (employee == null) {
            throw new IncorrectIdException();
        }
        employees.put(id, employeeMapper.employeeDtoToEmployee(employeeDto));
        return employeeMapper.employeeToEmployeeDto(employees.get(id));
    }
}
