package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.configurations.HrConfigurationProperties;
import hu.cubix.hr.tlevi.models.Employee;

import java.time.Duration;
import java.time.LocalDateTime;

public class SmartEmployeeService extends EmployeeServiceImp {
    private HrConfigurationProperties hrConfigurationProperties;

    public SmartEmployeeService(HrConfigurationProperties hrConfigurationProperties) {
        super();
        this.hrConfigurationProperties = hrConfigurationProperties;
    }

    public SmartEmployeeService() {
    }

    @Override
    public int getPayRaisePercent(Employee employee) {

        LocalDateTime currentDate = LocalDateTime.now();
        long years = Duration.between(employee.getStartOfTheWork(), currentDate).toDays() / 365;

        if (years >= hrConfigurationProperties.getIncreaseSalary().getSmart().getLimitHighest()) {
            return hrConfigurationProperties.getIncreaseSalary().getSmart().getPercentHighest();
        } else if (years >= hrConfigurationProperties.getIncreaseSalary().getSmart().getLimitMiddle()) {
            return hrConfigurationProperties.getIncreaseSalary().getSmart().getPercentMiddle();
        } else if (years >= hrConfigurationProperties.getIncreaseSalary().getSmart().getLimitSmallest()) {
            return hrConfigurationProperties.getIncreaseSalary().getSmart().getPercentSmallest();
        } else {
            return 0;
        }
    }
}
