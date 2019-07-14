package cat.wars.cms.api.cms;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 11:32 AM
 * Cms page api
 */

@Api(value = "Cms page management interface")
public interface CmsPageControllerApi {

    @ApiOperation("Paging query page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page num", required = true, paramType = "path", dataType = "int")
            , @ApiImplicitParam(name = "size", value = "page size", required = true, paramType = "path", dataType = "int")
    })
    QueryResponseResult<CmsPage> findList(int page, int size, CmsQueryPageRequest params);

    @ApiOperation("Add page")
    CmsPageResult add(CmsPage page);

    @ApiOperation("Query page by id")
    @ApiImplicitParam(name = "id", value = "page id", required = true, paramType = "path", dataType = "string")
    CmsPageResult findById(String id);

    @ApiOperation("Update page by id")
    @ApiImplicitParam(name = "id", value = "page id", required = true, paramType = "path", dataType = "string")
    CmsPageResult edit(String id, CmsPage page);

    @ApiOperation("Delete page by id")
    @ApiImplicitParam(name = "id", value = "page id", required = true, paramType = "path", dataType = "string")
    ResponseResult delete(String id);

    @ApiOperation("Release page by id")
    @ApiImplicitParam(name = "id", value = "page id", required = true, paramType = "path", dataType = "string")
    ResponseResult release(String id);

    @ApiOperation("Save page, add or update")
    CmsPageResult save(CmsPage page);

    @ApiOperation("Quick release page")
    CmsPostPageResult releaseQuick(CmsPage cmsPage);
}
