package com.teamconnect.mapper;

import com.teamconnect.api.output.channel.ChannelPublicOutput;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.model.nosql.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ChannelMapper {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    List<ChannelDto> channelListToChannelDtoList(List<Channel> channelList);

    List<ChannelPublicOutput> channelDtoListToChannelPublicOutputlist(List<ChannelDto> channelDtoList);
}
