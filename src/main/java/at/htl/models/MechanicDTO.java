package at.htl.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MechanicDTO extends PersonDTO{
    private List<Long> reparationIds;
    private BigDecimal pricePerHour;
    private LocalDateTime workStart;
    private LocalDateTime workEnd;

    public MechanicDTO() {
    }

    public MechanicDTO(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Long> reparationIds, BigDecimal pricePerHour, LocalDateTime workStart, LocalDateTime workEnd) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.reparationIds = reparationIds;
        this.pricePerHour = pricePerHour;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public List<Long> getReparationIds() {
        return reparationIds;
    }

    public void setReparationIds(List<Long> reparationIds) {
        this.reparationIds = reparationIds;
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
}
