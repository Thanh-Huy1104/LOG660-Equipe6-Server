package com.equipe6.dao;

import com.equipe6.model.Personne;
import com.equipe6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PersonneDAO {

    public Personne findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Personne> query = session.createQuery(
                    "SELECT p FROM Personne p " +
                            "WHERE p.idPersonne = :id", Personne.class
            );
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }
}
