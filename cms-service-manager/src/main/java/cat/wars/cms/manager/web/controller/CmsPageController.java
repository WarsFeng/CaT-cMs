package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsPageControllerApi;
import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.ResponseResult;
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
    public QueryResponseResult<CmsPage> findList(@PathVariable(name = "page") int page
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

    @Override
    @GetMapping("/get/{id}")
    public CmsPageResult findById(@PathVariable(name = "id") String id) {
        log.info("\nQuery page by id, id({})", id);
        return service.findById(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public CmsPageResult edit(@PathVariable(name = "id") String id, @RequestBody CmsPage page) {
        log.info("\nEdit page, id({}), page(\n\t{}\n)", id, page);
        return service.edit(id, page);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable(name = "id") String id) {
        log.info("\nDelete page, id({})", id);
        return service.delete(id);
    }

    @Override
    @PostMapping("/release/{id}")
    public ResponseResult release(@PathVariable(name = "id") String id) {
        log.info("\nRelease page, id({})", id);
        return service.release(id);
    }

    @Override
    @PostMapping("/save")
    public CmsPageResult save(@RequestBody CmsPage page) {
        log.info("\nSave page, page(\n\t{}\n)", page);
        return service.save(page);
    }

    @Override
    @PostMapping("/release_quick")
    public CmsPostPageResult releaseQuick(@RequestBody CmsPage page) {
        log.info("\nQuick release page, page(\n\t{}\t)", page);
        return service.releaseQuick(page);
    }
}
