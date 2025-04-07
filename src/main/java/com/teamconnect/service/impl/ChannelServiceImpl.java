package com.teamconnect.service.impl;

import com.teamconnect.api.input.channel.ChannelCreateInput;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.mapper.ChannelMapper;
import com.teamconnect.model.nosql.Channel;
import com.teamconnect.repository.couchbase.ChannelRepository;
import com.teamconnect.service.ChannelService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ChannelDto createChannel(ChannelCreateInput channelCreateInput) {
        Channel channel = ChannelMapper.INSTANCE.channelCreateInputToChannel(channelCreateInput);
        channelRepository.save(channel);

        return ChannelMapper.INSTANCE.channelToChannelDto(channel);
    }
}
