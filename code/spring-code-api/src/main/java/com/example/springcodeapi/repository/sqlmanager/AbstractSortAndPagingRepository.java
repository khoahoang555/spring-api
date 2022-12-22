package com.example.springcodeapi.repository.sqlmanager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;

public abstract class AbstractSortAndPagingRepository {

//    protected ExtSqlManagerImpl sqlManagerImpl;
//
//    /**
//     * Select result List and do paging
//     *
//     * @param sqlResource
//     * @param condition
//     * @param pageable
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public <T> Page<T> selectListPagingByCondition(ClasspathSqlResource sqlResource,
//                                                   AbstractSortAndPagingCondition condition,
//                                                   Pageable pageable,
//                                                   Class<T> clazz) {
//        final Integer count;
//        if (Objects.isNull(pageable) || pageable.isUnpaged()) {
//            count = 0;
//        } else {
//            condition.setUnpaged(true);
//            count = sqlManagerImpl.getCount(sqlResource, condition);
//            condition.setUnpaged(false);
//        }
//        List<T> resultList = sqlManagerImpl.getResultList(clazz, sqlResource, condition);
//        return PageableExecutionUtils.getPage(resultList, pageable, () -> count.longValue());
//    }
}
