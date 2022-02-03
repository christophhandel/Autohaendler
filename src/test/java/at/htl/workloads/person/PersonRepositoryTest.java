package at.htl.workloads.person;

import at.htl.IntTestBase;
import at.htl.models.results.IncomePerPerson;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.vehicle.Vehicle;
import com.google.common.math.BigDecimalMath;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.Convert;
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
class PersonRepositoryTest extends IntTestBase {

    @Inject
    PersonRepository personRepository;

    @Test
    void saveMechanicOk() {
        Mechanic m = new Mechanic("asds234asf",
                "Hans","Mayr",
                LocalDate.of(2008,12,24),
                "065089232","123",new ArrayList<>(),
                BigDecimal.ONE, LocalTime.of(8,24),LocalTime.of(10,32));

        AtomicReference<Mechanic> newM = new AtomicReference<>();
        assertThatCode(()-> newM.set(personRepository.saveMechanic(m))).doesNotThrowAnyException();
        assertThatCode(() -> personRepository.findMechanicById(newM.get().getSvNr()))
                .doesNotThrowAnyException();
        assertThat(personRepository.findMechanicById(newM.get().getSvNr())).isNotNull();
        assertThatCode(() -> personRepository.deleteMechanic(newM.get())).doesNotThrowAnyException();
    }

    @Test
    void saveOwnerOk() {
        Owner owner = new Owner("sdgdfh45","Lisa","Haus",
                LocalDate.of(2008,12,24)
                ,"057839585","5645",new ArrayList<>());
        AtomicReference<Owner> newOwner= new AtomicReference<>();
        assertThatCode(() -> newOwner.set(personRepository.saveOwner(owner))).doesNotThrowAnyException();
        assertThatCode(() -> personRepository.findOwnerById(newOwner.get().getSvNr()))
                .doesNotThrowAnyException();
        assertThat(personRepository.findOwnerById(newOwner.get().getSvNr())).isNotNull();
        assertThatCode(() -> personRepository.deleteOwner(newOwner.get())).doesNotThrowAnyException();
    }

    @Test
    void saveTenantOk() {
        Tenant tenant = new Tenant("sdgdfh45","Lisa","Haus",
                LocalDate.of(2008,12,24)
                ,"057839585","5645",new ArrayList<>()
                ,12.45);
        AtomicReference<Tenant> newTenant= new AtomicReference<>();
        assertThatCode(() -> newTenant.set(personRepository.saveTanant(tenant))).doesNotThrowAnyException();
        assertThatCode(() -> personRepository.findTenantById(newTenant.get().getSvNr()))
                .doesNotThrowAnyException();
        assertThat(personRepository.findTenantById(newTenant.get().getSvNr())).isNotNull();
        assertThatCode(() -> personRepository.deleteTenant(newTenant.get())).doesNotThrowAnyException();
    }

    @Test
    void updateMechanic(){
        Mechanic m = new Mechanic("asds234asf",
                "Hans","Mayr",
                LocalDate.of(2008,12,24),
                "065089232","123",new ArrayList<>(),
                BigDecimal.ONE, LocalTime.of(8,24),LocalTime.of(10,32));

        AtomicReference<Mechanic> newM = new AtomicReference<>();
        assertThatCode(()-> newM.set(personRepository.saveMechanic(m))).doesNotThrowAnyException();
        Mechanic m1 = newM.get();
        m1.setPricePerHour(BigDecimal.TEN);
        assertThatCode(()-> personRepository.updateMechanic(m)).doesNotThrowAnyException();
        assertThat(personRepository.findMechanicById(m.getSvNr()))
                .hasFieldOrPropertyWithValue("pricePerHour",BigDecimal.TEN);
        assertThatCode(() -> personRepository.deleteMechanic(m1)).doesNotThrowAnyException();
    }

    @Test
    void updateOwner(){
        Owner owner = new Owner("sdgdfh45","Lisa","Haus",
                LocalDate.of(2008,12,24)
                ,"057839585","5645",new ArrayList<>());
        AtomicReference<Owner> newOwner= new AtomicReference<>();
        assertThatCode(() -> newOwner.set(personRepository.saveOwner(owner))).doesNotThrowAnyException();
        Owner o1 = newOwner.get();
        o1.setDriverLicenceNumber("124342");
        assertThatCode(()-> personRepository.updateOwner(o1)).doesNotThrowAnyException();
        assertThat(personRepository.findOwnerById(o1.getSvNr()))
                .hasFieldOrPropertyWithValue("driverLicenceNumber","124342");
        assertThatCode(() -> personRepository.deleteOwner(o1)).doesNotThrowAnyException();
    }

    @Test
    void updateTenant(){
        Tenant tenant = new Tenant("sdgdfh45","Lisa","Haus",
                LocalDate.of(2008,12,24)
                ,"057839585","5645",new ArrayList<>()
                ,12.45);
        AtomicReference<Tenant> newTenant= new AtomicReference<>();
        assertThatCode(() -> newTenant.set(personRepository.saveTanant(tenant))).doesNotThrowAnyException();

        Tenant tenant1 =  newTenant.get();
        tenant1.setPriceDiscountPercent(15.567);
        assertThatCode(()-> personRepository.updateTenant(tenant1)).doesNotThrowAnyException();
        assertThat(personRepository.findTenantById(tenant1.getSvNr()))
                .hasFieldOrPropertyWithValue("priceDiscountPercent",15.567);
        assertThatCode(() -> personRepository.deleteTenant(tenant1)).doesNotThrowAnyException();
    }

    @Test
    void findByIdNoExistendOwner(){
        assertThatCode(() -> personRepository.findOwnerById("1"))
                .doesNotThrowAnyException();
        assertThat(personRepository.findOwnerById("1")).isNull();
    }

    @Test
    void findByIdNoExistendMechanic(){
        assertThatCode(() -> personRepository.findMechanicById("1"))
                .doesNotThrowAnyException();
        assertThat(personRepository.findMechanicById("1")).isNull();
    }

    @Test
    void findByIdNoExistendTenant(){
        assertThatCode(() -> personRepository.findTenantById("1"))
                .doesNotThrowAnyException();
        assertThat(personRepository.findTenantById("1")).isNull();
    }

    @Test
    void testCalculateIncomePerTenant() {

        assertThatCode(() -> personRepository.calculateIncomePerOwner())
                .doesNotThrowAnyException();

        List<IncomePerPerson> tmpIncome = personRepository.calculateIncomePerOwner();
        assertThat(tmpIncome).isNotNull();
        assertThat(tmpIncome.size()).isEqualTo(personRepository.findAllOwners().size());

        BigDecimal curIncome = null;
        for (IncomePerPerson tmp : tmpIncome){
            Owner o = (Owner) tmp.getPerson();
            for (Vehicle v : o.getVehicles()) {
                for (Reparation rep : v.getReparations()) {
                    curIncome = rep.getMechanic().getPricePerHour().multiply(BigDecimal.valueOf(rep.getDuration()));
                }
            }
            assertThat(tmp.getIncome()).isNotNull().isEqualTo(curIncome);
        }
    }
}