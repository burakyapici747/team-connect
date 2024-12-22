package com.teamconnect.repository;

import com.teamconnect.model.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("""
        SELECT u FROM User u 
        WHERE (:keyword IS NULL OR 
               LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR 
               LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')))
        AND (:availability IS NULL OR u.profile.availability = :availability)
        AND (:language IS NULL OR :language MEMBER OF u.profile.languages)
    """)
    List<User> searchUsers(String keyword, String availability, String language);
}
