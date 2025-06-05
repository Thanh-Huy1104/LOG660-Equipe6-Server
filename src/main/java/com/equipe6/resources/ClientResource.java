package com.equipe6.resources;

import jakarta.ws.rs.*;
import com.equipe6.facade.ClientFacade;
import com.equipe6.dto.ClientDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/clients")
@Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
public class ClientResource {
    private final ClientFacade clientFacade = new ClientFacade();

    // Endpoint to get a client by ID
    @GET
    @Path("/{id}")
    public Response getClientById(@PathParam("id") String id) {
        ClientDTO clientDTO = clientFacade.getClientById(id);
        if (clientDTO != null) {
            return Response.ok(clientDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Client not found").build();
        }
    }

    @GET
    public Response getAllClients() {
        return Response.ok(clientFacade.getAllClients()).build();
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "ClientResource is working.";
    }
}
