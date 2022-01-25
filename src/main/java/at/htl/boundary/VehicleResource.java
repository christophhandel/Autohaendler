package at.htl.boundary;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vehicle")
public class VehicleResource {
    @Inject
    VehicleService service;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance list(List<Vehicle> vehicles);
        public static native TemplateInstance buy();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/list")
    public TemplateInstance listMechanics() {
        return Templates.list(service.findAll());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/buy")
    public TemplateInstance buyVehicle() {
        return Templates.buy();
    }


}
