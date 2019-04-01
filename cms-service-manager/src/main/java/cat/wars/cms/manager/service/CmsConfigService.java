package cat.wars.cms.manager.service;

import cat.wars.cms.framework.domain.cms.response.CmsConfigResult;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 8:39 AM
 * Cms config service
 */

public interface CmsConfigService {

    CmsConfigResult findById(String id);
}
