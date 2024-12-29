package com.teamconnect.repository;

import com.teamconnect.model.sql.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    @Query("SELECT DISTINCT t FROM Team t " +
           "LEFT JOIN FETCH t.teamMembers " +
           "LEFT JOIN FETCH t.teamRoles " +
           "LEFT JOIN FETCH t.meetings " +
           "WHERE t.id = :id")
    Optional<Team> findByIdWithDetails(@Param("id") String id);

    @Query("SELECT DISTINCT t FROM Team t " +
           "LEFT JOIN FETCH t.teamMembers " +
           "LEFT JOIN FETCH t.teamRoles " +
           "LEFT JOIN FETCH t.meetings " +
           "WHERE t.name = :name")
    Optional<Team> findByNameWithDetails(@Param("name") String name);

    @Query("SELECT DISTINCT t FROM Team t " +
           "LEFT JOIN FETCH t.teamMembers " +
           "LEFT JOIN FETCH t.teamRoles " +
           "LEFT JOIN FETCH t.meetings")
    List<Team> findAllWithDetails();

    Optional<Team> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
}
