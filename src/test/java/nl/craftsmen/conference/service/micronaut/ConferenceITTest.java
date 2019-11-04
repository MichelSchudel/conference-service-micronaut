package nl.craftsmen.conference.service.micronaut;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;

@MicronautTest(environments = { "test" })
public class ConferenceITTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    private HelloController helloController;

    @Test
    public void testHello() {
        assertThat(helloController.hello()).isEqualTo("Hi from test profile!");
    }

    @Test
    @Transactional
    public void testconferences() {

        Conference conference = new Conference();
        conference.setName("Devoxx");

        given().body(conference)
                .port(embeddedServer.getPort())
                .contentType("application/json")
                .when()
                .post("/conferences")
                .then()
                .statusCode(200);

        given().port(embeddedServer.getPort())

                .when()
                .get("/conferences")
                .then()
                .extract()
                .path("[0].name")
                .equals("michel");

    }

    @Test
    public void testConferencesWithCountry() {
        Conference conference = new Conference();
        conference.setName("Devoxx");
        given().body(conference)
                .port(embeddedServer.getPort())
                .contentType("application/json")
                .when()
                .post("/conferences")
                .then()
                .statusCode(200);

        given().when()
                .port(embeddedServer.getPort())
                .get("/conferenceswithcountry")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].countryName")
                .equals("Belgium");
    }

    @MockBean(CountryClient.class)
    CountryClient countryClient() {
        final CountryClient mock = mock(CountryClient.class);
        Country country = new Country();
        country.setName("Belgium");
        when(mock.getCountryOfConference(isA(String.class))).thenReturn(country);
        return mock;
    }

}
