package com.teamconnect.exception;

import com.teamconnect.api.input.LoginInput;
import jakarta.validation.ConstraintViolation;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InvalidLoginInputException extends AuthenticationException {
    private final Set<ConstraintViolation<LoginInput>> violations;

    public InvalidLoginInputException(Set<ConstraintViolation<LoginInput>> violations) {
        super("Invalid login input");
        this.violations = violations;
    }

    public Set<ConstraintViolation<LoginInput>> getViolations() {
        return Collections.unmodifiableSet(violations);
    }

    public List<String> getFormattedErrorMessages(){
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
    }
}
