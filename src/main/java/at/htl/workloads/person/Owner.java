package at.htl.workloads.person;

import at.htl.workloads.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue(value="Own")
@PrimaryKeyJoinColumn(name = "person_id")
public class Owner extends Person{

    //region fields
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL) @JsonIgnore
    private List<Vehicle> vehicles;
    //endregion

    //region Constructor
    public Owner(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Vehicle> vehicles) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.vehicles = vehicles;
    }

    public Owner() {
    }
    //endregion

    //region Getter and Setter
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    //endregion

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(vehicles, owner.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vehicles);
    }
    //endregion
}
