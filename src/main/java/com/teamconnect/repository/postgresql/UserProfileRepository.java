package com.teamconnect.repository.postgresql;

import com.teamconnect.model.sql.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    UserProfile findByUserId(String userId);
}
