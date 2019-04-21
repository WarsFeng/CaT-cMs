package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsTemplateControllerApi;
import cat.wars.cms.framework.domain.cms.CmsTemplate;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager.service.CmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/26/19
 * Time: 12:08 AM
 * Cms template controller
 */

@Slf4j
@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

    private final CmsTemplateService service;

    @Autowired
    public CmsTemplateController(CmsTemplateService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/sublist")
    public QueryResponseResult<CmsTemplate> findSubList(String siteId, String query) {
        log.info("\nQuery subTemplate list, siteId({}), query({})", siteId, query);
        return service.findSubList(siteId, query);
    }
}
