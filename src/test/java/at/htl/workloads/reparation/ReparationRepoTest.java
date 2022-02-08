package at.htl.workloads.reparation;

import at.htl.IntTestBase;
import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.Owner;
import at.htl.workloads.person.PersonRepository;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleRepository;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@QuarkusTest
class ReparationRepoTest extends IntTestBase {

    @Inject
    ReparationRepo reparationRepo;
    @Inject
    VehicleRepository vehicleRepository;
    @Inject
    PersonRepository personRepository;

    @Test
    void savePartOk() {
        Part p = new Part(new PartId("Reifen","Michelin"),20);

        AtomicReference<Part> mewPart = new AtomicReference<>();
        assertThatCode(()-> mewPart.set(reparationRepo.addPart(p))).doesNotThrowAnyException();
        assertThatCode(() -> reparationRepo.findPartByType(
                mewPart.get().getPartId().getPartType(),
                        mewPart.get().getPartId().getDescription()))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findPartByType(
                mewPart.get().getPartId().getPartType(),
                mewPart.get().getPartId().getDescription())).isNotNull().isEqualTo(mewPart.get());
        assertThatCode(() -> reparationRepo.deletePart(mewPart.get())).doesNotThrowAnyException();
    }

    @Test
    void saveReparation(){
        Reparation reparation = new Reparation(vehicleRepository.findById(1L),
                personRepository.findMechanicById("1234567890"),
                LocalDateTime.of(2022,12,23,1,2),
                7);

        AtomicReference<Reparation> newRep = new AtomicReference<>();
        assertThatCode(()-> newRep.set(reparationRepo.addReparation(reparation)))
                .doesNotThrowAnyException();
        assertThatCode(() -> reparationRepo.findReparationById(newRep.get().getId()))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findReparationById(newRep.get().getId()))
                .isNotNull().isEqualTo(newRep.get());
        assertThatCode(() -> reparationRepo.deleteReparation(newRep.get()))
                .doesNotThrowAnyException();
    }

    @Test
    void saveReplacement(){
        Part part = new Part(new PartId("Reife","Hello"),12);

        Replacement replacement = new Replacement(
                new ReplacementId(part,reparationRepo.findReparationById(1L)),14
        );

        reparationRepo.addPart(part);

        AtomicReference<Replacement> newRep = new AtomicReference<>();
        assertThatCode(()-> newRep.set(reparationRepo.addReplacement(replacement)))
                .doesNotThrowAnyException();
        assertThatCode(() -> reparationRepo.findReplacementById(
                newRep.get().getId().getPart().getPartId().getPartType(),
                newRep.get().getId().getPart().getPartId().getDescription(),
                newRep.get().getId().getReparation().getId()))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findReplacementById(
                newRep.get().getId().getPart().getPartId().getPartType(),
                newRep.get().getId().getPart().getPartId().getDescription(),
                newRep.get().getId().getReparation().getId()))
                .isNotNull()
                .isEqualTo(newRep.get());
        assertThatCode(() -> reparationRepo.deleteReplacement(newRep.get()))
                .doesNotThrowAnyException();
    }

    @Test
    void updatePart(){
        Part p = new Part(new PartId("Reifen","Michelin"),20);

        AtomicReference<Part> newPart = new AtomicReference<>();
        assertThatCode(()-> newPart.set(reparationRepo.addPart(p)))
                .doesNotThrowAnyException();
        Part p1 = newPart.get();
        p1.setAmountStored(20);
        assertThatCode(()-> reparationRepo.updatePart(p))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findPartById("Reifen","Michelin"))
                .hasFieldOrPropertyWithValue("amountStored",20);
        assertThatCode(() -> reparationRepo.deletePart(p1)).doesNotThrowAnyException();
    }

    @Test
    void updateReparation(){
        Reparation r = new Reparation(vehicleRepository.findById(1L),
                personRepository.findMechanicById("1234567890"),
                null,100);

        AtomicReference<Reparation> newRep = new AtomicReference<>();
        assertThatCode(()-> newRep.set(reparationRepo.addReparation(r)))
                .doesNotThrowAnyException();
        Reparation r1 = newRep.get();
        r1.setDuration(20);
        assertThatCode(()-> reparationRepo.updateReparation(r))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findReparationById(newRep.get().getId()))
                .hasFieldOrPropertyWithValue("duration",20);
        assertThatCode(() -> reparationRepo.deleteReparation(r1)).doesNotThrowAnyException();
    }

    @Test
    void updateReplacement(){
        PartId partId = new PartId("Bremsklotz","Continental M&S 275-17R-55");
        Part part = new Part(partId,32);
        ReplacementId replacementId = new ReplacementId(part,reparationRepo.findReparationById(1L));

        Replacement r = new Replacement(replacementId,100);

        AtomicReference<Replacement> newRep = new AtomicReference<>();
        assertThatCode(()-> newRep.set(reparationRepo.addReplacement(r)))
                .doesNotThrowAnyException();
        Replacement r1 = newRep.get();
        r1.setAmount(30);
        assertThatCode(()-> reparationRepo.updateReplacement(r))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findReplacementById(
                newRep.get().getId().getPart().getPartId().getPartType(),
                newRep.get().getId().getPart().getPartId().getDescription(),
                newRep.get().getId().getReparation().getId()))
                .hasFieldOrPropertyWithValue("amount",30);
        assertThatCode(() -> reparationRepo.deleteReplacement(r1))
                .doesNotThrowAnyException();
    }

    @Test
    void findByIdNoExistendPart(){
        assertThatCode(() -> reparationRepo.findPartById("Kerze","Brenner"))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findPartById("Kerze","Brenner")).isNull();
    }

    @Test
    void findByIdNoExistendReparation(){
        assertThatCode(() -> reparationRepo.findReparationById(999L))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findReparationById(999L)).isNull();
    }

    @Test
    void findByIdNoExistendReplacement(){
        assertThatCode(() -> reparationRepo.findReplacementById(
                "Kerze","Brenner",4L))
                .doesNotThrowAnyException();
        assertThat(reparationRepo.findReplacementById(
                "Kerze","Brenner",4L)).isNull();
    }
}