package com.teamconnect.api.controller;

import com.teamconnect.api.input.message.GetMessageQueryParams;
import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.output.message.MessageOutput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.mapper.MessageMapper;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.MessageService;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/channels/{channelId}/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MessageOutput>>> getMessages(
        @PathVariable(value = "channelId") String channelId,
        @Valid GetMessageQueryParams getMessageQueryParams,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return ResponseWrapper.ok(
            MessageMapper.INSTANCE.messageDtoListToMessageOutputList(
                messageService.getMessages(
                    channelId,
                    getMessageQueryParams.before(),
                    getMessageQueryParams.after(),
                    getMessageQueryParams.limit()
                )
            )
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<MessageOutput>> sendMessage(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(value = "channelId") String channelId,
        @RequestBody MessageCreateInput messageCreateInput
    ) throws InterruptedException {
        MessageOutput response = MessageMapper.INSTANCE.messageDtoToMessageOutput(messageService.sendMessage(channelId, customUserDetails.getId(), messageCreateInput));
        return ResponseWrapper.ok(response);
    }
}
