package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsConfigControllerApi;
import cat.wars.cms.framework.domain.cms.response.CmsConfigResult;
import cat.wars.cms.manager.service.CmsConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 8:53 AM
 * Cms config controller
 */

@Slf4j
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {

    private final CmsConfigService service;

    public CmsConfigController(CmsConfigService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/getmodel/{id}")
    public CmsConfigResult getModel(@PathVariable(name = "id") String id) {
        log.info("\nQuery model by id, id({})", id);
        return service.findById(id);
    }
}
