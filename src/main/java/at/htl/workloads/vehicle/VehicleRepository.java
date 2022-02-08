package at.htl.workloads.vehicle;

import at.htl.models.results.PartsUsedInVehicle;
import at.htl.workloads.ownership.Rental;

import java.util.List;

public interface VehicleRepository {

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle findById(Long id);

    void deleteVehicle(Vehicle v);

    List<Vehicle> getAllVehiclesInIdList(List<Long> vehicleIds);

    List<Vehicle> findSoldVehicles();

    List<Rental> findRentalsForVehicleInFuture(Vehicle v);

    List<Vehicle> findOwnedVehicles();

    List<Vehicle> findAllVehicles();

    List<PartsUsedInVehicle> countsThePartsInAVehicle();
}
