package com.teamconnect.repository.postgresql;

import com.teamconnect.model.sql.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, String> {
    @Query("SELECT f FROM Friendship f" +
        " WHERE (f.user1 = :userId AND f.status = 'FRIEND') OR (f.user2 = :userId AND f.status = 'FRIEND')")
    List<Friendship> findAllFriendshipsByUserId(@Param("userId") String userId);

    @Query(
        "SELECT f FROM Friendship f " +
        "WHERE " +
            "(f.user1 = :userId AND f.status = 'REQ_UID1') " +
        "OR" +
            " (f.user2 = :userId AND f.status = 'REQ_UID2')"
    )
    List<Friendship> findOutgoingFriendRequestsByUserId(@Param("userId") String userId);

    @Query("SELECT  f FROM Friendship f " +
    "WHERE " +
        "(f.user1 = :userId AND f.status = 'REQ_UID2') " +
    "OR " +
        "(f.user2 = :userId AND f.status = 'REQ_UID1')")
    List<Friendship> findIncomingFriendRequestsByUserId(@Param("userId") String userId);

    @Query("SELECT f FROM Friendship f " +
        "WHERE ((f.user1.id = :currentUserId AND f.user2.id = :otherUserId) " +
        "OR (f.user1.id = :otherUserId AND f.user2.id = :currentUserId))")
    Friendship findFriendshipBetweenUsers(@Param("currentUserId") String currentUserId, @Param("otherUserId") String otherUserId);
}
