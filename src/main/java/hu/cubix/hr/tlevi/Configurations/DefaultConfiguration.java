package hu.cubix.hr.tlevi.Configurations;

import hu.cubix.hr.tlevi.Services.DefaultEmployeeService;
import hu.cubix.hr.tlevi.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {
    @Autowired
    HrConfigurationProperties hrConfigurationProperties;

    @Bean
    public EmployeeService employeeservice() {
        return new DefaultEmployeeService(hrConfigurationProperties);
    }
}
