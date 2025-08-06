package com.teamconnect.repository.couchbase;

import com.teamconnect.model.nosql.Message;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends CouchbaseRepository<Message, UUID> {
    @Query("#{#n1ql.selectEntity} WHERE channelId = $channelId ORDER BY timestamp DESC LIMIT $limit")
    List<Message> findInitialMessages(
        @Param("channelId") UUID channelId,
        @Param("limit") int limit
    );

    @Query("#{#n1ql.selectEntity} WHERE channelId = $channelId AND timestamp < (SELECT RAW m.timestamp FROM `teamconnect` m WHERE META(m).id = $before)[0] ORDER BY timestamp DESC LIMIT $limit")
    List<Message> findMessagesBeforeId(
        @Param("channelId") UUID channelId,
        @Param("before") UUID before,
        @Param("limit") int limit
    );

    @Query("#{#n1ql.selectEntity} WHERE channelId = $channelId AND META().id < $after ORDER BY timestamp ASC LIMIT $limit")
    List<Message> findMessagesAfterId(
        @Param("channelId") UUID channelId,
        @Param("after") UUID after,
        @Param("limit") int limit
    );

    @Query("#{#n1ql.selectEntity}  WHERE META().id = $id  AND channelId = $channelId")
    Optional<Message> findByChannelIdAndId(@Param("channelId") UUID channelId, @Param("id") UUID id);

    Optional<Message> findById(UUID id);
}
