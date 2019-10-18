package person.service.micronaut;

import java.util.List;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.retry.annotation.Retryable;

@Client("${country.service.url:`http://localhost:9000`}")
public interface CountryClient {

    @Get("/countries")
    @Retryable(attempts = "3")
    @CircuitBreaker
    public List<Country> getAllCountries();
}