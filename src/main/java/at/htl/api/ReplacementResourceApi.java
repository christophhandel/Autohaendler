package at.htl.api;

import at.htl.models.ReplacementDTO;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationService;
import at.htl.workloads.reparation.Replacement;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/replacement")
public class ReplacementResourceApi {
    private final ReparationService reparationService;

    @Inject
    public ReplacementResourceApi(ReparationService reparationService) {
        this.reparationService = reparationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ReplacementDTO replacementDTO) {
        Replacement r = null;
        try {
            r = reparationService.addReplacement(
                    replacementDTO.getPartType(),
                    replacementDTO.getPartDescription(),
                    replacementDTO.getReparationId(),
                    replacementDTO.getAmount()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/replacement/"+replacementDTO.getReparationId()))
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/")
    public Response update(ReplacementDTO replacementDTO) {
        Replacement r = null;
        try {
            r = reparationService.updateReplacement(
                    replacementDTO.getPartType(),
                    replacementDTO.getPartDescription(),
                    replacementDTO.getReparationId(),
                    replacementDTO.getAmount()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.ok(r).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(ReplacementDTO replacementDTO) {
        Replacement r = reparationService.findReplacementById(replacementDTO.getPartType(),
                replacementDTO.getPartDescription(),
                replacementDTO.getReparationId());

        if(r == null) {
            return Response.status(404)
                    .entity("Reparation mit dieser id existiert nicht!").build();
        }

        return Response.ok(r).build();
    }

    @GET
    @Path("/{reparationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readByReparationId(@PathParam("reparationId") Long id) {
        Reparation r = reparationService.findReparationById(id);

        if(r == null) {
            return Response.status(404)
                    .entity("Reparation mit dieser id existiert nicht!").build();
        }

        return Response.ok(r.getReplacements()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response readAll() {
        return Response.ok(reparationService.findAllReplacements()).build();
    }

    @DELETE
    @Transactional
    @Path("/")
    public Response delete(ReplacementDTO replacementDTO) {
        Replacement r = reparationService.findReplacementById(replacementDTO.getPartType(),
                replacementDTO.getPartDescription(),
                replacementDTO.getReparationId());

        if(r == null) {
            return Response.status(404)
                    .entity("Reparation mit dieser id existiert nicht!").build();
        }

        reparationService.deleteReplacement(r);

        return Response.ok(r).build();
    }
}
