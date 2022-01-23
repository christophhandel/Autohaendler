package at.htl.workloads.reparation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@ApplicationScoped
public class ReparationRepoImpl implements ReparationRepo{

    private final EntityManager entityManager;

    @Inject
    public ReparationRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Reparation> findAllReparations() {
        return entityManager.createQuery("select r from Reparation r",Reparation.class).getResultList();
    }

    @Override
    public Reparation findReparationById(Long id) {
        try {
            return entityManager.createQuery("select r from Reparation r where r.id = :ID",Reparation.class)
                    .setParameter("ID",id)
                    .getSingleResult();
        }catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Reparation addReparation(Reparation reparation) {
        entityManager.persist(reparation);
        return reparation;
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
    public List<Reparation> findReparationsByIds(List<Long> reparationIds) {
        try {
            return entityManager.createQuery("select r from Reparation r " +
                    "where r.id in :ids", Reparation.class)
                    .setParameter("ids", reparationIds)
                    .getResultList();
        }  catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Replacement> findAllReplacements() {
        return entityManager.createQuery("select r from Replacement r", Replacement.class)
                .getResultList();
    }

    @Override
    public Replacement findReplacementById(String partType, String partDescription, Long reparationId) {
        try {
            return entityManager.createQuery("select r from Replacement r where " +
                    "r.id.part.partId.partType = :partType and " +
                    "r.id.part.partId.description = :description and " +
                    "r.id.reparation.id = :reparationId", Replacement.class)
                    .setParameter("partType", partType)
                    .setParameter("reparationId", reparationId)
                    .setParameter("description", partDescription)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void deleteReplacement(Replacement replacement) {
        entityManager.remove(replacement);
    }

    @Override
    public Replacement addReplacement(Replacement r) {
        entityManager.persist(r);
        return r;
    }

    @Override
    public Part findPartById(String partType, String partDescription) {
        try {
            return entityManager.createQuery("select p from Part p where " +
                    "p.partId.partType = :partType and " +
                    "p.partId.description = :description", Part.class)
                    .setParameter("partType", partType)
                    .setParameter("description", partDescription)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Replacement updateReplacement(Replacement r) {
        return entityManager.merge(r);
    }
}
