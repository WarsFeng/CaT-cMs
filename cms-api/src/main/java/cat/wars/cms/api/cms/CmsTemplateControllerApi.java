package cat.wars.cms.api.cms;

import cat.wars.cms.framework.domain.cms.CmsTemplate;
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
            @ApiImplicitParam(name = "siteId", value = "Site id", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "query", value = "Template name", paramType = "query", dataType = "string")
    })
    QueryResponseResult<CmsTemplate> findSubList(String siteId, String query);
}
