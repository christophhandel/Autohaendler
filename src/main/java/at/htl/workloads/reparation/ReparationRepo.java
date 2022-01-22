package at.htl.workloads.reparation;

import java.util.List;

public interface ReparationRepo {
    List<Reparation> getAll();

    Reparation getById(Long id);

    void addReparation(Reparation reparation);

    Reparation updateReparation(Reparation reparation);

    void deleteReparation(Reparation reparation);

    /**
     * @param reparationIds
     * @return List of reparations with reparationIds or null if one of the ids does not exist
     */
    List<Reparation> getByIds(List<Long> reparationIds);
}
