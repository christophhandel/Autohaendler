package at.htl.workloads.person;

import at.htl.workloads.reparation.Reparation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Mechanic extends Person {

    //region fields
    @OneToMany
    private List<Reparation> reparations;
    private BigDecimal pricePerHour;
    private LocalDateTime workStart;
    private LocalDateTime workEnd;
    //endregion

    //region Constructor
    public Mechanic(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Reparation> reparations, BigDecimal pricePerHour, LocalDateTime workStart, LocalDateTime workEnd) {
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

    public LocalDateTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDateTime workStart) {
        this.workStart = workStart;
    }

    public LocalDateTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalDateTime workEnd) {
        this.workEnd = workEnd;
    }
    //endregion
}
