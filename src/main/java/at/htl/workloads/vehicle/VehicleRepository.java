package at.htl.workloads.vehicle;

import at.htl.workloads.ownership.Rental;

import java.util.List;

public interface VehicleRepository {

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle findById(Long id);

    List<Vehicle> listAll();

    void deleteVehicle(Vehicle v);

    List<Vehicle> getAllVehiclesInIdList(List<Long> vehicleIds);

    List<Vehicle> findSoldVehicles();

    List<Rental> findRentalsForVehicleInFuture(Vehicle v);
}
