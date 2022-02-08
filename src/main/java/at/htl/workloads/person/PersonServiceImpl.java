package at.htl.workloads.person;

import at.htl.models.results.IncomePerPerson;
import at.htl.workloads.reparation.ReparationRepo;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonServiceImpl implements PersonService{

    private final PersonRepository repository;

    private final ReparationRepo reparationRepository;
    private final VehicleService vehicleService;

    @Inject
    public PersonServiceImpl(PersonRepository repository,
                             ReparationRepo reparationRepo,
                             VehicleService vehicleService) {
        this.repository = repository;
        this.reparationRepository = reparationRepo;
        this.vehicleService = vehicleService;
    }

    @Override
    public Mechanic saveMechanic(String svNr, String firstName, String lastName,
                                 LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber,
                                 BigDecimal pricePerHour, LocalTime workStart, LocalTime workEnd) throws ValidationException {

        if(findMechanicById(svNr) != null)
            throw new ValidationException("Der Mechaniker existiert bereit!");

        if (dateOfBirth.compareTo(LocalDate.now())> 0)
        {
            throw new ValidationException("Geburtsdatum darf nicht in der Zukunft liegen!");
        }

        if (pricePerHour.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ValidationException("Der Stundenlohn darf nicht unter null sein!");
        }

        if (workStart.compareTo(workEnd) >= 0)
        {
            throw new ValidationException("Der Arbeitsstart darf nicht vor dem Ende liegen!");
        }

        Mechanic m = new Mechanic(svNr,firstName,lastName,dateOfBirth,
                phoneNumber,driverLicenceNumber, new ArrayList<>(), pricePerHour,
                workStart, workEnd);

        return repository.saveMechanic(m);
    }

    @Override
    public Mechanic updateMechanic(String actSvNr, String newSvNr, String firstName, String lastName,
                                   LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber,
                                   BigDecimal pricePerHour,
                                   LocalTime workStart, LocalTime workEnd) throws ValidationException {

        if(findMechanicById(actSvNr) == null)
            throw new ValidationException("Mechanic with that svNr does not exist!");
        else if(!actSvNr.equals(newSvNr))
            throw new ValidationException("The 2 svNrs are not equal!!");

        Mechanic m = findMechanicById(actSvNr);
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setDateOfBirth(dateOfBirth);
        m.setPhoneNumber(phoneNumber);
        m.setDriverLicenceNumber(driverLicenceNumber);
        m.setPricePerHour(pricePerHour);
        m.setWorkStart(workStart);
        m.setWorkEnd(workEnd);

        m = repository.updateMechanic(m);

        return m;
    }

    @Override
    public Mechanic findMechanicById(String svNr) {
        return repository.findMechanicById(svNr);
    }

    @Override
    public List<Mechanic> findAllMechanics() {
        return repository.findAllMechanics();
    }

    @Override
    public void deleteMechanic(Mechanic m) {
        repository.deleteMechanic(m);
    }

    @Override
    public Tenant saveTenant(String svNr, String firstName, String lastName,
                             LocalDate dateOfBirth, String phoneNumber,
                             String driverLicenceNumber, double priceDiscountPercent)
            throws ValidationException {

        if(findTenantById(svNr) != null)
            throw new ValidationException("Tenant already exists!");

        if (dateOfBirth.compareTo(LocalDate.now()) > 0){
            throw new ValidationException("Geburtsdatum darf nicht in der Zukunft liegen!");
        }

        if (priceDiscountPercent <0.0){
            throw new ValidationException("Der Prozentsatz darf nicht unter null sein!");
        }

        Tenant tenant = new Tenant(svNr,firstName,lastName,dateOfBirth,
                phoneNumber,driverLicenceNumber, new ArrayList<>(),priceDiscountPercent);

        return repository.saveTanant(tenant);
    }

    @Override
    public Tenant updateTenant(String svNr, String svNr1, String firstName,
                               String lastName, LocalDate dateOfBirth,
                               String phoneNumber, String driverLicenceNumber,
                               double priceDiscountPercent)
            throws ValidationException {
        if(findTenantById(svNr) == null)
            throw new ValidationException("Tenant with that svNr does not exist!");
        else if(!svNr.equals(svNr1))
            throw new ValidationException("The 2 svNrs are not equal!!");

        Tenant t = findTenantById(svNr);
        t.setFirstName(firstName);
        t.setLastName(lastName);
        t.setDateOfBirth(dateOfBirth);
        t.setPhoneNumber(phoneNumber);
        t.setDriverLicenceNumber(driverLicenceNumber);
        t.setPriceDiscountPercent(priceDiscountPercent);

        t = repository.updateTenant(t);

        return t;
    }

    @Override
    public Tenant findTenantById(String svNr) {
        return repository.findTenantById(svNr);
    }

    @Override
    public List<Tenant> findAllTenants() {
        return repository.findAllTenants();
    }

    @Override
    public void deleteTenant(Tenant t) {
        repository.deleteTenant(t);
    }

    @Override
    public Owner saveOwner(String svNr, String firstName, String lastName,
                           LocalDate dateOfBirth, String phoneNumber,
                           String driverLicenceNumber)
            throws ValidationException {

        if(findOwnerById(svNr) != null)
            throw new ValidationException("Bestizer existiert bereits!");

        if (dateOfBirth.compareTo(LocalDate.now()) > 0){
            throw new ValidationException("Geburtsdatum darf nicht in der Zukunft liegen!");
        }

        Owner o = new Owner(
                svNr,
                firstName,
                lastName,
                dateOfBirth,
                phoneNumber,
                driverLicenceNumber,
                new ArrayList<>()
        );



        return repository.saveOwner(o);
    }

    @Override
    public Owner updateOwner(String svNr, String svNr1, String firstName,
                             String lastName, LocalDate dateOfBirth, String phoneNumber,
                             String driverLicenceNumber,
                             List<Long> vehicleIds)
            throws ValidationException {

        if(!svNr.equals(svNr1))
            throw new ValidationException("Cannot change Insurance Number!");
        else if(findOwnerById(svNr) == null)
            throw new ValidationException("Owner does not exist!");
        else if(!vehicleIds.isEmpty() && vehicleService.findWithIds(vehicleIds) == null)
            throw new ValidationException("One of the vehicleIds does not exist in the database!");

        Owner o = findOwnerById(svNr);

        o.setFirstName(firstName);
        o.setLastName(lastName);
        o.setDateOfBirth(dateOfBirth);
        o.setPhoneNumber(phoneNumber);
        o.setDriverLicenceNumber(driverLicenceNumber);

        List<Vehicle> vehicles = o.getVehicles();

        for(Long vehicleId : vehicleIds) {
            vehicles.add(vehicleService.findById(vehicleId));
        }

        o.setVehicles(vehicles);

        o = repository.updateOwner(o);

        return o;
    }

    @Override
    public Owner findOwnerById(String svNr) {
        return repository.findOwnerById(svNr);
    }

    @Override
    public List<Owner> findAllOwners() {
        return repository.findAllOwners();
    }

    @Override
    public void deleteOwner(Owner o) {
        repository.deleteOwner(o);
    }

    @Override
    public List<Person> findAllPeople() {
        return repository.findAllPeople();
    }

    @Override
    public Person findPersonById(String svNr) {
        return repository.findPersonById(svNr);
    }

    @Override
    public List<IncomePerPerson> calculateIncomePerOwner() {
        return repository.calculateIncomePerOwner();
    }

    @Override
    public List<IncomePerPerson> calculateIncomePerTenant() {
        return repository.calculateIncomePerTenant();
    }
}
