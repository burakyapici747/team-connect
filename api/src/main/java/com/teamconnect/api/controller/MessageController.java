package com.teamconnect.api.controller;

import com.teamconnect.api.input.ReactionCreateInput;
import com.teamconnect.api.input.message.GetMessageQueryParams;
import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.input.message.MessageEditInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.dto.MessageDto;
import com.teamconnect.dto.ReactionUserDto;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.MessageService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/channels/{channelId}/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MessageDto>>> getMessages(
        @PathVariable("channelId") UUID channelId,
        @ModelAttribute @Valid GetMessageQueryParams params,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<MessageDto> response = messageService.getMessages(
            channelId,
            params.before(),
            params.after(),
            params.limit()
        );
        return ResponseWrapper.ok(response);
    }

    @GetMapping(path = "/{messageId}")
    public ResponseEntity<ResponseWrapper<MessageDto>> getMessageById(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        MessageDto dto = messageService.getMessageByChannelIdAndId(channelId, messageId);
        return ResponseWrapper.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<MessageDto>> createMessage(
        @PathVariable("channelId") UUID channelId,
        @Valid @RequestBody MessageCreateInput input,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException {
        MessageDto dto = messageService.sendMessage(
            channelId,
            UUID.fromString(userDetails.getId()),
            input
        );
        return ResponseWrapper.ok(dto);
    }

    @PatchMapping(path = "{messageId}")
    public ResponseEntity<ResponseWrapper<MessageDto>> editMessage(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @RequestBody MessageEditInput messageEditInput,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return null;
    }

    @DeleteMapping(path = "{messageId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteMessage(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return null;
    }

    @GetMapping(path = "{messageId}/reactions/{emoji}")
    public ResponseEntity<ResponseWrapper<List<ReactionUserDto>>> getReactions(
        @PathVariable UUID channelId,
        @PathVariable UUID messageId,
        @PathVariable String emoji,
        @RequestParam(required = false) UUID before,
        @RequestParam(required = false) UUID after,
        @RequestParam(defaultValue = "25") int limit
    ){
        return null;
    }

    @PostMapping(path = "/{messageId}/reactions/{emoji}/me")
    public ResponseEntity<ResponseWrapper<Void>> createReaction(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @PathVariable("emoji") String emoji,
        @RequestBody ReactionCreateInput reactionCreateInput,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return null;
    }

    @DeleteMapping(path = "/{messageId}/reactions/{emoji}/me")
    public ResponseEntity<ResponseWrapper<Void>> deleteOwnReaction(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @PathVariable("emoji") String emoji,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return null;
    }

    @DeleteMapping(path = "/{messageId}/reactions/{emoji}/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteUserReaction(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @PathVariable("emoji") String emoji,
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }

    @DeleteMapping(path = "/{messageId}/reactions")
    public ResponseEntity<ResponseWrapper<Void>> deleteAllReactions(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }

    @DeleteMapping(path = "/{messageId}/reactions/{emoji}")
    public ResponseEntity<ResponseWrapper<Void>> deleteAllReactionForEmoji(
        @PathVariable("channelId") UUID channelId,
        @PathVariable("messageId") UUID messageId,
        @PathVariable("emoji") String emoji,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }
}
