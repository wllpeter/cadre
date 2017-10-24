package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.BaseDao;
import com.ddb.xaplan.cadre.entity.BaseEntity;
import com.ddb.xaplan.cadre.service.BaseService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 王凯斌 on 2017/4/24.
 */
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    /**
     * 更新忽略属性
     */
    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[]{
            BaseEntity.CREATE_DATE_PROPERTY_NAME,
            BaseEntity.MODIFY_DATE_PROPERTY_NAME,
            BaseEntity.VERSION_PROPERTY_NAME,
            BaseEntity.DELETED_PROPERTY_NAME};

    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public T find(Long id) {

        if (id == null) {
            return null;
        }
        return baseDao.findOne(id);
    }

    @Override
    public List<T> findAll() {

        List<T> result = new ArrayList<T>();
        baseDao.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<T> findList(Long[] ids) {

        List<T> result = new ArrayList<T>();
        if (ids == null) {
            return result;
        }
        ids = Arrays.stream(ids)
                .filter(s -> (s != null))
                .toArray(Long[]::new);
        baseDao.findAll(Arrays.asList(ids)).forEach(result::add);
        return result;
    }

    @Override
    public Set<T> findSet(Long[] ids) {

        return new HashSet<T>(findList(ids));
    }

    @Override
    public Boolean exists(Long id) {
        if (id == null) {
            return false;
        }
        return baseDao.exists(id);
    }

    @Override
    public T save(T t) {

        if (!t.isNew()) {
            throw new IllegalArgumentException("id should be empty");
        }
        return baseDao.save(t);
    }

    @Override
    public T update(T t) {

        return update(t, UPDATE_IGNORE_PROPERTIES);
    }

    @Override
    public T update(T t, String... ignore) {

//        if (!baseDao.exists(t.getId())) {
//            throw new IllegalArgumentException("update target does not exits");
//        }
        T original = baseDao.findOne(t.getId());
        if (original != null) {
            copyProperties(t, original, (String[]) ArrayUtils.addAll(ignore, UPDATE_IGNORE_PROPERTIES));
        }
        return baseDao.save(original);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(T... ts) {

        for (T t : ts) {
            delete(t);
        }
    }

    @Override
    public void delete(T t) {

        if (t == null || !baseDao.exists(t.getId())) {
            throw new IllegalArgumentException("delete target does not exits");
        }
        t.setDeleted(true);
        baseDao.save(t);
    }

    @Override
    public void delete(Long id) {

        delete(find(id));
    }

    protected void copyProperties(T source, T target,
                                  String... ignoreProperties) {

        PropertyDescriptor[] propertyDescriptors = PropertyUtils
                .getPropertyDescriptors(target);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (ArrayUtils.contains(ignoreProperties, propertyName)
                    || readMethod == null || writeMethod == null) {
                continue;
            }
            try {
                Object sourceValue = readMethod.invoke(source);
                writeMethod.invoke(target, sourceValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    @Override
    public Page<T> findPage(Pageable pageable) {

        return baseDao.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Long[] ids) {

        for (Long id : ids) {
            delete(id);
        }
    }

    @Override
    public List<T> search(String conditionName, Object conditionValue) {
        return baseDao.findAll(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(conditionName)) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get(conditionName), conditionValue));
                }
                return predicate;
            }
        });
    }
}
