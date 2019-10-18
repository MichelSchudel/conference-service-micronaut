package person.service.micronaut;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class PersonRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createPerson(final Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public List<Person> getAllPersons() {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p", Person.class);
        return query.getResultList();
    }
}
