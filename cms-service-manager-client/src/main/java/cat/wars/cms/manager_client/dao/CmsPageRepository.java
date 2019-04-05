package cat.wars.cms.manager_client.dao;

import cat.wars.cms.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 5:01 PM
 * Cms page mongodb repository
 */

@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
}
