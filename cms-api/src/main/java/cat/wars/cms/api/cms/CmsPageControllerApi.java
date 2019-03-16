package cat.wars.cms.api.cms;

import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.model.response.QueryResponseResult;
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
    QueryResponseResult findList(int page, int size, CmsQueryPageRequest cmsQueryPageRequest);
}
