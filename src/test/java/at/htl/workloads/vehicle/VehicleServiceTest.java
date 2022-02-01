package at.htl.workloads.vehicle;

import at.htl.IntTestBase;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class VehicleServiceTest extends IntTestBase {
    @Inject
    VehicleService service;

    @Test
    void addOk() {
        AtomicReference<Vehicle> newV = new AtomicReference<>();

        assertThatCode(() -> newV.set(service.saveVehicle(
                "BMW",
                LocalDate.of(2002, 1, 1),
                400,
                10,
                null
        )))
                .doesNotThrowAnyException();

        assertThat(service.findById(newV.get().getId()))
                .isNotNull().isEqualTo(newV.get());

        assertThatCode(() -> service.delete(newV.get()))
                .doesNotThrowAnyException();
    }

    @Test
    void add_BuildDate_In_Future() {
        assertThatThrownBy(() -> service.saveVehicle(
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                400,
                10,
                null
        ))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void update_buildDate_in_future(){
        assertThatThrownBy(() -> service.updateVehicle(
                1L,
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                400,
                10,
                null
        ))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void add_hp_lowerthan_0() {
        assertThatThrownBy(() -> service.saveVehicle(
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                0,
                10,
                null
        ))
                .isInstanceOf(ValidationException.class);

        assertThatThrownBy(() -> service.saveVehicle(
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                -3,
                10,
                null
        ))
                .isInstanceOf(ValidationException.class);
    }



    @Test
    void update_horsepower_lowerthan_0(){
        assertThatThrownBy(() -> service.updateVehicle(
                1L,
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                0,
                10,
                null
        ))
                .isInstanceOf(ValidationException.class);

        assertThatThrownBy(() -> service.updateVehicle(
                1L,
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                -3,
                10,
                null
        ))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void add_acceleration_lowerthan_0() {
        assertThatThrownBy(() -> service.saveVehicle(
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                200,
                0,
                null
        ))
                .isInstanceOf(ValidationException.class);

        assertThatThrownBy(() -> service.saveVehicle(
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                200,
                -1,
                null
        ))
                .isInstanceOf(ValidationException.class);
    }



    @Test
    void update_acceleration_lowerthan_0(){
        assertThatThrownBy(() -> service.updateVehicle(
                1L,
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                200,
                0,
                null
        ))
                .isInstanceOf(ValidationException.class);

        assertThatThrownBy(() -> service.updateVehicle(
                1L,
                "BMW",
                LocalDate.of(LocalDate.now().getYear()+1, 1, 1),
                200,
                -5,
                null
        ))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void updateOk() {
        AtomicReference<Vehicle> newV = new AtomicReference<>();

        assertThatCode(() -> newV.set(service.saveVehicle(
                "BMW",
                LocalDate.of(2002, 1, 1),
                400,
                10,
                null
        )))
                .doesNotThrowAnyException();

        Vehicle v = newV.get();

        assertThatCode(() -> service.updateVehicle(
                v.getId(),
                v.getBrand(),
                v.getConstructionPerYear(),
                150,
                12,
                null
        ))
                .doesNotThrowAnyException();

        assertThat(service.findById(newV.get().getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("horsePower", 150);

        assertThatCode(() -> service.delete(newV.get()))
                .doesNotThrowAnyException();
    }
}