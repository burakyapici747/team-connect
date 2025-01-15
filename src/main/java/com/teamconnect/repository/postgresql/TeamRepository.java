package com.teamconnect.repository;

import com.teamconnect.model.sql.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
    @Query(" SELECT DISTINCT t" +
        "    FROM Team t" +
        "    LEFT JOIN t.members m" +
        "    WHERE t.owner.id = :userId" +
        "       OR m.user.id = :userId")
    List<Team> findUserTeams(String userId);
}
