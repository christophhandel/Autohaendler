package at.htl.workloads.vehicle;

import at.htl.workloads.ownership.Rental;
import at.htl.workloads.person.PersonService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class VehicleServiceImpl implements VehicleService{
    private final VehicleRepository vehicleRepository;
    private final PersonService personService;

    @Inject
    public VehicleServiceImpl(VehicleRepository vehicleRepository, PersonService personService) {
        this.vehicleRepository = vehicleRepository;
        this.personService = personService;
    }

    @Override
    public Vehicle saveVehicle(String brand, LocalDate constructionPerYear, int horsePower, int acceleration, String ownerId)
            throws ValidationException {
        if(constructionPerYear.compareTo(LocalDate.now()) > 0)
            throw new ValidationException("ConstructionYear kann nicht in der Zukunft liegen!");

        else if(horsePower <= 0)
            throw new ValidationException("PS kann nicht unter 0 sein!");

        else if(acceleration <= 0)
            throw new ValidationException("0-100 Beschleuningungsdauer kann nicht unter 0 sein!");

        Vehicle vehicle = new Vehicle(
                brand,
                constructionPerYear,
                horsePower,
                acceleration,
                null);
        return vehicleRepository.saveVehicle(vehicle);

    }

    @Override
    public Vehicle updateVehicle(Long id, String brand, LocalDate constructionPerYear,
                                 int horsePower,
                                 int acceleration,
                                 String ownerId) throws ValidationException {
        if(constructionPerYear.compareTo(LocalDate.now()) > 0)
            throw new ValidationException("ConstructionYear kann nicht in der Zukunft liegen!");

        else if(horsePower <= 0)
            throw new ValidationException("PS kann nicht unter 0 sein!");

        else if(acceleration <= 0)
            throw new ValidationException("0-100 Beschleuningungsdauer kann nicht unter 0 sein!");

        if (findById(id) == null){
            throw new ValidationException("Dieses Fahrzeig existiert nicht!");
        }

        if (ownerId != null && personService.findOwnerById(ownerId) == null){
            throw  new ValidationException("Dieses Besitzer gibt es noch nicht!");
        }

        Vehicle vehicle = findById(id);

        vehicle.setBrand(brand);
        vehicle.setAcceleration(acceleration);
        vehicle.setHorsePower(horsePower);
        vehicle.setOwner(ownerId == null ? null : personService.findOwnerById(ownerId));
        vehicle.setConstructionPerYear(constructionPerYear);

        return vehicleRepository.updateVehicle(vehicle);
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.listAll();
    }

    @Override
    public void delete(Vehicle v) {
        vehicleRepository.deleteVehicle(v);
    }

    /**
     * Liefert alle KFZs zurück, deren ID in vehicleIds liste drinnen ist.
     * @param vehicleIds
     * @return
     */
    @Override
    public List<Vehicle> findWithIds(List<Long> vehicleIds) {
        return vehicleRepository.getAllVehiclesInIdList(vehicleIds);
    }

    /**
     * Liefert alle KFZs zurück, die nicht verkauft sind.
     */
    @Override
    public List<Vehicle> findSoldVehicles() {
        return vehicleRepository.findSoldVehicles();
    }

    @Override
    public List<Rental> getFutureRentals(Vehicle v) {
        return vehicleRepository.findRentalsForVehicleInFuture(v);
    }
}
