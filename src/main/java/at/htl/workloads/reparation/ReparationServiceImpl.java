package at.htl.workloads.reparation;

import com.arjuna.ats.jta.exceptions.NotImplementedException;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReparationServiceImpl implements ReparationService{

    private ReparationRepo reparationRepo;

    @Override
    public List<Reparation> findAll(){
        return this.reparationRepo.getAll();
    }

    @Override
    public Reparation findById(Long id){
        return this.reparationRepo.getById(id);
    }

    @Override
    public void deleteReparation(Reparation reparation){
        this.reparationRepo.deleteReparation(reparation);
    }

    @Override
    public Reparation addReparation(Long vehicleId, Long mechanicId, LocalDateTime nextAppointment, int duration) {
        // TODO: Create reparation and save, throw ValidationException if there is an other reparation with the same mechanic at the same appointment
        // TODO: If mechanic with id does not exist, throw ValidationException
        // TODO: If vehicle with id does not exist, throw ValidationException
        return null;
    }

    @Override
    public Reparation updateReparation(Long reparationId, Long vehicleId, Long mechanicId, LocalDateTime nextAppointment, int duration) {
        // TODO: Find reparation and update. If reparation does not exist, throw ValidationException.
        //  TODO: if there is an other reparation with the same mechanic at the same appointment throw ValidationException
        // TODO: If mechanic with id does not exist, throw ValidationException
        // TODO: If vehicle with id does not exist, throw ValidationException
        return null;
    }
}
