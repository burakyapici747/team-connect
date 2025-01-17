package com.teamconnect.repository.couchbase;

import com.teamconnect.model.nosql.Channel;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends CouchbaseRepository<Channel, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} " +
           "AND ARRAY_CONTAINS(recipients, $userId) " +
           "AND type = 'DIRECT_CHANNEL'")
    List<Channel> findChannelsByUserId(@Param("userId") String userId);
}
