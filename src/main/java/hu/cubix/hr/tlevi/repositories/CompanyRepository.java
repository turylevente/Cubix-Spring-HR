package hu.cubix.hr.tlevi.repositories;

import hu.cubix.hr.tlevi.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByRegistationNumber(long registationNumber);

    @Query("SELECT AVG(e.salary) FROM Company c JOIN c.employeeList e WHERE c.id = :companyId")
    Double findAverageSalaryByCompanyId(@Param("companyId") long companyId);

    @Query("SELECT c FROM Company c WHERE SIZE(c.employeeList) > :employeeLimit")
    List<Company> findCompaniesWithEmployeeCountExceeding(@Param("employeeLimit") int employeeLimit);

    @Query("SELECT DISTINCT c FROM Company c JOIN c.employeeList e WHERE e.salary > :salaryLimit")
    List<Company> findCompaniesWithEmployeeSalaryHigherThan(@Param("salaryLimit") int salaryLimit);

}