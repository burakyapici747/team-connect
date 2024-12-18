package com.teamconnect.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamconnect.api.input.LoginInput;
import com.teamconnect.api.output.AuthenticationOutput;
import com.teamconnect.exception.CustomAuthenticationFailureHandler;
import com.teamconnect.exception.InvalidLoginInputException;
import com.teamconnect.service.impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/v1/api/auth", "POST");
    private final Validator validator;
    private final JWTService jwtService;
    private final ObjectMapper objectMapper;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public CustomAuthenticationFilter(
            Validator validator,
            JWTService jwtService,
            ObjectMapper objectMapper,
            CustomAuthenticationFailureHandler customAuthenticationFailureHandler
    ) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.validator = validator;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        setAuthenticationFailureHandler(customAuthenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException, IOException, ServletException {
        LoginInput loginInput = obtainLoginInput(request);
        validateLoginInput(loginInput);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginInput.username(),
                loginInput.password()
        );

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException{
        SecurityContextHolder.getContext().setAuthentication(authResult);
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String accessToken = jwtService.generateToken(userDetails);
        sendSuccessAuthenticationResponse(response, accessToken);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException, ServletException{
        super.unsuccessfulAuthentication(request, response, failed);
    }

    private void validateLoginInput(LoginInput loginInput) {
        Set<ConstraintViolation<LoginInput>> violations = validator.validate(loginInput);
        if(!violations.isEmpty()){
            throw new InvalidLoginInputException(violations);
        }
    }

    private void sendSuccessAuthenticationResponse(
            HttpServletResponse response,
            String accessToken
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        AuthenticationOutput authenticationOutput = new AuthenticationOutput(accessToken);

        try{
            this.objectMapper.writeValue(response.getWriter(), authenticationOutput);
        }catch (IOException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Interval Server Error");
        }
    }

    private LoginInput obtainLoginInput(HttpServletRequest request) throws IOException {
        try{
            return this.objectMapper.readValue(request.getInputStream(), LoginInput.class);
        }catch (JsonProcessingException e){
            throw new InvalidLoginInputException(Set.of());
        }
    }
}