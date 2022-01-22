package at.htl.models;

import java.time.LocalDate;
import java.util.List;

public class OwnerDTO extends PersonDTO{
    private List<Long> vehicleIds;

    public OwnerDTO() {

    }
    public OwnerDTO(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Long> vehicleIds) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.vehicleIds = vehicleIds;
    }

    public List<Long> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<Long> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
