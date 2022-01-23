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

    @Override
    public Owner saveOwner(Owner o) {
        entityManager.persist(o);
        return o;
    }

    @Override
    public Owner updateOwner(Owner o) {
        o = entityManager.merge(o);
        return o;
    }

    @Override
    public Owner findOwnerById(String svNr) {
        return entityManager.createQuery("select o from Owner o " +
                "where o.svNr = :svNr", Owner.class)
                .setParameter("svNr", svNr)
                .getSingleResult();
    }

    @Override
    public List<Owner> findAllOwners() {
        return entityManager.createQuery("select o from Owner o", Owner.class)
                .getResultList();
    }

    @Override
    public void deleteOwner(Owner o) {
        entityManager.remove(o);
    }
}
