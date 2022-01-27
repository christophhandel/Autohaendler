package at.htl.workloads.ownership;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.vehicle.VehicleService;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

public class RentalServiceImpl implements RentalService {


    private final VehicleService vehicleService;

    private final PersonService personservice;

    private final RentalRepo rentalRepo;

    @Inject
    public RentalServiceImpl(VehicleService vehicleService, PersonService personservice, RentalRepo rentalRepo) {
        this.vehicleService = vehicleService;
        this.personservice = personservice;
        this.rentalRepo = rentalRepo;
    }

    @Override
    public Rental saveRental(Long vehicleId, String tenantId, LocalDateTime from, LocalDateTime to) {
        Rental r = new Rental(vehicleService.findById(vehicleId),personservice.findTenantById(tenantId),from,to);

        return rentalRepo.saveRental(r);
    }

    @Override
    public Rental findRentalById(Long id) {
        return rentalRepo.findRentalById(id);
    }

    @Override
    public List<Rental> findAllRentals() {
        return rentalRepo.findAllRentals();
    }

    @Override
    public void deleteRental(Rental r) {
        rentalRepo.deleteRental(r);
    }

    @Override
    public Rental updateRental(Long id,Long vehicleId, String tenantId, LocalDateTime from, LocalDateTime to) throws ValidationException {
        if(vehicleService.findById(vehicleId) == null)
            throw new ValidationException("Vehicle with that id does not exist!");
        else if(personservice.findTenantById(tenantId) == null)
            throw new ValidationException("Tenant with that id does not exist!");

        Rental r = findRentalById(id);
        r.setFrom(from);
        r.setTo(to);
        r.setTenant(personservice.findTenantById(tenantId));
        r.setVehicle(vehicleService.findById(vehicleId));

        r = rentalRepo.updateMechanic(r);

        return r;
    }
}
