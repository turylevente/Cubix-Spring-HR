package hu.cubix.hr.tlevi.Services;

import hu.cubix.hr.tlevi.Configurations.HrConfigurationProperties;
import hu.cubix.hr.tlevi.Models.Employee;

import java.time.Duration;
import java.time.LocalDateTime;

public class SmartEmployeeService implements EmployeeService {
    private HrConfigurationProperties hrConfigurationProperties;

    public SmartEmployeeService(HrConfigurationProperties hrConfigurationProperties) {
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
