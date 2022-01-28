package at.htl.workloads.ownership;

import java.util.List;

public interface RentalRepo {
    Rental saveRental(Rental r);
    Rental updateMechanic(Rental r);

    VehicleTransfer saveTransfer(VehicleTransfer v);
    void deleteVehicle(VehicleTransfer v);
    List<VehicleTransfer> findAllTransfers();
    VehicleTransfer findTransferById(Long id);


    void deleteRental(Rental r);

    Rental findRentalById(Long id);

    List<Rental> findAllRentals();
}
