package com.teamconnect.api.input;

import com.teamconnect.constant.TeamConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateInput {
    @NotBlank(message = TeamConstant.NAME_NOT_EMPTY)
    @Size(min = 3, max = 50, message = TeamConstant.NAME_SIZE)
    private String name;

    @Size(max = 500, message = TeamConstant.DESCRIPTION_SIZE)
    private String description;
}
