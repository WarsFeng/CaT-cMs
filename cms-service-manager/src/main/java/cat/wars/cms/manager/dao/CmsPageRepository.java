package cat.wars.cms.manager.dao;

import cat.wars.cms.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 4:55 PM
 * Cms page repository
 */

@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    /**
     * Check if the page exists
     *
     * @param siteId      Id of [site]
     * @param pageWebPath Web access path
     * @param pageName    Web access name, path+name=uri
     * @return java.util.Optional
     */
    Optional<CmsPage> findBySiteIdAndPageWebPathAndPageName(String siteId, String pageWebPath, String pageName);
}
