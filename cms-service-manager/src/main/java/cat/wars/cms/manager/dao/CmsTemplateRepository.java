package cat.wars.cms.manager.dao;

import cat.wars.cms.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/25/19
 * Time: 11:00 PM
 * Cms template repository
 */

@Repository
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String> {
}
