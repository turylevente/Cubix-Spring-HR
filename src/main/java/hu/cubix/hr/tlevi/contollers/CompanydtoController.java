package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.CompanyDTO;
import hu.cubix.hr.tlevi.dtos.CompanyWithoutEmployeesDTO;
import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.mapper.CompanyMapper;
import hu.cubix.hr.tlevi.mapper.EmployeeMapper;
import hu.cubix.hr.tlevi.models.Company;
import hu.cubix.hr.tlevi.repositories.CompanyRepository;
import hu.cubix.hr.tlevi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/companies")
public class CompanydtoController {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<?> getCompanies(@RequestParam(required = false) Boolean full,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "3") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of((page-1), size, Sort.by(sortBy));
        if (full) {
<<<<<<< Updated upstream
            Page<Company> companyPage = companyService.findAll(pageable);
            return ResponseEntity.ok(companyPage.getContent());
        } else {
            Page<CompanyDTO> companyDtoPage = companyService.findAll(pageable).map(companyMapper::companyToCompanyDto);
            return ResponseEntity.ok(companyDtoPage.getContent());
=======
            Page<CompanyDTO> companyDtoPage = companyService.findAll(pageable).map(companyMapper::companyToCompanyDto);
            return ResponseEntity.ok(companyDtoPage.getContent());
        } else {
            Page<CompanyWithoutEmployeesDTO> CompanyWithoutEmployeesPage = companyService.findAll(pageable).map(companyMapper::companyToCompanyWithoutEmployeesDTO);
            return ResponseEntity.ok(CompanyWithoutEmployeesPage.getContent());
>>>>>>> Stashed changes
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable long id, @RequestParam Boolean full) {
        if (full) {
            return ResponseEntity.ok(companyMapper.companyToCompanyDto(companyService.getCompanyById(id)));
        } else {
            return (ResponseEntity.ok(companyMapper.companyToCompanyWithoutEmployeesDTO(companyService.getCompanyById(id))));
        }
    }


    @PostMapping("/create-new-company")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyMapper.companyToCompanyDto(companyService.createCompany(companyMapper.companyDtoToCompany(companyDTO))));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyService.deleteCompanyById(id);
    }

    @PutMapping("/modify-company/{id}")
    public ResponseEntity<CompanyDTO> modifyCompany(@RequestBody CompanyDTO companyDTO, @PathVariable long id) {
        return ResponseEntity.ok(companyMapper.companyToCompanyDto(companyService.modifyCompanyById(companyMapper.companyDtoToCompany(companyDTO), id)));

    }

    @PutMapping("/{id}/add-new-employee")
    public ResponseEntity<CompanyDTO> addEmployeeToCompany(@PathVariable long id, @RequestBody EmployeeDto employeedto) {
        return ResponseEntity.ok(companyMapper.companyToCompanyDto(companyService.addEmployeeToCompany(id, employeeMapper.employeeDtoToEmployee(employeedto))));
    }

    @DeleteMapping("/{companyId}/delete-employee/{employeeId}")
    public void deleteEmployeeFromCompany(@PathVariable long companyId, @PathVariable long employeeId) {
        companyService.deleteEmployeeFromCompany(companyId, employeeId);
    }

    @PutMapping("/set-company-employees/{id}")
    public ResponseEntity<CompanyDTO> exchangeEmployeesAtCompany(@RequestBody List<EmployeeDto> list, @PathVariable long id) {
        return ResponseEntity.ok(companyMapper.companyToCompanyDto(companyService.exchangeEmployeesAtCompany(employeeMapper.employeesDtoToEmployeeList(list), id)));
    }

    @GetMapping("/findAverageSalaryByCompanyId/{companyId}")
    public ResponseEntity<Double> findAverageSalaryByCompanyId(@PathVariable long companyId) {
        return ResponseEntity.ok(companyRepository.findAverageSalaryByCompanyId(companyId));
    }

    @GetMapping("/findCompaniesWithEmployeeCountExceeding/{employeeLimit}")
    public ResponseEntity<List<CompanyDTO>> findCompaniesWithEmployeeCountExceeding(@PathVariable int employeeLimit) {
        return ResponseEntity.ok(companyMapper.companiesToDto(companyRepository.findCompaniesWithEmployeeCountExceeding(employeeLimit)));
    }

    @GetMapping("/findCompaniesWithEmployeeSalaryHigherThan/{salaryLimit}")
    public ResponseEntity<List<CompanyDTO>> findCompaniesWithEmployeeSalaryHigherThan(@PathVariable int salaryLimit) {
        return ResponseEntity.ok(companyMapper.companiesToDto(companyRepository.findCompaniesWithEmployeeSalaryHigherThan(salaryLimit)));
    }
}

