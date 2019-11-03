package nl.craftsmen.conference.service.micronaut;

import javax.transaction.Transactional;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;

@JdbcRepository
public interface ConferenceJdbcCrudRepository extends CrudRepository<Conference, Long> {

    @Transactional
    @ReadOnly
    Iterable<Conference> findByName(String name);
}
