package at.htl.workloads.reparation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReparationRepo {

    List<Reparation> findAllReparations();

    Reparation findReparationById(Long id);

    Reparation addReparation(Reparation reparation);

    Reparation updateReparation(Reparation reparation);

    void deleteReparation(Reparation reparation);

    /**
     * @param reparationIds
     * @return List of reparations with reparationIds or null if one of the ids does not exist
     */
    List<Reparation> findReparationsByIds(List<Long> reparationIds);

    List<Replacement> findAllReplacements();

    Reparation findReparationByMechanicIDAndAppointment(String mechanicId, LocalDateTime nextAppointment);

    Replacement findReplacementById(String partType, String partDescription, Long reparationId);

    void deleteReplacement(Replacement replacement);

    Replacement addReplacement(Replacement r);

    Replacement updateReplacement(Replacement r);


    List<Part> findAllParts();

    Part findPartByType(String partType, String partDescription);

    Part addPart(Part part);

    Part updatePart(Part part);

    void deletePart(Part part);

    Part findPartById(String partType, String description);
}
