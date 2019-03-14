package cat.wars.cms.manager.web.controller;

import cat.wars.cms.api.cms.CmsPageControllerApi;
import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsPageRequest;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable(name = "page") int page
            , @PathVariable(name = "size") int size, CmsPageRequest cmsPageRequest) {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试页面");
        List<CmsPage> cmsPageList = List.of(cmsPage);

        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(cmsPageList);

        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
