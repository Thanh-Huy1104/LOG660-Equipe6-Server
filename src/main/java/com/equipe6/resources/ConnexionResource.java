package com.equipe6.resources;

import com.equipe6.dto.ClientLoginDTO;
import com.equipe6.facade.ClientFacade;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/auth")
public class ConnexionResource {

    private final ClientFacade clientFacade = new ClientFacade();

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @FormParam("email") String email,
            @FormParam("password") String password
    ) {
        try {
            ClientLoginDTO client = clientFacade.login(email, password);
            if (client != null) {
                return Response.ok(client).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid email or password")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Internal server error").build();
        }
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "ConnexionResource is live.";
    }
}