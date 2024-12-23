package com.teamconnect.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto extends BaseDto {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}
