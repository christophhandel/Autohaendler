package at.htl.api;

import at.htl.models.PartDTO;
import at.htl.models.ReparationDTO;
import at.htl.workloads.reparation.Part;
import at.htl.workloads.reparation.PartId;
import at.htl.workloads.reparation.Reparation;
import at.htl.workloads.reparation.ReparationService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Path("/api/part")
public class PartResourceApi {

    @Inject
    ReparationService reparationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PartDTO partDTO) {

        Part newPart;
        try {
            newPart = reparationService.addPart(partDTO.getPartType(),
                    partDTO.getDescription(), partDTO.getAmountStored());
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/part/partType=" + URLEncoder.encode(newPart.getPartId().getPartType(), StandardCharsets.UTF_8)+"&description="
                        + URLEncoder.encode(newPart.getPartId().getDescription(), StandardCharsets.UTF_8))).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PartDTO partDTO) {
        //TODO

        return Response.noContent().build();
    }

    @GET
    @Path("/partType={partType}&description={description}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("partType") String partType,@PathParam("description") String description) {
        Part part = reparationService.findPartById(partType,description);

        if(part == null) {
            return Response.status(404)
                    .entity("Part mit dieser id existiert nicht!").build();
        }

        return Response.ok(part).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(reparationService.findAllParts()).build();
    }

    @DELETE
    @Transactional
    @Path("/partType={partType}&description={description}")
    public Response delete(@PathParam("partType") String partType,@PathParam("description") String description) {
        Part part = reparationService.findPartByType(partType,description);

        if(part == null) {
            return Response.status(404)
                    .entity("Part mit dieser id existiert nicht!").build();
        }

        reparationService.deletePart(part);

        return Response.ok(part).build();
    }

}
