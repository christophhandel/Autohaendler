package at.htl.workloads.reparation;

import java.util.List;

public interface ReparationRepo {
    List<Reparation> getAll();

    Reparation getById(Long id);

    void addReparation(Reparation reparation);

    Reparation updateReparation(Reparation reparation);

    void deleteReparation(Reparation reparation);
}
