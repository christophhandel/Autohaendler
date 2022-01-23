package at.htl.workloads.person;

import at.htl.workloads.ownership.Rental;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue(value="Ten")
@PrimaryKeyJoinColumn(name = "person_id")
public class Tenant extends Person{

    //region fields
    @OneToMany(mappedBy = "tenant")
    private List<Rental> rentals;
    private double priceDiscountPercent;
    //endregion

    //region Constructor
    public Tenant(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Rental> rentals, double priceDiscountPercent) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.rentals = rentals;
        this.priceDiscountPercent = priceDiscountPercent;
    }

    public Tenant() {
    }
    //endregion

    //region Getter and Setter
    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public double getPriceDiscountPercent() {
        return priceDiscountPercent;
    }

    public void setPriceDiscountPercent(double priceDiscountPercent) {
        this.priceDiscountPercent = priceDiscountPercent;
    }
    //endregion
}
