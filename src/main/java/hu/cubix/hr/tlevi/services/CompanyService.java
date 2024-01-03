package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.CompanyDTO;
import hu.cubix.hr.tlevi.dtos.EmployeeDto;

import java.util.List;

public interface CompanyService {
    List<?> findAll(Boolean full);

    Object getCompanyById(long id, Boolean full);

    CompanyDTO createCompany(CompanyDTO companyDTO);

    void deleteCompanyById(long id);

    CompanyDTO modifyEmployeeById(CompanyDTO companyDTO, long id);

    CompanyDTO addEmployeeToCompany(long id, EmployeeDto employeedto);

    CompanyDTO exchangeEmployeesAtCompany(List<EmployeeDto> list, long id);

    void deleteEmployeeFromCompany(long cid, long eid);
}
