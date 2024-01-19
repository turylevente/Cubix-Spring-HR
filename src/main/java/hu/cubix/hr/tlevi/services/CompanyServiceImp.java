package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.exception.IncorrectIdException;
import hu.cubix.hr.tlevi.exception.WrongRegistrationNumber;
import hu.cubix.hr.tlevi.models.Company;
import hu.cubix.hr.tlevi.models.Employee;
import hu.cubix.hr.tlevi.repositories.CompanyRepository;
import hu.cubix.hr.tlevi.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }


    @Transactional
    public Company createCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findByRegistationNumber(company.getRegistationNumber());
        if (existingCompany.isPresent()) {
            throw new WrongRegistrationNumber();
        } else {
            for (Employee employee : company.getEmployeeList()) {
                employee.setCompany(company);
            }
            company.setEmployeeList(company.getEmployeeList());
            companyRepository.save(company);

            return company;
        }
    }

    public Company getCompanyById(long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            throw new IncorrectIdException();
        } else {
            return optionalCompany.get();
        }
    }

    @Transactional

    public void deleteCompanyById(long id) {
        Optional<Company> optionalEmployee = companyRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new IncorrectIdException();
        } else {
            companyRepository.deleteById(id);
        }
    }

    @Transactional
    public Company modifyCompanyById(Company company, long id) {
        company.setId(id);
        Optional<Company> optionalEmployee = companyRepository.findByRegistationNumber(company.getRegistationNumber());
        if (optionalEmployee.isEmpty()) {
            throw new IncorrectIdException();
        } else {

            for (Employee employee : company.getEmployeeList()) {
                employee.setCompany(company);
            }
            companyRepository.save(company);
            return company;
        }
    }

    @Transactional
    public Company addEmployeeToCompany(long id, Employee employee) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            employee.setCompany(company);
            company.getEmployeeList().add(employee);
            companyRepository.save(company);
            return company;
        } else {
            throw new IncorrectIdException();
        }
    }

    @Transactional
    public Company exchangeEmployeesAtCompany(List<Employee> employeeList, long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            employeeRepository.deleteByCompanyId(id);

            for (Employee employee : employeeList) {
                employee.setCompany(company);
                employeeRepository.save(employee);
            }

            company.setEmployeeList(employeeList);
            companyRepository.save(company);

            return company;
        } else {
            throw new IncorrectIdException();
        }
    }

    @Transactional
    public void deleteEmployeeFromCompany(long companyId, long employeeId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            employeeRepository.deleteById(employeeId);
            companyRepository.save(company);
        } else {
            throw new IncorrectIdException();
        }
    }
}