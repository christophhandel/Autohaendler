package at.htl.boundary;

import at.htl.models.results.IncomePerPerson;
import at.htl.workloads.person.PersonRepository;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/owner")
public class OwnerResource {
    @Inject
    PersonRepository repository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance newOwner();
        public static native TemplateInstance incomePerPerson(List<IncomePerPerson> incomePerPersonList);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/new")
    public TemplateInstance newOwner() {
        return Templates.newOwner();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/incomePerPerson")
    public TemplateInstance getIncomePerPerson() {
        return Templates.incomePerPerson(repository.calculateIncomePerOwner());
    }
}
