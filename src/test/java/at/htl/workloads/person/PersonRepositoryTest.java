package at.htl.workloads.person;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@QuarkusTest
class PersonRepositoryTest {

    @Inject
    PersonRepository personRepository;

    @Test
    void saveMechanicOk() {
        Mechanic m = new Mechanic("asds234asf",
                "Hans","Mayr",
                LocalDate.of(2008,12,24),
                "065089232","123",new ArrayList<>(),
                BigDecimal.ONE, LocalTime.of(8,24),LocalTime.of(10,32));

        AtomicReference<Mechanic> newM = null;
        assertThatCode(()-> newM.set(personRepository.saveMechanic(m))).doesNotThrowAnyException();
    }


    @Test
    void saveOwnerOk() {
        Owner owner = new Owner("sdgdfh45","Lisa","Haus",
                LocalDate.of(2008,12,24)
                ,"057839585","5645",new ArrayList<>());
        AtomicReference<Owner> newOwner = null;
        assertThatCode(() -> newOwner.set(personRepository.saveOwner(owner))).doesNotThrowAnyException();
    }

    @Test
    void saveTenantOk() {
        Tenant tenant = new Tenant("sdgdfh45","Lisa","Haus",
                LocalDate.of(2008,12,24)
                ,"057839585","5645",new ArrayList<>()
                ,12.45);
        AtomicReference<Tenant> newTenant = null;
        assertThatCode(() -> newTenant.set(personRepository.saveTanant(tenant))).doesNotThrowAnyException();
    }


}