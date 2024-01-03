package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.dtos.CompanyDTO;
import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.exception.IncorrectIdException;
import hu.cubix.hr.tlevi.mapper.CompanyMapper;
import hu.cubix.hr.tlevi.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {
    private final List<Company> companies = new ArrayList<>();
    @Autowired
    CompanyMapper companyMapper;

    {
        List<EmployeeDto> employeesAtCompany1 = new ArrayList<EmployeeDto>();
        {
            employeesAtCompany1.add(new EmployeeDto(1, "István", "job1", 500, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
            employeesAtCompany1.add(new EmployeeDto(2, "Géza", "job2", 800, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
            employeesAtCompany1.add(new EmployeeDto(3, "Elemér", "job3", 1100, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
        }
        List<EmployeeDto> employeesAtCompany2 = new ArrayList<EmployeeDto>();
        {
            employeesAtCompany2.add(new EmployeeDto(1, "Ismail", "job1", 6000, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
            employeesAtCompany2.add(new EmployeeDto(2, "Mohammed", "job2", 6500, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
            employeesAtCompany2.add(new EmployeeDto(3, "Khalid", "job3", 7000, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
        }
        List<EmployeeDto> employeesAtCompany3 = new ArrayList<EmployeeDto>();
        {
            employeesAtCompany3.add(new EmployeeDto(1, "Michael", "job1", 4000, LocalDateTime.of(2010, Month.JANUARY, 1, 9, 0)));
            employeesAtCompany3.add(new EmployeeDto(2, "Tyler", "job2", 4500, LocalDateTime.of(2017, Month.JANUARY, 1, 9, 0)));
            employeesAtCompany3.add(new EmployeeDto(3, "Jacob", "job3", 5000, LocalDateTime.of(2023, Month.JANUARY, 1, 9, 0)));
        }
        companies.add(new Company(1, 123, "Company1", "Budapest", employeesAtCompany1));
        companies.add(new Company(2, 456, "Company2", "Dubai", employeesAtCompany2));
        companies.add(new Company(3, 789, "Company3", "New York", employeesAtCompany3));
    }

    public List<?> findAll(Boolean full) {
        return FullListOrNot(full);
    }


    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        if (companies.stream().anyMatch(company -> company.getId() == companyDTO.getId())) {
            throw new IncorrectIdException();
        }
        companies.add(companyMapper.companyDtoToCompany(companyDTO));
        return companyDTO;
    }

    public Object getCompanyById(long id, Boolean full) {
        Optional<Company> companyOptional = companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst();
        if (companyOptional.isEmpty()) {
            throw new IncorrectIdException();
        } else {
            companies.sort(Comparator.comparing(Company::getId));
            return FullListOrNot(full).get((int) (id - 1));
        }
    }


    public void deleteCompanyById(long id) {
        Optional<Company> companyOptional = companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst();
        if (companyOptional.isEmpty()) {
            throw new IncorrectIdException();
        } else {
            companies.remove(companyOptional.get());
        }
    }

    public CompanyDTO modifyEmployeeById(CompanyDTO companyDTO, long id) {
        companyDTO.setId(id);
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setAdress(companyDTO.getAdress());
                company.setName(companyDTO.getName());
                company.setRegistationNumber((int) companyDTO.getRegistationNumber());
                company.setEmployeeDtoList(companyDTO.getEmployeeDtoList());
                return companyMapper.companyToCompanyDto(company);
            }
        }
        throw new IncorrectIdException();
    }

    public CompanyDTO addEmployeeToCompany(long id, EmployeeDto employeedto) {
        for (Company company : companies) {
            if (company.getId() == id) {
                company.getEmployeeDtoList().add(employeedto);
                return companyMapper.companyToCompanyDto(company);
            }
        }
        throw new IncorrectIdException();
    }

    public CompanyDTO exchangeEmployeesAtCompany(List<EmployeeDto> list, long id) {
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setEmployeeDtoList(list);
                return companyMapper.companyToCompanyDto(company);
            }
        }
        throw new IncorrectIdException();
    }

    public void deleteEmployeeFromCompany(long cid, long eid) {
        Company company = companies.stream()
                .filter(c -> c.getId() == cid)
                .findFirst()
                .orElseThrow(IncorrectIdException::new);

        company.getEmployeeDtoList().removeIf(employeeDto -> employeeDto.getId() == eid);
    }

    private List<?> FullListOrNot(boolean full) {
        if (full) {
            return companyMapper.companiesToDto(companies);
        } else {
            return companyMapper.companiesToCompaniesWithoutEmployeesDTO(companies);
        }
    }
}
