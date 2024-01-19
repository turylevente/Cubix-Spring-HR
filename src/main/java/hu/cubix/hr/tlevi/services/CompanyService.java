package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.models.Company;
import hu.cubix.hr.tlevi.models.Employee;

import java.util.List;

public interface CompanyService {
    List<?> findAll(Boolean full);

    Company getCompanyById(long id, Boolean full);

    Company createCompany(Company company);

    void deleteCompanyById(long id);

    Company modifyCompanyById(Company company, long id);

    Company addEmployeeToCompany(long id, Employee employee);

    Company exchangeEmployeesAtCompany(List<Employee> list, long id);

    void deleteEmployeeFromCompany(long companyId, long employeeIdd);
}
