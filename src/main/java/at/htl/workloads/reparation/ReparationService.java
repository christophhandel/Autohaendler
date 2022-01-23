package at.htl.workloads.reparation;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

public interface ReparationService {
    List<Reparation> findAll();

    Reparation findById(Long id);

    void deleteReparation(Reparation reparation);

    Reparation addReparation(Long vehicleId, Long mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException;

    Reparation updateReparation(Long id, Long vehicleId, Long mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException;
}
