package at.htl.workloads.vehicle;

import at.htl.workloads.ownership.Rental;
import at.htl.workloads.person.Owner;
import at.htl.workloads.reparation.Reparation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vehicle {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private LocalDate constructionPerYear;
    private int horsePower;
    private int acceleration;
    @ManyToOne
    private Owner owner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle") @JsonIgnore
    List<Reparation> reparations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    List<Rental> rentals;
    //endregion

    //region Constructor
    public Vehicle(String brand, LocalDate constructionPerYear, int horsePower, int acceleration, Owner owner) {
        this.brand = brand;
        this.constructionPerYear = constructionPerYear;
        this.horsePower = horsePower;
        this.acceleration = acceleration;
        this.owner = owner;
    }

    public Vehicle() {
    }
    //endregion

    //region Getter and Setter


    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Reparation> getReparations() {
        return reparations;
    }

    public void setReparations(List<Reparation> reparations) {
        this.reparations = reparations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getConstructionPerYear() {
        return constructionPerYear;
    }

    public void setConstructionPerYear(LocalDate constructionPerYear) {
        this.constructionPerYear = constructionPerYear;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    //endregion
}
