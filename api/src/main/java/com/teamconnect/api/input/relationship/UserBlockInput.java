package com.teamconnect.api.input.relationship;

import com.teamconnect.common.enumarator.RelationshipType;

public record UserBlockInput(
    RelationshipType type
) {}
