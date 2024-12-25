package com.teamconnect.repository;

import com.teamconnect.model.sql.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
}
