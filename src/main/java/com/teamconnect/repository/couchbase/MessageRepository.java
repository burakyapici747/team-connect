package com.teamconnect.repository.couchbase;

import com.teamconnect.model.nosql.Message;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CouchbaseRepository<Message, String> {
    @Query("#{#n1ql.selectEntity} WHERE channelId = $channelId ORDER BY timestamp DESC")
    List<Message> findMessageByChannelId(@Param("channelId") String channelId);
}
