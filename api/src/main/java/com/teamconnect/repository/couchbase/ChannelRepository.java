package com.teamconnect.repository.couchbase;

import com.teamconnect.model.nosql.Channel;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChannelRepository extends CouchbaseRepository<Channel, UUID> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} " +
        "AND (ARRAY_CONTAINS(recipients, $userId) OR ownerId = $userId) " +
        "AND channelType = 'DM'")
    List<Channel> findChannelsByUserId(@Param("userId") String userId);

    Optional<Channel> findById(UUID id);
}
