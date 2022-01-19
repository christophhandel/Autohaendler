package at.htl.workloads.ownership;

import at.htl.workloads.person.Person;
import at.htl.workloads.vehicle.Vehicle;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VehicleTransfer {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Person newOwner;
    private LocalDateTime time;
    //endregion

    //region Constructor
    public VehicleTransfer(Long id, Vehicle vehicle, Person newOwner, LocalDateTime time) {
        this.id = id;
        this.vehicle = vehicle;
        this.newOwner = newOwner;
        this.time = time;
    }

    public VehicleTransfer() {
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

    public Person getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(Person newOwner) {
        this.newOwner = newOwner;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    //endregion
}
