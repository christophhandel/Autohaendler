package at.htl.workloads.person;

import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface PersonService {
    Mechanic saveMechanic(String svNr, String firstName, String lastName,
                          LocalDate dateOfBirth, String phoneNumber,
                          String driverLicenceNumber,
                          BigDecimal pricePerHour, LocalTime workStart, LocalTime workEnd) throws ValidationException;

    Mechanic updateMechanic(String actSvNr, String newSvNr, String firstName,
                            String lastName, LocalDate dateOfBirth, String phoneNumber,
                            String driverLicenceNumber, List<Long> reparationIds, BigDecimal pricePerHour,
                            LocalTime workStart, LocalTime workEnd) throws ValidationException;

    Mechanic findMechanicById(String svNr);

    List<Mechanic> findAllMechanics();

    void deleteMechanic(Mechanic m);

    Tenant saveTenant(String svNr, String firstName, String lastName,
                      LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber,
                      double priceDiscountPercent) throws ValidationException;

    Tenant updateTenant(String svNr, String svNr1, String firstName,
                          String lastName, LocalDate dateOfBirth, String phoneNumber,
                          String driverLicenceNumber, double priceDiscountPercent, List<Long> rentalIds)
            throws ValidationException;

    Tenant findTenantById(String svNr);

    List<Tenant> findAllTenants();

    void deleteTenant(Tenant t);
}
