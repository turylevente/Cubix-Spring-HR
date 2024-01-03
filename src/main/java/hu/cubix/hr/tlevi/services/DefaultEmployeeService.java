package hu.cubix.hr.tlevi.services;

import hu.cubix.hr.tlevi.configurations.HrConfigurationProperties;
import hu.cubix.hr.tlevi.models.Employee;


public class DefaultEmployeeService extends EmployeeServiceImp {

    private final HrConfigurationProperties hrConfigurationProperties;

    public DefaultEmployeeService(HrConfigurationProperties hrConfigurationProperties) {
        this.hrConfigurationProperties = hrConfigurationProperties;
    }

    @Override
    public int getPayRaisePercent(Employee employee) {
        return hrConfigurationProperties.getIncreaseSalary().getDefaultpercent().getPercent();
    }
}
