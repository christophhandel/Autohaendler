package at.htl.api;

import at.htl.models.ReparationDTO;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/reparation")
public class ReparationResourceApi {
    @Inject
    ReparationService reparationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ReparationDTO reparationDTO) {
        Reparation r = new Reparation();
        try {
            r = reparationService.addReparation(
                    reparationDTO.getVehicleId(),
                    reparationDTO.getMechanicId(),
                    reparationDTO.getNextAppointment(),
                    reparationDTO.getDuration()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/reparation/"+r.getId()))
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ReparationDTO reparationDTO) {
        Reparation r = null;
        try {
            r = reparationService.updateReparation(
                    id,
                    reparationDTO.getVehicleId(),
                    reparationDTO.getMechanicId(),
                    reparationDTO.getNextAppointment(),
                    reparationDTO.getDuration()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.ok(r).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("id") Long id) {
        Reparation r = reparationService.findReparationById(id);

        if(r == null) {
            return Response.status(404)
                    .entity("Reparation mit dieser id existiert nicht!").build();
        }

        return Response.ok(r).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(reparationService.findAllReparations()).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Reparation r = reparationService.findReparationById(id);

        if(r == null) {
            return Response.status(404)
                    .entity("Reparation mit dieser id existiert nicht!").build();
        }

        reparationService.deleteReparation(r);

        return Response.ok(r).build();
    }

}
