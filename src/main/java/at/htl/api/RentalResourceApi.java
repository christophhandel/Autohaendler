package at.htl.api;

import at.htl.models.RentalDTO;
import at.htl.workloads.ownership.Rental;
import at.htl.workloads.ownership.RentalService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/rental")
public class RentalResourceApi {

    private final RentalService rentalservice;

    @Inject
    public RentalResourceApi(RentalService rentalservice) {
        this.rentalservice = rentalservice;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(RentalDTO rentalDTO) {
        Rental r = null;
        try {
            r = rentalservice.saveRental(rentalDTO.getVehicleId(),
                    rentalDTO.getTenantId(),
                    rentalDTO.getFrom(),
                    rentalDTO.getTo());
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/rental/"+r.getId()))
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RentalDTO rentalDTO) throws ValidationException {
        Rental r = null;
        r = rentalservice.updateRental(id,rentalDTO.getVehicleId(),
                rentalDTO.getTenantId(),
                rentalDTO.getFrom(),
                rentalDTO.getTo());

        return Response.ok(r).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("id") Long id) {
        Rental r = rentalservice.findRentalById(id);

        if(r == null) {
            return Response.status(404)
                    .entity("Rental mit dieser id existiert nicht!").build();
        }

        return Response.ok(r).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(rentalservice.findAllRentals()).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Rental r = rentalservice.findRentalById(id);

        if(r == null) {
            return Response.status(404)
                    .entity("Rental mit dieser id existiert nicht!").build();
        }

        rentalservice.deleteRental(r);

        return Response.ok(r).build();
    }
}
