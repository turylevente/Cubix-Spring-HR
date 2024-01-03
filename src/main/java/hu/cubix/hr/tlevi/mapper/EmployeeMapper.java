package hu.cubix.hr.tlevi.mapper;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.models.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);

    List<EmployeeDto> employeesToDto(List<Employee> employeeList);

    List<Employee> employeesDtoToEmployeeList(List<EmployeeDto> employeeDtoList);

}
