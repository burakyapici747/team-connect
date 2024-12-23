package com.teamconnect.repository;

import com.teamconnect.model.sql.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    
    Optional<UserProfile> findByUserId(String userId);
    
    @Query("SELECT up FROM UserProfile up WHERE up.availability = :availability")
    List<UserProfile> findAllByAvailability(@Param("availability") String availability);
    
    @Query("SELECT up FROM UserProfile up WHERE up.language = :language")
    List<UserProfile> findAllByLanguage(@Param("language") String language);
    
    @Query("""
        SELECT up FROM UserProfile up 
        WHERE LOWER(up.bio) LIKE LOWER(CONCAT('%', :keyword, '%')) 
        OR LOWER(up.statusDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<UserProfile> searchProfiles(@Param("keyword") String keyword);
    
    @Query("""
        SELECT up FROM UserProfile up 
        JOIN up.user u 
        WHERE u.lastSeenAt > CURRENT_TIMESTAMP - :minutes
    """)
    List<UserProfile> findRecentlyActiveProfiles(@Param("minutes") int minutes);
    
    boolean existsByUserId(String userId);
    
    void deleteByUserId(String userId);
} 