package nl.craftsmen.conference.service.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;

@Endpoint(id= "customhealth", defaultSensitive = false)
public class CustomHealthCheck {

    @Read(produces = MediaType.TEXT_PLAIN)
    String getMessage() {
        return "all good here!";
    }
}
