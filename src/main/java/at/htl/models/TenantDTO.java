package at.htl.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TenantDTO extends PersonDTO{
    private double priceDiscountPercent;

    public TenantDTO() {
    }

    public TenantDTO(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Long> rentalIds, double priceDiscountPercent) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.priceDiscountPercent = priceDiscountPercent;
    }

    public double getPriceDiscountPercent() {
        return priceDiscountPercent;
    }

    public void setPriceDiscountPercent(double priceDiscountPercent) {
        this.priceDiscountPercent = priceDiscountPercent;
    }
}
