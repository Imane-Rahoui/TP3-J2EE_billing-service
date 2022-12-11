package com.example.billingservice.security;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

// public class KeycloakSecurityConfig extends WebSecurityConfigurerAdapter {
// if u r using spring security
@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        // auth.jdbcAuthentication()
        // auth.userDetailsService() // a toi d implementer la couche security comme service
        auth.authenticationProvider(keycloakAuthenticationProvider()); // deleguer la gestion des users et roles par un provider : keycloak
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().antMatchers("/bills/**").authenticated();

        // http://localhost:8080/realms/ecom-realm/.well-known/openid-configuration for more informations exp to get public keys
    }
}
