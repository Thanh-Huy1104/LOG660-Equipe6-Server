package com.equipe6.facade;

import com.equipe6.dao.ClientDAO;
import com.equipe6.dto.ClientDTO;
import com.equipe6.model.Client;
import java.util.List;
import java.util.stream.Collectors;

public class ClientFacade {
    private final ClientDAO clientDAO = new ClientDAO();

    public ClientDTO getClientById(String id) {
        Client client = clientDAO.findById(id);
        return (client != null) ? new ClientDTO(client) : null;
    }

    public List<ClientDTO> getAllClients() {
        return clientDAO.findAll().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

}
