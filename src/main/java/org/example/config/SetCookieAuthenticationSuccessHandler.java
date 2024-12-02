package org.example.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Optional;

public class SetCookieAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    Optional.of(getIdToken(authentication)).ifPresent(idToken -> response.addCookie(constructIdTokenCookie(idToken)));
    super.onAuthenticationSuccess(request, response, authentication);
  }

  private String getIdToken(Authentication authentication) {
    if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {
      OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
      if (oAuth2User instanceof OidcUser oidcUser) {
        return oidcUser.getIdToken().getTokenValue();
      }
    }
    return null;
  }

  private Cookie constructIdTokenCookie(String idToken) {
    Cookie cookie = new Cookie("my-jwt-token", idToken);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    return cookie;
  }

}
