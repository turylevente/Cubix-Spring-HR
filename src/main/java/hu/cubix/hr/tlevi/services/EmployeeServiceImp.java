package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.exception.IncorrectIdException;
import hu.cubix.hr.tlevi.mapper.EmployeeMapper;
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
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public int getPayRaisePercent(Employee employee) {
        return 0;
    }

    public List<EmployeeDto> findAll() {
        return employeeMapper.employeesToDto(employeeRepository.findAll());
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDto.getId());
        if (optionalEmployee.isPresent()) {
            throw new IncorrectIdException();
        } else {
            return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto)));
        }
    }

    public EmployeeDto getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return employeeMapper.employeeToEmployeeDto(optionalEmployee.get());
        } else {
            throw new IncorrectIdException();
        }
    }

    public List<EmployeeDto> listEmployeesWithHigherSalary(int limit) {
        return employeeMapper.employeesToDto(employeeRepository.findBySalaryGreaterThan(limit));
    }

    @Transactional
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public EmployeeDto modifyEmployeeId(EmployeeDto employeeDto, long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeDto.setId(id);
            return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto)));
        } else {
            throw new IncorrectIdException();
        }
    }

    public List<EmployeeDto> findByJob(String job) {
        return employeeMapper.employeesToDto(employeeRepository.findByJob(job));
    }

    public List<EmployeeDto> findByNameIgnoreCaseStartingWith(String namePrefix) {
        return employeeMapper.employeesToDto(employeeRepository.findByNameIgnoreCaseStartingWith(namePrefix));
    }

    public List<EmployeeDto> findByStartOfTheWorkBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return employeeMapper.employeesToDto(employeeRepository.findByStartOfTheWorkBetween(startDate, endDate));
    }
}
