package com.equipe6.resources;

import com.equipe6.facade.FilmFacade;
import com.equipe6.model.Film;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;
import java.util.stream.Collectors;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource {

    private final FilmFacade filmFacade = new FilmFacade();

    @GET
    @Path("/{id}")
    public Response getFilmById(@PathParam("id") String id) {
        Film film = filmFacade.getFilmById(id);
        return film != null
                ? Response.ok(film).build()
                : Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
    }

    @GET
    public Response searchFilms(@Context UriInfo uriInfo) {
        Map<String, String[]> params = uriInfo.getQueryParameters().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toArray(new String[0])
                ));
        List<Film> films = filmFacade.searchFilms(params);
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
