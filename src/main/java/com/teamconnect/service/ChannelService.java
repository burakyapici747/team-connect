package com.teamconnect.service;

import com.teamconnect.dto.ChannelDto;

import java.util.List;

public interface ChannelService {
    List<ChannelDto> getChannelsByUserId(String userId);
}
