package at.htl.models;

import at.htl.workloads.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Optional;

public class VehicleDTO {

    private String brand;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate constructionYear;
    private int horsePower;
    private int acceleration;
    private Optional<String> ownerId;

    public VehicleDTO() {
        this.ownerId = Optional.empty();
    }

    public VehicleDTO(String brand, LocalDate constructionYear, int horsePower, int acceleration, String ownerId) {
        this.brand = brand;
        this.constructionYear = constructionYear;
        this.horsePower = horsePower;
        this.acceleration = acceleration;
        this.ownerId = Optional.of(ownerId);
    }

    public static VehicleDTO fromVehicle(Vehicle v) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setAcceleration(v.getAcceleration());
        vehicleDTO.setBrand(v.getBrand());
        vehicleDTO.setConstructionYear(v.getConstructionPerYear());
        vehicleDTO.setHorsePower(v.getHorsePower());
        vehicleDTO.setOwnerId(v.getOwner() == null ? null : v.getOwner().getSvNr());
        return vehicleDTO;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(LocalDate constructionYear) {
        this.constructionYear = constructionYear;
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

    public String getOwnerId() {
        return ownerId.orElse(null);
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = Optional.of(ownerId);
    }
}
