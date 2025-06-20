package com.equipe6.resources;

import com.equipe6.dto.ClientLoginDTO;
import com.equipe6.facade.ClientFacade;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConnexionResourceTest {

    private ConnexionResource connexionResource;
    private ClientFacade clientFacade;

    @BeforeEach
    void setUp() {
        clientFacade = mock(ClientFacade.class);
        connexionResource = new ConnexionResource(clientFacade);
    }

    @Test
    void login_shouldReturnOk_whenCredentialsAreValid() {
        // Given
        ClientLoginDTO dto = new ClientLoginDTO("123", "John", "Doe", "john.doe@example.com");
        when(clientFacade.login("john.doe@example.com", "securePass")).thenReturn(dto);

        // When
        Response response = connexionResource.login("john.doe@example.com", "securePass");

        // Then
        assertEquals(200, response.getStatus());
        assertEquals(dto, response.getEntity());
    }

    @Test
    void login_shouldReturnUnauthorized_whenCredentialsAreInvalid() {
        when(clientFacade.login("invalid@example.com", "wrong")).thenReturn(null);

        Response response = connexionResource.login("invalid@example.com", "wrong");

        assertEquals(401, response.getStatus());
        assertEquals("Invalid email or password", response.getEntity());
    }

    @Test
    void login_shouldReturnServerError_whenExceptionIsThrown() {
        when(clientFacade.login(anyString(), anyString())).thenThrow(new RuntimeException("DB down"));

        Response response = connexionResource.login("test@example.com", "pass");

        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getEntity());
    }
}
