package hu.cubix.hr.tlevi.Services;

import hu.cubix.hr.tlevi.Configurations.HrConfigurationProperties;
import hu.cubix.hr.tlevi.Models.Employee;

public class DefaultEmployeeService implements EmployeeService {

    private final HrConfigurationProperties hrConfigurationProperties;

    public DefaultEmployeeService(HrConfigurationProperties hrConfigurationProperties) {
        this.hrConfigurationProperties = hrConfigurationProperties;
    }

    @Override
    public int getPayRaisePercent(Employee employee) {
        return hrConfigurationProperties.getIncreaseSalary().getDefaultpercent().getPercent();
    }
}
