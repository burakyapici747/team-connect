package com.teamconnect.api.controller;

import com.teamconnect.api.input.channel.ChannelCreateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.channel.ChannelPrivateOutput;
import com.teamconnect.mapper.ChannelMapper;
import com.teamconnect.service.ChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/channels")
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService){
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<ChannelPrivateOutput>> createChannel(ChannelCreateInput channelCreateInput){
        return ResponseWrapper.ok(
            ChannelMapper.INSTANCE.channelDtoToChannelPrivateOutput(channelService.createChannel(channelCreateInput))
        );
    }
}
