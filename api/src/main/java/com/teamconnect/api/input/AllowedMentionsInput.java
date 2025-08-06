package com.teamconnect.api.input;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record AllowedMentionsInput(
    List<String> parse,
    List<UUID> users,
    List<UUID> roles,

    @JsonProperty("replied_user")
    boolean repliedUser
) {}
