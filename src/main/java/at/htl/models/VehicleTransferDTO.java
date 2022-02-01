package at.htl.models;

import at.htl.workloads.person.Person;
import at.htl.workloads.vehicle.Vehicle;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class VehicleTransferDTO {

    private Long vehicleId;
    private String ownerId;

    public VehicleTransferDTO(Long vehicleId, String ownerId) {
        this.vehicleId = vehicleId;
        this.ownerId = ownerId;
    }

    public VehicleTransferDTO() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
