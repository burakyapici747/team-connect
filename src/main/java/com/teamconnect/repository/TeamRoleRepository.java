package com.teamconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRoleRepository extends JpaRepository<TeamRole, String> {
}
