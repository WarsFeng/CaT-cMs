package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.CoursePic;
import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.domain.course.response.CourseCoverResponse;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.dao.CourseImageRepository;
import cat.wars.cms.manager_course.service.CourseImageService;
import cat.wars.cms.manager_course.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/5/19
 * Time: 2:36 AM
 * Course image service basic implementation
 */

@Service
public class CourseImageServiceImpl implements CourseImageService {

    private final CourseService courseService;
    private final CourseImageRepository repository;

    public CourseImageServiceImpl(CourseService courseService, CourseImageRepository repository) {
        this.courseService = courseService;
        this.repository = repository;
    }

    @Override
    public ResponseResult saveCourseCover(String courseId, String fileId) {
        // Check course
        courseService.getById(courseId);
        // Save
        CoursePic image = new CoursePic();
        image.setCourseid(courseId);
        image.setPic(fileId);
        repository.save(image);
        return ResponseResult.SUCCESS();
    }

    @Override
    public CoursePic getCoverByCourseId(String courseId) {
        // Check course
        courseService.getById(courseId);
        // Get
        Optional<CoursePic> imageOptional;
        if ((imageOptional = repository.findById(courseId)).isEmpty())
            ExceptionCast.cast(CourseCode.COURSE_COVER_NOT_EXISTS);
        return imageOptional.get();
    }

    @Override
    public CourseCoverResponse findCoverByCourseId(String courseId) {
        // Check course
        courseService.getById(courseId);
        // Get
        Optional<CoursePic> imageOptional;
        if ((imageOptional = repository.findById(courseId)).isEmpty())
            return new CourseCoverResponse(CourseCode.COURSE_COVER_NOT_EXISTS, null);
        // Result
        return new CourseCoverResponse(imageOptional.get());
    }

    @Override
    public ResponseResult deleteCoverByCourseId(String courseId) {
        // Check course
        getCoverByCourseId(courseId);
        // Delete
        repository.deleteById(courseId);
        // Result
        return ResponseResult.SUCCESS();
    }
}
