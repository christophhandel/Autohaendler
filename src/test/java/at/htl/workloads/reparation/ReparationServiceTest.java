package at.htl.workloads.reparation;

import at.htl.IntTestBase;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;

import java.math.BigDecimal;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ReparationServiceTest extends IntTestBase {

    @Inject
    ReparationService reparationService;


    @Test
    void createReparationOk(){
        assertThatCode(() ->
                reparationService.addReparation(1L,"asfsdg23",
                        LocalDateTime.now(),100))
                .doesNotThrowAnyException();

        assertThat(reparationService.findReparationById(1L)).isNotNull();
        reparationService.deleteReparation(reparationService.findReparationById(1L));
    }

    @Test
    void createReparationWrongDuration(){
        assertThatThrownBy(() ->
                reparationService.addReparation(1L,"asfsdg23",
                        LocalDateTime.now(),-2))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createReparationWrongAppointment(){
        assertThatThrownBy(() ->
                reparationService.addReparation(1L,"asfsdg23",
                        LocalDateTime.of(2020,11,05,23,23),200))
                .isInstanceOf(ValidationException.class);
    }

}