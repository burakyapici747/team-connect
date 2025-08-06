package com.teamconnect.api.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record MessageUpdateInput(
    String content,
    String nonce,

    @JsonProperty("tts")
    boolean textToSpeech,

    @JsonProperty("allowed_mentions")
    AllowedMentionsInput allowedMentions,

    @JsonProperty("message_reference")
    MessageReferenceInput messageReference,

    @JsonProperty("sticker_ids")
    List<String> stickerIds,

    List<MultipartFile> files,

    @JsonProperty("payload_json")
    String payloadJson,

    List<AttachmentInput> attachments,
    Integer flags
) {}
