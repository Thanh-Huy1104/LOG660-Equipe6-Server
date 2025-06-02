package com.equipe6.resources;

import com.equipe6.model.Film;
import com.equipe6.service.FilmService;
import com.equipe6.service.FilmServiceImpl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;
import java.util.stream.Collectors;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource {

    private final FilmService filmService = new FilmServiceImpl();

    // GET /api/films/{id}
    @GET
    @Path("/{id}")
    public Response getFilmById(@PathParam("id") String id) {
        Film film = filmService.getFilmById(id);
        if (film != null) {
            return Response.ok(film).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film introuvable").build();
        }
    }

    // GET /api/films?title=Batman&year=2024
    @GET
    public Response searchFilms(@Context UriInfo uriInfo) {
        Map<String, String[]> params = uriInfo.getQueryParameters().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toArray(new String[0])
                ));

        List<Film> films = filmService.rechercherFilms(params);
        return Response.ok(films).build();
    }

    // Optional health check
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "FilmResource is working.";
    }
}
