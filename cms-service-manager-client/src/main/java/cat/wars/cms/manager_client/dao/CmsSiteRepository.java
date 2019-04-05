package cat.wars.cms.manager_client.dao;

import cat.wars.cms.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 5:30 PM
 * Cms site mongo repository
 */

@Repository
public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
