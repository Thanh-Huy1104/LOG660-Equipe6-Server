package com.equipe6.facade;

import com.equipe6.dao.ClientDAO;
import com.equipe6.dao.LocationDAO;
import com.equipe6.dto.LocationDTO;
import com.equipe6.model.*;
import com.equipe6.util.HibernateUtil;
import jakarta.ws.rs.WebApplicationException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LocationFacade {

    private final LocationDAO locationDAO = new LocationDAO();
    private final ClientDAO clientDAO = new ClientDAO();

    /**
     * Manages the business logic for renting a film.
     *
     * @param filmId   The ID of the film to rent.
     * @param clientId The ID of the client renting the film.
     * @return A DTO of the new location record.
     * @throws Exception if the client is not found, no copies are available, or the rental limit is reached.
     */
    public LocationDTO createLocation(String filmId, String clientId) throws Exception {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // 1. Find client and eagerly fetch related entities
            Client client = clientDAO.findById(clientId, session)
                    .orElseThrow(() -> new WebApplicationException("Client not found.", 404));

            int nombreLocations = client.getLocations().size();
            System.out.println(client.getLocations().toString());
            String codeForfait = client.getForfait().getCodeForfait();
            int limiteLocations;
            long dureeMax;

            // 2. Determine rental limit and duration
            switch (codeForfait) {
                case "D":
                    limiteLocations = 1;
                    dureeMax = 10;
                    break;
                case "I":
                    limiteLocations = 5;
                    dureeMax = 30;
                    break;
                case "A":
                    limiteLocations = 10;
                    dureeMax = -1;
                    break;
                default:
                    throw new WebApplicationException("Unknown forfait type.", 400);
            }

            System.out.println(
                    "Client: " + client.getIdUser() + ", Forfait: " + codeForfait +
                    ", Locations: " + nombreLocations + ", Limit: " + limiteLocations +
                    ", Max Duration: " + dureeMax
            );
            // 3. Check rental limit
            if (nombreLocations >= limiteLocations) {
                throw new WebApplicationException("Forfait limit reached.", 403);
            }

            // 4. Find available copy
            Copie copieDispo = locationDAO.findAvailableCopy(filmId, session)
                    .orElseThrow(() -> new WebApplicationException("No available copy.", 409));

            // 5. Update copy's status
            DomaineCopie statutLouee = session.get(DomaineCopie.class, "louee");
            if (statutLouee == null) {
                throw new IllegalStateException("Le statut 'louee' n'existe pas.");
            }
            copieDispo.setDomaineCopie(statutLouee);

            // 6. Create Location
            Location newLocation = new Location();
            newLocation.setIdLocation(UUID.randomUUID().toString().substring(0, 10));
            newLocation.setClient(client);
            newLocation.setCopie(copieDispo);
            newLocation.setDateDebut(new Date());

            if (dureeMax != -1) {
                newLocation.setDateFin(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(dureeMax)));
            }

            // 7. Save
            locationDAO.save(newLocation, copieDispo, session);

            // 8. Commit
            tx.commit();
            return new LocationDTO(
                    newLocation.getIdLocation(),
                    copieDispo.getCode(),
                    newLocation.getDateDebut().toString(),
                    newLocation.getDateFin() != null ? newLocation.getDateFin().toString() : null
            );

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try {
                    tx.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace(); // Optionnel : loguez la double erreur
                }
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}