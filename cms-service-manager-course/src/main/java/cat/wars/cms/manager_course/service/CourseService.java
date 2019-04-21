package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.ResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/18/19
 * Time: 12:34 AM
 * Course service
 */

public interface CourseService {

    /**
     * Course paginate
     *
     * @param page   page num, starting from 0
     * @param size   page size
     * @param params other query param
     * @return cat.wars.cms.framework.model.response.QueryResponseResult
     */
    QueryResponseResult<CourseInfo> findList(int page, int size, CourseListRequest params);

    /**
     * Add course
     *
     * @param course {@link CourseBase}
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult add(CourseBase course);
}
