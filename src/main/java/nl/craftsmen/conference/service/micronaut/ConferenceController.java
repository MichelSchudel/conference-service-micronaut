package nl.craftsmen.conference.service.micronaut;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/")
@Context
public class ConferenceController {

    @Inject
    ConferenceService conferenceService;

    @Value("${app.hellomessage}")
    String helloMessage;

    @Get("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloMessage;
    }

    @Get("/conferences")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Conference> getAll() {
        return conferenceService.getAll();
    }

    @Post("/conferences")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Conference conference) {
        conferenceService.create(conference);

    }

    @Get("/conferenceswithcountry")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExtendedConference> getAllWithCountry() {
        return conferenceService.getAllWithCountry();
    }
}