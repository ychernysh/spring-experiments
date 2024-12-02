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
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Path("/")
public class RootResource {
    @Autowired
    private Person person;
    @GET
    @Produces("text/plain")
    public String index() {
        return "Hello, " + getAuthenticatedUserName() + "! I know abut " + person.getName() + ", btw...";
    }

    private String getAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            return ((OidcUser)authentication.getPrincipal()).getName();
        } else if (authentication instanceof JwtAuthenticationToken) {
            return ((JwtAuthenticationToken)authentication).getTokenAttributes().get("preferred_username").toString();
        }
        return "null";
    }
}
