package com.teamconnect.api.input;

import com.teamconnect.common.constant.TeamConstant;

import jakarta.validation.constraints.Size;

public record TeamUpdateInput(
    @Size(min = 3, max = 50, message = TeamConstant.NAME_SIZE)
    String name,

    @Size(max = 500, message = TeamConstant.DESCRIPTION_SIZE)
    String description
) {} 