package at.htl.models;

import java.time.LocalDateTime;

public class VehicleDTO {

    private String brand;
    private LocalDateTime constructionPerYear;
    private int horsePower;
    private int acceleration;
    private Long ownerId;

    public VehicleDTO() {
    }

    public VehicleDTO(String brand, LocalDateTime constructionPerYear, int horsePower, int acceleration, Long ownerId) {
        this.brand = brand;
        this.constructionPerYear = constructionPerYear;
        this.horsePower = horsePower;
        this.acceleration = acceleration;
        this.ownerId = ownerId;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
