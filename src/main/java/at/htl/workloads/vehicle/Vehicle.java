package at.htl.workloads.vehicle;

import at.htl.workloads.person.Owner;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vehicle {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private LocalDateTime constructionPerYear;
    private int horsePower;
    private int acceleration;
    @ManyToOne
    private Owner owner;
    //endregion

    //region Constructor
    public Vehicle(String brand, LocalDateTime constructionPerYear, int horsePower, int acceleration, Owner owner) {
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

    public LocalDateTime getConstructionPerYear() {
        return constructionPerYear;
    }

    public void setConstructionPerYear(LocalDateTime constructionPerYear) {
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
