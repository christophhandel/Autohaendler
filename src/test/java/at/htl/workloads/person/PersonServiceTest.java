package at.htl.workloads.person;

import at.htl.IntTestBase;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PersonServiceTest extends IntTestBase {

    @Inject
    PersonService personService;

    @Test
    void createMechanicOk(){
        assertThatCode(() ->
                personService.saveMechanic("asds234asf",
                        "Hans","Mayr",
                        LocalDate.of(2008,12,24),
                        "065089232","123",
                        BigDecimal.ONE, LocalTime.of(8,24)
                        ,LocalTime.of(10,32)))
                .doesNotThrowAnyException();

        assertThat(personService.findMechanicById("asds234asf")).isNotNull();
        personService.deleteMechanic(personService.findMechanicById("asds234asf"));
    }

    @Test
    void createMechanicWrongDob(){
        assertThatThrownBy(() ->
                personService.saveMechanic("asds234asf",
                        "Hans","Mayr",
                        LocalDate.of(2025,12,24),
                        "065089232","123",
                        BigDecimal.ONE, LocalTime.of(8,24)
                        ,LocalTime.of(10,32)))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createMechanicWrongWorkingHours(){
        assertThatThrownBy(() ->
                personService.saveMechanic("asds234asf",
                        "Hans","Mayr",
                        LocalDate.of(2025,12,24),
                        "065089232","123",
                        BigDecimal.ONE, LocalTime.of(12,24)
                        ,LocalTime.of(10,32)))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createMechanicWrongPricePerHour(){
        assertThatThrownBy(() ->
                personService.saveMechanic("asds234asf",
                        "Hans","Mayr",
                        LocalDate.of(2025,12,24),
                        "065089232","123",
                        BigDecimal.ZERO, LocalTime.of(12,24)
                        ,LocalTime.of(10,32)))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createMechanicTwice(){
        assertThatCode(() ->
                personService.saveMechanic("asds234asf",
                        "Hans","Mayr",
                        LocalDate.of(2008,12,24),
                        "065089232","123",
                        BigDecimal.ONE, LocalTime.of(8,24)
                        ,LocalTime.of(10,32)))
                .doesNotThrowAnyException();

        assertThatThrownBy(() ->
                personService.saveMechanic("asds234asf",
                        "Hans","Mayr",
                        LocalDate.of(2020,12,24),
                        "065089232","123",
                        BigDecimal.ZERO, LocalTime.of(12,24)
                        ,LocalTime.of(10,32)))
                .isInstanceOf(ValidationException.class);
        personService.deleteMechanic(personService.findMechanicById("asds234asf"));
    }


}