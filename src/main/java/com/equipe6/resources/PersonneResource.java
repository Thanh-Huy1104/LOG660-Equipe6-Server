package com.equipe6.resources;

import com.equipe6.model.Personne;
import com.equipe6.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.hibernate.Session;

@Path("/personnes")
@Produces(MediaType.APPLICATION_JSON)
public class PersonneResource {

    @GET
    @Path("/{id}")
    public Response getPersonneById(@PathParam("id") String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Personne personne = session.get(Personne.class, id);
            if (personne != null) {
                return Response.ok(personne).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Personne introuvable")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("Erreur lors de la récupération de la personne")
                    .build();
        }
    }

    // Optional: health check
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "PersonneResource is live.";
    }
}
