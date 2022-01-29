package at.htl.boundary;

import at.htl.api.PartResourceApi;
import at.htl.workloads.person.Mechanic;
import at.htl.workloads.reparation.Part;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationService;
import at.htl.workloads.vehicle.Vehicle;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/part")
public class PartResource {
    @Inject
    ReparationService service;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance newPart();
        public static native TemplateInstance list(List<Part> parts);
    }

    @Path("/new")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance newPart() {
        return Templates.newPart();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance list() {
        return Templates.list(service.findAllParts());
    }
}
