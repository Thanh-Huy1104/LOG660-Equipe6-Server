package com.equipe6.service;

import com.equipe6.model.Film;
import com.equipe6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

public class FilmServiceImpl implements FilmService {

    @Override
    public Film getFilmById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Film.class, id);
        }
    }

    @Override
    public List<Film> rechercherFilms(Map<String, String[]> params) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StringBuilder hql = new StringBuilder("SELECT DISTINCT f FROM Film f ");
            Set<String> joins = new HashSet<>();
            List<String> conditions = new ArrayList<>();
            Map<String, Object> queryParams = new HashMap<>();

            // Titre
            if (params.containsKey("titre")) {
                conditions.add("LOWER(f.titre) LIKE :titre");
                queryParams.put("titre", "%" + params.get("titre")[0].toLowerCase() + "%");
            }

            // Année min / max
            if (params.containsKey("anneeMin")) {
                conditions.add("f.annee >= :anneeMin");
                queryParams.put("anneeMin", Integer.parseInt(params.get("anneeMin")[0]));
            }
            if (params.containsKey("anneeMax")) {
                conditions.add("f.annee <= :anneeMax");
                queryParams.put("anneeMax", Integer.parseInt(params.get("anneeMax")[0]));
            }

            // Langue
            if (params.containsKey("langue")) {
                String[] langues = params.get("langue");
                conditions.add("f.langue IN (:langues)");
                queryParams.put("langues", Arrays.asList(langues));
            }

            // Pays de production
            if (params.containsKey("pays")) {
                joins.add("JOIN f.paysProductions pp");
                String[] pays = params.get("pays");
                conditions.add("pp.nomPays IN (:pays)");
                queryParams.put("pays", Arrays.asList(pays));
            }

            // Genres
            if (params.containsKey("genre")) {
                joins.add("JOIN f.genres fg");
                String[] genres = params.get("genre");
                conditions.add("fg.nomGenre IN (:genres)");
                queryParams.put("genres", Arrays.asList(genres));
            }

//            // Always fetch realisateur
            joins.add("JOIN FETCH f.realisateur r");
//
//            if (params.containsKey("realisateur")) {
//                conditions.add("LOWER(r.nom) LIKE :realisateur");
//                queryParams.put("realisateur", "%" + params.get("realisateur")[0].toLowerCase() + "%");
//            }

//            // Acteurs (nom partiel, plusieurs)
//            if (params.containsKey("acteur")) {
//                joins.add("JOIN f.roles role JOIN role.personne acteur");
//                String[] acteurs = params.get("acteur");
//                List<String> acteurConditions = new ArrayList<>();
//                for (int i = 0; i < acteurs.length; i++) {
//                    String key = "acteur" + i;
//                    acteurConditions.add("LOWER(acteur.nom) LIKE :" + key);
//                    queryParams.put(key, "%" + acteurs[i].toLowerCase() + "%");
//                }
//                conditions.add("(" + String.join(" OR ", acteurConditions) + ")");
//            }

            // Final HQL
            for (String join : joins) {
                hql.append(" ").append(join).append(" ");
            }
            if (!conditions.isEmpty()) {
                hql.append(" WHERE ").append(String.join(" AND ", conditions));
            }

            Query<Film> query = session.createQuery(hql.toString(), Film.class);
            // Loop over all query parameters
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                // Differentiate between single values and collections (List or Set)
                if (entry.getValue() instanceof Collection) {
                    // If the value is a collection, use setParameterList
                    // Example:
                    // "f.langue IN (:langues)" → setParameterList("langues", List.of("Français", "Anglais"))
                    query.setParameterList(entry.getKey(), (Collection<?>) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }

            return query.list();
        }
    }
}
