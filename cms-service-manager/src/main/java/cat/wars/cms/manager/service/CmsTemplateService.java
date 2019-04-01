package cat.wars.cms.manager.service;

import cat.wars.cms.framework.domain.cms.CmsTemplate;
import cat.wars.cms.framework.model.response.QueryResponseResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/25/19
 * Time: 11:04 PM
 * Cms template service
 */

public interface CmsTemplateService {

    /**
     * Find template list of id,name
     * limit 10
     *
     * @param siteId site id
     * @param query  template name
     * @return cat.wars.cms.framework.model.response.QueryResponseResult
     */
    QueryResponseResult findSubList(String siteId, String query);

    /**
     * Get template by id, if return, always not null
     *
     * @param id template id
     * @return cat.wars.cms.framework.domain.cms.CmsTemplate
     */
    CmsTemplate getById(String id);

    /**
     * Get template file to string
     *
     * @param id template id
     * @return template file string
     */
    String getTemplateStr(String id);
}
