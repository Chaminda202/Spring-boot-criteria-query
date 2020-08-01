package com.spring.jpa.entitygraph.repository;

import com.spring.jpa.entitygraph.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, BaseRepository<User, Long> {
}
