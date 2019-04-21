package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/18/19
 * Time: 8:42 PM
 * Course mapper
 */

@Mapper
public interface CourseMapper {

    /**
     * Paginate query course and picture
     *
     * @param params {@link CourseListRequest}
     * @return com.github.pagehelper.Page
     */
    Page<CourseInfo> findCoursePage(CourseListRequest params);
}
