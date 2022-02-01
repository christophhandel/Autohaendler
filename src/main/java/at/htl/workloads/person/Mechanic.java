package at.htl.workloads.person;

import at.htl.workloads.reparation.Reparation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue(value="Mech")
@PrimaryKeyJoinColumn(name = "person_id")
public class Mechanic extends Person {

    //region fields
    @OneToMany(mappedBy = "mechanic") @JsonIgnore
    private List<Reparation> reparations;
    private BigDecimal pricePerHour;
    private LocalTime workStart;
    private LocalTime workEnd;
    //endregion

    //region Constructor
    public Mechanic(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Reparation> reparations, BigDecimal pricePerHour, LocalTime workStart, LocalTime workEnd) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.reparations = reparations;
        this.pricePerHour = pricePerHour;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Mechanic() {
    }
    //endregion

    //region Getter and Setter
    public List<Reparation> getReparations() {
        return reparations;
    }

    public void setReparations(List<Reparation> reparations) {
        this.reparations = reparations;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public LocalTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalTime workStart) {
        this.workStart = workStart;
    }

    public LocalTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalTime workEnd) {
        this.workEnd = workEnd;
    }
    //endregion

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mechanic mechanic = (Mechanic) o;
        return Objects.equals(reparations, mechanic.reparations) && Objects.equals(pricePerHour, mechanic.pricePerHour) && Objects.equals(workStart, mechanic.workStart) && Objects.equals(workEnd, mechanic.workEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reparations, pricePerHour, workStart, workEnd);
    }
    //endregion
}
