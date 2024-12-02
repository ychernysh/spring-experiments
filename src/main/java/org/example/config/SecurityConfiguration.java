package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(r -> r.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .build();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
    }

    private ClientRegistration keycloakClientRegistration() {
        return ClientRegistrations.fromOidcIssuerLocation("http://localhost:8090/realms/dummy")
                .registrationId("keycloak")
                .scope("openid")
                .clientId("my-spring-webapp")
                .clientSecret("ORZjeGpva5VWBrnfLek2RRzVKcxw3yp6")
                .userNameAttributeName("preferred_username")
                .build();
    }

}
