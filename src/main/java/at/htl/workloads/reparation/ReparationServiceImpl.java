package at.htl.workloads.reparation;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ReparationServiceImpl implements ReparationService{

    private ReparationRepo reparationRepo;

    @Override
    public List<Reparation> getAll(){
        return this.reparationRepo.getAll();
    }

    @Override
    public Reparation getById(Long id){
        return this.reparationRepo.getById(id);
    }

    @Override
    public void addReparation(Reparation reparation) {
        this.reparationRepo.addReparation(reparation);
    }

    @Override
    public Reparation updateReparation(Reparation reparation){
        return this.reparationRepo.updateReparation(reparation);
    }

    @Override
    public void deleteReparation(Reparation reparation){
        this.reparationRepo.deleteReparation(reparation);
    }
}
