package hu.cubix.hr.tlevi;

import hu.cubix.hr.tlevi.services.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class HrApplication implements CommandLineRunner {
    @Autowired
    private InitDbService initDbService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initDbService.clearDB();
        initDbService.insertTestData();
    }
}
