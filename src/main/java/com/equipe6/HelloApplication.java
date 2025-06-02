package com.equipe6;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        packages("com.equipe6.resources"); // ✅ Good: scans for @Path classes automatically
    }
}
