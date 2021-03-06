package at.htl.workloads.person;

import at.htl.models.results.IncomePerPerson;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PersonService {
    Mechanic saveMechanic(String svNr, String firstName, String lastName,
                          LocalDate dateOfBirth, String phoneNumber,
                          String driverLicenceNumber,
                          BigDecimal pricePerHour, LocalTime workStart, LocalTime workEnd) throws ValidationException;

    Mechanic updateMechanic(String actSvNr, String newSvNr, String firstName,
                            String lastName, LocalDate dateOfBirth, String phoneNumber,
                            String driverLicenceNumber, BigDecimal pricePerHour,
                            LocalTime workStart, LocalTime workEnd) throws ValidationException;

    Mechanic findMechanicById(String svNr);

    List<Mechanic> findAllMechanics();

    void deleteMechanic(Mechanic m);

    Tenant saveTenant(String svNr, String firstName, String lastName,
                      LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber,
                      double priceDiscountPercent) throws ValidationException;

    Tenant updateTenant(String svNr, String svNr1, String firstName,
                          String lastName, LocalDate dateOfBirth, String phoneNumber,
                          String driverLicenceNumber, double priceDiscountPercent)
            throws ValidationException;

    Tenant findTenantById(String svNr);

    List<Tenant> findAllTenants();

    void deleteTenant(Tenant t);

    Owner saveOwner(String svNr, String firstName, String lastName,
                      LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber)
            throws ValidationException;

    Owner updateOwner(String svNr, String svNr1, String firstName,
                        String lastName, LocalDate dateOfBirth, String phoneNumber,
                        String driverLicenceNumber, List<Long> vehicleIds)
            throws ValidationException;

    Owner findOwnerById(String svNr);

    List<Owner> findAllOwners();

    void deleteOwner(Owner o);

    List<Person> findAllPeople();

    Person findPersonById(String svNr);

    List<IncomePerPerson> calculateIncomePerOwner();

    List<IncomePerPerson> calculateIncomePerTenant();
}
