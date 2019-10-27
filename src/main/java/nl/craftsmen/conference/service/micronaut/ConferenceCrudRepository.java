package nl.craftsmen.conference.service.micronaut;

import javax.transaction.Transactional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;

import java.util.List;

@Repository
public interface ConferenceCrudRepository extends CrudRepository<Conference, Long> {

    List<Conference> findByName(String name);

    List<Conference> listOrderByName();

    List<Conference> listOrderByNameDesc();

    List<Conference> findTop3ByNameLike(String name);

}
