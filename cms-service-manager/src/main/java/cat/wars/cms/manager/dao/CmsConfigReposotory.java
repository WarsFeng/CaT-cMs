package cat.wars.cms.manager.dao;

import cat.wars.cms.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 8:35 AM
 * Cms config repository
 */

@Repository
public interface CmsConfigReposotory extends MongoRepository<CmsConfig, String> {
}
