package com.teamconnect.model.nosql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionOverwrite {
    private UUID id;

    private OverwriteType type;

    private long allow;

    private long deny;

    public enum OverwriteType {
        ROLE,
        MEMBER
    }
}
