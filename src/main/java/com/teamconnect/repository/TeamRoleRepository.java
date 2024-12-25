package com.teamconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamconnect.model.sql.TeamRole;

@Repository
public interface TeamRoleRepository extends JpaRepository<TeamRole, String> {
}