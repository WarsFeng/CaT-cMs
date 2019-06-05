package cat.wars.cms.framework.domain.filesystem.response;

import cat.wars.cms.framework.domain.filesystem.FileSystemImage;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UploadImageResult extends ResponseResult {

    @ApiModelProperty(value = "文件信息", example = "true", required = true)
    FileSystemImage image;

    public UploadImageResult(ResultCode resultCode, FileSystemImage image) {
        super(resultCode);
        this.image = image;
    }
}
