package com.teamconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teamconnect.model.sql.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, String> {

       @Query("SELECT tm FROM TeamMember tm " +
                     "LEFT JOIN FETCH tm.team " +
                     "LEFT JOIN FETCH tm.user " +
                     "WHERE tm.team.id = :teamId AND tm.user.id = :userId")
       Optional<TeamMember> findByTeamIdAndUserId(
                     @Param("teamId") String teamId,
                     @Param("userId") String userId);

       @Query("SELECT tm FROM TeamMember tm " +
                     "LEFT JOIN FETCH tm.team " +
                     "LEFT JOIN FETCH tm.user " +
                     "WHERE tm.user.id = :userId")
       List<TeamMember> findByUserId(@Param("userId") String userId);

       @Query("SELECT COUNT(tm) > 0 FROM TeamMember tm " +
                     "WHERE tm.team.id = :teamId AND tm.user.id = :userId")
       boolean existsByTeamIdAndUserId(
                     @Param("teamId") String teamId,
                     @Param("userId") String userId);

       @Query("SELECT tm FROM TeamMember tm " +
                     "LEFT JOIN FETCH tm.team " +
                     "LEFT JOIN FETCH tm.user " +
                     "WHERE tm.team.id = :teamId")
       List<TeamMember> findByTeamId(@Param("teamId") String teamId);

       @Query("SELECT COUNT(tm) FROM TeamMember tm WHERE tm.team.id = :teamId")
       long countByTeamId(@Param("teamId") String teamId);
}
