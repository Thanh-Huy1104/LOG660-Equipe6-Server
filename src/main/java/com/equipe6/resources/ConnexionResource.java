package com.equipe6.resources;

import com.equipe6.model.Client;
import com.equipe6.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

@Path("/auth")
public class ConnexionResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @FormParam("email") String email,
            @FormParam("password") String password
    ) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery(
                    "SELECT c FROM Client c JOIN c.utilisateur u WHERE u.courriel = :email AND u.motDePasse = :pwd",
                    Client.class
            );
            query.setParameter("email", email);
            query.setParameter("pwd", password);
            Client client = query.uniqueResult();

            if (client != null) {
                return Response.ok(client).build(); // return JSON object of client
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid email or password")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal server error").build();
        }
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "ConnexionResource is live.";
    }
}
