package nl.craftsmen.conference.service.micronaut;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.Size;

@ConfigurationProperties("app")
public class ConferenceConfiguration {


    @Size(min = 5)
    public String helloMessage;
}
