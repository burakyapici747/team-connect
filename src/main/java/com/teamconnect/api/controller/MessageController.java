package com.teamconnect.api.controller;

import com.teamconnect.api.input.message.MessageCreateInput;
import com.teamconnect.api.output.MessageOutput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.mapper.MessageMapper;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.MessageService;
import java.util.List;

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
    public ResponseEntity<ResponseWrapper<List<MessageOutput>>> getMessagesByChannelId(
        @PathVariable(value = "channelId") String channelId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return ResponseWrapper.ok(
            MessageMapper.INSTANCE.messageDtoListToMessageOutputList(
                messageService.getMessagesByChannelId(channelId)
            )
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<MessageOutput>> sendMessage(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable(value = "channelId") String channelId,
        @RequestBody MessageCreateInput messageCreateInput
    ){
        return ResponseWrapper.ok(
            MessageMapper.INSTANCE.messageDtoToMessageOutput(
                messageService.sendMessage(
                    channelId,
                    customUserDetails.getId(),
                    messageCreateInput
                )
            )
        );
    }

//    @GetMapping("/received")
//    public ResponseEntity<ResponseWrapper<List<MessageOutput>>> getReceivedMessages(
//        @AuthenticationPrincipal CustomUserDetails userDetails,
//        @RequestParam(required = false) Long timestamp,
//        @RequestParam(defaultValue = "20") int limit
//    ) {
//        return ResponseWrapper.ok(MessageMapper.INSTANCE.messageDtosToMessageOutputs(
//            messageService.getMessagesByReceiverId(userDetails.getId(), timestamp, limit)
//        ));
//    }
//
//    @GetMapping("/sent")
//    public ResponseEntity<ResponseWrapper<List<MessageOutput>>> getSentMessages(
//        @AuthenticationPrincipal CustomUserDetails userDetails,
//        @RequestParam(required = false) Long timestamp,
//        @RequestParam(defaultValue = "20") int limit
//    ) {
//        return ResponseWrapper.ok(MessageMapper.INSTANCE.messageDtosToMessageOutputs(
//            messageService.getMessagesBySenderId(userDetails.getId(), timestamp, limit)
//        ));
//    }
//
//    @DeleteMapping("/{messageId}")
//    public ResponseEntity<ResponseWrapper<Void>> deleteMessage(
//        @PathVariable String messageId,
//        @AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        messageService.deleteMessage(messageId, userDetails.getId());
//        return ResponseWrapper.noContent();
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<ResponseWrapper<List<MessageOutput>>> searchMessages(
//        @AuthenticationPrincipal CustomUserDetails userDetails,
//        @RequestParam String query,
//        @RequestParam(defaultValue = "20") int limit
//    ) {
//        return ResponseWrapper.ok(MessageMapper.INSTANCE.messageDtosToMessageOutputs(
//            messageService.searchMessages(userDetails.getId(), query, limit)
//        ));
//    }
//
//    @GetMapping("/unread-count")
//    public ResponseEntity<ResponseWrapper<Long>> getUnreadMessageCount(
//        @AuthenticationPrincipal CustomUserDetails userDetails
//    ) {
//        return ResponseWrapper.ok(messageService.getUnreadMessageCount(userDetails.getId()));
//    }
}
