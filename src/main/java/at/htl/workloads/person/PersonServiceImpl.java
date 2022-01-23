package at.htl.workloads.person;

import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonServiceImpl implements PersonService{

    private final PersonRepository repository;

    private final ReparationRepo reparationRepository;

    @Inject
    public PersonServiceImpl(PersonRepository repository,
                             ReparationRepo reparationRepo) {
        this.repository = repository;
        this.reparationRepository = reparationRepo;
    }

    @Override
    public Mechanic saveMechanic(String svNr, String firstName, String lastName,
                                 LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber,
                                 BigDecimal pricePerHour, LocalTime workStart, LocalTime workEnd) throws ValidationException {

        if(findMechanicById(svNr) != null)
            throw new ValidationException("Mechanic already exists!");

        Mechanic m = new Mechanic(svNr,firstName,lastName,dateOfBirth,
                phoneNumber,driverLicenceNumber, new ArrayList<>(), pricePerHour,
                workStart, workEnd);

        return repository.saveMechanic(m);
    }

    @Override
    public Mechanic updateMechanic(String actSvNr, String newSvNr, String firstName, String lastName,
                                   LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber,
                                   List<Long> reparationIds, BigDecimal pricePerHour,
                                   LocalTime workStart, LocalTime workEnd) throws ValidationException {

        if(findMechanicById(actSvNr) == null)
            throw new ValidationException("Mechanic with that svNr does not exist!");
        else if(!actSvNr.equals(newSvNr))
            throw new ValidationException("The 2 svNrs are not equal!!");

        List<Reparation> newReparations = reparationRepository.getByIds(reparationIds);

        if(newReparations == null)
            throw new ValidationException("One of the reparations do not exist!!");

        Mechanic m = findMechanicById(actSvNr);
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setDateOfBirth(dateOfBirth);
        m.setPhoneNumber(phoneNumber);
        m.setDriverLicenceNumber(driverLicenceNumber);
        m.getReparations().addAll(newReparations);
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
    public Tenant saveTenant(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, double priceDiscountPercent) throws ValidationException {
        // TODO: Implement
        return null;
    }

    @Override
    public Tenant updateTenant(String svNr, String svNr1, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, double priceDiscountPercent, List<Long> rentalIds) throws ValidationException {
        // TODO: Implement
        return null;
    }

    @Override
    public Tenant findTenantById(String svNr) {
        // TODO: Implement
        return null;
    }

    @Override
    public List<Tenant> findAllTenants() {
        // TODO: Implement
        return null;
    }

    @Override
    public void deleteTenant(Tenant t) {
        // TODO: Implement

    }
}
