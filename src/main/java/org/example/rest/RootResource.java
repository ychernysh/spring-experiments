package org.example.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/")
public class RootResource {
    @Autowired
    private Person person;
    @GET
    @Produces("text/plain")
    public String index() {
        return "Hello, " + person.getName();
    }
}
