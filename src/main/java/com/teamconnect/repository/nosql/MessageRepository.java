package com.teamconnect.repository.nosql;

import com.teamconnect.model.nosql.Message;
import java.time.Instant;
import java.util.List;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CouchbaseRepository<Message, String> {
    
    List<Message> findByReceiverIdAndIsDeletedFalseAndCreatedAtBeforeOrderByCreatedAtDesc(
        String receiverId, 
        Instant createdAt, 
        int limit
    );
    
    List<Message> findByReceiverIdAndIsDeletedFalseOrderByCreatedAtDesc(
        String receiverId, 
        int limit
    );
    
    List<Message> findBySenderIdAndIsDeletedFalseAndCreatedAtBeforeOrderByCreatedAtDesc(
        String senderId, 
        Instant createdAt, 
        int limit
    );
    
    List<Message> findBySenderIdAndIsDeletedFalseOrderByCreatedAtDesc(
        String senderId, 
        int limit
    );

    @Query("#{#n1ql.selectEntity} WHERE (receiverId = $1 OR senderId = $1) " +
           "AND content LIKE $2 AND isDeleted = false " +
           "ORDER BY createdAt DESC LIMIT $3")
    List<Message> searchMessages(String userId, String query, int limit);

    @Query("SELECT COUNT(*) FROM #{#n1ql.bucket} " +
           "WHERE receiverId = $1 AND isDeleted = false AND isRead = false")
    Long countUnreadMessages(String userId);
} 