package hu.cubix.hr.tlevi.repositories;

import hu.cubix.hr.tlevi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalaryGreaterThan(@Param("limit") int limit);

    List<Employee> findByJob_Id(long jobId);

    List<Employee> findByNameIgnoreCaseStartingWith(String namePrefix);

    List<Employee> findByStartOfTheWorkBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Modifying
    @Query("DELETE FROM Employee e WHERE e.company.id = :companyId")
    void deleteByCompanyId(long companyId);

}
