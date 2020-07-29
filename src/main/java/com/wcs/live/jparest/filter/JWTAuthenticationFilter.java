package com.wcs.live.jparest.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wcs.live.jparest.config.AppConfig;
import com.wcs.live.jparest.model.request.LoginRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final long EXPIRATION_TIME = 36_000_000;
    private static final String TOKEN_PREFIX = "Bearer ";


    private final AuthenticationManager manager;
    private final AppConfig config;

    public JWTAuthenticationFilter(AuthenticationManager manager, AppConfig config) {
        this.manager = manager;
        this.config= config;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            // convert json body to POJO
            LoginRequest request  = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginRequest.class);

            return manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword(),
                            Collections.emptyList()
                    )
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                //.withArrayClaim("roles", new String[]{"role1", "role2"})
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(config.getJwtSecret().getBytes()));

        res.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);
        res.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");



        // same as: String newContent = "{ \"token\" : \"" + token + "\" }";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("token", token);
        String newContent = json.toPrettyString();

        res.setContentLength(newContent.length());
        res.getWriter().write(newContent);



    }
}