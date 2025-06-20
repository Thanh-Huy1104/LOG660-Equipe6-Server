package com.equipe6.resources;

import com.equipe6.dao.FilmDAO;
import com.equipe6.dto.FilmDTO;
import com.equipe6.dto.FilmDetailDTO;
import com.equipe6.facade.FilmFacade;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;
import java.util.stream.Collectors;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource {

    private final FilmFacade filmFacade;

    public FilmResource() {
        this.filmFacade = new FilmFacade(new FilmDAO());
    }

    public FilmResource(FilmFacade filmFacade) {
        this.filmFacade = filmFacade;
    }

    @GET
    @Path("/{id}")
    public Response getFilmById(@PathParam("id") String id) {
        FilmDetailDTO dto = filmFacade.getFilmById(id);
        return (dto != null)
                ? Response.ok(dto).build()
                : Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
    }

    @GET
    public Response searchFilms(@Context UriInfo uriInfo) {
        Map<String, String[]> params = uriInfo.getQueryParameters().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toArray(new String[0])
                ));

        List<FilmDTO> dtos = filmFacade.searchFilms(params);
        return Response.ok(dtos).build();
    }

    // Optional health check
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "FilmResource is working.";
    }
}
