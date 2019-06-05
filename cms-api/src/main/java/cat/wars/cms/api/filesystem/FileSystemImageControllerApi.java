package cat.wars.cms.api.filesystem;

import cat.wars.cms.framework.domain.filesystem.response.UploadImageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/1/19
 * Time: 9:52 PM
 * FS image api
 */

@Api("Filesystem image api")
public interface FileSystemImageControllerApi {

    @ApiOperation("Upload image to FastDFS")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileTag", value = "caller tag", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "businessKey", value = "caller business key", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "metadata", value = "image metadata", required = false, paramType = "query", dataType = "string")
    })
    UploadImageResult upload(MultipartFile multipartFile, String fileTag, String businessKey, String metadata);
}
