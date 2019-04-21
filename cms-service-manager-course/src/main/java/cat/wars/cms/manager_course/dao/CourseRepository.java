package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.CourseBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/9/19
 * Time: 9:56 AM
 * Course dao by jpa
 */

@Repository
public interface CourseRepository extends JpaRepository<CourseBase, String> {
}
