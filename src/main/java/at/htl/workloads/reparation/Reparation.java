package at.htl.workloads.reparation;

import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.person.Mechanic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    @OneToMany(mappedBy = "id.reparation", cascade = CascadeType.ALL)
    private List<Replacement> replacements;
    //endregion

    //region Constructor
    public Reparation( Vehicle vehicle, Mechanic mechanic, LocalDateTime nextAppointment, int duration) {
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

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reparation that = (Reparation) o;
        return duration == that.duration && Objects.equals(id, that.id) && Objects.equals(vehicle, that.vehicle) && Objects.equals(mechanic, that.mechanic) && Objects.equals(nextAppointment, that.nextAppointment) && Objects.equals(replacements, that.replacements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicle, mechanic, nextAppointment, duration, replacements);
    }
    //endregion
}
