package com.equipe6.dao;

import com.equipe6.model.Film;
import com.equipe6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

public class FilmDAO {

    public Film findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery(
                    "SELECT f FROM Film f " +
                            "LEFT JOIN FETCH f.realisateur r " +
                            "LEFT JOIN FETCH f.roles role " +
                            "LEFT JOIN FETCH role.acteur " +
                            "LEFT JOIN FETCH f.scenaristes " +
                            "LEFT JOIN FETCH f.genres " +
                            "LEFT JOIN FETCH f.paysProduction " +
                            "LEFT JOIN FETCH f.bandeAnnonces " +
                            "WHERE f.idFilm = :id", Film.class
            );
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    public List<Film> searchByParams(Map<String, String[]> params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder hql = new StringBuilder("SELECT f FROM Film f ");
            Set<String> joins = new LinkedHashSet<>();
            List<String> conditions = new ArrayList<>();
            Map<String, Object> queryParams = new HashMap<>();

            // Always fetch required relationships else LAZY_LOADING_EXCEPTION
            joins.add("LEFT JOIN FETCH f.realisateur r");
            joins.add("LEFT JOIN FETCH f.roles role");
            joins.add("LEFT JOIN FETCH role.acteur");
            joins.add("LEFT JOIN FETCH f.genres g");
            joins.add("LEFT JOIN FETCH f.paysProduction p");

            // Filters
            if (params.containsKey("titre")) {
                conditions.add("LOWER(f.titre) LIKE :titre");
                queryParams.put("titre", "%" + params.get("titre")[0].toLowerCase() + "%");
            }

            if (params.containsKey("anneeMin")) {
                conditions.add("f.anneeSortie >= :anneeMin");
                queryParams.put("anneeMin", Integer.parseInt(params.get("anneeMin")[0]));
            }

            if (params.containsKey("anneeMax")) {
                conditions.add("f.anneeSortie <= :anneeMax");
                queryParams.put("anneeMax", Integer.parseInt(params.get("anneeMax")[0]));
            }

            if (params.containsKey("langue")) {
                conditions.add("f.langue IN (:langues)");
                queryParams.put("langues", Arrays.asList(params.get("langue")));
            }

            if (params.containsKey("pays")) {
                conditions.add("p.nomPays IN (:pays)");
                queryParams.put("pays", Arrays.asList(params.get("pays")));
            }

            if (params.containsKey("genre")) {
                conditions.add("g.nomGenre IN (:genres)");
                queryParams.put("genres", Arrays.asList(params.get("genre")));
            }

            if (params.containsKey("realisateur")) {
                conditions.add("LOWER(r.nom) LIKE :realisateur");
                queryParams.put("realisateur", "%" + params.get("realisateur")[0].toLowerCase()+ "%");
            }

            if (params.containsKey("acteur")) {
                conditions.add("LOWER(role.acteur.nom) LIKE :acteur");
                queryParams.put("acteur", "%" + params.get("acteur")[0].toLowerCase() + "%");
            }

            for (String join : joins) {
                hql.append(" ").append(join);
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
