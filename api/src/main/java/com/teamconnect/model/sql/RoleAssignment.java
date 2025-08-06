package com.teamconnect.model.sql;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ROLE_ASSIGNMENTS")
public class RoleAssignment {
    @EmbeddedId
    private RoleAssignmentId id;

    //TODO: User relationship kurulacak.

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "assigned_at", nullable = false)
    private Instant assignedAt;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class RoleAssignmentId implements Serializable {
        @Column(name = "user_id", nullable = false)
        private String userId;

        @Column(name = "role_id", nullable = false)
        private String roleId;
    }
}
