package at.htl.workloads.ownership;

import at.htl.workloads.person.Mechanic;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
