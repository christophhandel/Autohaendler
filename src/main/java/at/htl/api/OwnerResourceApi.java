package at.htl.api;

import at.htl.models.MechanicDTO;
import at.htl.models.OwnerDTO;
import at.htl.models.TenantDTO;
import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.Owner;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.person.Tenant;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/owner")
public class OwnerResourceApi {

    private final PersonService personService;

    @Inject
    public OwnerResourceApi(PersonService personService) {
        this.personService = personService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(OwnerDTO ownerDTO) {
        Owner owner = null;
        try {
            owner = personService.saveOwner(
                    ownerDTO.getSvNr(),
                    ownerDTO.getFirstName(),
                    ownerDTO.getLastName(),
                    ownerDTO.getDateOfBirth(),
                    ownerDTO.getPhoneNumber(),
                    ownerDTO.getDriverLicenceNumber());

        } catch (ValidationException e) {
            return Response.status(400, e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/owner/"+owner.getSvNr()))
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{svNr}")
    public Response update(@PathParam("svNr") String svNr, OwnerDTO ownerDTO) {
        Owner owner = null;
        try {
            owner = personService.updateOwner(
                    svNr,
                    ownerDTO.getSvNr(),
                    ownerDTO.getFirstName(),
                    ownerDTO.getLastName(),
                    ownerDTO.getDateOfBirth(),
                    ownerDTO.getPhoneNumber(),
                    ownerDTO.getDriverLicenceNumber(),
                    ownerDTO.getVehicleIds());
        } catch (ValidationException e) {
            return Response.status(400, e.getMessage()).build();
        }

        return Response.ok(owner).build();
    }

    @GET
    @Path("/{svNr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("svNr") String svNr) {
        Owner owner = personService.findOwnerById(svNr);

        if(owner == null) {
            return Response.noContent().build();
        }

        return Response.ok(owner).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(personService.findAllOwners()).build();
    }

    @DELETE
    @Transactional
    @Path("/{svNr}")
    public Response delete(@PathParam("svNr") String svNr) {
        Owner owner = personService.findOwnerById(svNr);

        if(owner == null) {
            return Response.noContent().build();
        }

        personService.deleteOwner(owner);

        return Response.ok(owner).build();
    }
}
