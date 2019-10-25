package nl.craftsmen.conference.service.micronaut;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Singleton
public class ConferenceRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Iterable<Conference> findAll() {
        TypedQuery<Conference> query = entityManager.createQuery("select c from Conference c", Conference.class);
        return query.getResultList();
    }

    @Transactional
    public void save(final Conference conference) {
        entityManager.persist(conference);
    }

}
