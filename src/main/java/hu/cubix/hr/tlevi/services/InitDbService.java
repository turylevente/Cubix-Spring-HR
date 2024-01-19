package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.enums.CompanyTypes;
import hu.cubix.hr.tlevi.enums.neededQualificationTypes;
import hu.cubix.hr.tlevi.models.Company;
import hu.cubix.hr.tlevi.models.Employee;
import hu.cubix.hr.tlevi.models.Job;
import hu.cubix.hr.tlevi.repositories.CompanyRepository;
import hu.cubix.hr.tlevi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class InitDbService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public InitDbService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public void clearDB() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    public void insertTestData() {
        Company company1 = new Company(1, 123, "Company1", "Address1", new ArrayList<>(), CompanyTypes.CORPORATION);
        Company company2 = new Company(2, 456, "Company2", "Address2", new ArrayList<>(), CompanyTypes.LLC);
        Company company3 = new Company(3, 789, "Company3", "Address3", new ArrayList<>(), CompanyTypes.LIMITED_PARTNERSHIP);

        Job job1 = new Job(1, "job1", neededQualificationTypes.NONE, 100000);
        Job job2 = new Job(2, "job2", neededQualificationTypes.HIGH_SCHOOL, 200000);
        Job job3 = new Job(3, "job3", neededQualificationTypes.COLLEGE, 400000);
        Job job4 = new Job(4, "job4", neededQualificationTypes.COLLEGE, 450000);
        Job job5 = new Job(5, "job5", neededQualificationTypes.HIGH_SCHOOL, 600000);
        Job job6 = new Job(6, "job6", neededQualificationTypes.HIGH_SCHOOL, 650000);

        Employee employee1 = new Employee(1, "Employee1", job1, 100000, LocalDateTime.now(), company1);
        Employee employee2 = new Employee(2, "Employee2", job3, 400000, LocalDateTime.now(), company1);
        Employee employee3 = new Employee(3, "Employee3", job6, 650000, LocalDateTime.now(), company1);

        Employee employee4 = new Employee(4, "Employee4", job2, 200000, LocalDateTime.now(), company2);
        Employee employee5 = new Employee(5, "Employee5", job4, 450000, LocalDateTime.now(), company2);
        Employee employee6 = new Employee(6, "Employee6", job5, 600000, LocalDateTime.now(), company2);


        company1.setEmployeeList(Arrays.asList(employee1, employee2, employee3));
        company2.setEmployeeList(Arrays.asList(employee4, employee5, employee6));

        companyRepository.saveAll(Arrays.asList(company1, company2, company3));
    }
}
