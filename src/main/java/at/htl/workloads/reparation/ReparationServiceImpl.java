package at.htl.workloads.reparation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReparationServiceImpl implements ReparationService{

    private ReparationRepo reparationRepo;

    @Inject
    public ReparationServiceImpl(ReparationRepo reparationRepo) {
        this.reparationRepo = reparationRepo;
    }

    @Override
    public List<Reparation> findAllReparations(){
        return this.reparationRepo.findAllReparations();
    }

    @Override
    public Reparation findReparationById(Long id){
        return this.reparationRepo.findReparationById(id);
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

    @Override
    public List<Replacement> findAllReplacements() {
        return reparationRepo.findAllReplacements();
    }

    @Override
    public Replacement findReplacementById(String partType, String partDescription, Long reparationId) {
        return reparationRepo.findReplacementById( partType,  partDescription,  reparationId);
    }

    @Override
    public void deleteReplacement(Replacement replacement) {
        reparationRepo.deleteReplacement(replacement);
    }

    @Override
    public Replacement addReplacement(String partType, String partDescription, Long reparationId, int amount)
            throws ValidationException {
        ReplacementId rId = new ReplacementId(findPartById(partType, partDescription), findReparationById(reparationId));

        if(rId.getReparation() == null ||rId.getPart() == null)
            throw new ValidationException("Nonexistent Replacement!");

        Replacement r = new Replacement(rId, amount);
        return reparationRepo.addReplacement(r);
    }

    public Part findPartById(String partType, String partDescription) {
        return reparationRepo.findPartById(partType, partDescription);
    }

    @Override
    public Replacement updateReplacement(String partType, String partDescription, Long reparationId, int amount) throws ValidationException {
        Replacement r = findReplacementById(partType, partDescription, reparationId);

        r.setAmount(amount);

        return  reparationRepo.updateReplacement(r);
    }
}
