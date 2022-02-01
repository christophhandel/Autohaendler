package at.htl.boundary;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationService;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/reparation")
public class ReparationResource {
    @Inject
    VehicleService vehicleService;

    @Inject
    ReparationService reparationService;

    @Inject
    PersonService personService;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance newReparation(List<Vehicle> vehicles, List<Mechanic> mechanics);
        public static native TemplateInstance reparation(Long reparationId);
        public static native TemplateInstance list(List<Reparation> reparations);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/new")
    public TemplateInstance newReparation() {
        return Templates.newReparation(vehicleService.findAll(), personService.findAllMechanics());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/info/{id}")
    public TemplateInstance reparationInfo(@PathParam("id") Long id) {
        return Templates.reparation(id);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/list")
    public TemplateInstance list() {
        return Templates.list(reparationService.findAllReparations());
    }


}
