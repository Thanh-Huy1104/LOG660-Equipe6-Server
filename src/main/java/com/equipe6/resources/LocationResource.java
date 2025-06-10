package com.equipe6.resources;

import com.equipe6.model.*;
import com.equipe6.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Path("/locations")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {



    @POST
    public Response louerFilm(
            @FormParam("filmId") String filmId,
            @FormParam("clientId") String clientId
    ) {
        if (filmId == null || clientId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("filmId and clientId are required.")
                    .build();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Fetch client
            Client client = session.get(Client.class, clientId);
            if (client == null) {
                tx.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Client not found.").build();
            }

            // Check available copy
            Query<Copie> copieQuery = session.createQuery(
                    "FROM Copie c WHERE c.film.idFilm = :filmId AND c.domaineCopie.etat = 'disponible'",
                    Copie.class
            );
            copieQuery.setParameter("filmId", filmId);
            Copie copieDispo = copieQuery.setMaxResults(1).uniqueResult();

            if (copieDispo == null) {
                tx.rollback();
                return Response.status(Response.Status.CONFLICT)
                        .entity("No available copy of the film.").build();
            }

            // Check forfait limit
            Set<Location> locations = client.getLocations();
            if (locations != null && locations.size() >= 3) {
                tx.rollback();
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("Forfait limit reached.").build();
            }

            // Create Location
            Location location = new Location();
            location.setIdLocation(UUID.randomUUID().toString().substring(0, 10));
            location.setClient(client);
            location.setCopie(copieDispo);
            location.setDateDebut(new Date());

            Date dateFin = new Date(System.currentTimeMillis() + 7L * 24 * 3600 * 1000);
            location.setDateFin(dateFin);

            session.persist(location);

            // Update copy status
            DomaineCopie statutLouee = session.get(DomaineCopie.class, "louee");
            copieDispo.setDomaineCopie(statutLouee);
            session.persist(copieDispo);

            tx.commit();

            return Response.ok(location).build(); // return location JSON
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("Erreur lors de la location.").build();
        }
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "LocationResource is live.";
    }
}