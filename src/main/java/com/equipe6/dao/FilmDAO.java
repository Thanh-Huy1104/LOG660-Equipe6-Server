package com.equipe6.dao;

import com.equipe6.model.Film;
import com.equipe6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class FilmDAO {

    public Film findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Film.class, id);
        }
    }

    public void save(Film film) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if (film.getIdFilm() == null) {
                session.persist(film); // Insert new entity
            } else {
                session.merge(film);   // Update existing (detached) entity
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void delete(Film film) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Film managedFilm = session.merge(film); // Ensure attached
            session.remove(managedFilm);            // Delete

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public List<Film> searchByParams(Map<String, String[]> params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder hql = new StringBuilder("SELECT DISTINCT f FROM Film f ");
            Set<String> joins = new HashSet<>();
            List<String> conditions = new ArrayList<>();
            Map<String, Object> queryParams = new HashMap<>();

            // Filters
            if (params.containsKey("titre")) {
                conditions.add("LOWER(f.titre) LIKE :titre");
                queryParams.put("titre", "%" + params.get("titre")[0].toLowerCase() + "%");
            }

            if (params.containsKey("anneeMin")) {
                conditions.add("f.annee >= :anneeMin");
                queryParams.put("anneeMin", Integer.parseInt(params.get("anneeMin")[0]));
            }

            if (params.containsKey("anneeMax")) {
                conditions.add("f.annee <= :anneeMax");
                queryParams.put("anneeMax", Integer.parseInt(params.get("anneeMax")[0]));
            }

            if (params.containsKey("langue")) {
                conditions.add("f.langue IN (:langues)");
                queryParams.put("langues", Arrays.asList(params.get("langue")));
            }

            if (params.containsKey("pays")) {
                joins.add("JOIN f.paysProductions pp");
                conditions.add("pp.nomPays IN (:pays)");
                queryParams.put("pays", Arrays.asList(params.get("pays")));
            }

            if (params.containsKey("genre")) {
                joins.add("JOIN f.genres fg");
                conditions.add("fg.nomGenre IN (:genres)");
                queryParams.put("genres", Arrays.asList(params.get("genre")));
            }

            // Always fetch the realisateur
            joins.add("JOIN FETCH f.realisateur r");

            // Compose final HQL
            for (String join : joins) {
                hql.append(" ").append(join).append(" ");
            }

            if (!conditions.isEmpty()) {
                hql.append(" WHERE ").append(String.join(" AND ", conditions));
            }

            Query<Film> query = session.createQuery(hql.toString(), Film.class);

            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                if (entry.getValue() instanceof Collection) {
                    query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }

            return query.list();
        }
    }
}
