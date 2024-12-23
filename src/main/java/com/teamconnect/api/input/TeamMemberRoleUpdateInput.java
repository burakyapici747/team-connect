package com.teamconnect.api.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberRoleUpdateInput {
    @NotNull(message = "Rol boş olamaz")
    private String role;
} 