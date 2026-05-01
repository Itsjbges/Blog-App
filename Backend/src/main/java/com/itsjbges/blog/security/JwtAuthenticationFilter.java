package com.itsjbges.blog.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.itsjbges.blog.services.AuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = extractToken(request);
            if (token != null) {
                UserDetails userDetails = authenticationService.validateToken(token);

                // Perlu bikin this class variable supaya bisa use the SecurityContextHolder gr"
                // dia cmn accept parameters that implement Authenticate
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, // Principals/userDetails implementation
                        null, // Credentials/password, gk perlul gr" JWT Token udh prove the identity
                        userDetails.getAuthorities()); // Authorities of the user

                // Kasi tw the entire programm klo this user is either authenticated or not
                SecurityContextHolder.getContext().setAuthentication(authentication);

                if (userDetails instanceof BlogUserDetail) {
                    // Supaya bisa pake @RequestAttributes di controller untuk dptin a certain
                    // attributes lbh gampang
                    request.setAttribute("userId", ((BlogUserDetail) userDetails).getId());
                }
            }
        } catch (Exception e) {
            // Don't throw exception, just don't authenticate the user
            log.warn("Received invalid auth token");
        }

        filterChain.doFilter(request, response);
    }

    // HttpServletRequest itu di Server side, meanwhile HttpRequest itu Client side
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
