package cat.wars.cms.manager.dao;

import cat.wars.cms.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/24/19
 * Time: 10:51 PM
 * Cms site repository
 */

public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
