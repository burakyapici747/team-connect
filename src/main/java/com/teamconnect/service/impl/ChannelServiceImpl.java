package com.teamconnect.service.impl;

import com.teamconnect.api.input.channel.ChannelCreateInput;
import com.teamconnect.common.enumarator.ChannelType;
import com.teamconnect.configuration.RabbitMQConfig;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.mapper.ChannelMapper;
import com.teamconnect.model.nosql.Channel;
import com.teamconnect.repository.couchbase.ChannelRepository;
import com.teamconnect.security.CustomUserDetails;
import com.teamconnect.service.ChannelService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final RabbitAdmin rabbitAdmin;
    private final DirectExchange dmChannelDirectExchange;

    public ChannelServiceImpl(
        ChannelRepository channelRepository,
        RabbitAdmin rabbitAdmin,
        DirectExchange dmChannelDirectExchange
    ){
        this.channelRepository = channelRepository;
        this.rabbitAdmin = rabbitAdmin;
        this.dmChannelDirectExchange = dmChannelDirectExchange;
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

        String queueName = "dm.channel.message.queue." + channel.getId();

        Queue dmChannelMessageQueue = QueueBuilder.durable(queueName).build();
        rabbitAdmin.declareQueue(dmChannelMessageQueue);

        String bindingKey = "dm.channel.message.route." + channel.getId();

        Binding binding = BindingBuilder.bind(dmChannelMessageQueue)
            .to(dmChannelDirectExchange)
            .with(bindingKey);
        rabbitAdmin.declareBinding(binding);

        return ChannelMapper.INSTANCE.channelToChannelDto(channel);
    }
}
