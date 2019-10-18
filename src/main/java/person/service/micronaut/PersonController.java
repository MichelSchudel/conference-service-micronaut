package person.service.micronaut;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/")
@Context
public class PersonController {

    @Inject
    PersonService personService;

    @Value("${micronaut.hellomessage}")
    String helloMessage;


    @Get("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloMessage;
    }


    @Get("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @Post("/persons")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(Person person) {
        personService.createPerson(person);

    }
}