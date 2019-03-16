package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsPageControllerApi;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 2:59 PM
 * Cms page controller
 */

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    private final CmsPageService service;

    @Autowired
    public CmsPageController(CmsPageService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable(name = "page") int page
            , @PathVariable(name = "size") int size, CmsQueryPageRequest request) {
        return service.findList(page, size, request);
    }
}
