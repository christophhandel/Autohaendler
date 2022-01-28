package at.htl.boundary;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/replacement")
public class ReplacementResource {
    @Inject
    ReparationService reparationService;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance add(List<Reparation> reparations, List<Part> parts, Long reparationId);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/add")
    public TemplateInstance newReparation() {
        return Templates.add(reparationService.findAllReparations(), reparationService.findAllParts(), null);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/add/{reparationId}")
    public TemplateInstance newReparationForID(@PathParam("reparationId") Long id) {
        return Templates.add(reparationService.findAllReparations(), reparationService.findAllParts(), id);
    }
}
