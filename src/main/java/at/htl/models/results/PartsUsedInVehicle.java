package at.htl.models.results;

import at.htl.workloads.reparation.Part;
import at.htl.workloads.vehicle.Vehicle;

public class PartsUsedInVehicle {

    Part part;
    Vehicle vehicle;

    //region Constructor
    public PartsUsedInVehicle(Part part, Vehicle vehicle) {
        this.part = part;
        this.vehicle = vehicle;
    }

    public PartsUsedInVehicle() {
    }
    //endregion

    //region Getter and Setter
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    //endregion
}
