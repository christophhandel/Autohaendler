package at.htl.workloads.reparation;

import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.person.Mechanic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reparation {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Mechanic mechanic;
    private LocalDateTime nextAppointment;
    private int duration;
    @OneToMany(mappedBy = "id.reparation") @JsonIgnore
    private List<Replacement> replacements;
    //endregion

    //region Constructor
    public Reparation(Long id, Vehicle vehicle, Mechanic mechanic, LocalDateTime nextAppointment, int duration) {
        this.id = id;
        this.vehicle = vehicle;
        this.mechanic = mechanic;
        this.nextAppointment = nextAppointment;
        this.duration = duration;
    }

    public Reparation() {
    }
    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
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

    public List<Replacement> getReplacements() {
        return replacements;
    }

    public void setReplacements(List<Replacement> replacements) {
        this.replacements = replacements;
    }
    //endregion
}
