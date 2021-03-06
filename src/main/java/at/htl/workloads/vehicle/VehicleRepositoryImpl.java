package at.htl.workloads.vehicle;

import at.htl.models.results.IncomePerPerson;
import at.htl.models.results.PartsUsedInVehicle;
import at.htl.workloads.ownership.Rental;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class VehicleRepositoryImpl implements VehicleRepository{

    @Inject
    EntityManager entityManager;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        entityManager.merge(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle findById(Long id) {
            try {
                return entityManager.createQuery("select v from Vehicle v where v.id = :id", Vehicle.class)
                        .setParameter("id", id)
                        .getSingleResult();
            } catch (NoResultException ex) {
                return null;
            }
    }

    @Override
    public void deleteVehicle(Vehicle v) {
        entityManager.remove(v);
    }

    @Override
    public List<Vehicle> getAllVehiclesInIdList(List<Long> vehicleIds) {
        try {
            return entityManager.createQuery("select v from Vehicle v " +
                            "where v.id in :ids", Vehicle.class)
                    .setParameter("ids", vehicleIds)
                    .getResultList();
        }  catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Vehicle> findSoldVehicles() {
        try {
            return entityManager.createQuery("select v from Vehicle v where v.owner is not null",Vehicle.class).getResultList();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<Rental> findRentalsForVehicleInFuture(Vehicle v) {
        return entityManager.createQuery("select r from Rental r " +
                "where r.vehicle.id = :id " +
                        "and r.from > :now", Rental.class)
                .setParameter("id", v.getId())
                .setParameter("now", LocalDateTime.now())
                .getResultList();
    }

    @Override
    public List<Vehicle> findOwnedVehicles() {
        try {
            return entityManager.createQuery("select v from Vehicle v where v.owner is null",Vehicle.class).getResultList();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        try {
            return entityManager.createQuery("select v from Vehicle v",Vehicle.class).getResultList();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<PartsUsedInVehicle> countsThePartsInAVehicle() {
        return entityManager.createQuery("" +
                                "select new at.htl.models.results.PartsUsedInVehicle(rr.id.part,v) from Vehicle v" +
                                " join v.reparations r " +
                                " join r.replacements rr"
                ,PartsUsedInVehicle.class)
                .getResultList();
    }

}
