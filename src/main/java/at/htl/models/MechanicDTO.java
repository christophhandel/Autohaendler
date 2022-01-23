package at.htl.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class MechanicDTO extends PersonDTO{
    private BigDecimal pricePerHour;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime workStart;
    private LocalTime workEnd;

    public MechanicDTO(String svNr, String firstName, String lastName, LocalDate dateOfBirth,
                       String phoneNumber, String driverLicenceNumber, List<Long> reparationIds,
                       BigDecimal pricePerHour, LocalTime workStart, LocalTime workEnd) {

        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.pricePerHour = pricePerHour;
        this.workStart = workStart;
        this.workEnd = workEnd;
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
}
