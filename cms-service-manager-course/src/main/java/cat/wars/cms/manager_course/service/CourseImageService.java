package cat.wars.cms.manager_course.service;

import cat.wars.cms.framework.domain.course.CoursePic;
import cat.wars.cms.framework.domain.course.response.CourseCoverResponse;
import cat.wars.cms.framework.model.response.ResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/5/19
 * Time: 2:35 AM
 * Course image service
 */

public interface CourseImageService {


    /**
     * Save course cover image
     *
     * @param fileId   fs file id
     * @param courseId course id
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult saveCourseCover(String courseId, String fileId);

    /**
     * Get cover by course id, If return, always not equals null
     *
     * @param courseId course id
     * @return FileSystemImage
     */
    CoursePic getCoverByCourseId(String courseId);

    /**
     * Find cover by course id
     *
     * @param courseId course id
     * @return cat.wars.cms.framework.domain.filesystem.response.ImageResult
     */
    CourseCoverResponse findCoverByCourseId(String courseId);

    /**
     * Delete cover by course id
     *
     * @param courseId course id
     * @return cat.wars.cms.framework.model.response.ResponseResult
     */
    ResponseResult deleteCoverByCourseId(String courseId);
}
