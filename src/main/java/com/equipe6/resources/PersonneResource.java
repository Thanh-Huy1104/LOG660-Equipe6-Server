package com.equipe6.resources;

import com.equipe6.dto.PersonneDTO;
import com.equipe6.facade.PersonneFacade;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/personnes")
@Produces(MediaType.APPLICATION_JSON)
public class PersonneResource {

    private final PersonneFacade personneFacade = new PersonneFacade();

    @GET
    @Path("/{id}")
    public Response getPersonneById(@PathParam("id") String id) {
        PersonneDTO dto = personneFacade.getPersonneById(id);
        return (dto != null)
                ? Response.ok(dto).build()
                : Response.status(Response.Status.NOT_FOUND).entity("Personne introuvable").build();
    }

    // Optional: health check
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "PersonneResource is live.";
    }
}
