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
                        LocalDateTime.of(2020,11,5,23,23),200))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createReplacementOk(){
        assertThatCode(() ->
                reparationService.addReplacement("Bremsklotz", "Continental M&S 275-17R-55"
                        ,5L,8))
                .doesNotThrowAnyException();

        assertThat(reparationService.findReplacementById("Bremsklotz",
                "Continental M&S 275-17R-55"
                ,5L)).isNotNull();
        reparationService.deleteReplacement(reparationService
                .findReplacementById("Bremsklotz", "Continental M&S 275-17R-55"
                ,5L));
    }

    @Test
    void createReplacementWrongAmount(){
        assertThatThrownBy(() ->
                reparationService.addReplacement("Reifen","Pirelli Scorpion Verde",
                        4L,-2))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void createPartOk(){
        assertThatCode(() ->
                reparationService.addPart(
                        "Continental M&S 275-17R-55",
                        "Bremsklotz",
                        10))
                .doesNotThrowAnyException();

        assertThat(reparationService.findPartById(
                "Continental M&S 275-17R-55",
                "Bremsklotz")).isNotNull();
        reparationService.deletePart(reparationService.findPartById(
                "Continental M&S 275-17R-55",
                "Bremsklotz"
        ));

    }

    @Test
    void createPartWrongAmount(){
        assertThatThrownBy(() ->
                reparationService.addPart("Reifen","Pirelli Scorpion Verde",
                        -4))
                .isInstanceOf(ValidationException.class);
    }
}