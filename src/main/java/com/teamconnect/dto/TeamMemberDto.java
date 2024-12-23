package com.teamconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberDto extends BaseDto {
    private String id;
    private String teamId;
    private String userId;
    private String role;
    private Instant joinDate;
    private String email;
    private String teamName;
} 