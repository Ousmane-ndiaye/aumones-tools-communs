package com.aumones.tools.communs.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public abstract class AuditorAwareImpl<ID>  implements AuditorAware<ID> {

  @Override
  public Optional<ID> getCurrentAuditor() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(authentication -> {
          if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
          }
          return getIdentityFieldValue(authentication);
        });
  }

  public abstract ID getIdentityFieldValue(Authentication authentication);
}
