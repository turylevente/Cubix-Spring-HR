package hu.cubix.hr.tlevi.configurations;

import hu.cubix.hr.tlevi.services.EmployeeService;
import hu.cubix.hr.tlevi.services.SmartEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("smart")
public class SmartConfiguration {
    @Autowired
    HrConfigurationProperties hrConfigurationProperties;

    @Bean
    public EmployeeService employeeservice() {
        return new SmartEmployeeService(hrConfigurationProperties);
    }
}
