package at.htl.workloads.reparation;

import at.htl.IntTestBase;
import at.htl.workloads.person.Mechanic;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ReparationRepoTest extends IntTestBase {

    @Inject
    ReparationRepo reparationRepo;

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
                mewPart.get().getPartId().getDescription())).isNotNull();
        assertThatCode(() -> reparationRepo.deletePart(mewPart.get())).doesNotThrowAnyException();
    }

}