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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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
                .location(URI.create("/api/part/?partType=" + URLEncoder.encode(newPart.getPartId().getPartType(), StandardCharsets.UTF_8)+"&description="
                        + URLEncoder.encode(newPart.getPartId().getDescription(), StandardCharsets.UTF_8))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@QueryParam("partType") String partType,
                             @QueryParam("description") String description) {
        if(partType == null || description==null)
            return Response.status(400).build();
        Part part = reparationService.findPartById(URLDecoder.decode(partType, Charset.defaultCharset()),
                URLDecoder.decode(description, Charset.defaultCharset()));

        if(part == null) {
            return Response.status(404)
                    .entity("Part mit dieser id existiert nicht!").build();
        }

        return Response.ok(part).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response readAll() {
        return Response.ok(reparationService.findAllParts()).build();
    }

    @DELETE
    @Transactional
    public Response delete(@QueryParam("partType") String partType,
                           @QueryParam("description") String description) {
        Part part = reparationService.findPartByType(partType,description);

        if(part == null) {
            return Response.status(404)
                    .entity("Part mit dieser id existiert nicht!").build();
        }

        reparationService.deletePart(part);

        return Response.ok(part).build();
    }

}
