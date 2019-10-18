package person.service.micronaut;

import java.util.List;

import javax.transaction.Transactional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;

@Repository
public interface PersonCrudRepository extends CrudRepository<Person, Long> {

    @Transactional
    @ReadOnly
    List<Person> findByName(String name);
}
