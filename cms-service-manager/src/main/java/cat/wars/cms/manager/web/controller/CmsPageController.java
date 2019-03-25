package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsPageControllerApi;
import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager.service.CmsPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 2:59 PM
 * Cms page controller
 */

@Slf4j
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
            , @PathVariable(name = "size") int size, CmsQueryPageRequest params) {
        log.info("\nQuery page list, page({}), size({}), params(\n\t{}\n)", page, size, params);
        return service.findList(page, size, params);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage page) {
        log.info("\nAdd page, page(\n\t{}\n)", page);
        return service.add(page);
    }
}
