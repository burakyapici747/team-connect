package com.teamconnect.repository.couchbase;

import com.teamconnect.model.nosql.File;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends CouchbaseRepository<File, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} " +
           "AND ownerId = $ownerId " +
           "AND isDeleted = false")
    List<File> findFilesByOwnerId(@Param("ownerId") String ownerId);
}
