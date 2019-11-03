package nl.craftsmen.conference.service.micronaut;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConferenceService {

    @Inject
    private ConferenceRepository conferenceRepository;

    @Inject
    private ConferenceCrudRepository conferenceCrudRepository;

    @Inject
    private ConferenceJdbcCrudRepository conferenceJdbcCrudRepository;

    @Inject
    CountryClient countryClient;

    public List<Conference> getAll() {
        return toList(conferenceRepository.findAll());
    }

    public void create(Conference conference) {
        conferenceRepository.save(conference);
    }

    public List<ExtendedConference> getAllWithCountry() {
        List<Conference> conferences = toList(this.getAll());
        return conferences.stream()
                .map(this::getExtendedConference)
                .collect(Collectors.toList());
    }

    public ExtendedConference getExtendedConference(Conference conference) {
        ExtendedConference extendedConference = new ExtendedConference();
        extendedConference.setName(conference.getName());
        extendedConference.setCountryName(this.getCountryOfConference(conference.getName())
                .getName());
        return extendedConference;
    }

    public Country getCountryOfConference(String name) {
        return countryClient.getCountryOfConference(name);
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

}