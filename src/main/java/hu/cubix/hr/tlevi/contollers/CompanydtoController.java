package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.CompanyDTO;
import hu.cubix.hr.tlevi.dtos.CompanyWithoutEmployeesDTO;
import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/companies")
public class CompanydtoController {
    private final List<CompanyDTO> companies = new ArrayList<>();

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
        companies.add(new CompanyDTO(1, 123, "Company1", "Budapest", employeesAtCompany1));
        companies.add(new CompanyDTO(2, 456, "Company2", "Dubai", employeesAtCompany2));
        companies.add(new CompanyDTO(3, 789, "Company3", "New York", employeesAtCompany3));
    }

    @GetMapping
    public ResponseEntity<?> getCompanies(@RequestParam String full) {
        if (full.equals("true")) {
            return ResponseEntity.ok(companies);
        } else {
            List<CompanyWithoutEmployeesDTO> listOfCompanies = new ArrayList<CompanyWithoutEmployeesDTO>();
            for (CompanyDTO companyDTO : companies) {
                CompanyWithoutEmployeesDTO companyWithoutEmployeesDTO = new CompanyWithoutEmployeesDTO(companyDTO.getId(), (int) companyDTO.getRegistationNumber(), companyDTO.getName(), companyDTO.getAdress());
                listOfCompanies.add(companyWithoutEmployeesDTO);
            }
            return ResponseEntity.ok(listOfCompanies);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanies(@PathVariable int id, @RequestParam String full) {
        Optional<CompanyDTO> companyOptional = companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst();
        if (companyOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (full.equals("true")) {
            return ResponseEntity.ok(companyOptional.get());
        } else {
            return ResponseEntity.ok(new CompanyWithoutEmployeesDTO(companyOptional.get().getId(), (int) companyOptional.get().getRegistationNumber(), companyOptional.get().getName(), companyOptional.get().getAdress()));
        }
    }

    @PostMapping("/create-new-company")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        if (companies.stream().anyMatch(company -> company.getId() == companyDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        companies.add(companyDTO);
        return ResponseEntity.ok(companyDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable int id) {
        Optional<CompanyDTO> companyOptional = companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst();

        if (companyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            CompanyDTO companyToRemove = companyOptional.get();
            companies.remove(companyToRemove);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/modify-company/{id}")
    public ResponseEntity<CompanyDTO> modifyCompany(@RequestBody CompanyDTO companyDTO, @PathVariable long id) {
        companyDTO.setId(id);
        for (CompanyDTO company : companies) {
            if (company.getId() == id) {
                company.setAdress(companyDTO.getAdress());
                company.setName(companyDTO.getName());
                company.setRegistationNumber((int) companyDTO.getRegistationNumber());
                company.setEmployeeDtoList(companyDTO.getEmployeeDtoList());
                return ResponseEntity.ok(company);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}/add-new-employee")
    public ResponseEntity<CompanyDTO> addEmployeeToCompany(@PathVariable long id, @RequestBody EmployeeDto employee) {
        for (CompanyDTO company : companies) {
            if (company.getId() == id) {
                company.getEmployeeDtoList().add(employee);
                return ResponseEntity.ok(company);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{cid}/delete-employee/{eid}")
    public ResponseEntity<CompanyDTO> deleteEmployeeFromCompany(@PathVariable long cid, @PathVariable long eid) {
        for (CompanyDTO company : companies) {
            if (company.getId() == cid) {
                for (EmployeeDto employee : company.getEmployeeDtoList()) {
                    if (employee.getId() == eid) {
                        company.getEmployeeDtoList().remove(employee);
                        return ResponseEntity.ok(company);
                    }
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/set-company-employees/{id}")
    public ResponseEntity<CompanyDTO> exchangeEmployeesAtCompany(@RequestBody List<EmployeeDto> list, @PathVariable long id) {
        for (CompanyDTO company : companies) {
            if (company.getId() == id) {
                company.setEmployeeDtoList(list);
                return ResponseEntity.ok(company);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}

