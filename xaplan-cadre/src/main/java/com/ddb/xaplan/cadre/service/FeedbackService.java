package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.FeedbackDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface FeedbackService extends BaseService<FeedbackDO>{

    Page<FeedbackDO> search(String keyword, Date startDate, Date endDate, Pageable pageable);
}
