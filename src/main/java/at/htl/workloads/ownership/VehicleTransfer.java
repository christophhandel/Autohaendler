package at.htl.workloads.ownership;

import at.htl.workloads.person.Person;
import at.htl.workloads.vehicle.Vehicle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class VehicleTransfer {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vehicle vehicle;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Person newOwner;
    private LocalDateTime time;
    //endregion

    //region Constructor
    public VehicleTransfer(Vehicle vehicle, Person newOwner, LocalDateTime time) {
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

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleTransfer that = (VehicleTransfer) o;
        return Objects.equals(id, that.id) && Objects.equals(vehicle, that.vehicle) && Objects.equals(newOwner, that.newOwner) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicle, newOwner, time);
    }
    //endregion
}