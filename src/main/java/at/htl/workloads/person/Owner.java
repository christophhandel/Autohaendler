package at.htl.workloads.person;

import at.htl.workloads.vehicle.Vehicle;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Owner extends Person{

    //region fields
    @OneToMany
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
