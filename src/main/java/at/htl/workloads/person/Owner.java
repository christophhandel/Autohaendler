package at.htl.workloads.person;

import at.htl.workloads.vehicle.Vehicle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue(value="Own")
@PrimaryKeyJoinColumn(name = "person_id")
public class Owner extends Person{

    //region fields
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
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
}
