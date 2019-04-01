package cat.wars.cms.api.cms;

import cat.wars.cms.framework.domain.cms.response.CmsConfigResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 8:49 AM
 * Cms config controller api
 */

@Api("Cms configuration management api")
public interface CmsConfigControllerApi {

    @ApiOperation("Get configuration information by id")
    @ApiImplicitParam(name = "id", value = "config id", required = true, paramType = "path", dataType = "string")
    CmsConfigResult getModel(String id);
}
