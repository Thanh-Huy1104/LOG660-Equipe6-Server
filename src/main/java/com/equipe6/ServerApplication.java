package com.equipe6;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ServerApplication extends ResourceConfig {
    public ServerApplication() {
        packages("com.equipe6.resources");
        register(com.equipe6.util.CORSFilter.class);
    }
}
