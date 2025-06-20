package com.equipe6.facade;

import com.equipe6.dao.ClientDAO;
import com.equipe6.dto.ClientDTO;
import com.equipe6.dto.ClientLoginDTO;
import com.equipe6.model.Client;
import java.util.List;
import java.util.stream.Collectors;

public class ClientFacade {

    private final ClientDAO clientDAO;

    public ClientFacade(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientLoginDTO login(String email, String password) {
        Client client = clientDAO.findByEmailAndPassword(email, password);
        if (client == null || client.getUtilisateur() == null) return null;

        return new ClientLoginDTO(
                client.getIdUser(),
                client.getUtilisateur().getNom(),
                client.getUtilisateur().getPrenom(),
                client.getUtilisateur().getCourriel()
        );
    }
}
