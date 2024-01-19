package hu.cubix.hr.tlevi.mapper;

import hu.cubix.hr.tlevi.dtos.CompanyDTO;
import hu.cubix.hr.tlevi.dtos.CompanyWithoutEmployeesDTO;
import hu.cubix.hr.tlevi.models.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CompanyMapper {
    Company companyDtoToCompany(CompanyDTO companyDTO);

    CompanyDTO companyToCompanyDto(Company company);


    CompanyWithoutEmployeesDTO companyToCompanyWithoutEmployeesDTO(Company company);


    List<CompanyDTO> companiesToDto(List<Company> companies);

    List<CompanyWithoutEmployeesDTO> companiesToCompaniesWithoutEmployeesDTO(List<Company> companies);

    List<Company> companyDtosToCompanyList(List<CompanyDTO> companyDtos);
}
