package at.htl.workloads.ownership;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RentalService {

    Rental saveRental(Long vehicleId, String tenantId, LocalDateTime from, LocalDateTime to) throws ValidationException;

    Rental findRentalById(Long id);

    List<Rental> findAllRentals();

    void deleteRental(Rental r);

    Rental updateRental(Long id,Long vehicleId, String tenantId, LocalDateTime from, LocalDateTime to) throws ValidationException;

    VehicleTransfer saveTransfer(Long vehicleId, String ownerId) throws ValidationException;

    VehicleTransfer findTransferById(Long id);

    void deleteTransfer(Long id);

    List<VehicleTransfer> findAllTransfers();
}
