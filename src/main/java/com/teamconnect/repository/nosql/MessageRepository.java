package com.teamconnect.repository.nosql;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.teamconnect.model.nosql.Message;

@Repository
public interface MessageRepository extends CouchbaseRepository<Message, String> {
    // ... methods
} 