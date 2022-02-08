package at.htl.workloads.ownership;

import at.htl.IntTestBase;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.person.Tenant;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RentalServiceTest extends IntTestBase {
    @Inject
    PersonService personService;

    @Inject
    VehicleService vehicleService;

    @Inject
    RentalService rentalService;

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
                    BigDecimal.valueOf(10),
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
    void saveRentalFromDateAfterToDate() {
        Rental r = new Rental();

        assertThatThrownBy(() -> rentalService.saveRental(vehicle.getId(),
                tenant.getSvNr(),
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2020,1,20,10,0))
        )
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void saveRentalNonExistentTenant() {
        Rental r = new Rental();

        assertThatThrownBy(() -> rentalService.saveRental(vehicle.getId(),
                "asdf",
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0))
        )
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void saveRentalNonExistentVehicle() {
        Rental r = new Rental();

        assertThatThrownBy(() -> rentalService.saveRental(9999L,
                tenant.getSvNr(),
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0))
        )
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void saveRentalNullDate() {
        Rental r = new Rental();

        assertThatThrownBy(() -> rentalService.saveRental(vehicle.getId(),
                tenant.getSvNr(),
                null,
                null)
        )
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createAndSaveRentalOK() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0)
        );

        assertThat(rentalService.findRentalById(r.getId())).isNotNull().isEqualTo(r);

        rentalService.deleteRental(r);
    }

    @Test
    void updateRentalOK() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0)
        );

        assertThatCode(() -> rentalService.updateRental(r.getId(),
                r.getVehicle().getId(),
                r.getTenant().getSvNr(),
                LocalDateTime.of(2021,1,11,10,0),
                LocalDateTime.of(2021,1,12,10,0))).doesNotThrowAnyException();

        assertThat(rentalService.findRentalById(r.getId()))
                .hasFieldOrPropertyWithValue("from", LocalDateTime.of(2021,1,11,10,0))
                .hasFieldOrPropertyWithValue("to", LocalDateTime.of(2021,1,12,10,0));

        rentalService.deleteRental(r);
    }

    @Test
    void updateRentalNonexistentTenant() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0)
        );

        assertThatThrownBy(() -> rentalService.updateRental(r.getId(),
                r.getVehicle().getId(),
                "asdf",
                r.getFrom(),
                r.getTo())).isInstanceOf(ValidationException.class);
    }

    @Test
    void updateRentalNonexistentVehicle() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0)
        );

        assertThatThrownBy(() -> rentalService.updateRental(r.getId(),
                999L,
                tenant.getSvNr(),
                r.getFrom(),
                r.getTo())).isInstanceOf(ValidationException.class);
    }

    @Test
    void updateRentalNonexistentRental() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0)
        );

        assertThatThrownBy(() -> rentalService.updateRental(999L,
                vehicle.getId(),
                tenant.getSvNr(),
                r.getFrom(),
                r.getTo())).isInstanceOf(ValidationException.class);
    }

    @Test
    void updateRentalFromAfterTo() {
        Rental r = createAndSaveRental(
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2022,1,20,10,0)
        );

        assertThatThrownBy(() -> rentalService.updateRental(r.getId(),
                vehicle.getId(),
                tenant.getSvNr(),
                LocalDateTime.of(2021,1,10,10,0),
                LocalDateTime.of(2020,1,10,10,0))).isInstanceOf(ValidationException.class);
    }

    private Rental createAndSaveRental(LocalDateTime of, LocalDateTime of1) {
        AtomicReference<Rental> savedR = new AtomicReference<>();
        assertThatCode(() -> savedR.set(rentalService.saveRental(vehicle.getId(),
                tenant.getSvNr(),
                of,
                of1))).doesNotThrowAnyException();

        return savedR.get();
    }
}