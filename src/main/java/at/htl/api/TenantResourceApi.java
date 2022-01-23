package at.htl.api;

import at.htl.models.MechanicDTO;
import at.htl.models.TenantDTO;
import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;
import at.htl.workloads.person.Tenant;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/tenant")
public class TenantResourceApi {
    private final PersonService personService;

    @Inject
    public TenantResourceApi(PersonService personService) {
        this.personService = personService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(TenantDTO tenantDTO) {
        Tenant t = null;
        try {
            t = personService.saveTenant(tenantDTO.getSvNr(),
                    tenantDTO.getFirstName(),
                    tenantDTO.getLastName(),
                    tenantDTO.getDateOfBirth(),
                    tenantDTO.getPhoneNumber(),
                    tenantDTO.getDriverLicenceNumber(),
                    tenantDTO.getPriceDiscountPercent());
        } catch (ValidationException e) {
            return Response.status(400, e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/mechanic/"+t.getSvNr()))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{svNr}")
    public Response update(@PathParam("svNr") String svNr, TenantDTO tenantDTO) {
        Tenant t = null;
        try {
            t = personService.updateTenant(
                    svNr,
                    tenantDTO.getSvNr(),
                    tenantDTO.getFirstName(),
                    tenantDTO.getLastName(),
                    tenantDTO.getDateOfBirth(),
                    tenantDTO.getPhoneNumber(),
                    tenantDTO.getDriverLicenceNumber(),
                    tenantDTO.getPriceDiscountPercent(),
                    tenantDTO.getRentalIds()
            );
        } catch (ValidationException e) {
            return Response.status(400, e.getMessage()).build();
        }

        return Response.ok(t).build();
    }

    @GET
    @Path("/{svNr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("svNr") String svNr) {
        Tenant t = personService.findTenantById(svNr);

        if(t == null) {
            return Response.noContent().build();
        }

        return Response.ok(t).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(personService.findAllTenants()).build();
    }

    @DELETE
    @Transactional
    @Path("/{svNr}")
    public Response delete(@PathParam("svNr") String svNr) {
        Tenant t = personService.findTenantById(svNr);

        if(t == null) {
            return Response.noContent().build();
        }

        personService.deleteTenant(t);

        return Response.ok(t).build();
    }
}
