package com.teamconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamconnect.model.sql.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, String> {
}
