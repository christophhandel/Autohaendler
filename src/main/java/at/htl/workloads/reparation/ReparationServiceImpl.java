package at.htl.workloads.reparation;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReparationServiceImpl implements ReparationService{

    private ReparationRepo reparationRepo;

    private VehicleService vehicleService;

    private PersonService personService;

    @Inject
    public ReparationServiceImpl(ReparationRepo reparationRepo, VehicleService vehicleService, PersonService personService) {
        this.reparationRepo = reparationRepo;
        this.vehicleService = vehicleService;
        this.personService = personService;
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
    public Reparation findReparationByMechanicIDAndAppointment(String mechanicId, LocalDateTime nextAppointment){
        return this.reparationRepo.findReparationByMechanicIDAndAppointment(mechanicId,nextAppointment);
    }

    @Override
    public void deleteReparation(Reparation reparation){
        this.reparationRepo.deleteReparation(reparation);
    }

    @Override
    public Reparation addReparation(Long vehicleId, String mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException {

        Mechanic mechanic=personService.findMechanicById(mechanicId);
        Vehicle vehicle=vehicleService.findById(vehicleId);

        if (reparationRepo.findReparationByMechanicIDAndAppointment(mechanicId,nextAppointment) == null)
            throw new ValidationException("Der Mechaniker kann an diesem Datum nicht reserviert werden!");

        Reparation reparation= new Reparation(vehicle,mechanic,nextAppointment,duration);
        return reparationRepo.addReparation(reparation);
    }

    @Override
    public Reparation updateReparation(Long reparationId, Long vehicleId, String mechanicId, LocalDateTime nextAppointment, int duration)
            throws ValidationException {

        Reparation reparation = reparationRepo.findReparationById(reparationId);
        if (reparation == null)
            throw new ValidationException("Diese Reperation existiert nicht!");
        if (reparationRepo.findReparationByMechanicIDAndAppointment(mechanicId,nextAppointment) == null)
            throw new ValidationException("Der Mechaniker kann an diesem Datum nicht reserviert werden!");

        Mechanic mechanic = personService.findMechanicById(mechanicId);
        if (mechanic == null)
            throw new ValidationException("Der Mechaniker existiert nicht!");

        Vehicle vehicle = vehicleService.findById(vehicleId);
        if (vehicle == null)
            throw new ValidationException("Das Vehicle existiert nicht!");

        reparation.setNextAppointment(nextAppointment);
        reparation.setVehicle(vehicle);
        reparation.setMechanic(mechanic);
        reparation.setDuration(duration);
        return reparationRepo.updateReparation(reparation);
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
