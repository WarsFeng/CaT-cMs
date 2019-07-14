package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.domain.cms.response.CoursePreviewResult;
import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.domain.course.response.CourseResponse;
import cat.wars.cms.framework.domain.course.response.CourseViewResult;
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

    /**
     * Get course by id, if return, always not null
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.course.CourseBase
     */
    CourseBase getById(String id);

    /**
     * Find course by id
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.course.response.CourseResponse
     */
    CourseResponse findById(String id);

    /**
     * Update course
     *
     * @param course {@link CourseBase}
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult edit(CourseBase course);

    /**
     * Get course detail view data
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.course.ext.CourseView
     */
    CourseViewResult view(String id);

    /**
     * Preview course detail page
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.cms.response.CoursePreviewResult
     */
    CoursePreviewResult preview(String id);

    /**
     * Release course(Full) = releasePage + releaseState
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.cms.response.CmsPostPageResult
     */
    CmsPostPageResult release(String id);

    /**
     * Release course detail page
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.cms.response.CmsPostPageResult
     */
    CmsPostPageResult releasePage(String id);

    /**
     * Release course state
     *
     * @param id course id
     * @throws cat.wars.cms.framework.exception.CustomException code-11111
     */
    void releaseState(String id);
}
