package com.teamconnect.repository.nosql;

import com.teamconnect.model.nosql.Message;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface MessageRepository extends CouchbaseRepository<Message, String> {
}
