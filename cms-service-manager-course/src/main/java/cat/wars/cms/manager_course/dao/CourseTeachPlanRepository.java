package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.TeachPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/9/19
 * Time: 12:34 AM
 * Teach plan dao by jpa
 */

@Repository
public interface CourseTeachPlanRepository extends JpaRepository<TeachPlan, String> {

    boolean existsByCourseid(String courseid);

    Optional<TeachPlan> findByParentidAndCourseid(String parentId, String courseId);

    @Query(nativeQuery = true, value = "SELECT orderby FROM teach_plan WHERE parentid=?1 ORDER BY orderby DESC LIMIT 1")
    Optional<Integer> findOrderbyByParentId(String parentId);

    @Query(nativeQuery = true, value = "SELECT courseid FROM teach_plan WHERE id=?1")
    Optional<String> findCourseidById(String id);
}
