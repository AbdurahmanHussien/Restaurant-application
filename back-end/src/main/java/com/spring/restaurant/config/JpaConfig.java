package com.spring.restaurant.config;

import com.spring.restaurant.service.auth.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !auth.isAuthenticated()) {
                return Optional.of("anonymousUser");
            }

            Object principal = auth.getPrincipal();

            if (principal instanceof CustomUserDetails) {
                return Optional.of(((CustomUserDetails) principal).getUser().getUsername());
            } else {
                return Optional.of(principal.toString());
            }
        };
    }

}
