package hu.cubix.hr.tlevi.contollers;

import hu.cubix.hr.tlevi.dtos.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    private static final String BASE_URI = "/api/employees";

    @Autowired
    WebTestClient webTestClient;


    @Test
    void testThatNewValidEmployeeCanBeSaved() throws Exception {
        List<EmployeeDto> employeesBefore = getAllEmployees();

        EmployeeDto newEmployee = newValidEmployee();
        saveEmployee(newEmployee)
                .expectStatus()
                .isOk();

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter.size()).isEqualTo(employeesBefore.size() + 1);
        assertThat(employeesAfter.get(employeesAfter.size() - 1))
                .usingRecursiveComparison()
                .isEqualTo(newEmployee);

        deleteEmployee(newEmployee)
                .expectStatus().isOk();


    }

    @Test
    void testThatNewInvalidEmployeeCannotBeSaved() throws Exception {
        List<EmployeeDto> employeesBefore = getAllEmployees();

        EmployeeDto newEmployee = newInvalidEmployee();
        saveEmployee(newEmployee)
                .expectStatus()
                .isBadRequest();

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);


    }

    @Test
    void testThatEmployeeCanBeUpdatedWithValidFields() throws Exception {

        EmployeeDto newEmployee = newValidEmployee();
        EmployeeDto savedEmployee = saveEmployee(newEmployee)
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class).returnResult().getResponseBody();

        List<EmployeeDto> employeesBefore = getAllEmployees();
        assert savedEmployee != null;
        savedEmployee.setName("modified");
        modifyEmployee(savedEmployee)
                .expectStatus()
                .isOk();

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size() - 1))
                .usingRecursiveComparison()
                .isEqualTo(savedEmployee);
        deleteEmployee(savedEmployee)
                .expectStatus().isOk();
    }

    @Test
    void testThatEmployeeCannotBeUpdatedWithInvalidFields() throws Exception {
        EmployeeDto newEmployee = new EmployeeDto(5L, "ABC", "student", 200000, LocalDateTime.of(2019, 01, 01, 8, 0, 0));
        EmployeeDto savedEmployee = saveEmployee(newEmployee)
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .returnResult()
                .getResponseBody();

        List<EmployeeDto> employeesBefore = getAllEmployees();
        assert savedEmployee != null;
        newEmployee.setId(0);
        newEmployee.setName("");
        modifyEmployee(newEmployee)
                .expectStatus()
                .isBadRequest();

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size() - 1))
                .usingRecursiveComparison()
                .isEqualTo(savedEmployee);

        deleteEmployee(newEmployee)
                .expectStatus().isOk();
    }

    private EmployeeDto newInvalidEmployee() {
        return new EmployeeDto(-1, "", "", 0, LocalDateTime.of(2219, 01, 01, 8, 0, 0));
    }

    private EmployeeDto newValidEmployee() {
        return new EmployeeDto(Long.MAX_VALUE, "ABC", "student", 200000, LocalDateTime.of(2019, 01, 01, 8, 0, 0));
    }


    private ResponseSpec modifyEmployee(EmployeeDto newEmployee) {
        String path = BASE_URI + "/" + newEmployee.getId();
        return webTestClient
                .put()
                .uri(path)
                .bodyValue(newEmployee)
                .exchange();
    }

    private ResponseSpec saveEmployee(EmployeeDto newEmployee) {
        return webTestClient
                .post()
                .uri(BASE_URI)
                .bodyValue(newEmployee)
                .exchange();
    }

    private List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> responseList = webTestClient
                .get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(EmployeeDto.class)
                .returnResult()
                .getResponseBody();
        assert responseList != null;
        responseList.sort(Comparator.comparing(EmployeeDto::getId));
        return responseList;
    }

    private ResponseSpec deleteEmployee(EmployeeDto Employee) {
        String path = BASE_URI + "/" + Employee.getId();
        return (ResponseSpec) webTestClient
                .delete()
                .uri(path)
                .exchange();
    }

}
