package com.teamconnect.api.input.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TeamDeleteInput(
    @NotBlank(message = "Confirmation text is required")
    @Pattern(regexp = "^DELETE$", message = "Confirmation text must be exactly 'DELETE'")
    String confirmationText
) {}
