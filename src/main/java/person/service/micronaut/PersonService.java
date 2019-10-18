package person.service.micronaut;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    @Inject
    private PersonCrudRepository personCrudRepository;

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public List<Person> getPersonsByName(String name) {
        return personCrudRepository.findByName(name);
    }

    public void createPerson(Person person) {
        personRepository.createPerson(person);

    }
}