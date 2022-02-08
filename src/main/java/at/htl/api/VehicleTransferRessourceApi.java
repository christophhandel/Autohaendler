package at.htl.api;

import at.htl.models.VehicleTransferDTO;
import at.htl.workloads.ownership.RentalService;
import at.htl.workloads.ownership.VehicleTransfer;
import at.htl.workloads.vehicle.Vehicle;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/vehicleTransfer")
public class VehicleTransferRessourceApi {

    @Inject
    RentalService rentalService;
    private final VehicleResourceApi vehicleResourceApi;

    public VehicleTransferRessourceApi(VehicleResourceApi vehicleResourceApi) {
        this.vehicleResourceApi = vehicleResourceApi;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createTransfer(VehicleTransferDTO vehicleTransferDTO){
        VehicleTransfer v = null;
        try {
            v = rentalService.saveTransfer(vehicleTransferDTO.getVehicleId()
                    ,vehicleTransferDTO.getOwnerId()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/vehicleTransfer/"+v.getId()))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTransferById(@PathParam("id") Long id) {
        VehicleTransfer v = rentalService.findTransferById(id);
        if (v == null) {
            return Response.status(404)
                    .entity("Transfer mit dieser id existiert nicht!").build();
        }
        return Response.ok(VehicleTransferDTO.fromVehicleTransfer(v)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(rentalService.findAllTransfers()).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteTransfer(@PathParam("id") Long id) {
        VehicleTransfer vehicleTransfer = rentalService.findTransferById(id);
        if (vehicleTransfer == null)
            return Response.status(404)
                    .entity("Transfer mit dieser id existiert nicht!").build();

        rentalService.deleteTransfer(id);
        return Response.ok(VehicleTransferDTO.fromVehicleTransfer(vehicleTransfer)).build();
    }
}
