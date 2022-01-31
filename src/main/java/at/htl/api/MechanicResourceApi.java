package at.htl.api;

import at.htl.models.MechanicDTO;
import at.htl.workloads.person.Mechanic;
import at.htl.workloads.person.PersonService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/mechanic")
public class MechanicResourceApi {
    private final PersonService personService;

    @Inject
    public MechanicResourceApi(PersonService personService) {
        this.personService = personService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(MechanicDTO mechanicDTO) {
        Mechanic m = null;
        try {
            m = personService.saveMechanic(mechanicDTO.getSvNr(),
                    mechanicDTO.getFirstName(),
                    mechanicDTO.getLastName(),
                    mechanicDTO.getDateOfBirth(),
                    mechanicDTO.getPhoneNumber(),
                    mechanicDTO.getDriverLicenceNumber(),
                    mechanicDTO.getPricePerHour(),
                    mechanicDTO.getWorkStart(),
                    mechanicDTO.getWorkEnd());
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.status(301)
                .location(URI.create("/api/mechanic/"+m.getSvNr()))
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{svNr}")
    public Response update(@PathParam("svNr") String svNr, MechanicDTO mechanicDTO) {
        Mechanic m = null;
        try {
            m = personService.updateMechanic(
                    svNr,
                    mechanicDTO.getSvNr(),
                    mechanicDTO.getFirstName(),
                    mechanicDTO.getLastName(),
                    mechanicDTO.getDateOfBirth(),
                    mechanicDTO.getPhoneNumber(),
                    mechanicDTO.getDriverLicenceNumber(),
                    mechanicDTO.getPricePerHour(),
                    mechanicDTO.getWorkStart(),
                    mechanicDTO.getWorkEnd()
            );
        } catch (ValidationException e) {
            return Response.status(422)
                    .entity(e.getMessage()).build();
        }

        return Response.ok(m).build();
    }

    @GET
    @Path("/{svNr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("svNr") String svNr) {
        Mechanic m = personService.findMechanicById(svNr);

        if(m == null) {
            return Response.status(404)
                    .entity("Mechaniker mit dieser SvNr existiert nicht!").build();
        }

        return Response.ok(m).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        return Response.ok(personService.findAllMechanics()).build();
    }

    @DELETE
    @Transactional
    @Path("/{svNr}")
    public Response delete(@PathParam("svNr") String svNr) {
        Mechanic m = personService.findMechanicById(svNr);

        if(m == null) {
            return Response.status(404)
                    .entity("Mechaniker mit dieser SvNr existiert nicht!").build();
        }

        personService.deleteMechanic(m);

        return Response.ok(m).build();
    }
}
