package com.spring.jpa.entitygraph.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "user-with-phone-named-query", query = "SELECT DISTINCT u " +
                "FROM User as u " +
                "LEFT JOIN FETCH u.phones " +
                "WHERE u.id = :id"),
        @NamedQuery(name = "user-with-phone-and-call-named-query", query = "SELECT DISTINCT u " +
                "FROM User as u " +
                "LEFT JOIN FETCH u.phones as p " +
                "LEFT JOIN FETCH p.calls " +
                "WHERE u.id = :id"),
        @NamedQuery(name = "user-with-phone-named-query-all", query = "SELECT DISTINCT u " +
                "FROM User as u " +
                "LEFT JOIN FETCH u.phones"),
        @NamedQuery(name = "user-with-phone-and-call-named-query-all", query = "SELECT DISTINCT u " +
                "FROM User as u " +
                "LEFT JOIN FETCH u.phones as p " +
                "LEFT JOIN FETCH p.calls")
})
@NamedEntityGraphs({
        @NamedEntityGraph(
            name = "user-with-phone",
            attributeNodes = {
                    @NamedAttributeNode("phones")
            }
        ),
        @NamedEntityGraph(
                name = "user-with-phone-and-call",
                attributeNodes = {
                        @NamedAttributeNode(value = "phones", subgraph = "call-details")
                },
                subgraphs = {@NamedSubgraph(
                        name = "call-details",
                        attributeNodes = {
                                @NamedAttributeNode("calls")
                        }
                )}
        )
})
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> addresses;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Phone> phones;
}
