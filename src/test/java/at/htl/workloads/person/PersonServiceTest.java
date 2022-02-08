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

    @Test
    void createOwnerOk(){
        assertThatCode(() ->
                personService.saveOwner("sdgdfh47","Lisa","Haus",
                        LocalDate.of(2008,12,24)
                        ,"057839585","5645"))
                .doesNotThrowAnyException();

        assertThat(personService.findOwnerById("sdgdfh47")).isNotNull();
        personService.deleteOwner(personService.findOwnerById("sdgdfh47"));
    }

    @Test
    void createOwnerWrongDob(){
        assertThatThrownBy(() ->
                personService.saveOwner("sdgdfh45","Lisa","Haus",
                        LocalDate.of(2025,12,24)
                        ,"057839585","5645"))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createTenantOk(){
        assertThatCode(() ->
                personService.saveTenant("sdgdfh46","Lisa","Haus",
                        LocalDate.of(2008,12,24)
                        ,"057839585","5645",16.86))
                .doesNotThrowAnyException();

        assertThat(personService.findTenantById("sdgdfh46")).isNotNull();
        personService.deleteTenant(personService.findTenantById("sdgdfh46"));
    }

    @Test
    void createTenantWrongDob(){
        assertThatThrownBy(() ->
                personService.saveTenant("sdgdfh45","Lisa","Haus",
                        LocalDate.of(2025,12,24)
                        ,"057839585","5645",34.12))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createTenantWongPriceDiscountPercent(){
        assertThatThrownBy(() ->
                personService.saveTenant("sdgdfh45","Lisa","Haus",
                        LocalDate.of(2008,12,24)
                        ,"057839585","5645",-1.2))
                .isInstanceOf(ValidationException.class);
    }

}