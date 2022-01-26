package at.htl.workloads.ownership;

import java.util.List;

public interface RentalRepo {
    Rental saveRental(Rental r);

    Rental updateMechanic(Rental r);

    void deleteRental(Rental r);

    Rental findRentalById(Long id);

    List<Rental> findAllRentals();
}
