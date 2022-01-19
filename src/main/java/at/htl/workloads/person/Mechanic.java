package at.htl.workloads.person;

import at.htl.workloads.reparation.Reparation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Mechanic extends Person {

    //region fields
    @ManyToOne
    private List<Reparation> reperations;
    private BigDecimal pricePerHour;
    private LocalDateTime workStart;
    private LocalDateTime workEnd;
    //endregion

    //region Constructor
    public Mechanic(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Reparation> reperations, BigDecimal pricePerHour, LocalDateTime workStart, LocalDateTime workEnd) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.reperations = reperations;
        this.pricePerHour = pricePerHour;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Mechanic() {
    }
    //endregion

    //region Getter and Setter
    public List<Reparation> getReperations() {
        return reperations;
    }

    public void setReperations(List<Reparation> reperations) {
        this.reperations = reperations;
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
