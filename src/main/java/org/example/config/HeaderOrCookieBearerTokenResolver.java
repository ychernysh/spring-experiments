package org.example.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.web.util.WebUtils;

public class HeaderOrCookieBearerTokenResolver implements BearerTokenResolver {
  // Would be easier just to inherit, but the made it final..
  private DefaultBearerTokenResolver defaultBearerTokenResolver = new DefaultBearerTokenResolver();

  @Override
  public String resolve(HttpServletRequest request) {
    String fromDefault = defaultBearerTokenResolver.resolve(request);
    if (fromDefault != null) {
      return fromDefault;
    }
    Cookie cookie = WebUtils.getCookie(request, "my-jwt-cookie");
    if (cookie != null) {
      return cookie.getValue();
    }
    return null;
  }
}
