package at.htl.workloads.ownership;

import at.htl.workloads.person.Owner;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleRepository;
import at.htl.workloads.vehicle.VehicleService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class RentalServiceImpl implements RentalService {

    @Inject
    RentalRepo rentalRepo;

    @Inject
    VehicleRepository vehicleRepository;

    private final VehicleService vehicleService;
    private final PersonService personservice;


    @Inject
    public RentalServiceImpl(VehicleService vehicleService, PersonService personservice, RentalRepo rentalRepo) {
        this.vehicleService = vehicleService;
        this.personservice = personservice;
        this.rentalRepo = rentalRepo;
    }

    @Override
    public Rental saveRental(Long vehicleId, String tenantId, LocalDateTime from, LocalDateTime to) throws ValidationException {
        if(from == null || to == null)
            throw new ValidationException("Datum muss valid sein!");
        else if(from.compareTo(to) > 0)
            throw new ValidationException("Von-Datum kann nicht größer sein als bis-datum");
        else if(vehicleService.findById(vehicleId) == null)
            throw new ValidationException("KFZ mit dieser ID existiert nicht!");
        else if(personservice.findTenantById(tenantId) == null)
            throw new ValidationException("Tenant mit dieser ID existiert nicht!");

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
        if(from == null || to == null)
            throw new ValidationException("Datum muss valid sein!");
        else if(from.compareTo(to) > 0)
            throw new ValidationException("Von-Datum kann nicht größer sein als bis-datum");
        else if(personservice.findTenantById(tenantId) == null)
            throw new ValidationException("Tenant with that id does not exist!");
        else if(findRentalById(id) == null)
            throw new ValidationException("Rental mit dieser ID existiert nicht!");

        Rental r = findRentalById(id);

        r.setFrom(from);
        r.setTo(to);
        r.setTenant(personservice.findTenantById(tenantId));
        r.setVehicle(vehicleService.findById(vehicleId));

        r = rentalRepo.updateRental(r);

        return r;
    }

    @Override
    public VehicleTransfer saveTransfer(Long vehicleId, String ownerId) throws ValidationException {
        Vehicle ve = vehicleService.findById(vehicleId);

        if (ve == null)
            throw new ValidationException("KFZ nicht gefunden!");
        else if(ve.getOwner() != null)
            throw new ValidationException("KFZ gehört nicht mehr uns!");

        Owner o = personservice.findOwnerById(ownerId);
        if (o == null)
            throw new ValidationException("Person nicht gefunden!");

        ve.setOwner(o);
        vehicleRepository.updateVehicle(ve);
        VehicleTransfer v = new VehicleTransfer(ve,
                personservice.findOwnerById(ownerId),
                LocalDateTime.now());

        return rentalRepo.saveTransfer(v);
    }

    @Override
    public VehicleTransfer findTransferById(Long id) {
        return rentalRepo.findTransferById(id);
    }

    @Override
    public void deleteTransfer(Long id) {
        VehicleTransfer v = findTransferById(id);
        rentalRepo.deleteVehicle(v);
    }

    @Override
    public List<VehicleTransfer> findAllTransfers() {
        return rentalRepo.findAllTransfers();
    }
}
