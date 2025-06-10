package com.equipe6.dao;

import com.equipe6.model.Client;
import com.equipe6.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class ClientDAO {
    public Client findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT c FROM Client c " +
                            "LEFT JOIN FETCH c.utilisateur u " +
                            "WHERE c.idUser = :id", Client.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public List<Client> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT c FROM Client c " +
                            "LEFT JOIN FETCH c.utilisateur u " +
                            "LEFT JOIN FETCH c.domaineCarteCredit " +
                            "LEFT JOIN FETCH c.forfait " +
                            "LEFT JOIN FETCH u.employeProfil", // Charge aussi l'employé pour éviter N+1
                    Client.class
            ).list();
        }
    }

    public Optional<Client> findById(String clientId, Session session) {
        Client client = session.createQuery(
                        "SELECT c FROM Client c " +
                                "LEFT JOIN FETCH c.forfait " +
                                "LEFT JOIN FETCH c.locations " +
                                "WHERE c.idUser = :clientId", Client.class)
                .setParameter("clientId", clientId)
                .uniqueResult();

        return Optional.ofNullable(client);
    }
}
