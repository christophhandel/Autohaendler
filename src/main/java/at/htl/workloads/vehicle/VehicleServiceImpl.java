package at.htl.workloads.vehicle;

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
    public Vehicle saveVehicle(String brand, LocalDate constructionPerYear, int horsePower, int acceleration, String ownerId) throws ValidationException {
        Vehicle vehicle = new Vehicle(
                brand,
                constructionPerYear,
                horsePower,
                acceleration,
                null);
        return vehicleRepository.saveVehicle(vehicle);

    }

    @Override
    public Vehicle updateVehicle(Long id, String brand, LocalDate constructionPerYear, int horsePower, int acceleration, String ownerId) throws ValidationException {
        if (findById(id) != null){
            throw new ValidationException("Dieses Fahrzeig existiert bereits!");
        }

        if (personService.findOwnerById(ownerId) == null){
            throw  new ValidationException("Dieses Besitzer gibt es noch nicht!");
        }
        Vehicle vehicle = new Vehicle(
                brand,
                constructionPerYear,
                horsePower,
                acceleration,
                personService.findOwnerById(ownerId));
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
}
