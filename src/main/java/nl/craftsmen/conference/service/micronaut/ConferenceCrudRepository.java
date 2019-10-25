package nl.craftsmen.conference.service.micronaut;

import java.util.List;

import javax.transaction.Transactional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;

@Repository
public interface ConferenceCrudRepository extends CrudRepository<Conference, Long> {

    @Transactional
    @ReadOnly
    List<Conference> findByName(String name);
}
