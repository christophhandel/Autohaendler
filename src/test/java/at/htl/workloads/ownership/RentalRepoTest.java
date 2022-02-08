package at.htl.workloads.ownership;

import at.htl.IntTestBase;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.person.Tenant;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.*;

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
                    BigDecimal.valueOf(100),
                    null
            );
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void delete(){
        vehicleService.delete(vehicle);
        personService.deleteTenant(tenant);
    }

    @Test
    void getByIdNonExistent() {
        AtomicReference<Rental> r = new AtomicReference<>();
        assertThatCode(() -> r.set(rentalRepository.findRentalById(999L))).doesNotThrowAnyException();
        assertThat(r.get()).isNull();
    }

    @Test
    void saveRental() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2020,1,20,10,0)
        );

        var loadedRental = rentalRepository.findRentalById(r.getId());

        assertThat(loadedRental).isEqualTo(r).isNotNull();

        rentalRepository.deleteRental(r);
    }

    @Test
    void updateRental() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2021,1,20,10,0)
        );

        r.setFrom(LocalDateTime.of(2022,1,10,10,0));

        assertThatCode(() -> rentalRepository.updateRental(r)).doesNotThrowAnyException();

        var loadedRental = rentalRepository.findRentalById(r.getId());

        assertThat(loadedRental).isNotNull().hasFieldOrPropertyWithValue("from", LocalDateTime.of(2022,1,10,10,0));

        rentalRepository.deleteRental(r);
    }

    private Rental createAndSaveRental(LocalDateTime of, LocalDateTime of1) {
        Rental r = new Rental(vehicle,
                tenant,
                of,
                of1);

        AtomicReference<Rental> savedR = new AtomicReference<>();
        assertThatCode(() -> savedR.set(rentalRepository.saveRental(r))).doesNotThrowAnyException();

        return savedR.get();
    }
}