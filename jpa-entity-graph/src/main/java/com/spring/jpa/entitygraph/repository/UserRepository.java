package com.spring.jpa.entitygraph.repository;

import com.spring.jpa.entitygraph.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, BaseRepository<User, Long> {
    @EntityGraph(type= EntityGraph.EntityGraphType.FETCH, value="user-with-phone-and-call")
    @Override
    Optional<User> findById(Long id);

    @EntityGraph(type= EntityGraph.EntityGraphType.FETCH, value="user-with-phone-and-call")
    @Override
    List<User> findAll();
}
