package at.htl.boundary;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/owner")
public class OwnerResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance newOwner();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/new")
    public TemplateInstance newOwner() {
        return Templates.newOwner();
    }
}
