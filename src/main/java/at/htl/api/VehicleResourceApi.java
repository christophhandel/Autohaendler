package at.htl.api;

import at.htl.models.VehicleDTO;
import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.vehicle.VehicleService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.net.URI;

@Path("/api/vehicle")
public class VehicleResourceApi {
    private final VehicleService vehicleService;

    @Inject
    public VehicleResourceApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(VehicleDTO vehicleDTO) {
        Vehicle v = null;
        try {
            v = vehicleService.saveVehicle(
                    vehicleDTO.getBrand(),
                    vehicleDTO.getConstructionYear(),
                    vehicleDTO.getHorsePower(),
                    vehicleDTO.getAcceleration(),
                    vehicleDTO.getOwnerId()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/vehicle/"+v.getId()))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, VehicleDTO vehicleDTO) {
        Vehicle v = null;
        try {
            v = vehicleService.updateVehicle(
                    id,
                    vehicleDTO.getBrand(),
                    vehicleDTO.getConstructionYear(),
                    vehicleDTO.getHorsePower(),
                    vehicleDTO.getAcceleration(),
                    vehicleDTO.getOwnerId()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.ok(v).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("id") Long id) {
        Vehicle v = vehicleService.findById(id);

        if(v == null) {
            return Response.status(404)
                    .entity("KFZ mit dieser id existiert nicht!").build();
        }

        return Response.ok(v).build();
    }

    @GET
    @Path("/futureRentals/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response futureRentalsFor(@PathParam("id") Long id) {
        Vehicle v = vehicleService.findById(id);

        if(v == null) {
            return Response.status(404)
                    .entity("KFZ mit dieser id existiert nicht!").build();
        }

        return Response.ok(vehicleService.getFutureRentals(v)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(vehicleService.findAll()).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Vehicle v = vehicleService.findById(id);

        if(v == null) {
            return Response.status(404)
                    .entity("KFZ mit dieser id existiert nicht!").build();
        }

        vehicleService.delete(v);

        return Response.ok(v).build();
    }
}
