package com.example.isa.configuration;

import com.example.isa.model.enums.UserTypes;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Order(value = 3)
public class JwtFilter extends GenericFilterBean {

    private static final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        try {
            final String authorizationHeader = httpServletRequest.getHeader("Authorization");
            final String token = authorizationHeader.substring("Bearer ".length());
            final long userId = jwtService.getUserId(token);
            final UserTypes role = jwtService.getRole(token);
            final Principal principal = new Principal(userId, role);
            final Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, List.of((GrantedAuthority) role::name));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (ExpiredJwtException e) {
            LOG.debug("Security exception for user {} - {}. Expired token.", e.getClaims().getSubject(), e.getMessage());
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        } catch (RuntimeException e) {
            LOG.debug("Authentication token is invalid. {}", e.getMessage());
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }
    }

}