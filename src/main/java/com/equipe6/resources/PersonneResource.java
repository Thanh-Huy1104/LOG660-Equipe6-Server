package com.equipe6.resources;

import com.equipe6.dao.PersonneDAO;
import com.equipe6.dto.PersonneDTO;
import com.equipe6.facade.PersonneFacade;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/personnes")
@Produces(MediaType.APPLICATION_JSON)
public class PersonneResource {

    private final PersonneFacade personneFacade;

    public PersonneResource() {
        this.personneFacade = new PersonneFacade(new PersonneDAO());
    }

    public PersonneResource(PersonneFacade personneFacade) {
        this.personneFacade = personneFacade;
    }

    @GET
    @Path("/{id}")
    public Response getPersonneById(@PathParam("id") String id) {
        PersonneDTO dto = personneFacade.getPersonneById(id);
        return (dto != null)
                ? Response.ok(dto).build()
                : Response.status(Response.Status.NOT_FOUND).entity("Personne introuvable").build();
    }

    @GET
    @Path("/name/{name}")
    public Response getPersonneByName(@PathParam("name") String name) {
        PersonneDTO dto = personneFacade.getPersonneByName(name);
        return (dto != null)
                ? Response.ok(dto).build()
                : Response.status(Response.Status.NOT_FOUND).entity("Personne non trouvée par nom").build();
    }

    // Optional: health check
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "PersonneResource is live.";
    }
}
