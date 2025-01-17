package com.teamconnect.service.impl;

import com.teamconnect.dto.ChannelDto;
import com.teamconnect.mapper.ChannelMapper;
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
}
