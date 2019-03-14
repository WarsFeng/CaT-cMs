package cat.wars.cms.manager.dao;

import cat.wars.cms.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 4:55 PM
 * Cms page repository
 */

public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
}
