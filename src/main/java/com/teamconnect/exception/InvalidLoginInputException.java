package com.teamconnect.exception;

import com.teamconnect.api.input.user.UserLoginInput;
import jakarta.validation.ConstraintViolation;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InvalidLoginInputException extends AuthenticationException {
    private final Set<ConstraintViolation<UserLoginInput>> violations;

    public InvalidLoginInputException(Set<ConstraintViolation<UserLoginInput>> violations) {
        super("Invalid login input");
        this.violations = violations;
    }

    public Set<ConstraintViolation<UserLoginInput>> getViolations() {
        return Collections.unmodifiableSet(violations);
    }

    public List<String> getFormattedErrorMessages(){
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
    }
}
