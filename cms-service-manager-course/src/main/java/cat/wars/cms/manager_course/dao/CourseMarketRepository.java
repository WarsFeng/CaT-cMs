package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.CourseMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/20/19
 * Time: 12:07 PM
 * Course market repository
 */

@Repository
public interface CourseMarketRepository extends JpaRepository<CourseMarket, String> {
}
