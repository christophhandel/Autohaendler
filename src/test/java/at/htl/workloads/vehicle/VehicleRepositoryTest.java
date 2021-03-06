package at.htl.workloads.vehicle;

import at.htl.IntTestBase;
import at.htl.models.results.PartsUsedInVehicle;
import at.htl.workloads.ownership.Rental;
import at.htl.workloads.ownership.RentalRepo;
import at.htl.workloads.person.PersonRepository;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.Replacement;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@QuarkusTest
class VehicleRepositoryTest extends IntTestBase {
    @Inject
    VehicleRepository repository;

    @Inject
    RentalRepo rentalRepo;

    @Inject
    PersonRepository personRepository;

   @Inject
   VehicleRepository vehicleRepository;

    @Test
    void saveVehicleOk() {
        Vehicle v = new Vehicle(
                "BMW",
                LocalDate.of(2020, 1, 1),
                500,
                BigDecimal.valueOf(10),
                null
        );

        AtomicReference<Vehicle> newV = new AtomicReference<>();

        assertThatCode(() -> newV.set(repository.saveVehicle(v)))
                .doesNotThrowAnyException();

        assertThat(repository.findById(newV.get().getId()))
                .isNotNull()
                .isEqualTo(newV.get());

        assertThatCode(() -> repository.deleteVehicle(newV.get()))
                .doesNotThrowAnyException();
    }

    @Test
    void updateVehicleOk() {
        Vehicle v = new Vehicle(
                "BMW",
                LocalDate.of(2020, 1, 1),
                500,
                BigDecimal.valueOf(10),
                null
        );

        AtomicReference<Vehicle> newV = new AtomicReference<>();

        assertThatCode(() -> newV.set(repository.saveVehicle(v)))
                .doesNotThrowAnyException();

        Vehicle updatedV = newV.get();
        updatedV.setPricePerHour(BigDecimal.valueOf(999));

        assertThatCode(() -> repository.updateVehicle(v))
                .doesNotThrowAnyException();

        assertThat(repository.findById(newV.get().getId()))
                .hasFieldOrPropertyWithValue("pricePerHour", BigDecimal.valueOf(999));

        assertThatCode(() -> repository.deleteVehicle(newV.get()))
                .doesNotThrowAnyException();
    }

    @Test
    void getAllVehiclesInIdListOk() {
        List<Long> ids = List.of(1L, 2L, 3L);

        AtomicReference<List<Vehicle>> vehicles = new AtomicReference<>();

        assertThatCode(() -> vehicles.set(repository.getAllVehiclesInIdList(ids)))
                .doesNotThrowAnyException();

        List<Vehicle> sVehicles = vehicles.get();
        sVehicles.sort(Comparator.comparing(Vehicle::getId));

        for (int i = 0; i < sVehicles.size(); i++) {
            assertThat(ids.get(i)).isEqualTo(sVehicles.get(i).getId());
        }
    }

    @Test
    void findSoldVehicles() {
        Vehicle v1 = new Vehicle(
                "BMW",
                LocalDate.of(2020, 1, 1),
                500,
                BigDecimal.valueOf(100),
                null
        );
        Vehicle v2 = new Vehicle(
                "BMW",
                LocalDate.of(2021, 1, 1),
                500,
                BigDecimal.valueOf(10),
                personRepository.findOwnerById("4444444444")
        );

        AtomicReference<Vehicle> vNoOwner = new AtomicReference<>();
        AtomicReference<Vehicle> vOwner = new AtomicReference<>();

        assertThatCode(() -> vNoOwner.set(repository.saveVehicle(v1)))
                .doesNotThrowAnyException();
        assertThatCode(() -> vOwner.set(repository.saveVehicle(v2)))
                .doesNotThrowAnyException();

        assertThat(vNoOwner.get()).isNotIn(repository.findSoldVehicles());
        assertThat(vOwner.get()).isIn(repository.findSoldVehicles());
    }

    @Test
    void findRentalsForVehicleOk() {
        Vehicle v = new Vehicle(
                "BMW",
                LocalDate.of(2020, 1, 1),
                500,
                BigDecimal.valueOf(10),
                null
        );

        Rental r = new Rental(
                v,
                personRepository.findTenantById("1111166666"),
                LocalDateTime.of(LocalDateTime.now().getYear()+1, 1,1,1,1),
                LocalDateTime.of(LocalDateTime.now().getYear()+2, 1,1,1,1)
        );

        AtomicReference<Vehicle> newV = new AtomicReference<>();
        AtomicReference<Rental> newR = new AtomicReference<>();

        assertThatCode(() -> newV.set(repository.saveVehicle(v)))
                .doesNotThrowAnyException();
        assertThatCode(() -> newR.set(rentalRepo.saveRental(r)))
                .doesNotThrowAnyException();

        assertThat(newR.get()).isIn(repository.findRentalsForVehicleInFuture(newV.get()));
    }

    @Test
    void findPartsPerVehicle() {

        assertThatCode(() -> vehicleRepository.countsThePartsInAVehicle())
                .doesNotThrowAnyException();

        List<PartsUsedInVehicle> tmpParts = vehicleRepository.countsThePartsInAVehicle();
        assertThat(tmpParts).isNotNull();

        for (var part: tmpParts) {
            boolean contains = false;
            for (Reparation reparation : part.getVehicle().getReparations()) {
                    contains = (reparation.getReplacements().stream()
                            .anyMatch(replacement ->
                                    replacement.getId().getPart().getPartId().equals(part.getPart().getPartId()))
                    );
                    if(contains)
                        break;
            }

            assertThat(contains).isEqualTo(true);
        }
    }
}