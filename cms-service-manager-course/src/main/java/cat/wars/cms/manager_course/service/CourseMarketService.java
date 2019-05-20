package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.domain.course.response.CourseMarketResponse;
import cat.wars.cms.framework.model.response.ResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/20/19
 * Time: 11:53 AM
 * Course market service interface
 */

public interface CourseMarketService {

    /**
     * Get course market by id, if return, always not null
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.course.response.CourseMarketResponse
     */
    CourseMarket getById(String id);

    /**
     * Find course market by id, if return, always not null
     *
     * @param id course id
     * @return cat.wars.cms.framework.domain.course.response.CourseResponse
     */
    CourseMarketResponse findById(String id);

    /**
     * Add or update course market
     *
     * @param id     course id
     * @param market {@link CourseMarket}
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult save(String id, CourseMarket market);
}
