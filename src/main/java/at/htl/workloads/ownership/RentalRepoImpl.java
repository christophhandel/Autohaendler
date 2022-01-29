package at.htl.workloads.ownership;

import at.htl.workloads.person.Mechanic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
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
    public VehicleTransfer saveTransfer(VehicleTransfer v) {
        return entityManager.merge(v);
    }

    @Override
    public void deleteVehicle(VehicleTransfer v) {
        entityManager.remove(v);
    }

    @Override
    public List<VehicleTransfer> findAllTransfers() {
        try {
             return entityManager.createQuery("select vt from VehicleTransfer vt",VehicleTransfer.class)
                     .getResultList();
        } catch (NoResultException e){
            return new ArrayList<>();
        }

    }

    @Override
    public VehicleTransfer findTransferById(Long id) {
        try{
            return entityManager.createQuery("select vt from VehicleTransfer vt " +
                    " where vt.id = :id",VehicleTransfer.class).setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    public void deleteRental(Rental r) {
        entityManager.remove(r);
    }

    @Override
    public Rental findRentalById(Long id) {
        try {
            return entityManager.createQuery("select r from Rental r where r.id = :ID",Rental.class)
                    .setParameter("ID",id)
                    .getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<Rental> findAllRentals() {
        return entityManager.createQuery("select r from Rental r",Rental.class).getResultList();
    }
}
