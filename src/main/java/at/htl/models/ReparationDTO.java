package at.htl.models;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.vehicle.Vehicle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ReparationDTO {

    private Long id;
    private Long vehicleId;
    private Mechanic mechanicId;
    private LocalDateTime nextAppointment;
    private int duration;

    public ReparationDTO(Long id, Long vehicleId, Mechanic mechanicId, LocalDateTime nextAppointment, int duration) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.mechanicId = mechanicId;
        this.nextAppointment = nextAppointment;
        this.duration = duration;
    }

    public ReparationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Mechanic getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(Mechanic mechanicId) {
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
