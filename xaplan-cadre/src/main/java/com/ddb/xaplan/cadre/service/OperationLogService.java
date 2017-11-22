package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.OperationLogDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface OperationLogService extends BaseService<OperationLogDO>{

    Page<OperationLogDO> search(String keyword, Date startDate, Date endDate, Pageable pageable);

    List<OperationLogDO> search(Date startDate, Date endDate);

    void logger(String userInfo,String content);

}
