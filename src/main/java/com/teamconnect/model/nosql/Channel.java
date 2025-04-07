package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.ChannelType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Set;

@Document
@TypeAlias("CHANNEL")
@Getter
@Setter
public class Channel extends BaseCouchbaseModel {
    private ChannelType channelType;
    private String teamId;
    private Integer position;
    private Integer permissionOverwrites; //TODO: Izinler sonrasinda ayarlanacak.
    private String name;
    private String lastMessageId;
    private Integer userLimit; // if its voice channel
    private Set<String> recipients;
    private String ownerId;
}
