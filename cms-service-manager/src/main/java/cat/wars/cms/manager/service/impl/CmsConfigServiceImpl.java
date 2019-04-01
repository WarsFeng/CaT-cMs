package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsConfig;
import cat.wars.cms.framework.domain.cms.response.CmsCode;
import cat.wars.cms.framework.domain.cms.response.CmsConfigResult;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.manager.dao.CmsConfigReposotory;
import cat.wars.cms.manager.service.CmsConfigService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 8:43 AM
 * Cms config service basically implements
 */

@Service
public class CmsConfigServiceImpl implements CmsConfigService {

    private final CmsConfigReposotory reposotory;

    public CmsConfigServiceImpl(CmsConfigReposotory reposotory) {
        this.reposotory = reposotory;
    }

    @Override
    public CmsConfigResult findById(String id) {
        if (isEmpty(id)) ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);

        Optional<CmsConfig> configOptional = reposotory.findById(id);
        if (configOptional.isEmpty()) ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);

        return new CmsConfigResult(CommonCode.SUCCESS, configOptional.get());
    }
}
