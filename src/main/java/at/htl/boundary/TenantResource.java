package at.htl.boundary;

import at.htl.workloads.reparation.Part;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("/tenant")
public class TenantResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance newTenant();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/new")
    public TemplateInstance newTenant() {
        return Templates.newTenant();
    }
}
