package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Phone;

import java.util.List;

public interface EmployeeCustomRepository {
    void testSaveMultipleEntities(MultipleEntity multipleEntities);

    /***
     * Returns multiple entities as response. if there is more than one records in each entity, will return
     * more records [ no of record one entity * no of record one entity for two entity response]
     * @return List<MultipleEntity>
     */
    List<MultipleEntity> all();

    /***
     * Returns multiple entities which are fulfilled predicate (conditions) [multiple roots with some conditions]
     * @return List<MultipleEntity>
     */
    List<MultipleEntity> selectEntityFulfillingConditions();

    List<Phone> joinQuery();

    /***
     * Eager fetching record when they mapping with OneToMany or ManyToOne
     * @return
     */
    List<Phone> eagerFetchInQuery();
}
