package at.htl.boundary;

import at.htl.workloads.vehicle.Vehicle;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/error")
public class ErrorResource {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance error(String error);
    }

    @GET
    @Path("/msg={message}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance error(@PathParam("message") String message) {
        return Templates.error(message);
    }
}
