package at.htl.workloads.reparation;

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

    Replacement findReplacementById(String partType, String partDescription, Long reparationId);

    void deleteReplacement(Replacement replacement);

    Replacement addReplacement(Replacement r);

    Part findPartById(String partType, String partDescription);

    Replacement updateReplacement(Replacement r);
}
