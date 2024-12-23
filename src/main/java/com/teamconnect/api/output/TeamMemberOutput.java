package com.teamconnect.api.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberOutput {
    private Long id;
    private UserOutput user;
    private TeamOutput team;
    private String role;
    private Instant joinDate;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserOutput {
        private Long id;
        private String email;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamOutput {
        private Long id;
        private String name;
        private String description;
    }
} 