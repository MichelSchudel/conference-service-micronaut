package person.service.micronaut;

import java.util.List;

import javax.inject.Inject;

import io.micronaut.context.annotation.Context;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.retry.annotation.Fallback;

@Controller
public class CountryController {

    @Inject
    CountryClient countryClient;

    @Get("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> getAllCountries() {
        return countryClient.getAllCountries();
    }

}
