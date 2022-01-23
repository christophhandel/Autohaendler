package at.htl.models;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ReparationDTO {

    private Long vehicleId;
    private String mechanicId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime nextAppointment;
    private int duration;

    public ReparationDTO( Long vehicleId, String mechanicId, LocalDateTime nextAppointment, int duration) {
        this.vehicleId = vehicleId;
        this.mechanicId = mechanicId;
        this.nextAppointment = nextAppointment;
        this.duration = duration;
    }

    public ReparationDTO() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
        this.mechanicId = mechanicId;
    }

    public LocalDateTime getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(LocalDateTime nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
