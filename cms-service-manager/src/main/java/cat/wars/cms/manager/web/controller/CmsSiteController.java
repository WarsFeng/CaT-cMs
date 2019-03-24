package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsSiteControllerApi;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager.service.CmsSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/24/19
 * Time: 10:40 PM
 * Cms site controller
 */

@Slf4j
@RestController
@RequestMapping("/cms/site")
public class CmsSiteController implements CmsSiteControllerApi {

    private final CmsSiteService service;

    @Autowired
    public CmsSiteController(CmsSiteService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/sublist")
    public QueryResponseResult findSubList(String siteName) {
        return service.findSubList(siteName);
    }
}
