package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/5/19
 * Time: 2:47 AM
 * Course image repository by jpa
 */

@Repository
public interface CourseImageRepository extends JpaRepository<CoursePic, String> {
}
