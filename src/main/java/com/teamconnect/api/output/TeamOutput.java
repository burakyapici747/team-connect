package com.teamconnect.api.output;

import com.teamconnect.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// TODO: Su anda team'in icinde kullanilan user, teamMember, teamrole ve
// meetinglerin output'u yok. Bu nedenle bunlarin output'u da olusturulmalidir.
public class TeamOutput extends BaseDto {
    private String id;
    private String name;
    private String description;
    private UserOutput owner;
    private List<TeamMemberOutput> members;
    private List<TeamRoleOutput> roles;
    private List<MeetingOutput> meetings;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserOutput {
        private String id;
        private String username;
        private String email;
        private String fullName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamMemberOutput {
        private String id;
        private UserOutput user;
        private String role;
        private String joinDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamRoleOutput {
        private String id;
        private String name;
        private String description;
        private List<String> permissions;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MeetingOutput {
        private String id;
        private String title;
        private String description;
        private String startTime;
        private String endTime;
        private String status;
        private List<UserOutput> participants;
    }
}