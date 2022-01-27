package at.htl.workloads.ownership;

import at.htl.workloads.person.Mechanic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RentalRepoImpl implements RentalRepo{

    private final EntityManager entityManager;

    @Inject
    public RentalRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Rental saveRental(Rental r) {
        return entityManager.merge(r);
    }

    @Override
    public Rental updateMechanic(Rental r) {
        Rental newR = entityManager.merge(r);
        entityManager.flush();
        return newR;
    }

    @Override
    public void deleteRental(Rental r) {
        entityManager.remove(r);
    }

    @Override
    public Rental findRentalById(Long id) {
        return entityManager.createQuery("select r from Rental r where r.id = :ID",Rental.class)
                .setParameter("ID",id)
                .getSingleResult();
    }

    @Override
    public List<Rental> findAllRentals() {
        return entityManager.createQuery("select r from Rental r",Rental.class).getResultList();
    }
}
