package person.service.micronaut;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;

@MicronautTest(environments={"test"})
public class PersonITTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    private PersonController personController;

    @Test
    void testItWorks() {
        assertTrue(embeddedServer.isRunning());
    }

    @Test
    public void testPersonInternal() {
        personController.getAllPersons();
    }

    @Test
    public void testHello() {
        assertThat(personController.hello()).isEqualTo("Hi from test profile!");
    }
    @Test
    @Transactional
    public void testPersons() {
        System.out.println(embeddedServer.getPort());
        Person person = new Person();
        person.setName("Michel");

        given().body(person)
                .port(embeddedServer.getPort())
                .contentType("application/json")
                .when()
                .post("/persons")
                .then()
                .statusCode(200);

        given().port(embeddedServer.getPort())

                .when()
                .get("/persons")
                .then()
                .extract()
                .path("[0].name")
                .equals("michel");

    }

    @Test
    public void testCountries() {
        given().when()
                .port(embeddedServer.getPort())
                .get("/countries")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].name")
                .equals("Belgium");
    }

    @MockBean(CountryClient.class)
    CountryClient countryClient() {
        final CountryClient mock = mock(CountryClient.class);
        Country country = new Country();
        country.setName("Belgium");
        when(mock.getAllCountries()).thenReturn(Arrays.asList(country));
        return mock;
    }

}
