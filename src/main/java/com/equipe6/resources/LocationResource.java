package com.equipe6.resources;

import com.equipe6.dao.ClientDAO;
import com.equipe6.dao.LocationDAO;
import com.equipe6.dto.LocationDTO;
import com.equipe6.facade.LocationFacade;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {

    private final LocationFacade locationFacade;

    public LocationResource() {
        this.locationFacade = new LocationFacade(new LocationDAO(), new ClientDAO());
    }

    public LocationResource(LocationFacade locationFacade) {
        this.locationFacade = locationFacade;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response louerFilm(
            @FormParam("filmId") String filmId,
            @FormParam("clientId") String clientId
    ) {
        if (filmId == null || clientId == null || filmId.trim().isEmpty() || clientId.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("filmId and clientId are required.")
                    .build();
        }

        try {
            LocationDTO locationDTO = locationFacade.createLocation(filmId, clientId);
            return Response.status(Response.Status.CREATED).entity(locationDTO).build();

        } catch (WebApplicationException e) {
            // Handle specific business logic exceptions (e.g., Not Found, Forbidden)
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            // Handle unexpected server errors
            e.printStackTrace();
            return Response.serverError()
                    .entity("An internal error occurred during the rental process.")
                    .build();
        }
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "LocationResource is live.";
    }
}
