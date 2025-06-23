package com.equipe6.facade;

import com.equipe6.dao.ClientDAO;
import com.equipe6.dto.ClientLoginDTO;
import com.equipe6.model.Client;
import com.equipe6.model.Utilisateur;
import com.equipe6.security.PasswordUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientFacadeTest {
    private ClientDAO clientDAOMock;
    private ClientFacade clientFacade;

    @BeforeEach
    void setUp() {
        clientDAOMock = mock(ClientDAO.class);
        clientFacade = new ClientFacade(clientDAOMock);
    }

    @Test
    void login_shouldReturnDTO_whenCredentialsAreValid() {
        String email = "ArnoldLWixom70@gmail.com";
        String plainPassword = "ahng7Rooh2lo";
        String hashedPassword = PasswordUtils.hashPassword(plainPassword);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Wixom");
        utilisateur.setPrenom("Arnold");
        utilisateur.setCourriel(email);
        utilisateur.setMotDePasse(hashedPassword); // ✅ important line

        Client client = new Client();
        client.setIdUser("924621");
        client.setUtilisateur(utilisateur);

        when(clientDAOMock.findByEmail(email)).thenReturn(client);

        ClientLoginDTO res = clientFacade.login(email, plainPassword);

        assertNotNull(res, "Login should return a valid ClientLoginDTO");
        assertEquals("924621", res.getIdUser());
        assertEquals("Arnold", res.getPrenom());
        assertEquals("Wixom", res.getNom());
        assertEquals(email, res.getCourriel());
    }


    @Test
    void login_shouldReturnNull_whenClientNotFound() {
        when(clientDAOMock.findByEmail("invalid@example.com")).thenReturn(null);
        ClientLoginDTO result = clientFacade.login("invalid@example.com", "wrong");
        assertNull(result);
    }
}
