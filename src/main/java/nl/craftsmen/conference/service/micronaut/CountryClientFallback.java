package nl.craftsmen.conference.service.micronaut;

import io.micronaut.retry.annotation.Fallback;

@Fallback
public class CountryClientFallback implements CountryClient {

    @Override
    public Country getCountryOfConference(final String name) {
        Country country = new Country();
        country.setName("don't know!");
        return country;
    }
}
