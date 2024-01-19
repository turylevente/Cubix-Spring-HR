package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.exception.IncorrectIdException;
import hu.cubix.hr.tlevi.models.Employee;
import hu.cubix.hr.tlevi.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class EmployeeServiceImp implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if (optionalEmployee.isPresent()) {
            throw new IncorrectIdException();
        } else {
            return employeeRepository.save(employee);
        }
    }

    public Employee getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new IncorrectIdException();
        }
    }

    public List<Employee> listEmployeesWithHigherSalary(int limit) {
        return employeeRepository.findBySalaryGreaterThan(limit);
    }

    @Transactional
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public Employee modifyEmployeeId(EmployeeDto employeeDto, long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeDto.setId(id);
            optionalEmployee.get().setJob(employeeDto.getJob());
            optionalEmployee.get().setName(employeeDto.getName());
            optionalEmployee.get().setCompany(employeeDto.getCompany());
            optionalEmployee.get().setSalary(employeeDto.getSalary());
            optionalEmployee.get().setStartOfTheWork(employeeDto.getStartOfTheWork());
            return employeeRepository.save(optionalEmployee.get());
        } else {
            throw new IncorrectIdException();
        }
    }

    public List<Employee> findByJob(long jobId) {
        return employeeRepository.findByJob_Id(jobId);
    }

    public List<Employee> findByNameIgnoreCaseStartingWith(String namePrefix) {
        return employeeRepository.findByNameIgnoreCaseStartingWith(namePrefix);
    }

    public List<Employee> findByStartOfTheWorkBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return employeeRepository.findByStartOfTheWorkBetween(startDate, endDate);
    }
}
