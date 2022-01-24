package at.htl.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerDTO extends PersonDTO{
    private Optional<List<Long>> vehicleIds;

    public OwnerDTO() {
        vehicleIds = Optional.of(new ArrayList<>());
    }
    public OwnerDTO(String svNr, String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String driverLicenceNumber, List<Long> vehicleIds) {
        super(svNr, firstName, lastName, dateOfBirth, phoneNumber, driverLicenceNumber);
        this.vehicleIds = Optional.of(vehicleIds);
    }

    public List<Long> getVehicleIds() {
        return vehicleIds.orElse(null);
    }

    public void setVehicleIds(List<Long> vehicleIds) {
        this.vehicleIds = Optional.of(vehicleIds);
    }
}
