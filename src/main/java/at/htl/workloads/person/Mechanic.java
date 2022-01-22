package at.htl.workloads.person;

import at.htl.workloads.reparation.Reparation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@DiscriminatorValue(value="Mech")
@PrimaryKeyJoinColumn(name = "person_id")
public class Mechanic extends Person {

    //region fields
    @OneToMany @JsonIgnore
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
}
