package com.teamconnect.service.impl;

import com.teamconnect.api.input.channel.ChannelCreateInput;
import com.teamconnect.common.enumarator.ChannelType;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.mapper.ChannelMapper;
import com.teamconnect.model.nosql.Channel;
import com.teamconnect.repository.couchbase.ChannelRepository;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.ChannelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    @Override
    public List<ChannelDto> getChannelsByUserId(String userId) {
        return ChannelMapper.INSTANCE.channelListToChannelDtoList(channelRepository.findChannelsByUserId(userId));
    }

    @Override
    public ChannelDto createChannel(ChannelCreateInput channelCreateInput, CustomUserDetails customUserDetails) {
        Channel channel = ChannelMapper.INSTANCE.channelCreateInputToChannel(channelCreateInput);
        channel.setId(UUID.randomUUID().toString());
        channel.setChannelType(ChannelType.DM);
        channel.setPosition(0);
        channel.setOwnerId(customUserDetails.getId());
        channel.setRecipients(Set.of("e50eb138-aa7c-4845-a9e0-832eaeb47031"));

        channel = channelRepository.save(channel);

        return ChannelMapper.INSTANCE.channelToChannelDto(channel);
    }
}
