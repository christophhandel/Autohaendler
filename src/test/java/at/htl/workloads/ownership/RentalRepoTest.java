package at.htl.workloads.ownership;

import at.htl.IntTestBase;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.person.Tenant;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@QuarkusTest
class RentalRepoTest extends IntTestBase {
    @Inject
    PersonService personService;

    @Inject
    VehicleService vehicleService;

    @Inject
    RentalRepo rentalRepository;

    Tenant tenant;
    Vehicle vehicle;

    @BeforeEach
    private void insert() {
        try {
            tenant = personService.saveTenant(
                    "129384712",
                    "A",
                    "B",
                    LocalDate.now(),
                    "0123748123",
                    "AT33123",
                    2
            );

            vehicle = vehicleService.saveVehicle(
                    "ASD",
                    LocalDate.now(),
                    500,
                    100,
                    null
            );
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    @Test
    void saveRental() {
        Rental r = new Rental(vehicle,
                tenant,
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2021,1,20,10,0));

        AtomicReference<Rental> savedR = new AtomicReference<>();
        assertThatCode(() -> savedR.set(rentalRepository.saveRental(r))).doesNotThrowAnyException();

        var loadedRental = rentalRepository.findRentalById(savedR.get().getId());

        assertThat(loadedRental).isNotNull();
    }

    // TODO: Finish
}