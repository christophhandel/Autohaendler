package at.htl.workloads.vehicle;

import at.htl.workloads.person.Mechanic;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

public interface VehicleService {
    /*
    If owner with ownerId does not exist, throw ValidateException with a message
     */
    Vehicle saveVehicle(String brand,
                        LocalDateTime constructionPerYear,
                        int horsePower,
                        int acceleration,
                        Long ownerId) throws ValidationException;

    Vehicle updateVehicle(Long id,
                          String brand,
                          LocalDateTime constructionPerYear,
                          int horsePower,
                          int acceleration,
                          Long ownerId) throws ValidationException;

    Vehicle findById(Long id);

    List<Vehicle> findAll();

    void delete(Vehicle v);
}
