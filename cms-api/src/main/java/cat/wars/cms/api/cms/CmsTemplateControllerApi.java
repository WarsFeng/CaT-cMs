package cat.wars.cms.api.cms;

import cat.wars.cms.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/26/19
 * Time: 12:01 AM
 * Cms template api
 */

@Api(value = "Cms template management interface")
public interface CmsTemplateControllerApi {

    @ApiOperation(value = "Query template list of name,id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "siteId", value = "Site id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "query", value = "Template name", paramType = "query", dataType = "String")
    })
    QueryResponseResult findSubList(String siteId, String query);
}
