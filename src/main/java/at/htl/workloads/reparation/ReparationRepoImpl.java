package at.htl.workloads.reparation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ReparationRepoImpl implements ReparationRepo{

    private final EntityManager entityManager;

    @Inject
    public ReparationRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Reparation> getAll() {
        return entityManager.createQuery("select r from Reparation r",Reparation.class).getResultList();
    }

    @Override
    public Reparation getById(Long id) {
        return entityManager.createQuery("select r from Reparation r where r.id = :ID",Reparation.class)
                .setParameter("ID",id)
                .getSingleResult();
    }

    @Override
    public void addReparation(Reparation reparation) {
        entityManager.persist(reparation);
    }

    @Override
    public Reparation updateReparation(Reparation reparation) {
        return entityManager.merge(reparation);
    }

    @Override
    public void deleteReparation(Reparation reparation) {
        entityManager.remove(reparation);
    }

    @Override
    public List<Reparation> getByIds(List<Long> reparationIds) {
        return entityManager.createQuery("select r from Reparation r " +
                "where r.id in :ids", Reparation.class)
                .setParameter("ids", reparationIds)
                .getResultList();
    }
}
