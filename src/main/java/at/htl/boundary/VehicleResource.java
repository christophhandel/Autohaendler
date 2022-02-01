package at.htl.boundary;

import at.htl.workloads.person.*;
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

@Path("/vehicle")
public class VehicleResource {
    @Inject
    VehicleService service;

    @Inject
    PersonService personService;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance list(List<Vehicle> vehicles);
        public static native TemplateInstance buy();
        public static native TemplateInstance info(Long vehicleId);
        public static native TemplateInstance sell(List<Vehicle> vehicles, List<Owner> people);
        public static native TemplateInstance rent(List<Vehicle> vehicles, List<Tenant> people);
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

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/sell")
    public TemplateInstance sellVehicle() {
        return Templates.sell(service.findAll(), personService.findAllOwners());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/rent")
    public TemplateInstance rentVehicle() {
        return Templates.rent(service.findAll(), personService.findAllTenants());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/info/{vehicleId}")
    public TemplateInstance infoOnVehicle(@PathParam("vehicleId") Long vehicleId) {
        return Templates.info(vehicleId);
    }


}
