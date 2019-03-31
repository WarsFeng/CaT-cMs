package cat.wars.cms.api.cms;

import cat.wars.cms.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/24/19
 * Time: 8:17 PM
 * Cms site api
 */

@Api(value = "Cms site management interface")
public interface CmsSiteControllerApi {

    @ApiOperation(value = "Query site list of name,id")
    @ApiImplicitParam(name = "siteName", value = "Site name", paramType = "query", dataType = "string")
    QueryResponseResult findSubList(String siteName);
}
