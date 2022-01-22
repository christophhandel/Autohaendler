package at.htl.boundary;

import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/mechanic")
public class MechanicResource {
    @Inject
    PersonService service;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance list(List<Mechanic> mechanicList);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/list")
    public TemplateInstance listMechanics() {
        return Templates.list(service.findAllMechanics());
    }
}
