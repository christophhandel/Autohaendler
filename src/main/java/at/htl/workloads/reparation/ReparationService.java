package at.htl.workloads.reparation;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

public interface ReparationService {
    List<Reparation> findAllReparations();

    Reparation findReparationById(Long id);

    void deleteReparation(Reparation reparation);

    Reparation addReparation(Long vehicleId, Long mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException;

    Reparation updateReparation(Long id, Long vehicleId, Long mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException;

    List<Replacement> findAllReplacements();

    Replacement findReplacementById(String partType, String partDescription, Long reparationId);

    void deleteReplacement(Replacement replacement);

    Replacement addReplacement(String partType, String partDescription, Long reparationId, int amount)
            throws ValidationException;

    Replacement updateReplacement(String partType, String partDescription, Long reparationId, int amount)
            throws ValidationException;

    Part findPartById(String partType, String partDescription);
}
