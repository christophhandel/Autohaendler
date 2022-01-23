package at.htl.workloads.vehicle;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class VehicleServiceImpl implements VehicleService{
    // TODO: Add VehicleRepository and write methods.

    @Override
    public Vehicle saveVehicle(String brand, LocalDateTime constructionPerYear, int horsePower, int acceleration, Long ownerId) throws ValidationException {
        return null;
    }

    @Override
    public Vehicle updateVehicle(Long id, String brand, LocalDateTime constructionPerYear, int horsePower, int acceleration, Long ownerId) throws ValidationException {
        return null;
    }

    @Override
    public Vehicle findById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public void delete(Vehicle v) {

    }
}
