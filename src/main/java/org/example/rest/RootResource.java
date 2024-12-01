package org.example.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Path("/")
public class RootResource {
    @Autowired
    private Person person;
    @GET
    @Produces("text/plain")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = ((OidcUser)authentication.getPrincipal()).getName();
        return "Hello, " + userName + "! I know abut " + person.getName() + ", btw...";
    }
}
