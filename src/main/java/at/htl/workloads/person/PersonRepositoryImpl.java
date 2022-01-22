package at.htl.workloads.person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PersonRepositoryImpl implements PersonRepository{
    private final EntityManager entityManager;

    @Inject
    public PersonRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Mechanic saveMechanic(Mechanic m) {
        entityManager.persist(m);
        entityManager.flush();

        return m;
    }

    @Override
    public Mechanic updateMechanic(Mechanic m) {
        Mechanic newM = entityManager.merge(m);
        entityManager.flush();
        return newM;
    }

    @Override
    public Mechanic findMechanicById(String svNr) {
        return entityManager.createQuery("select m from Mechanic m " +
                "where m.svNr = :svNr", Mechanic.class)
                .setParameter("svNr", svNr)
                .getSingleResult();
    }

    @Override
    public List<Mechanic> findAllMechanics() {
        return entityManager.createQuery("select m from Mechanic m", Mechanic.class)
                .getResultList();
    }

    @Override
    public void deleteMechanic(Mechanic m) {

        entityManager.remove(m);
        entityManager.flush();
    }
}
