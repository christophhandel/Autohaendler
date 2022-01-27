package at.htl.workloads.reparation;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

public interface ReparationService {
    List<Reparation> findAllReparations();

    Reparation findReparationById(Long id);

    void deleteReparation(Reparation reparation);

    Reparation addReparation(Long vehicleId, String mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException;

    Reparation updateReparation(Long id, Long vehicleId, String mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException;

    List<Replacement> findAllReplacements();

    Replacement findReplacementById(String partType, String partDescription, Long reparationId);

    void deleteReplacement(Replacement replacement);

    Replacement addReplacement(String partType, String partDescription, Long reparationId, int amount)
            throws ValidationException;

    Replacement updateReplacement(String partType, String partDescription, Long reparationId, int amount)
            throws ValidationException;

    Reparation findReparationByMechanicIDAndAppointment(String mechanicId, LocalDateTime nextAppointment);

    List<Part> findAllParts();

    Part findPartByType(String partType, String partDescription);

    Part updatePart(Part part);

    void deletePart(Part part);

    Part addPart(String partType, String description, int amountStored) throws ValidationException;

    Part findPartById(String partType, String description);
}
