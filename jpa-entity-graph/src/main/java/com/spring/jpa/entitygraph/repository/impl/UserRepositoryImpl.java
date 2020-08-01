package com.spring.jpa.entitygraph.repository.impl;

import com.spring.jpa.entitygraph.enumeration.UserAttributeNode;
import com.spring.jpa.entitygraph.model.entity.User;
import com.spring.jpa.entitygraph.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spring.jpa.entitygraph.enumeration.UserAttributeNode.USER_WITH_PHONE_AND_CALL_NODE;

@Repository
public class UserRepositoryImpl implements BaseRepository<User, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByIdAndGraph(Long id, String graph) {
        EntityGraph<?> entityGraph = this.entityManager.createEntityGraph(graph);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph); // fetching all the entity graph attribute nodes eagerly. Other mapped entities will be lazily fetched
        return this.entityManager.find(User.class, id, properties);
    }

    @Override
    public List<User> findAllByGraph(String graph) {
        TypedQuery<User> query = this.entityManager.createQuery("FROM User", User.class);
        EntityGraph<?> entityGraph = this.entityManager.createEntityGraph(graph);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public User findByIdAndNamedQuery(Long id, String namedQuery) {
        TypedQuery<User> query = this.entityManager.createNamedQuery(namedQuery, User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAllByNamedQuery(String namedQuery) {
        TypedQuery<User> query = this.entityManager.createNamedQuery(namedQuery, User.class);
        return query.getResultList();
    }

    @Override
    public User findByIdWithCriteriaAPI(Long id, UserAttributeNode attributeNode) {
        EntityGraph<User> entityGraph = this.entityManager.createEntityGraph(User.class);

        if(USER_WITH_PHONE_AND_CALL_NODE == attributeNode)
            entityGraph.addSubgraph("phones").addAttributeNodes("calls");
        else
            entityGraph.addAttributeNodes("phones");

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id").as(Long.class), id));
        TypedQuery<User> typedQuery = this.entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph); // fetching all the entity graph attribute nodes eagerly. Other mapped entities are treated based on the default/specified fetch type
        return typedQuery.getSingleResult();
    }

    @Override
    public List<User> findAllWithCriteriaAPI(UserAttributeNode attributeNode) {
        EntityGraph<User> entityGraph = this.entityManager.createEntityGraph(User.class);

        if(USER_WITH_PHONE_AND_CALL_NODE == attributeNode)
            entityGraph.addSubgraph("phones").addAttributeNodes("calls");
        else
            entityGraph.addAttributeNodes("phones");

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        TypedQuery<User> typedQuery = this.entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
        return typedQuery.getResultList();
    }
}
