package at.htl.models;

import java.time.LocalDate;
import java.util.List;

public class TenantDTO extends PersonDTO{
    private List<Long> rentalIds;
    private double priceDiscountPercent;

    public TenantDTO() {
    }

    public TenantDTO(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Long> rentalIds, double priceDiscountPercent) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.rentalIds = rentalIds;
        this.priceDiscountPercent = priceDiscountPercent;
    }

    public List<Long> getRentalIds() {
        return rentalIds;
    }

    public void setRentalIds(List<Long> rentalIds) {
        this.rentalIds = rentalIds;
    }

    public double getPriceDiscountPercent() {
        return priceDiscountPercent;
    }

    public void setPriceDiscountPercent(double priceDiscountPercent) {
        this.priceDiscountPercent = priceDiscountPercent;
    }
}
