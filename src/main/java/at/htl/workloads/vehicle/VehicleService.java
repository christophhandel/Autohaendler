package at.htl.workloads.vehicle;

import at.htl.workloads.person.Mechanic;

import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VehicleService {
    /*
    If owner with ownerId does not exist, throw ValidateException with a message
     */
    
    Vehicle saveVehicle(String brand,
                        LocalDate constructionPerYear,
                        int horsePower,
                        int acceleration,
                        String ownerId) throws ValidationException;

    Vehicle updateVehicle(Long id,
                          String brand,
                          LocalDate constructionPerYear,
                          int horsePower,
                          int acceleration,
                          String ownerId) throws ValidationException;

    Vehicle findById(Long id);

    List<Vehicle> findAll();

    void delete(Vehicle v);

    List<Vehicle> findWithIds(List<Long> vehicleIds);
}
