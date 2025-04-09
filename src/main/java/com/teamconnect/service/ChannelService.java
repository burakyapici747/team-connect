package com.teamconnect.service;

import com.teamconnect.api.input.channel.ChannelCreateInput;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.security.CustomUserDetails;

import java.util.List;

public interface ChannelService {
    List<ChannelDto> getChannelsByUserId(String userId);
    ChannelDto createChannel(ChannelCreateInput channelCreateInput, CustomUserDetails customUserDetails);
}
