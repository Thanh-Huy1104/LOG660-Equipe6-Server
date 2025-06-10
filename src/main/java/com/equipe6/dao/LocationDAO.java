package com.equipe6.dao;

import com.equipe6.model.Copie;
import com.equipe6.model.DomaineCopie;
import com.equipe6.model.Location;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class LocationDAO {

    /**
     * Finds the first available copy of a specific film using a provided session.
     * This query is now case-insensitive for the copy's status.
     *
     * @param filmId  The ID of the film to find a copy for.
     * @param session The active Hibernate session passed from the facade.
     * @return An Optional containing an available Copie if found, otherwise empty.
     */
    public Optional<Copie> findAvailableCopy(String filmId, Session session) {
        // Using LOWER() on the status makes the comparison case-insensitive.
        Query<Copie> query = session.createQuery(
                "FROM Copie c JOIN FETCH c.domaineCopie dc WHERE c.film.idFilm = :filmId AND LOWER(dc.etat) = 'disponible'",
                Copie.class
        );
        query.setParameter("filmId", filmId);
        return query.setMaxResults(1).uniqueResultOptional();
    }

    /**
     * Saves a new Location record and updates the status of the rented copy.
     * This version relies on Hibernate's automatic dirty checking.
     *
     * @param location The new Location object to be persisted.
     * @param copyToUpdate The attached Copie object whose status needs to be updated.
     * @param session The active Hibernate session passed from the facade.
     */
    public void save(Location location, Copie copyToUpdate, Session session) {
        // Fetch the 'louee' status within the same session.
        DomaineCopie statutLouee = session.get(DomaineCopie.class, "louee");
        if (statutLouee == null) {
            throw new IllegalStateException("Le statut 'louee' n'existe pas dans la table DomaineCopie.");
        }

        // Modify the attached 'copyToUpdate' object. Hibernate will automatically
        // detect this change and generate an UPDATE statement upon transaction commit.
        copyToUpdate.setDomaineCopie(statutLouee);

        // Persist the new location record.
        session.persist(location);
    }
}
