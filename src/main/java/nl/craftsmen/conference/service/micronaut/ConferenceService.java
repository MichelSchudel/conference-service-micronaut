package nl.craftsmen.conference.service.micronaut;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConferenceService {

    @Inject
    private ConferenceRepository conferenceRepository;

    @Inject
    private ConferenceCrudRepository conferenceCrudRepository;


    @Inject
    CountryClient countryClient;

    public List<Conference> getAll() {
        return conferenceRepository.getAll();
    }

    public List<Conference> getByName(String name) {
        return conferenceCrudRepository.findByName(name);
    }

    public void create(Conference conference) {
        conferenceRepository.create(conference);

    }

    public List<ExtendedConference> getAllWithCountry() {
        List<Conference> conferences = this.getAll();
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

    //@Fallback(fallbackMethod = "fallback")
    public Country getCountryOfConference(String name) {
        return countryClient.getCountryOfConference(name);
    }

}