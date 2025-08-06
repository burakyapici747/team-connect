package com.teamconnect.api.input;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record MessageReferenceInput (
    @JsonProperty("message_id")
    UUID messageId,

    @JsonProperty("channel_id")
    UUID channelId,

    @JsonProperty("guild_id")
    UUID guildId,

    @JsonProperty("fail_if_not_exists")
    boolean failIfNotExists
){ }
