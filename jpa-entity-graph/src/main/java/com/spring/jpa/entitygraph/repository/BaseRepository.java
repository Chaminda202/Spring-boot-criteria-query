package com.spring.jpa.entitygraph.repository;

import com.spring.jpa.entitygraph.enumeration.UserAttributeNode;

import java.util.List;

public interface BaseRepository<T, ID> {
    T findByIdAndGraph(ID id, String graph);
    List<T> findAllByGraph(String graph);
    T findByIdAndNamedQuery(ID id, String namedQuery);
    List<T> findAllByNamedQuery(String namedQuery);
    T findByIdWithCriteriaAPI(ID id, UserAttributeNode attributeNode); // dynamically create entity graph
    List<T> findAllWithCriteriaAPI(UserAttributeNode attributeNode); // dynamically create entity graph
}
