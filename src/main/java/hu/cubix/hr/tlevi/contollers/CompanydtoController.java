package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.CompanyDTO;
import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import hu.cubix.hr.tlevi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/companies")
public class CompanydtoController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getCompanies(@RequestParam Boolean full) {
        return ResponseEntity.ok(companyService.findAll(full));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable long id, @RequestParam Boolean full) {
        return ResponseEntity.ok(companyService.getCompanyById(id, full));
    }


    @PostMapping("/create-new-company")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.createCompany(companyDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyService.deleteCompanyById(id);
    }

    @PutMapping("/modify-company/{id}")
    public ResponseEntity<CompanyDTO> modifyCompany(@RequestBody CompanyDTO companyDTO, @PathVariable long id) {
        return ResponseEntity.ok(companyService.modifyEmployeeById(companyDTO, id));

    }

    @PutMapping("/{id}/add-new-employee")
    public ResponseEntity<CompanyDTO> addEmployeeToCompany(@PathVariable long id, @RequestBody EmployeeDto employeedto) {
        return ResponseEntity.ok(companyService.addEmployeeToCompany(id, employeedto));
    }

    @DeleteMapping("/{cid}/delete-employee/{eid}")
    public void deleteEmployeeFromCompany(@PathVariable long cid, @PathVariable long eid) {
        companyService.deleteEmployeeFromCompany(cid, eid);
    }

    @PutMapping("/set-company-employees/{id}")
    public ResponseEntity<CompanyDTO> exchangeEmployeesAtCompany(@RequestBody List<EmployeeDto> list, @PathVariable long id) {
        return ResponseEntity.ok(companyService.exchangeEmployeesAtCompany(list, id));
    }
}

