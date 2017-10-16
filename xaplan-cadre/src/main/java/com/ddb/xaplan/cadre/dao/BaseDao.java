package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * Created by 王凯斌 on 2017/10/16.
 */
public interface BaseDao<T extends BaseEntity> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

}
