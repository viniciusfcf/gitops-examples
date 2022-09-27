package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "quarkus.app.custom.message")
    String customMessage;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return customMessage;
    }
}