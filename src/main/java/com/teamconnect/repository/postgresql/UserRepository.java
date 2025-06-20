package com.teamconnect.repository.postgresql;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.teamconnect.model.sql.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>, JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, String id);

    List<User> findAllByIdIn(Set<String> userIds);
}
