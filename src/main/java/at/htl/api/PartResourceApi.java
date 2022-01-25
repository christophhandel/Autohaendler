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

@Path("/api/part")
public class PartResourceApi {

    @Inject
    ReparationService reparationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PartDTO partDTO) {
        Part newPart = new Part();
        newPart.getPartId().setPartType(partDTO.getPartType());
        newPart.getPartId().setDescription(partDTO.getDescription());
        newPart.setAmountStored(partDTO.getAmountStored());
        newPart = reparationService.addPart(newPart);

        return Response.status(301)
                .location(URI.create("/api/part/"+newPart.getPartId())).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PartDTO partDTO) {
        //TODO

        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("id") Long id) {
        Part part = reparationService.findPartById(id);

        if(part == null) {
            return Response.noContent().build();
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
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Part part = reparationService.findPartById(id);

        if(part == null) {
            return Response.noContent().build();
        }

        reparationService.deletePart(part);

        return Response.ok(part).build();
    }

}
