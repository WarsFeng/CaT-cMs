package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.course.response.SysDictionaryResponse;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 11:34 AM
 * Course sys dictionary service
 */

public interface CourseSysDictionaryService {

    /**
     * Get dictionary by type
     *
     * @param type dictionary type
     * @return cat.wars.cms.framework.domain.course.response.SysDictionaryResponse
     */
    SysDictionaryResponse getByType(String type);
}
